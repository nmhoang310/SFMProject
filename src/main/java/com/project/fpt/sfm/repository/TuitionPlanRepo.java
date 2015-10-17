package com.project.fpt.sfm.repository;

import com.project.fpt.sfm.entities.Student;
import com.project.fpt.sfm.entities.Term;
import com.project.fpt.sfm.entities.TuitionPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Khắc Vỹ on 10/13/2015.
 */
@Repository
public interface TuitionPlanRepo extends JpaRepository<TuitionPlan, Integer>{
    List<TuitionPlan> findByTerm(Term term);

    List<TuitionPlan> findByTermAndStudent(Term term, Student student);
}
