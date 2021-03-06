package com.project.fpt.sfm.entities;
// Generated Oct 12, 2015 4:18:54 PM by Hibernate Tools 4.3.1


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

/**
 * StudyStage generated by hbm2java
 */
@Entity
@Table(name="study_stage"
    ,catalog="sfm"
)
public class StudyStage  implements java.io.Serializable {


     private Integer studyStageId;
     private String stageCode;
     private String stageName;
     private int order;
     private String note;
     private boolean isActive;
     private Set<Semester> semesters = new HashSet<Semester>(0);

    public StudyStage() {
    }

	
    public StudyStage(String stageCode, String stageName, int order, boolean isActive) {
        this.stageCode = stageCode;
        this.stageName = stageName;
        this.order = order;
        this.isActive = isActive;
    }
    public StudyStage(String stageCode, String stageName, int order, String note, boolean isActive, Set<Semester> semesters) {
       this.stageCode = stageCode;
       this.stageName = stageName;
       this.order = order;
       this.note = note;
       this.isActive = isActive;
       this.semesters = semesters;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="StudyStageID", unique=true, nullable=false)
    public Integer getStudyStageId() {
        return this.studyStageId;
    }
    
    public void setStudyStageId(Integer studyStageId) {
        this.studyStageId = studyStageId;
    }

    
    @Column(name="StageCode", nullable=false, length=15)
    public String getStageCode() {
        return this.stageCode;
    }
    
    public void setStageCode(String stageCode) {
        this.stageCode = stageCode;
    }

    
    @Column(name="StageName", nullable=false, length=45)
    public String getStageName() {
        return this.stageName;
    }
    
    public void setStageName(String stageName) {
        this.stageName = stageName;
    }

    
    @Column(name="Order", nullable=false)
    public int getOrder() {
        return this.order;
    }
    
    public void setOrder(int order) {
        this.order = order;
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

@OneToMany(fetch=FetchType.LAZY, mappedBy="studyStage")
    public Set<Semester> getSemesters() {
        return this.semesters;
    }
    
    public void setSemesters(Set<Semester> semesters) {
        this.semesters = semesters;
    }




}


