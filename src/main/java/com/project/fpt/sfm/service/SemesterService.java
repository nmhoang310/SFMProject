package com.project.fpt.sfm.service;

import com.project.fpt.sfm.entities.*;

import java.util.List;

/**
 * Created by Khắc Vỹ on 10/12/2015.
 */
public interface SemesterService {
    /**
     * Get Current Term
     * @return
     */
    Term getCurrentTerm();

    /**
     * Get All Semester
     * @return
     */
    List<Semester> getListSemesterGroupByMajor(Term term);

    /**
     * Create New Term
     * @param newTerm
     * @return
     */
    boolean createNewTerm(Term newTerm);

    /**
     * Make plan for current Term
     */
    void planForTerm(Term oldTerm, Term newTerm);

    void tuitionPlanForStudent(Student student);

    /**
     * Find next Study stage for student when create new Term
     * @param term
     * @return
     */
    StudyStage findNextSemesterOfStudent(Student student,Term term);

    List<Semester> getAllSemesterInTerm();
}
