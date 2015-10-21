package com.project.fpt.sfm.repository;

import com.project.fpt.sfm.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Khắc Vỹ on 10/13/2015.
 */
@Repository
public interface StudentRepo extends JpaRepository<Student, Integer>{
    List<Student> findByIsActive(boolean isActive);

    @Query(value = "SELECT u FROM Student u where u.status = 'HỌC ĐI' or u.status = 'HỌC LẠI'")
    List<Student> listStudyingStudent();

    Student findByStudentCode(String studentCode);

    List<Student> findByStatus(String status);

    @Modifying
    @Transactional
    @Query("UPDATE Student SET PhoneNumber = :phoneNumber, ParentPhone = :parentPhone, ParentEmail = :parentEmail, Address = :address WHERE StudentID = :studentId")
    int updateBasicInfo(@Param("studentId") Integer studentId,
                        @Param("phoneNumber") String phoneNumber,
                        @Param("parentPhone") String parentPhone,
                        @Param("parentEmail") String parentEmail,
                        @Param("address") String address);
}
