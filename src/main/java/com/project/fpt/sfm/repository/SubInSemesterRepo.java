package com.project.fpt.sfm.repository;

import com.project.fpt.sfm.entities.Semester;
import com.project.fpt.sfm.entities.SubjectInSemester;
import com.project.fpt.sfm.entities.Term;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Khắc Vỹ on 10/12/2015.
 */
@Repository
public interface SubInSemesterRepo extends JpaRepository<SubjectInSemester, Integer>{
    List<SubjectInSemester> findBySemester(Semester semester);

    List<SubjectInSemester> findBySemesterTerm(Term term);
}
