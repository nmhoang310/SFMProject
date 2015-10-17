package com.project.fpt.sfm.repository;

import com.project.fpt.sfm.entities.Major;
import com.project.fpt.sfm.entities.Semester;
import com.project.fpt.sfm.entities.StudyStage;
import com.project.fpt.sfm.entities.Term;
import org.jboss.logging.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Khắc Vỹ on 10/12/2015.
 */
@Repository
public interface SemesterRepo extends JpaRepository<Semester, Integer>{
    List<Semester> findByTerm(Term term);
    @Query(value = "SELECT u FROM Semester u where u.term = ?1 group by u.major")
    List<Semester> findByTermGroupByMajor(Term term);
    Semester findByTermAndMajorMajorIdAndStudyStageStudyStageId(Term term, Integer majorId, Integer studyStageId);
    Semester findByTermAndMajorAndStudyStage(Term term, Major major, StudyStage stage);
    List<Semester> findByMajorAndTerm(Major major,Term term);
}
