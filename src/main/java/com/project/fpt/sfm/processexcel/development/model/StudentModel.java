package com.project.fpt.sfm.processexcel.development.model;

import com.project.fpt.sfm.entities.Student;

/**
 * Created by Khắc Vỹ on 10/12/2015.
 */
public class StudentModel {
    private String no;
    private String studentCode;
    private String studentName;
    private String subMajor;
    private String academicYear;
    private String startMajorSemester;
    private String clazz;
    private String term;
    private String studentType;
    private String financialType;
    private String studentStatus;
    private String note;
    private String major;
    private String narrowSpecialization;
    private String startEnglishLevel;

    public StudentModel() {
    }

    public StudentModel(String no, String studentCode, String studentName, String subMajor, String academicYear, String startMajorSemester, String clazz, String term, String studentType, String financialType, String studentStatus, String note, String major, String narrowSpecialization, String startEnglishLevel) {
        this.no = no;
        this.studentCode = studentCode;
        this.studentName = studentName;
        this.subMajor = subMajor;
        this.academicYear = academicYear;
        this.startMajorSemester = startMajorSemester;
        this.clazz = clazz;
        this.term = term;
        this.studentType = studentType;
        this.financialType = financialType;
        this.studentStatus = studentStatus;
        this.note = note;
        this.major = major;
        this.narrowSpecialization = narrowSpecialization;
        this.startEnglishLevel = startEnglishLevel;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getSubMajor() {
        return subMajor;
    }

    public void setSubMajor(String subMajor) {
        this.subMajor = subMajor;
    }

    public String getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(String academicYear) {
        this.academicYear = academicYear;
    }

    public String getStartMajorSemester() {
        return startMajorSemester;
    }

    public void setStartMajorSemester(String startMajorSemester) {
        this.startMajorSemester = startMajorSemester;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getStudentType() {
        return studentType;
    }

    public void setStudentType(String studentType) {
        this.studentType = studentType;
    }

    public String getFinancialType() {
        return financialType;
    }

    public void setFinancialType(String financialType) {
        this.financialType = financialType;
    }
    public String getStudentStatus() {
        return studentStatus;
    }

    public void setStudentStatus(String studentStatus) {
        this.studentStatus = studentStatus;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getStartEnglishLevel() {
        return startEnglishLevel;
    }

    public void setStartEnglishLevel(String startEnglishLevel) {
        this.startEnglishLevel = startEnglishLevel;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getNarrowSpecialization() {
        return narrowSpecialization;
    }

    public void setNarrowSpecialization(String narrowSpecialization) {
        this.narrowSpecialization = narrowSpecialization;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("StudentModel{");
        sb.append("no='").append(no).append('\'');
        sb.append(", studentCode='").append(studentCode).append('\'');
        sb.append(", studentName='").append(studentName).append('\'');
        sb.append(", subMajor='").append(subMajor).append('\'');
        sb.append(", academicYear='").append(academicYear).append('\'');
        sb.append(", startMajorSemester='").append(startMajorSemester).append('\'');
        sb.append(", clazz='").append(clazz).append('\'');
        sb.append(", term='").append(term).append('\'');
        sb.append(", studentType='").append(studentType).append('\'');
        sb.append(", financialType='").append(financialType).append('\'');
        sb.append(", studentStatus='").append(studentStatus).append('\'');
        sb.append(", note='").append(note).append('\'');
        sb.append(", major='").append(major).append('\'');
        sb.append(", narrowSpecialization='").append(narrowSpecialization).append('\'');
        sb.append(", startEnglishLevel='").append(startEnglishLevel).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
