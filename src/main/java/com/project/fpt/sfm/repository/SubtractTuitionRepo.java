package com.project.fpt.sfm.repository;

import com.project.fpt.sfm.entities.SubtractTuition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Khắc Vỹ on 10/14/2015.
 */
@Repository
public interface SubtractTuitionRepo extends JpaRepository<SubtractTuition, Integer>{
}
