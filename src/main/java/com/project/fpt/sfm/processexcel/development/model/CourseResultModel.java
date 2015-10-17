package com.project.fpt.sfm.processexcel.development.model;

/**
 * Created by Khắc Vỹ on 10/14/2015.
 */
public class CourseResultModel {
    private String mssv;
    private String stt;
    private String tenSinhVien;
    private String lop;
    private String tongDiem;
    private String trangThai;
    private String tongDiemThiLai;
    private String trangThaiThiLai;

    public CourseResultModel() {
    }

    public CourseResultModel(String mssv, String stt, String tenSinhVien, String lop, String tongDiem, String trangThai, String tongDiemThiLai, String trangThaiThiLai) {
        this.mssv = mssv;
        this.stt = stt;
        this.tenSinhVien = tenSinhVien;
        this.lop = lop;
        this.tongDiem = tongDiem;
        this.trangThai = trangThai;
        this.tongDiemThiLai = tongDiemThiLai;
        this.trangThaiThiLai = trangThaiThiLai;
    }

    public String getMssv() {
        return mssv;
    }

    public void setMssv(String mssv) {
        this.mssv = mssv;
    }

    public String getStt() {
        return stt;
    }

    public void setStt(String stt) {
        this.stt = stt;
    }

    public String getTenSinhVien() {
        return tenSinhVien;
    }

    public void setTenSinhVien(String tenSinhVien) {
        this.tenSinhVien = tenSinhVien;
    }

    public String getLop() {
        return lop;
    }

    public void setLop(String lop) {
        this.lop = lop;
    }

    public String getTongDiem() {
        return tongDiem;
    }

    public void setTongDiem(String tongDiem) {
        this.tongDiem = tongDiem;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getTongDiemThiLai() {
        return tongDiemThiLai;
    }

    public void setTongDiemThiLai(String tongDiemThiLai) {
        this.tongDiemThiLai = tongDiemThiLai;
    }

    public String getTrangThaiThiLai() {
        return trangThaiThiLai;
    }

    public void setTrangThaiThiLai(String trangThaiThiLai) {
        this.trangThaiThiLai = trangThaiThiLai;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("CourseResultModel{");
        sb.append("mssv='").append(mssv).append('\'');
        sb.append(", stt='").append(stt).append('\'');
        sb.append(", tenSinhVien='").append(tenSinhVien).append('\'');
        sb.append(", lop='").append(lop).append('\'');
        sb.append(", tongDiem='").append(tongDiem).append('\'');
        sb.append(", trangThai='").append(trangThai).append('\'');
        sb.append(", tongDiemThiLai='").append(tongDiemThiLai).append('\'');
        sb.append(", trangThaiThiLai='").append(trangThaiThiLai).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
