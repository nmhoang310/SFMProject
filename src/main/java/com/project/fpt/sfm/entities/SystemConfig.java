package com.project.fpt.sfm.entities;
// Generated Oct 12, 2015 2:10:19 AM by Hibernate Tools 4.3.1


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * SystemConfig generated by hbm2java
 */
@Entity
@Table(name="system_config"
    ,catalog="sfm"
)
public class SystemConfig  implements java.io.Serializable {


     private String key;
     private String value;
     private Date dateCreated;
     private Date dateUpdated;

    public SystemConfig() {
    }

	
    public SystemConfig(String key, String value) {
        this.key = key;
        this.value = value;
    }
    public SystemConfig(String key, String value, Date dateCreated, Date dateUpdated) {
       this.key = key;
       this.value = value;
       this.dateCreated = dateCreated;
       this.dateUpdated = dateUpdated;
    }
   
     @Id 

    
    @Column(name="Key", unique=true, nullable=false, length=40)
    public String getKey() {
        return this.key;
    }
    
    public void setKey(String key) {
        this.key = key;
    }

    
    @Column(name="Value", nullable=false, length=125)
    public String getValue() {
        return this.value;
    }
    
    public void setValue(String value) {
        this.value = value;
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


