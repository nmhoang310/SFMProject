package com.project.fpt.sfm.service;

import com.project.fpt.sfm.entities.*;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Khắc Vỹ on 10/12/2015.
 */
public interface AdminService {
    Term getCurrentTerm();
    /**
     * Get current semester
     *
     * @return
     */
    Semester getCurrentSemester();

    /**
     * Get List StudyStage in current Term
     * @return
     */
    List<StudyStage> getAllStudyStage();
    List<Subject> getAllSubject();
    List<Major> getAllMajor();
}
