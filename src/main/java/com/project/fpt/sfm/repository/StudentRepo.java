package com.project.fpt.sfm.repository;

import com.project.fpt.sfm.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

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
}
