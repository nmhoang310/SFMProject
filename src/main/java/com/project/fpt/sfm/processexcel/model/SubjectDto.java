package com.project.fpt.sfm.processexcel.model;

import com.project.fpt.sfm.entities.Subject;
import com.project.fpt.sfm.processexcel.utils.ExcelColumn;
import com.project.fpt.sfm.processexcel.utils.ExcelReport;

/**
 * Created by Khắc Vỹ on 10/9/2015.
 */
@ExcelReport(className = "com.project.fpt.sfm.processexcel.model.SubjectDto",
documentName = "DNHSACHMONHOC", sheetName = "Sheet1")
public class SubjectDto {
    private Integer number;
    private String englishName;
    private String vietnameseName;
    private String abbreviation;
    private String subjectCode;
    private String mandatorySubject;
    private Integer numOfCredit;
    private String note;

    @ExcelColumn(label = "STT", order = 0)
    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @ExcelColumn(label = "Tên tiếng Anh", order = 1)
    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    @ExcelColumn(label = "Tên tiếng Việt", order = 2)
    public String getVietnameseName() {
        return vietnameseName;
    }

    public void setVietnameseName(String vietnameseName) {
        this.vietnameseName = vietnameseName;
    }

    @ExcelColumn(label = "Tên viết tắt", order = 3)
    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    @ExcelColumn(label = "Mã học phần", order = 4)
    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    @ExcelColumn(label = "Học phần tiên quyết", order = 5)
    public String getMandatorySubject() {
        return mandatorySubject;
    }

    public void setMandatorySubject(String mandatorySubject) {
        this.mandatorySubject = mandatorySubject;
    }

    @ExcelColumn(label = "Số Tín Chỉ", order = 6)
    public Integer getNumOfCredit() {
        return numOfCredit;
    }

    public void setNumOfCredit(Integer numOfCredit) {
        this.numOfCredit = numOfCredit;
    }
    @ExcelColumn(label = "Ghi chú", order = 7)
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("SubjectDto{");
        sb.append("number=").append(number);
        sb.append(", englishName='").append(englishName).append('\'');
        sb.append(", vietnameseName='").append(vietnameseName).append('\'');
        sb.append(", abbreviation='").append(abbreviation).append('\'');
        sb.append(", subjectCode='").append(subjectCode).append('\'');
        sb.append(", mandatorySubject='").append(mandatorySubject).append('\'');
        sb.append(", numOfCredit=").append(numOfCredit);
        sb.append(", note='").append(note).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public Subject toSubject(){
        Subject sub = new Subject();
        sub.setSubjectNameE(this.englishName);
        sub.setSubjectNameV(this.vietnameseName);
        sub.setAbbreviation(this.abbreviation);
        sub.setSubjectCode(this.subjectCode);
        //sub.setNumOfCredit(this.numOfCredit);
        sub.setNote(this.note);
        sub.setIsActive(true);

        return sub;
    }
}
