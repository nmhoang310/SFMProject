package com.project.fpt.sfm.service;

import com.project.fpt.sfm.common.Constant;
import com.project.fpt.sfm.entities.*;
import com.project.fpt.sfm.processexcel.development.model.CourseResultModel;
import com.project.fpt.sfm.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Khắc Vỹ on 10/13/2015.
 */
@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    CourseRepo courseRepo;
    @Autowired
    TermRepo termRepo;
    @Autowired
    StudentRepo studentRepo;
    @Autowired
    ClassRepo classRepo;
    @Autowired
    SubInSemesterRepo subInSemesterRepo;
    @Autowired
    StudyStageRepo studyStageRepo;
    @Autowired
    SemesterRepo semesterRepo;

    /**
     * Get All Retake of a student
     *
     * @param student
     * @return
     */
    @Override
    public List<Course> getAllFailedCourseOfStudent(Student student) {
        List<Course> listFailedCourse = courseRepo.findByStudentAndIsPassAndIsRetake(student, false, false);
        System.out.println("Not pass and not retake : " + listFailedCourse.size());
        List<Course> listPassedRetakeCourse = courseRepo.findByStudentAndIsPassAndIsRetake(student, true, true);
        System.out.println("Pass and is retake" + listPassedRetakeCourse.size());
        List<Course> result = new ArrayList<>();
        /**
         * Khong co mon nao retake pass => k co mon nao bi retake
         */
        if(listPassedRetakeCourse.size() == 0){
            return  listFailedCourse;
        }else{
            for (Course failedCourse : listFailedCourse) {
                if (!listPassedRetakeCourse.contains(failedCourse)) {
                    result.add(failedCourse);
                }
            }
        }

        return result;
    }

    @Override
    public List<Course> getAllCourseInSemesterGroupByClass() {
        Term term = termRepo.findByIsCurrent(true);
        List<Course> listCourse = courseRepo.findCourseInTermOrderByClass(term);
        return listCourse;
    }

    @Override
    public void addCourseForStudent(Student student, Clazz clazz) {
        Term term = termRepo.findByIsCurrent(true);
        StudyStage studyStage = studyStageRepo.findByStageCode(student.getCurrentStudyStage());
        Semester semester = semesterRepo.findByTermAndMajorAndStudyStage(term, student.getMajor(), studyStage);
        if (semester != null) {
            List<SubjectInSemester> listSubInSem = subInSemesterRepo.findBySemester(semester);
            if (listSubInSem.size() > 0) {
                Course course;
                for (SubjectInSemester item : listSubInSem) {
                    course = new Course();
                    course.setStudent(student);
                    course.setClazz(clazz);
                    course.setSubjectInSemester(item);
                    course.setMark(-1);
                    course.setIsRetake(false);
                    course.setIsPass(false);

                    courseRepo.save(course);
                }
            }
        }else{
            System.out.println("Semester bi null roi");
        }
    }

    @Override
    public void addCourseResult(Integer clazzId, Integer subInSemId, CourseResultModel temp) {
        Student student = studentRepo.findByStudentCode(temp.getMssv());
        System.out.println(student);

        if (student != null) {
            Clazz clazz = classRepo.findOne(clazzId);
            if (clazz != null) {
                SubjectInSemester subInSem = subInSemesterRepo.findOne(subInSemId);
                Course course = courseRepo.findByStudentAndClazzAndSubjectInSemester(student, clazz, subInSem);
                if (course != null) {
                    course.setStudent(student);
                    course.setSubjectInSemester(subInSem);
                    course.setClazz(clazz);
                    /**
                     * Mark
                     */
                    float finalMark;
                    if (temp.getTongDiemThiLai().equals(Constant.DEFAULT_STRING_VALUE)) {
                        float totalMark = 0;
                        //Total mark
                        try {
                            totalMark = Float.parseFloat(temp.getTongDiem());
                        } catch (Exception e) {
                            totalMark = 0;
                        }
                        finalMark = totalMark;
                        if (!temp.getTrangThai().equals(Constant.DEFAULT_STRING_VALUE)) {
                            if (temp.getTrangThai().equals("Đạt")) {
                                course.setIsPass(true);
                            } else {
                                course.setIsPass(false);
                            }
                        } else {
                            course.setIsPass(false);
                        }
                    } else {
                        float retakeMark = 0;
                        try {
                            retakeMark = Float.parseFloat(temp.getTongDiemThiLai());
                        } catch (Exception e) {
                            retakeMark = 0;
                        }
                        finalMark = retakeMark;

                        if (!temp.getTrangThaiThiLai().equals(Constant.DEFAULT_STRING_VALUE)) {
                            if (temp.getTrangThaiThiLai().equals("Đạt")) {
                                course.setIsPass(true);
                            } else {
                                course.setIsPass(false);
                            }
                        } else {
                            course.setIsPass(false);
                        }
                    }
                    course.setMark(finalMark);

                    System.out.println(course);


                    courseRepo.save(course);
                }
            }
        }
    }
}
