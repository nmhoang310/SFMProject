package com.project.fpt.sfm.service;

import com.project.fpt.sfm.entities.RetakeSubjectPayment;
import com.project.fpt.sfm.entities.Student;
import com.project.fpt.sfm.entities.StudyStage;
import com.project.fpt.sfm.entities.Term;
import com.project.fpt.sfm.processexcel.development.model.StudentModel;
import com.project.fpt.sfm.processexcel.model.StudentTemplate;

import javax.persistence.EntityNotFoundException;
import java.util.List;

/**
 * Created by Khắc Vỹ on 10/13/2015.
 */
public interface StudentService {
    /**
     * Save Student entity from Student Template
     * @param studentTemplate
     */
    void addNewStudent(StudentTemplate studentTemplate);

    boolean addNewStudent(StudentModel studentModel);

    /**
     * Planning Tuition
     *
     * @param student
     */
    void tuitionPlanForNewStudent(Student student, StudyStage stage, Term term);

    void retakeCourseTuitionPlan(Student student, Term term);

    List<RetakeSubjectPayment> getListResitCourse(int studentId);

    List<Student> getListNewStudent();

    int updateBasicInfo(Student student);

    Student findByUsername(String username) throws EntityNotFoundException;
}
