package com.project.fpt.sfm.processexcel.development;

import com.project.fpt.sfm.processexcel.development.model.CourseResultModel;
import com.project.fpt.sfm.processexcel.development.model.StudentModel;
import com.project.fpt.sfm.processexcel.development.model.TuitionPaymentModel;
import com.project.fpt.sfm.processexcel.utils.ExcelUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Khắc Vỹ on 10/12/2015.
 */
public class ExcelParser {
    /**
     * Parse Student Information File
     *
     * @param file
     * @return
     */
    public List<StudentModel> parseStudentInformation(MultipartFile file) throws InvalidFormatException {
        List<StudentModel> listStudent = new ArrayList<>();

        ByteArrayInputStream inputStream = null;
        try {
            inputStream = new ByteArrayInputStream(file.getBytes());
            //Create Workbook
            Workbook workbook = WorkbookFactory.create(inputStream);
            //get first sheet
            Sheet sheet = workbook.getSheetAt(0);
            //Formula
            FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
            evaluator.setIgnoreMissingWorkbooks(true);
            //every sheet has rows, iterate over them
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                if (row.getRowNum() >= 3) {
                    String no = "";
                    String studentCode = "";
                    String studentName = "";
                    String subMajor = "";
                    String academicYear = "";
                    String startMajorSemester = "";
                    String clazz = "";
                    String term = "";
                    String studentType = "";
                    String financialType = "";
                    String studentStatus = "";
                    String note = "";
                    String major = "";
                    String narrowSpecialization = "";
                    String startEnglishLv = "";

                    //every row has cells, iterate over them
                    Iterator<Cell> cellIterator = row.cellIterator();
                    while (cellIterator.hasNext()) {
                        //Get the Cell object
                        Cell cell = cellIterator.next();
                        String cellValue = ExcelUtils.getValueOfCell(cell, cell.getCellType());
                        switch (cell.getColumnIndex()) {
                            case 0:
                                no = cellValue;
                                break;
                            case 1:
                                studentCode = cellValue;
                                break;
                            case 2:
                                studentName = cellValue;
                                break;
                            case 3:
                                subMajor = cellValue;
                                break;
                            case 4:
                                academicYear = cellValue;
                                break;
                            case 5:
                                startMajorSemester = cellValue;
                                break;
                            case 6:
                                clazz = cellValue;
                                break;
                            case 7:
                                term = cellValue;
                                break;
                            case 8:
                                studentType = cellValue;
                                break;
                            case 9:
                                financialType = cellValue;
                                break;
                            case 10:
                                studentStatus = cellValue;
                                break;
                            case 11:
                                note = cellValue;
                                break;
                            case 12:
                                major = cellValue;
                                break;
                            case 13:
                                narrowSpecialization = cellValue;
                                break;
                            case 14:
                                startEnglishLv = cellValue;
                                break;
                        }
                    }

                    StudentModel studentDto = new StudentModel(no, studentCode, studentName, subMajor, academicYear, startMajorSemester, clazz, term, studentType, financialType, studentStatus, note,major, narrowSpecialization, startEnglishLv);
                    listStudent.add(studentDto);
                }
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ExcelParser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ExcelParser.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                inputStream.close();
            } catch (IOException ex) {
                Logger.getLogger(ExcelParser.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return listStudent;
    }

    /**
     * Parse List Tuition Payment
     *
     * @param file
     * @return
     */
    public List<TuitionPaymentModel> parseListTuitionPayment(MultipartFile file) throws InvalidFormatException {
        List<TuitionPaymentModel> list = new ArrayList<>();


        ByteArrayInputStream inputStream = null;
        try {
            inputStream = new ByteArrayInputStream(file.getBytes());
            //Create Workbook
            Workbook workbook = WorkbookFactory.create(inputStream);
            //get first sheet
            Sheet sheet = workbook.getSheetAt(0);
            //Formula
            FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
            evaluator.setIgnoreMissingWorkbooks(true);
            //every sheet has rows, iterate over them
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                if (row.getRowNum() >= 2) {
                    String stt = "";
                    String quyenSo = "";
                    String sopt = "";
                    String ngayChuyenKhoan = "";
                    String nganHang = "";
                    String mssv = "";
                    String tenSinhVien = "";
                    String ttHocTap = "";
                    String ttTaiChinh = "";
                    String lop = "";
                    String noiDungNopTien = "";
                    String mucHocPhi = "";
                    String laptop = "";
                    String onToan = "";
                    String hocBong = "";
                    String tinDung = "";
                    String dauTu = "";
                    String khac = "";
                    String hocPhiCanThu = "";
                    String soTienSvChuyenKhoan = "";
                    String thucThu = "";

                    //every row has cells, iterate over them
                    Iterator<Cell> cellIterator = row.cellIterator();
                    while (cellIterator.hasNext()) {
                        //Get the Cell object
                        Cell cell = cellIterator.next();
                        String cellValue = ExcelUtils.getValueOfCell(cell, cell.getCellType());
                        switch (cell.getColumnIndex()) {
                            case 0:
                                stt = cellValue;
                                break;
                            case 1:
                                quyenSo = cellValue;
                                break;
                            case 2:
                                sopt = cellValue;
                                break;
                            case 3:
                                ngayChuyenKhoan = cellValue;
                                break;
                            case 4:
                                nganHang = cellValue;
                                break;
                            case 5:
                                mssv = cellValue;
                                break;
                            case 6:
                                tenSinhVien = cellValue;
                                break;
                            case 8:
                                ttHocTap = cellValue;
                                break;
                            case 9:
                                ttTaiChinh = cellValue;
                                break;
                            case 10:
                                lop = cellValue;
                                break;
                            case 11:
                                noiDungNopTien = cellValue;
                                break;
                            case 12:
                                mucHocPhi = cellValue;
                                break;
                            case 13:
                                laptop = cellValue;
                                break;
                            case 14:
                                onToan = cellValue;
                                break;
                            case 15:
                                hocBong = cellValue;
                                break;
                            case 16:
                                tinDung = cellValue;
                                break;
                            case 17:
                                dauTu = cellValue;
                                break;
                            case 18:
                                khac = cellValue;
                                break;
                            case 19:
                                hocPhiCanThu = cellValue;
                                break;
                            case 20:
                                soTienSvChuyenKhoan = cellValue;
                                break;
                            case 26:
                                thucThu = cellValue;
                                break;
                        }
                    }

                    TuitionPaymentModel model = new TuitionPaymentModel(stt, quyenSo, sopt, ngayChuyenKhoan, nganHang, mssv, tenSinhVien, ttHocTap, ttTaiChinh, lop, noiDungNopTien, mucHocPhi, laptop, onToan, hocBong, tinDung, dauTu, khac, hocPhiCanThu, soTienSvChuyenKhoan, thucThu);
                    list.add(model);
                }
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ExcelParser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ExcelParser.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                inputStream.close();
            } catch (IOException ex) {
                Logger.getLogger(ExcelParser.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

    public List<CourseResultModel> parseStudyResult(MultipartFile file) {
        List<CourseResultModel> list = new ArrayList<>();

        ByteArrayInputStream inputStream = null;
        try {
            inputStream = new ByteArrayInputStream(file.getBytes());
            //Create Workbook
            Workbook workbook = WorkbookFactory.create(inputStream);
            //get first sheet
            Sheet sheet = workbook.getSheetAt(0);
            //Formula
            FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
            evaluator.setIgnoreMissingWorkbooks(true);
            //every sheet has rows, iterate over them
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                if (row.getRowNum() >= 5) {
                    String mssv = "";
                    String stt = "";
                    String tenSinhVien = "";
                    String lop = "";
                    String tongDiem = "";
                    String trangThai = "";
                    String tongDiemThiLai = "";
                    String trangThaiThiLai = "";

                    //every row has cells, iterate over them
                    Iterator<Cell> cellIterator = row.cellIterator();
                    while (cellIterator.hasNext()) {
                        //Get the Cell object
                        Cell cell = cellIterator.next();
                        String cellValue = ExcelUtils.getValueOfCell(cell, cell.getCellType());
                        switch (cell.getColumnIndex()) {
                            case 0:
                                mssv = cellValue;
                                break;
                            case 1:
                                stt = cellValue;
                                break;
                            case 2:
                                tenSinhVien = cellValue;
                                break;
                            case 3:
                                lop = cellValue;
                                break;
                            case 14:
                                System.out.println("Cell Type : " + cell.getCellType());
                                tongDiem = cellValue;
                                break;
                            case 15:
                                trangThai = cellValue;
                                break;
                            case 18:
                                tongDiemThiLai = cellValue;
                                break;
                            case 19:
                                trangThaiThiLai = cellValue;
                                break;
                        }
                    }

                    CourseResultModel model = new CourseResultModel(mssv, stt, tenSinhVien, lop, tongDiem, trangThai, tongDiemThiLai, trangThaiThiLai);
                    System.out.println(model);
                    System.out.println("-----------------------------------------");

                    list.add(model);
                }
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ExcelParser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ExcelParser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException ex) {
                Logger.getLogger(ExcelParser.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return list;
    }

}
