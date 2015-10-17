package com.project.fpt.sfm.repository;

import com.project.fpt.sfm.entities.RetakeSubjectPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by TrungHD on 15/10/2015.
 */
@Repository
public interface RetakeSubjectPaymentRepo extends JpaRepository <RetakeSubjectPayment,Integer> {
    List<RetakeSubjectPayment> findByStudentStudentId(Integer studentId);
}
