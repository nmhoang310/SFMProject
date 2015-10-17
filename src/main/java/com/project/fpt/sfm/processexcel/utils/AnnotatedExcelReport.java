package com.project.fpt.sfm.processexcel.utils;

import java.io.*;
import java.lang.reflect.Method;
import java.util.*;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * Created by Khắc Vỹ on 10/9/2015.
 */
public class AnnotatedExcelReport {
    private Workbook workbook = null;
    private String workbookName = "workbook.xls";
    private Map<String, String> fieldLabelMap = new HashMap<String, String>();
    private Map<Integer, String> orderLabelsMap = new HashMap<>();
    private List<String> orderLabels = new ArrayList<String>();
    private CellStyle columnHeaderCellStyle = null;
    private String className;

    /**
     * InputStream
     */
    private ByteArrayInputStream inputStream;

    public AnnotatedExcelReport() {
        initialize();
    }

    public AnnotatedExcelReport(ByteArrayInputStream inputStream) {
        this.inputStream = inputStream;
    }

    private void initialize() {
        setWorkbook(new HSSFWorkbook());
        setColumnHeaderCellStyle(createColumnHeaderCellStyle());
    }

    private CellStyle createColumnHeaderCellStyle() {
        CellStyle cellStyle = getWorkbook().createCellStyle();
        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        cellStyle.setFillBackgroundColor(new HSSFColor.GREY_25_PERCENT().getIndex());
        cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        return cellStyle;
    }

    public void closeWorksheet() {
        // Write the output to a file
        FileOutputStream fileOut;
        try {
            fileOut = new FileOutputStream(getWorkbookName());
            getWorkbook().write(fileOut);
            fileOut.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Sheet getSheetWithName(String name) {
        Sheet sheet = null;
        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
            if (name.compareTo(workbook.getSheetName(i)) == 0) {
                sheet = workbook.getSheetAt(i);
                break;
            }
        }
        return sheet;
    }

    private void initializeForRead() throws InvalidFormatException, IOException {
        System.out.println("Open file");
        InputStream inp = new FileInputStream(getWorkbookName());
        System.out.println("create wb");
        workbook = WorkbookFactory.create(inp);
    }

    private void readFromStream() throws InvalidFormatException, IOException {
        System.out.println("Open file");
        workbook = WorkbookFactory.create(inputStream);
    }


    private <T> void processAnnotations(T object) {
        Class<?> clazz = object.getClass();
        ExcelReport reportAnnotation = (ExcelReport) clazz.getAnnotation(ExcelReport.class);
        System.out.println("Report Name  : " + reportAnnotation.documentName());
        String reportName = reportAnnotation.documentName();
        if ((reportName == null) || (reportName.trim().length() < 1)) {
            // should never get here.
            System.out.println("Invalid Worksheet Name");
        }
        setClassName(reportAnnotation.className());
        setWorkbookName(reportAnnotation.documentName());
        for (Method method : clazz.getMethods()) {
            ExcelColumn excelColumn = method.getAnnotation(ExcelColumn.class);
            if ((excelColumn != null) && !excelColumn.ignore()) {
                getFieldLabelMap().put(excelColumn.label(), method.getName());
                getOrderLabels().add(excelColumn.label());

                orderLabelsMap.put(excelColumn.order(), excelColumn.label());

                System.out.println("Annotation on method: " + method.getName());
                System.out.println("Ignore: " + excelColumn.ignore() + "  label: "
                        + excelColumn.label());
            }
        }
    }


    @SuppressWarnings("unchecked")
    public <T> List<T> readData(String classname) throws Exception {
        Class clazz = Class.forName(classname);
        processAnnotations(clazz.newInstance());
        //initializeForRead();
        readFromStream();
        //Sheet sheet = getSheetWithName(classname);
        Sheet sheet = getWorkbook().getSheetAt(0);
        List<T> result = new ArrayList<T>();
        Row row;

        for (int rowCount = 1; rowCount < sheet.getPhysicalNumberOfRows(); rowCount++) {
            T one = (T) clazz.newInstance();
            row = sheet.getRow(rowCount);
            int colCount = 0;
            result.add(one);
            for (Cell cell : row) {
                if (colCount >= getOrderLabels().size()) {
                    break;
                }
                int type = cell.getCellType();
//                String labelName = getOrderLabels().get(colCount++);
                String labelName = orderLabelsMap.get(cell.getColumnIndex());
                String getter = getFieldLabelMap().get(labelName);
                String fieldName = getter.substring(3);
                fieldName = decapitalize(fieldName);
                System.out.println("Getting field: " + fieldName);
                Method method = constructMethod(clazz, fieldName);

                if (type == 1) {
                    String value = cell.getStringCellValue();

                    Class<?> returnType = getGetterReturnClass(clazz, fieldName);
                    if (returnType == Integer.class) {
                        method.invoke(one, 0);
                    } else if (returnType == Double.class) {
                        method.invoke(one, 0d);
                    } else if (returnType == Float.class) {
                        method.invoke(one, 0f);
                    }else{
                        Object[] values = new Object[1];
                        values[0] = value;
                        method.invoke(one, values);
                    }
                } else if (type == 0) {
                    Double num = cell.getNumericCellValue();
                    Class<?> returnType = getGetterReturnClass(clazz, fieldName);
                    if (returnType == Integer.class) {
                        method.invoke(one, num.intValue());
                    } else if (returnType == Double.class) {
                        method.invoke(one, num);
                    } else if (returnType == Float.class) {
                        method.invoke(one, num.floatValue());
                    }else if(returnType == String.class){
                        method.invoke(one, num.intValue() + "");
                    }

                } else if (type == 3) {
                    Class<?> returnType = getGetterReturnClass(clazz, fieldName);
                    if (returnType == Integer.class) {
                        method.invoke(one, 0);
                    } else if (returnType == Double.class) {
                        method.invoke(one, 0d);
                    } else if (returnType == Float.class) {
                        method.invoke(one, 0f);
                    }else if(returnType == String.class){
                        method.invoke(one, "-");
                    }
                    /*double num = cell.getNumericCellValue();
                    Object[] values = new Object[1];
                    values[0] = num;
                    method.invoke(one, values);*/
                }
            }
        }

        System.out.println("The result set contains: " + result.size()
                + " items.");
        return result;
    }

    private Class<?> getGetterReturnClass(Class<?> clazz, String fieldName) {
        String methodName = "get" + capitalize(fieldName);
        Class<?> returnType = null;
        for (Method method : clazz.getMethods()) {
            if (method.getName().equals(methodName)) {
                returnType = method.getReturnType();
                break;
            }
        }
        return returnType;
    }

    @SuppressWarnings("unchecked")
    private Method constructMethod(Class clazz, String fieldName) throws SecurityException, NoSuchMethodException {
        Class<?> fieldClass = getGetterReturnClass(clazz, fieldName);
        return clazz.getMethod("set" + capitalize(fieldName), fieldClass);
    }

    public <T> void writeReportToExcel(List<T> data) throws Exception {
        processAnnotations(data.get(0));
        Sheet sheet = getWorkbook().createSheet(
                data.get(0).getClass().getName());
        int rowCount = 0;
        int columnCount = 0;

        Row row = sheet.createRow(rowCount++);
        for (String labelName : getOrderLabels()) {
            Cell cel = row.createCell(columnCount++);
            cel.setCellValue(labelName);
            cel.setCellStyle(getColumnHeaderCellStyle());
        }
        Class<? extends Object> classz = data.get(0).getClass();
        for (T t : data) {
            row = sheet.createRow(rowCount++);

            columnCount = 0;

            for (String label : getOrderLabels()) {
                String methodName = getFieldLabelMap().get(label);
                Cell cel = row.createCell(columnCount);
                Method method = classz.getMethod(methodName);
                Object value = method.invoke(t, (Object[]) null);
                if (value != null) {
                    if (value instanceof String) {
                        cel.setCellValue((String) value);
                    } else if (value instanceof Long) {
                        cel.setCellValue((Long) value);
                    } else if (value instanceof Integer) {
                        cel.setCellValue((Integer) value);
                    } else if (value instanceof Double) {
                        cel.setCellValue((Double) value);
                    }
                }
                columnCount++;
            }
        }
    }

    public Map<String, String> getFieldLabelMap() {
        return fieldLabelMap;
    }

    public void setFieldLabelMap(Map<String, String> fieldLabelMap) {
        this.fieldLabelMap = fieldLabelMap;
    }

    public List<String> getOrderLabels() {
        return orderLabels;
    }

    public void setOrderLabels(List<String> orderLabels) {
        this.orderLabels = orderLabels;
    }

    public String capitalize(String string) {
        String capital = string.substring(0, 1).toUpperCase();
        return capital + string.substring(1);
    }

    public String decapitalize(String string) {
        String capital = string.substring(0, 1).toLowerCase();
        return capital + string.substring(1);
    }

    public String getWorkbookName() {
        return workbookName;
    }

    public void setWorkbookName(String workbookName) {
        this.workbookName = workbookName;
    }

    void setWorkbook(Workbook workbook) {
        this.workbook = workbook;
    }

    Workbook getWorkbook() {
        return workbook;
    }

    public CellStyle getColumnHeaderCellStyle() {
        return columnHeaderCellStyle;
    }

    public void setColumnHeaderCellStyle(CellStyle columnHeaderCellStyle) {
        this.columnHeaderCellStyle = columnHeaderCellStyle;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
