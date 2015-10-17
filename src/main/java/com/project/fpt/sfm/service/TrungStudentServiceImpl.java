package com.project.fpt.sfm.service;

import com.project.fpt.sfm.entities.*;
import com.project.fpt.sfm.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TrungHD on 15/10/2015.
 */
@Service
public class TrungStudentServiceImpl implements TrungStudentService{

    @Autowired
    UserRepo userRepo;
    @Autowired
    StudentRepo studentRepo;
    @Autowired
    TermRepo termRepo;
    @Autowired
    StudyStageRepo studyStageRepo;
    @Autowired
    SemesterRepo semesterRepo;
    @Autowired
    CourseRepo courseRepo;

    @Override
    public Student viewProfile() {
        org.springframework.security.core.userdetails.User loginUser = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (loginUser != null) {
            User user = userRepo.findByUsername(loginUser.getUsername());
            Student student = studentRepo.findOne(user.getUserId());
            return student;
        }
        return null;
    }

    @Override
    public List<Course> getListCourse(Student student, Semester semester) {

        return courseRepo.findCourseByStudentAndSemester(student,semester);
    }

    @Override
    public List<Semester> getListSemester(Student student) {
        List<Semester> semesters = new ArrayList<>();
        List<Course> courses = courseRepo.findStudentCourseGroupBySemester(student);
        if (courses.size()>0){
            Semester sem;
            for (Course course : courses){
                sem = course.getSubjectInSemester().getSemester();
                semesters.add(sem);
            }
        }
        return semesters;
    }

    @Override
    public Semester findSemesterById(Integer semesterId) {
        return semesterRepo.findOne(semesterId);
    }


}
