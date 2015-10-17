package com.project.fpt.sfm.repository;

import com.project.fpt.sfm.entities.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.ReportAsSingleViolation;

/**
 * Created by Khắc Vỹ on 10/14/2015.
 */
@Repository
public interface UserRoleRepo extends JpaRepository<UserRole, Integer>{
}
