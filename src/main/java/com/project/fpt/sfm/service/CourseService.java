package com.project.fpt.sfm.service;


import com.project.fpt.sfm.entities.*;
import com.project.fpt.sfm.processexcel.development.model.CourseResultModel;
import com.project.fpt.sfm.processexcel.model.StudyResultTemplate;

import java.util.List;

/**
 * Created by Khắc Vỹ on 10/13/2015.
 */
public interface CourseService {
    /**
     * Get All Retake of a student
     * @param student
     * @return
     */
    List<Course> getAllFailedCourseOfStudent(Student student);

    List<Course> getAllCourseInSemesterGroupByClass();

    void addCourseForStudent(Student student, Clazz clazz);

    void addCourseResult(Integer clazzId, Integer subInSemId, CourseResultModel temp);
}
