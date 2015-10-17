package com.project.fpt.sfm.service.dto;

import java.util.Date;

/**
 * Created by Khắc Vỹ on 10/13/2015.
 */
public class TermDto {
    private String termName;
    private Date startDate;
    private Date endDate;
    private String note;

    public String getTermName() {
        return termName;
    }

    public void setTermName(String termName) {
        this.termName = termName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
