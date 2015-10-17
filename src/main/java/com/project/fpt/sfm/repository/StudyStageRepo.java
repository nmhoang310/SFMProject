package com.project.fpt.sfm.repository;

import com.project.fpt.sfm.entities.StudyStage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Khắc Vỹ on 10/12/2015.
 */
@Repository
public interface StudyStageRepo extends JpaRepository<StudyStage, Integer>{
    StudyStage findByStageCode(String stageCode);

    StudyStage findByOrder(Integer order);
}
