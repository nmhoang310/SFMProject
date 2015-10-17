package com.project.fpt.sfm.processexcel.model;

import com.project.fpt.sfm.processexcel.utils.ExcelColumn;
import com.project.fpt.sfm.processexcel.utils.ExcelReport;

/**
 * Created by Khắc Vỹ on 10/4/2015.
 */
@ExcelReport(className = "xx", documentName = "Document", sheetName = "Sheet")
public class StudentDto {
    private String no;
    private String studentCode;
    private String studentName;
    private String major;
    private String term;
    private String startMajorSemester;
    private String clazz;
    private String session;
    private String studentType;
    private String financeType;
    private String studentStatus;
    private String note;

    public StudentDto() {
    }

    public StudentDto(String no, String studentCode, String studentName, String major, String term, String startMajorSemester, String clazz, String session, String studentType, String financeType, String studentStatus, String note) {
        this.no = no;
        this.studentCode = studentCode;
        this.studentName = studentName;
        this.major = major;
        this.term = term;
        this.startMajorSemester = startMajorSemester;
        this.clazz = clazz;
        this.session = session;
        this.studentType = studentType;
        this.financeType = financeType;
        this.studentStatus = studentStatus;
        this.note = note;
    }

    @ExcelColumn(label = "TT", order = 0)
    public String getNo() {
        return no;
    }

    public void setNo(String no) {
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

    @ExcelColumn(label = "Khóa nhập học", order = 4)
    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    @ExcelColumn(label = "Kỳ bắt đầu học chuyên môn", order = 5)
    public String getStartMajorSemester() {
        return startMajorSemester;
    }

    public void setStartMajorSemester(String startMajorSemester) {
        this.startMajorSemester = startMajorSemester;
    }

    @ExcelColumn(label = "LỚP", order = 6)
    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    @ExcelColumn(label = "KỲ HỌC", order = 7)
    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    @ExcelColumn(label = "Diện", order = 8)
    public String getStudentType() {
        return studentType;
    }

    public void setStudentType(String studentType) {
        this.studentType = studentType;
    }

    @ExcelColumn(label = "Loại tài chính", order = 9)
    public String getFinanceType() {
        return financeType;
    }

    public void setFinanceType(String financeType) {
        this.financeType = financeType;
    }
    @ExcelColumn(label = "Trạng thái sinh viên", order = 10)
    public String getStudentStatus() {
        return studentStatus;
    }

    public void setStudentStatus(String studentStatus) {
        this.studentStatus = studentStatus;
    }

    @ExcelColumn(label = "Ghi chú", order = 11)
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("StudentDto{");
        sb.append("no='").append(no).append('\'');
        sb.append(", studentCode='").append(studentCode).append('\'');
        sb.append(", studentName='").append(studentName).append('\'');
        sb.append(", major='").append(major).append('\'');
        sb.append(", term='").append(term).append('\'');
        sb.append(", startMajorSemester='").append(startMajorSemester).append('\'');
        sb.append(", clazz='").append(clazz).append('\'');
        sb.append(", session='").append(session).append('\'');
        sb.append(", studentType='").append(studentType).append('\'');
        sb.append(", financeType='").append(financeType).append('\'');
        sb.append(", studentStatus='").append(studentStatus).append('\'');
        sb.append(", note='").append(note).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
