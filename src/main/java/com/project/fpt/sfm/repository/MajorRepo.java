package com.project.fpt.sfm.repository;

import com.project.fpt.sfm.entities.Major;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by Khắc Vỹ on 10/12/2015.
 */
@Repository
public interface MajorRepo extends JpaRepository<Major, Integer>{
    Major findByMajorCode(String majorCode);
}
