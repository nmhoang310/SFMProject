package com.project.fpt.sfm.entities;
// Generated Oct 12, 2015 2:10:19 AM by Hibernate Tools 4.3.1


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * RetakeSubjectPayment generated by hbm2java
 */
@Entity
@Table(name="retake_subject_payment"
    ,catalog="sfm"
)
public class RetakeSubjectPayment  implements java.io.Serializable {


     private Integer retakeSubPaymentId;
     private Student student;
     private SubjectInSemester subjectInSemester;
     private int totalTuition;
     private int paidTuition;
     private boolean status;
     private Date transferDate;
     private String bank;
     private String note;
     private boolean isActive;
     private Date dateCreated;
     private Date dateUpdated;

    public RetakeSubjectPayment() {
    }

	
    public RetakeSubjectPayment(Student student, SubjectInSemester subjectInSemester, int totalTuition, int paidTuition, boolean status, String bank, boolean isActive) {
        this.student = student;
        this.subjectInSemester = subjectInSemester;
        this.totalTuition = totalTuition;
        this.paidTuition = paidTuition;
        this.status = status;
        this.bank = bank;
        this.isActive = isActive;
    }
    public RetakeSubjectPayment(Student student, SubjectInSemester subjectInSemester, int totalTuition, int paidTuition, boolean status, Date transferDate, String bank, String note, boolean isActive, Date dateCreated, Date dateUpdated) {
       this.student = student;
       this.subjectInSemester = subjectInSemester;
       this.totalTuition = totalTuition;
       this.paidTuition = paidTuition;
       this.status = status;
       this.transferDate = transferDate;
       this.bank = bank;
       this.note = note;
       this.isActive = isActive;
       this.dateCreated = dateCreated;
       this.dateUpdated = dateUpdated;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="RetakeSubPaymentID", unique=true, nullable=false)
    public Integer getRetakeSubPaymentId() {
        return this.retakeSubPaymentId;
    }
    
    public void setRetakeSubPaymentId(Integer retakeSubPaymentId) {
        this.retakeSubPaymentId = retakeSubPaymentId;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="StudentID", nullable=false)
    public Student getStudent() {
        return this.student;
    }
    
    public void setStudent(Student student) {
        this.student = student;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="SubInSemID", nullable=false)
    public SubjectInSemester getSubjectInSemester() {
        return this.subjectInSemester;
    }
    
    public void setSubjectInSemester(SubjectInSemester subjectInSemester) {
        this.subjectInSemester = subjectInSemester;
    }

    
    @Column(name="TotalTuition", nullable=false)
    public int getTotalTuition() {
        return this.totalTuition;
    }
    
    public void setTotalTuition(int totalTuition) {
        this.totalTuition = totalTuition;
    }

    
    @Column(name="PaidTuition", nullable=false)
    public int getPaidTuition() {
        return this.paidTuition;
    }
    
    public void setPaidTuition(int paidTuition) {
        this.paidTuition = paidTuition;
    }

    
    @Column(name="Status", nullable=false)
    public boolean isStatus() {
        return this.status;
    }
    
    public void setStatus(boolean status) {
        this.status = status;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="TransferDate", length=19)
    public Date getTransferDate() {
        return this.transferDate;
    }
    
    public void setTransferDate(Date transferDate) {
        this.transferDate = transferDate;
    }

    
    @Column(name="Bank", nullable=false, length=125)
    public String getBank() {
        return this.bank;
    }
    
    public void setBank(String bank) {
        this.bank = bank;
    }

    
    @Column(name="Note", length=65535)
    public String getNote() {
        return this.note;
    }
    
    public void setNote(String note) {
        this.note = note;
    }

    
    @Column(name="IsActive", nullable=false)
    public boolean isIsActive() {
        return this.isActive;
    }
    
    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="Date_Created", length=19)
    public Date getDateCreated() {
        return this.dateCreated;
    }
    
    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="DateUpdated", length=19)
    public Date getDateUpdated() {
        return this.dateUpdated;
    }
    
    public void setDateUpdated(Date dateUpdated) {
        this.dateUpdated = dateUpdated;
    }




}


