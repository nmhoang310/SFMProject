package com.project.fpt.sfm.processexcel.model;

import com.project.fpt.sfm.processexcel.utils.ExcelColumn;
import com.project.fpt.sfm.processexcel.utils.ExcelReport;

/**
 * Created by Khắc Vỹ on 10/13/2015.
 */
@ExcelReport(className = "xx", documentName = "Document", sheetName = "Sheet")
public class StudentTemplate {
    private Integer no;
    private String studentCode;
    private String studentName;
    private String major;
    private String subMajor;
    private String term;
    private String englishLevel;
    private String clazz;
    private String financeType;
    private String studentStatus;
    private String note;

    @ExcelColumn(label = "TT", order = 0)
    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    @ExcelColumn(label = "MSSV", order = 1)
    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }

    @ExcelColumn(label = "Họ tên", order = 2)
    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    @ExcelColumn(label = "Ngành học", order = 3)
    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    @ExcelColumn(label = "Ngành Học Chi Tiết", order = 4)
    public String getSubMajor() {
        return subMajor;
    }

    public void setSubMajor(String subMajor) {
        this.subMajor = subMajor;
    }

    @ExcelColumn(label = "Khóa nhập học", order = 5)
    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    @ExcelColumn(label = "Tiếng Anh Đầu Vào", order = 6)
    public String getEnglishLevel() {
        return englishLevel;
    }

    public void setEnglishLevel(String englishLevel) {
        this.englishLevel = englishLevel;
    }

    @ExcelColumn(label = "LỚP", order = 7)
    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    @ExcelColumn(label = "Loại tài chính", order = 8)
    public String getFinanceType() {
        return financeType;
    }

    public void setFinanceType(String financeType) {
        this.financeType = financeType;
    }

    @ExcelColumn(label = "Trạng thái sinh viên", order = 9)
    public String getStudentStatus() {
        return studentStatus;
    }

    public void setStudentStatus(String studentStatus) {
        this.studentStatus = studentStatus;
    }

    @ExcelColumn(label = "Ghi chú", order = 10)
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("StudentTemplate{");
        sb.append("no='").append(no).append('\'');
        sb.append(", studentCode='").append(studentCode).append('\'');
        sb.append(", studentName='").append(studentName).append('\'');
        sb.append(", major='").append(major).append('\'');
        sb.append(", subMajor='").append(subMajor).append('\'');
        sb.append(", term='").append(term).append('\'');
        sb.append(", englishLevel='").append(englishLevel).append('\'');
        sb.append(", clazz='").append(clazz).append('\'');
        sb.append(", financeType='").append(financeType).append('\'');
        sb.append(", studentStatus='").append(studentStatus).append('\'');
        sb.append(", note='").append(note).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
