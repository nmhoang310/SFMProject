package com.project.fpt.sfm.processexcel.development.model;

/**
 * Created by Khắc Vỹ on 10/14/2015.
 */
public class TuitionPaymentModel {
    private String stt;
    private String quyenSo;
    private String sopt;
    private String ngayChuyenKhoan;
    private String nganHang;
    private String mssv;
    private String tenSinhVien;
    private String ttHocTap;
    private String ttTaiChinh;
    private String lop;
    private String noiDungNopTien;
    private String mucHocPhi;
    private String laptop;
    private String onToan;
    private String hocBong;
    private String tinDung;
    private String dauTu;
    private String khac;
    private String hocPhiCanThu;
    private String soTienSvChuyenKhoan;
    private String thucThu;

    public TuitionPaymentModel() {
    }

    public TuitionPaymentModel(String stt, String quyenSo, String sopt, String ngayChuyenKhoan, String nganHang, String mssv, String tenSinhVien, String ttHocTap, String ttTaiChinh, String lop, String noiDungNopTien, String mucHocPhi, String laptop, String onToan, String hocBong, String tinDung, String dauTu, String khac, String hocPhiCanThu, String soTienSvChuyenKhoan, String thucThu) {
        this.stt = stt;
        this.quyenSo = quyenSo;
        this.sopt = sopt;
        this.ngayChuyenKhoan = ngayChuyenKhoan;
        this.nganHang = nganHang;
        this.mssv = mssv;
        this.tenSinhVien = tenSinhVien;
        this.ttHocTap = ttHocTap;
        this.ttTaiChinh = ttTaiChinh;
        this.lop = lop;
        this.noiDungNopTien = noiDungNopTien;
        this.mucHocPhi = mucHocPhi;
        this.laptop = laptop;
        this.onToan = onToan;
        this.hocBong = hocBong;
        this.tinDung = tinDung;
        this.dauTu = dauTu;
        this.khac = khac;
        this.hocPhiCanThu = hocPhiCanThu;
        this.soTienSvChuyenKhoan = soTienSvChuyenKhoan;
        this.thucThu = thucThu;
    }

    public String getStt() {
        return stt;
    }

    public void setStt(String stt) {
        this.stt = stt;
    }

    public String getQuyenSo() {
        return quyenSo;
    }

    public void setQuyenSo(String quyenSo) {
        this.quyenSo = quyenSo;
    }

    public String getSopt() {
        return sopt;
    }

    public void setSopt(String sopt) {
        this.sopt = sopt;
    }

    public String getNgayChuyenKhoan() {
        return ngayChuyenKhoan;
    }

    public void setNgayChuyenKhoan(String ngayChuyenKhoan) {
        this.ngayChuyenKhoan = ngayChuyenKhoan;
    }

    public String getNganHang() {
        return nganHang;
    }

    public void setNganHang(String nganHang) {
        this.nganHang = nganHang;
    }

    public String getMssv() {
        return mssv;
    }

    public void setMssv(String mssv) {
        this.mssv = mssv;
    }

    public String getTenSinhVien() {
        return tenSinhVien;
    }

    public void setTenSinhVien(String tenSinhVien) {
        this.tenSinhVien = tenSinhVien;
    }

    public String getTtHocTap() {
        return ttHocTap;
    }

    public void setTtHocTap(String ttHocTap) {
        this.ttHocTap = ttHocTap;
    }

    public String getTtTaiChinh() {
        return ttTaiChinh;
    }

    public void setTtTaiChinh(String ttTaiChinh) {
        this.ttTaiChinh = ttTaiChinh;
    }

    public String getLop() {
        return lop;
    }

    public void setLop(String lop) {
        this.lop = lop;
    }

    public String getNoiDungNopTien() {
        return noiDungNopTien;
    }

    public void setNoiDungNopTien(String noiDungNopTien) {
        this.noiDungNopTien = noiDungNopTien;
    }

    public String getMucHocPhi() {
        return mucHocPhi;
    }

    public void setMucHocPhi(String mucHocPhi) {
        this.mucHocPhi = mucHocPhi;
    }

    public String getLaptop() {
        return laptop;
    }

    public void setLaptop(String laptop) {
        this.laptop = laptop;
    }

    public String getOnToan() {
        return onToan;
    }

    public void setOnToan(String onToan) {
        this.onToan = onToan;
    }

    public String getHocBong() {
        return hocBong;
    }

    public void setHocBong(String hocBong) {
        this.hocBong = hocBong;
    }

    public String getTinDung() {
        return tinDung;
    }

    public void setTinDung(String tinDung) {
        this.tinDung = tinDung;
    }

    public String getDauTu() {
        return dauTu;
    }

    public void setDauTu(String dauTu) {
        this.dauTu = dauTu;
    }

    public String getKhac() {
        return khac;
    }

    public void setKhac(String khac) {
        this.khac = khac;
    }

    public String getHocPhiCanThu() {
        return hocPhiCanThu;
    }

    public void setHocPhiCanThu(String hocPhiCanThu) {
        this.hocPhiCanThu = hocPhiCanThu;
    }

    public String getSoTienSvChuyenKhoan() {
        return soTienSvChuyenKhoan;
    }

    public void setSoTienSvChuyenKhoan(String soTienSvChuyenKhoan) {
        this.soTienSvChuyenKhoan = soTienSvChuyenKhoan;
    }

    public String getThucThu() {
        return thucThu;
    }

    public void setThucThu(String thucThu) {
        this.thucThu = thucThu;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("TuitionPaymentModel{");
        sb.append("stt='").append(stt).append('\'');
        sb.append(", quyenSo='").append(quyenSo).append('\'');
        sb.append(", sopt='").append(sopt).append('\'');
        sb.append(", ngayChuyenKhoan='").append(ngayChuyenKhoan).append('\'');
        sb.append(", nganHang='").append(nganHang).append('\'');
        sb.append(", mssv='").append(mssv).append('\'');
        sb.append(", tenSinhVien='").append(tenSinhVien).append('\'');
        sb.append(", ttHocTap='").append(ttHocTap).append('\'');
        sb.append(", ttTaiChinh='").append(ttTaiChinh).append('\'');
        sb.append(", lop='").append(lop).append('\'');
        sb.append(", noiDungNopTien='").append(noiDungNopTien).append('\'');
        sb.append(", mucHocPhi='").append(mucHocPhi).append('\'');
        sb.append(", laptop='").append(laptop).append('\'');
        sb.append(", onToan='").append(onToan).append('\'');
        sb.append(", hocBong='").append(hocBong).append('\'');
        sb.append(", tinDung='").append(tinDung).append('\'');
        sb.append(", dauTu='").append(dauTu).append('\'');
        sb.append(", khac='").append(khac).append('\'');
        sb.append(", hocPhiCanThu='").append(hocPhiCanThu).append('\'');
        sb.append(", soTienSvChuyenKhoan='").append(soTienSvChuyenKhoan).append('\'');
        sb.append(", thucThu='").append(thucThu).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
