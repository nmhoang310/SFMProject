package com.project.fpt.sfm.entities;
// Generated Oct 15, 2015 7:12:40 AM by Hibernate Tools 4.3.1


import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * TuitionPayment generated by hbm2java
 */
@Entity
@Table(name = "tuition_payment"
        , catalog = "sfm"
)
public class TuitionPayment implements java.io.Serializable {


    private Integer tuitionPaymentId;
    private Semester semester;
    private Student student;
    private int totalTuition;
    private int paidTuition;
    private String bank;
    private Date transferDate;
    private boolean status;
    private String note;
    private boolean isActive;
    private Date dateCreated;
    private Date dateUpdated;
    private Set<SubtractTuition> subtractTuitions = new HashSet<SubtractTuition>(0);

    public TuitionPayment() {
    }


    public TuitionPayment(Semester semester, Student student, int totalTuition, int paidTuition, String bank, boolean status, boolean isActive) {
        this.semester = semester;
        this.student = student;
        this.totalTuition = totalTuition;
        this.paidTuition = paidTuition;
        this.bank = bank;
        this.status = status;
        this.isActive = isActive;
    }

    public TuitionPayment(Semester semester, Student student, int totalTuition, int paidTuition, String bank, Date transferDate, boolean status, String note, boolean isActive, Date dateCreated, Date dateUpdated, Set<SubtractTuition> subtractTuitions) {
        this.semester = semester;
        this.student = student;
        this.totalTuition = totalTuition;
        this.paidTuition = paidTuition;
        this.bank = bank;
        this.transferDate = transferDate;
        this.status = status;
        this.note = note;
        this.isActive = isActive;
        this.dateCreated = dateCreated;
        this.dateUpdated = dateUpdated;
        this.subtractTuitions = subtractTuitions;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)


    @Column(name = "TuitionPaymentID", unique = true, nullable = false)
    public Integer getTuitionPaymentId() {
        return this.tuitionPaymentId;
    }

    public void setTuitionPaymentId(Integer tuitionPaymentId) {
        this.tuitionPaymentId = tuitionPaymentId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SemesterID", nullable = false)
    public Semester getSemester() {
        return this.semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "StudentID", nullable = false)
    public Student getStudent() {
        return this.student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }


    @Column(name = "TotalTuition", nullable = false)
    public int getTotalTuition() {
        return this.totalTuition;
    }

    public void setTotalTuition(int totalTuition) {
        this.totalTuition = totalTuition;
    }


    @Column(name = "PaidTuition", nullable = false)
    public int getPaidTuition() {
        return this.paidTuition;
    }

    public void setPaidTuition(int paidTuition) {
        this.paidTuition = paidTuition;
    }


    @Column(name = "Bank", nullable = false, length = 100)
    public String getBank() {
        return this.bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "TransferDate", length = 19)
    public Date getTransferDate() {
        return this.transferDate;
    }

    public void setTransferDate(Date transferDate) {
        this.transferDate = transferDate;
    }


    @Column(name = "Status", nullable = false)
    public boolean isStatus() {
        return this.status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }


    @Column(name = "Note", length = 65535)
    public String getNote() {
        return this.note;
    }

    public void setNote(String note) {
        this.note = note;
    }


    @Column(name = "IsActive", nullable = false)
    public boolean isIsActive() {
        return this.isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "Date_Created", length = 19)
    public Date getDateCreated() {
        return this.dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DateUpdated", length = 19)
    public Date getDateUpdated() {
        return this.dateUpdated;
    }

    public void setDateUpdated(Date dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "tuitionPayment")
    @Cascade(CascadeType.ALL)
    public Set<SubtractTuition> getSubtractTuitions() {
        return this.subtractTuitions;
    }

    public void setSubtractTuitions(Set<SubtractTuition> subtractTuitions) {
        this.subtractTuitions = subtractTuitions;
    }


}

