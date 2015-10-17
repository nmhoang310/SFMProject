package com.project.fpt.sfm.processexcel.model;

import java.util.Map;

/**
 * Created by Khắc Vỹ on 10/5/2015.
 */
public class SessionTuitionDto {
    private int no;
    private String transferDate;
    private String bank;
    private String studentCode;
    private String studentName;
    private String content;
    private String tuitionAmount;
    private Map<String, String> subtractAmount;
    private String tuitionAmountRequired;
    private String sentAmount;
    private String cashBalance;
    private String cashShortage;
    private String collectedAmount;

    public SessionTuitionDto() {
    }

    public SessionTuitionDto(int no, String transferDate, String bank, String studentCode, String studentName, String content, String tuitionAmount, Map<String, String> subtractAmount, String tuitionAmountRequired, String sentAmount, String cashBalance, String cashShortage, String collectedAmount) {
        this.no = no;
        this.transferDate = transferDate;
        this.bank = bank;
        this.studentCode = studentCode;
        this.studentName = studentName;
        this.content = content;
        this.tuitionAmount = tuitionAmount;
        this.subtractAmount = subtractAmount;
        this.tuitionAmountRequired = tuitionAmountRequired;
        this.sentAmount = sentAmount;
        this.cashBalance = cashBalance;
        this.cashShortage = cashShortage;
        this.collectedAmount = collectedAmount;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getTransferDate() {
        return transferDate;
    }

    public void setTransferDate(String transferDate) {
        this.transferDate = transferDate;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTuitionAmount() {
        return tuitionAmount;
    }

    public void setTuitionAmount(String tuitionAmount) {
        this.tuitionAmount = tuitionAmount;
    }

    public Map<String, String> getSubtractAmount() {
        return subtractAmount;
    }

    public void setSubtractAmount(Map<String, String> subtractAmount) {
        this.subtractAmount = subtractAmount;
    }

    public String getTuitionAmountRequired() {
        return tuitionAmountRequired;
    }

    public void setTuitionAmountRequired(String tuitionAmountRequired) {
        this.tuitionAmountRequired = tuitionAmountRequired;
    }

    public String getSentAmount() {
        return sentAmount;
    }

    public void setSentAmount(String sentAmount) {
        this.sentAmount = sentAmount;
    }

    public String getCashBalance() {
        return cashBalance;
    }

    public void setCashBalance(String cashBalance) {
        this.cashBalance = cashBalance;
    }

    public String getCashShortage() {
        return cashShortage;
    }

    public void setCashShortage(String cashShortage) {
        this.cashShortage = cashShortage;
    }

    public String getCollectedAmount() {
        return collectedAmount;
    }

    public void setCollectedAmount(String collectedAmount) {
        this.collectedAmount = collectedAmount;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("SessionTuitionDto{");
        sb.append("no=").append(no);
        sb.append(", transferDate='").append(transferDate).append('\'');
        sb.append(", bank='").append(bank).append('\'');
        sb.append(", studentCode='").append(studentCode).append('\'');
        sb.append(", studentName='").append(studentName).append('\'');
        sb.append(", content='").append(content).append('\'');
        sb.append(", tuitionAmount='").append(tuitionAmount).append('\'');
        sb.append(", subtractAmount=").append(subtractAmount);
        sb.append(", tuitionAmountRequired='").append(tuitionAmountRequired).append('\'');
        sb.append(", sentAmount='").append(sentAmount).append('\'');
        sb.append(", cashBalance='").append(cashBalance).append('\'');
        sb.append(", cashShortage='").append(cashShortage).append('\'');
        sb.append(", collectedAmount='").append(collectedAmount).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
