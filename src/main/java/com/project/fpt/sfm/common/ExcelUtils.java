package com.project.fpt.sfm.common;

import com.project.fpt.sfm.processexcel.utils.ExcelColumn;
import com.project.fpt.sfm.processexcel.utils.ExcelReport;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Khắc Vỹ on 10/5/2015.
 */
public class ExcelUtils {

    /**
     * Create Workbook from data
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Workbook writeExcelFile(List<T> data){
        Class<? extends Object> classz = data.get(0).getClass();

        ExcelReport excelReport = (ExcelReport)classz.getAnnotation(ExcelReport.class);
        String sheetName = excelReport.sheetName();

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet(sheetName);

        List<String> fields = getFiledNames(classz);
        Map<String, String> mapFieldMap = getFieldLabelMap(classz);
        // Create a row and put some cells in it. Rows are 0 based.
        int rowCount = 0;
        int columnCount = 0;

        Row row = sheet.createRow(rowCount++);
        for (String fieldName : fields) {
            Cell cell = row.createCell(columnCount++);
            cell.setCellValue(fieldName.toUpperCase());
            cell.setCellStyle(createColumnHeaderCellStyle(workbook));
        }

        for (T t : data) {
            row = sheet.createRow(rowCount++);

            columnCount = 0;

            for (String label : fields) {
                String methodName = mapFieldMap.get(label);
                Cell cel = row.createCell(columnCount);
                Method method = null;
                try {
                    method = classz.getMethod(methodName);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
                Object value = null;
                try {
                    value = method.invoke(t, (Object[]) null);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
                if (value != null) {
                    if (value instanceof String) {
                        cel.setCellValue((String) value);
                    } else if (value instanceof Long) {
                        cel.setCellValue((Long) value);
                    } else if (value instanceof Integer) {
                        cel.setCellValue((Integer)value);
                    }else if (value instanceof Double) {
                        cel.setCellValue((Double) value);
                    }
                }
                columnCount++;
            }
        }

        return workbook;
    }

    /**
     * Get list fields name of a class
     *
     * @param clazz
     * @return
     */
    public static List<String> getFiledNames(Class clazz) {
        List<String> list = new ArrayList<>();
        for(Method method : clazz.getMethods()){
            ExcelColumn excelColumn = method.getAnnotation(ExcelColumn.class);
            if((excelColumn != null) && !excelColumn.ignore()){
                list.add(excelColumn.label());
            }
        }

        return list;
    }

    public static Map<String, String> getFieldLabelMap(Class clazz){
        Map<String,String> fieldLabelMap = new HashMap<String,String>();
        for(Method method : clazz.getMethods()){
            ExcelColumn excelColumn = method.getAnnotation(ExcelColumn.class);
            if((excelColumn != null) && !excelColumn.ignore()){
                fieldLabelMap.put(excelColumn.label(), method.getName());
            }
        }

        return fieldLabelMap;
    }

    /**
     * Cell Style : bold text and center
     *
     * @param workbook
     * @return
     */
    public static CellStyle createColumnHeaderCellStyle(HSSFWorkbook workbook) {
        CellStyle cellStyle = workbook.createCellStyle();
        HSSFFont font = workbook.createFont();
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        cellStyle.setFont(font);
        cellStyle.setAlignment(CellStyle.ALIGN_CENTER);

        return cellStyle;
    }
}
