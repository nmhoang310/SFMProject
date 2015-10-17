package com.project.fpt.sfm.processexcel.model;

import com.project.fpt.sfm.processexcel.utils.ExcelColumn;
import com.project.fpt.sfm.processexcel.utils.ExcelReport;

/**
 * Created by Khắc Vỹ on 10/14/2015.
 */
@ExcelReport(className = "xx", documentName = "Document", sheetName = "Sheet")
public class TuitionPaymentTemplate {
    private Integer no;
    private String dateTransfer;
    private String bank;
    private String studentCode;
    private String studentName;
    private String tuitionType;
    private Integer totalTuition;
    private Integer subtractLaptop;
    private Integer subtractMath;
    private Integer subtractOther;
    private Integer paidTuition;

    @ExcelColumn(label = "TT", order = 0)
    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }
    @ExcelColumn(label = "Ngày CK", order = 1)
    public String getDateTransfer() {
        return dateTransfer;
    }

    public void setDateTransfer(String dateTransfer) {
        this.dateTransfer = dateTransfer;
    }
    @ExcelColumn(label = "Ngân hàng", order = 2)
    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    @ExcelColumn(label = "MSSV", order = 3)
    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }
    @ExcelColumn(label = "Tên sinh viên", order = 4)
    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
    @ExcelColumn(label = " Nội dung nộp tiền", order = 5)
    public String getTuitionType() {
        return tuitionType;
    }

    public void setTuitionType(String tuitionType) {
        this.tuitionType = tuitionType;
    }
    @ExcelColumn(label = " Mức HP", order = 6)
    public Integer getTotalTuition() {
        return totalTuition;
    }

    public void setTotalTuition(Integer totalTuition) {
        this.totalTuition = totalTuition;
    }
    @ExcelColumn(label = " Lap top", order = 7)
    public Integer getSubtractLaptop() {
        return subtractLaptop;
    }

    public void setSubtractLaptop(Integer subtractLaptop) {
        this.subtractLaptop = subtractLaptop;
    }
    @ExcelColumn(label = " Ôn toán", order = 8)
    public Integer getSubtractMath() {
        return subtractMath;
    }

    public void setSubtractMath(Integer subtractMath) {
        this.subtractMath = subtractMath;
    }
    @ExcelColumn(label = " Khác", order = 9)
    public Integer getSubtractOther() {
        return subtractOther;
    }

    public void setSubtractOther(Integer subtractOther) {
        this.subtractOther = subtractOther;
    }
    @ExcelColumn(label = "Số tiền SV chuyển khoản", order = 10)
    public Integer getPaidTuition() {
        return paidTuition;
    }

    public void setPaidTuition(Integer paidTuition) {
        this.paidTuition = paidTuition;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("TuitionPaymentTemplate{");
        sb.append("no=").append(no);
        sb.append(", dateTransfer='").append(dateTransfer).append('\'');
        sb.append(", bank='").append(bank).append('\'');
        sb.append(", studentCode='").append(studentCode).append('\'');
        sb.append(", studentName='").append(studentName).append('\'');
        sb.append(", tuitionType='").append(tuitionType).append('\'');
        sb.append(", totalTuition=").append(totalTuition);
        sb.append(", subtractLaptop=").append(subtractLaptop);
        sb.append(", subtractMath=").append(subtractMath);
        sb.append(", subtractOther=").append(subtractOther);
        sb.append(", paidTuition=").append(paidTuition);
        sb.append('}');
        return sb.toString();
    }
}
