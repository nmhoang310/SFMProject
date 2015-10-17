package com.project.fpt.sfm.service;

import com.project.fpt.sfm.common.Constant;
import com.project.fpt.sfm.common.Utils;
import com.project.fpt.sfm.entities.*;
import com.project.fpt.sfm.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Khắc Vỹ on 10/12/2015.
 */
@Service
public class SemesterServiceImpl implements SemesterService {
    @Autowired
    CourseService courseService;

    @Autowired
    TermRepo termRepo;
    @Autowired
    SemesterRepo semesterRepo;
    @Autowired
    SubInSemesterRepo subInSemesterRepo;
    @Autowired
    CourseRepo courseRepo;
    @Autowired
    StudentRepo studentRepo;
    @Autowired
    StudyStageRepo studyStageRepo;
    @Autowired
    TuitionPlanRepo tuitionPlanRepo;

    @Autowired
    TaskScheduler taskScheduler;
    @Autowired
    PlatformTransactionManager txMgrApp;


    /**
     * Get Current Term
     *
     * @return
     */
    @Override
    public Term getCurrentTerm() {
        return termRepo.findByIsCurrent(true);
    }

    /**
     * Get All Semester
     *
     * @return
     */
    @Override
    public List<Semester> getListSemesterGroupByMajor(Term term) {
        return semesterRepo.findByTermGroupByMajor(term);
    }

    /**
     * Create New Term
     *
     * @param newTerm
     * @return
     */
    @Override
    public boolean createNewTerm(Term newTerm) {
        Term curTerm = getCurrentTerm();
        curTerm.setIsCurrent(false);
        curTerm.setDateUpdated(new Date());
        /**
         * Update current Term
         */
        Term svCurTerm = termRepo.save(curTerm);
        /**
         * Save new term
         */
        Term svTerm = termRepo.save(newTerm);
        /**
         * Construct new Semester
         */
        planForTerm(svCurTerm, svTerm);
        /**
         * Tuition Plan
         */
        makeTuitionPlan();

        return svTerm != null;
    }

    private void makeTuitionPlan() {
        List<Student> listStudent = studentRepo.listStudyingStudent();
        if(listStudent.size() > 0){
            for(Student student : listStudent){
                tuitionPlanForStudent(student);
            }
        }
    }



    /**
     * Make plan for current Term
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void planForTerm(Term oldTerm, Term newTerm) {
        List<Semester> listOldSem = semesterRepo.findByTerm(oldTerm);
        List<SubjectInSemester> listSubInSem = subInSemesterRepo.findBySemesterTerm(oldTerm);
        /**
         * Iterator passing list semester
         */
        List<Semester> listNewSem = new ArrayList<>();
        Semester newSem;
        for (Semester sem : listOldSem) {
            newSem = new Semester();
            newSem.setTerm(newTerm);
            newSem.setMajor(sem.getMajor());
            newSem.setStudyStage(sem.getStudyStage());
            newSem.setStageTuitionUsd(sem.getStageTuitionUsd());
            semesterRepo.save(newSem);
            listNewSem.add(newSem);
        }
        /**
         * Loop passing List SubInSem
         */
        SubjectInSemester newSubInSem;
        for (SubjectInSemester item : listSubInSem) {
            newSubInSem = new SubjectInSemester();
            newSubInSem.setSubject(item.getSubject());
            newSubInSem.setNumberOfCredit(item.getNumberOfCredit());
            newSubInSem.setHalf(item.getHalf());
            newSubInSem.setNote(item.getNote());

            for (Semester semItem : listNewSem) {
                if (semItem.getMajor().getMajorCode().equals(item.getSemester().getMajor().getMajorCode()) && semItem.getStudyStage().getStageCode().equals(item.getSemester().getStudyStage().getStageCode())) {
                    newSubInSem.setSemester(semItem);
                }
            }
            subInSemesterRepo.save(newSubInSem);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void tuitionPlanForStudent(Student student) {
        /**
         * Tuition Plan
         */
        Term term = termRepo.findByIsCurrent(true);
        String curStudyStage = student.getCurrentStudyStage();
        StudyStage studyStage = studyStageRepo.findByStageCode(curStudyStage);
        StudyStage nextStudyStage = studyStageRepo.findByOrder(studyStage.getOrder() + 1);
        if(nextStudyStage != null){
            Semester semester = semesterRepo.findByTermAndMajorAndStudyStage(term, student.getMajor(), studyStage);
            TuitionPlan semPlan = new TuitionPlan();
            semPlan.setStudent(student);
            semPlan.setTerm(term);
            semPlan.setTuitionType(Constant.PLAN_TUITION_TYPE_SEMESTER);
            semPlan.setTuitionName("ĐÓNG TIỀN HỌC PHÍ CHO KỲ " + term.getTermName());
            int totalTuition = semester.getStageTuitionUsd();
            int actualTuition;
            int financialRate = student.getFinancialType().getFinancialRate();
            if(financialRate == 100){
                actualTuition = 0;
            }else if(financialRate == 0){
                actualTuition = totalTuition;
            }else{
                actualTuition = (int)((1 - (float) financialRate / 100) * totalTuition);
            }
            System.out.println(Constant.ANSI_RED + "ACTUAL TUITION : " + actualTuition + Constant.ANSI_RESET);
            tuitionPlanRepo.save(semPlan);
        }

        /**
         * Retake Course Tuition
         */
        List<Course> listFailedCourse = courseService.getAllFailedCourseOfStudent(student);
        System.out.println("Student : " + student.getFullName() + " , failed : " + listFailedCourse.size());
        if(listFailedCourse.size() > 0){
            TuitionPlan plan;
            int totalTuition;
            for(Course course : listFailedCourse){
                plan = new TuitionPlan();
                plan.setStudent(student);
                plan.setTerm(term);
                plan.setTuitionType(Constant.PLAN_TUITION_TYPE_SUBJECT);
                plan.setTuitionName("ĐÓNG TIỀN HỌC LẠI CHO MÔN " + course.getSubjectInSemester().getSubject().getSubjectNameE());
                totalTuition = Utils.calculateResitTuition(course.getSubjectInSemester().getNumberOfCredit(), student.getFinancialType().getFinancialTypeName(), student.getFinancialType().getFinancialRate());
                plan.setTuition(totalTuition);

                tuitionPlanRepo.save(plan);
            }
        }


    }

    /**
     * Find next Study stage for student when create new Term
     *
     * @param term
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public StudyStage findNextSemesterOfStudent(Student student, Term term) {
        List<Course> listCourse = courseRepo.findStudentCourseGroupByStudyStage(student);
        StudyStage curStudyStage = listCourse.get(0).getSubjectInSemester().getSemester().getStudyStage();
        int order = curStudyStage.getOrder();
        StudyStage nextStudyStage = studyStageRepo.findByOrder(order + 1);
        return nextStudyStage;
    }

    @Override
    public List<Semester> getAllSemesterInTerm() {
        Term term = termRepo.findByIsCurrent(true);
        /**
         * Major
         */



        return semesterRepo.findByTerm(term);
    }

}

