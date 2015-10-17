package com.project.fpt.sfm.service;

import com.project.fpt.sfm.entities.*;
import com.project.fpt.sfm.processexcel.development.model.StudentModel;
import com.project.fpt.sfm.processexcel.model.StudentTemplate;

import java.util.List;

/**
 * Created by TrungHD on 15/10/2015.
 */
public interface TrungStudentService {
    Student viewProfile();
    List<Course> getListCourse(Student student, Semester semester);
    List<Semester> getListSemester(Student student);
    Semester findSemesterById(Integer semesterId);

}
