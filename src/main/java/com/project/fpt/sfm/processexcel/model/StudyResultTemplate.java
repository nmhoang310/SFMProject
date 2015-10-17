package com.project.fpt.sfm.processexcel.model;

import com.project.fpt.sfm.processexcel.utils.ExcelColumn;
import com.project.fpt.sfm.processexcel.utils.ExcelReport;

/**
 * Created by Khắc Vỹ on 10/14/2015.
 */
@ExcelReport(className = "xx", documentName = "Document", sheetName = "Sheet")
public class StudyResultTemplate {
    private Integer no;
    private String studentCode;
    private String studentName;
    private Float mark;
    private String isRetake;
    private String status;
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

    @ExcelColumn(label = "Tên sinh viên", order = 2)
    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    @ExcelColumn(label = "Điểm", order = 3)
    public Float getMark() {
        return mark;
    }

    public void setMark(Float mark) {
        this.mark = mark;
    }

    @ExcelColumn(label = "Học Lại", order = 4)
    public String getIsRetake() {
        return isRetake;
    }

    public void setIsRetake(String isRetake) {
        this.isRetake = isRetake;
    }

    @ExcelColumn(label = "Trạng Thái", order = 5)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @ExcelColumn(label = "Ghi Chú", order = 6)
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
