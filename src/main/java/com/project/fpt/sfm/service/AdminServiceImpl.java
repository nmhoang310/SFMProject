package com.project.fpt.sfm.service;

import com.project.fpt.sfm.entities.*;
import com.project.fpt.sfm.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Khắc Vỹ on 10/12/2015.
 */
@Service
public class AdminServiceImpl implements AdminService{
    @Autowired
    TermRepo termRepo;
    @Autowired
    SemesterRepo semesterRepo;
    @Autowired
    SubjectRepo subjectRepo;
    @Autowired
    StudyStageRepo studyStageRepo;
    @Autowired
    MajorRepo majorRepo;

    @Override
    public Term getCurrentTerm() {
        return termRepo.findByIsCurrent(true);
    }

    /**
     * Get current semester
     *
     * @return
     */
    @Override
    public Semester getCurrentSemester() {
        return null;
    }

    /**
     * Get List StudyStage in current Term
     *
     * @return
     */
    @Override
    public List<StudyStage> getAllStudyStage() {
        return studyStageRepo.findAll();
    }

    @Override
    public List<Subject> getAllSubject() {
        return subjectRepo.findAll();
    }

    @Override
    public List<Major> getAllMajor() {
        return majorRepo.findAll();
    }

}
