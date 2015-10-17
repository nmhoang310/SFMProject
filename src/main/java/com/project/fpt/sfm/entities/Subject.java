package com.project.fpt.sfm.entities;
// Generated Oct 12, 2015 2:10:19 AM by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Subject generated by hbm2java
 */
@Entity
@Table(name="subject"
    ,catalog="sfm"
)
public class Subject  implements java.io.Serializable {


     private Integer subjectId;
     private String subjectNameE;
     private String subjectNameV;
     private String subjectCode;
     private String abbreviation;
     private String note;
     private Boolean isActive;
     private Date dateCreated;
     private Date dateUpdated;
     private Set<MandatorySubject> mandatorySubjectsForRequireSubjectId = new HashSet<MandatorySubject>(0);
     private Set<MandatorySubject> mandatorySubjectsForSubjectId = new HashSet<MandatorySubject>(0);
     private Set<SubjectInSemester> subjectInSemesters = new HashSet<SubjectInSemester>(0);

    public Subject() {
        this.dateCreated = new Date();
        this.dateUpdated = new Date();
        this.isActive = true;
        this.note = "-";
    }

	
    public Subject(String subjectNameE, String subjectNameV, String subjectCode, String abbreviation, String note, Boolean isActive, Date dateCreated, Date dateUpdated, Set<MandatorySubject> mandatorySubjectsForRequireSubjectId, Set<MandatorySubject> mandatorySubjectsForSubjectId, Set<SubjectInSemester> subjectInSemesters) {
       this.subjectNameE = subjectNameE;
       this.subjectNameV = subjectNameV;
       this.subjectCode = subjectCode;
       this.abbreviation = abbreviation;
       this.note = note;
       this.isActive = isActive;
       this.dateCreated = dateCreated;
       this.dateUpdated = dateUpdated;
       this.mandatorySubjectsForRequireSubjectId = mandatorySubjectsForRequireSubjectId;
       this.mandatorySubjectsForSubjectId = mandatorySubjectsForSubjectId;
       this.subjectInSemesters = subjectInSemesters;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="SubjectID", unique=true, nullable=false)
    public Integer getSubjectId() {
        return this.subjectId;
    }
    
    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    
    @Column(name="SubjectNameE", length=80)
    public String getSubjectNameE() {
        return this.subjectNameE;
    }
    
    public void setSubjectNameE(String subjectNameE) {
        this.subjectNameE = subjectNameE;
    }

    
    @Column(name="SubjectNameV", length=80)
    public String getSubjectNameV() {
        return this.subjectNameV;
    }
    
    public void setSubjectNameV(String subjectNameV) {
        this.subjectNameV = subjectNameV;
    }

    
    @Column(name="SubjectCode", length=15)
    public String getSubjectCode() {
        return this.subjectCode;
    }
    
    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    
    @Column(name="Abbreviation", length=15)
    public String getAbbreviation() {
        return this.abbreviation;
    }
    
    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    
    @Column(name="Note", length=65535)
    public String getNote() {
        return this.note;
    }
    
    public void setNote(String note) {
        this.note = note;
    }

    
    @Column(name="IsActive")
    public Boolean getIsActive() {
        return this.isActive;
    }
    
    public void setIsActive(Boolean isActive) {
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

@OneToMany(fetch=FetchType.LAZY, mappedBy="subjectByRequireSubjectId")
    public Set<MandatorySubject> getMandatorySubjectsForRequireSubjectId() {
        return this.mandatorySubjectsForRequireSubjectId;
    }
    
    public void setMandatorySubjectsForRequireSubjectId(Set<MandatorySubject> mandatorySubjectsForRequireSubjectId) {
        this.mandatorySubjectsForRequireSubjectId = mandatorySubjectsForRequireSubjectId;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="subjectBySubjectId")
    public Set<MandatorySubject> getMandatorySubjectsForSubjectId() {
        return this.mandatorySubjectsForSubjectId;
    }
    
    public void setMandatorySubjectsForSubjectId(Set<MandatorySubject> mandatorySubjectsForSubjectId) {
        this.mandatorySubjectsForSubjectId = mandatorySubjectsForSubjectId;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="subject")
    public Set<SubjectInSemester> getSubjectInSemesters() {
        return this.subjectInSemesters;
    }
    
    public void setSubjectInSemesters(Set<SubjectInSemester> subjectInSemesters) {
        this.subjectInSemesters = subjectInSemesters;
    }




}


