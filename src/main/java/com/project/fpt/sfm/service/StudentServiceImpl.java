package com.project.fpt.sfm.service;

import com.project.fpt.sfm.common.Constant;
import com.project.fpt.sfm.common.Utils;
import com.project.fpt.sfm.entities.*;
import com.project.fpt.sfm.processexcel.development.model.StudentModel;
import com.project.fpt.sfm.processexcel.model.StudentTemplate;
import com.project.fpt.sfm.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by Khắc Vỹ on 10/13/2015.
 */
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    ScheduledTaskExample example;
    @Autowired
    TaskScheduler taskScheduler;
    @Autowired
    AdminService adminService;
    @Autowired
    CourseService courseService;

    @Autowired
    MajorRepo majorRepo;
    @Autowired
    FinancialTypeRepo financialTypeRepo;
    @Autowired
    StudyStageRepo studyStageRepo;
    @Autowired
    UserRepo userRepo;
    @Autowired
    TermRepo termRepo;
    @Autowired
    RoleRepo roleRepo;
    @Autowired
    UserRoleRepo userRoleRepo;
    @Autowired
    StudentRepo studentRepo;
    @Autowired
    ClassRepo classRepo;
    @Autowired
    SemesterRepo semesterRepo;
    @Autowired
    SubInSemesterRepo subInSemesterRepo;
    @Autowired
    CourseRepo courseRepo;
    @Autowired
    TuitionPlanRepo tuitionPlanRepo;
    @Autowired
    RetakeSubjectPaymentRepo retakeSubjectPaymentRepo;
    /**
     * Save Student entity from Student Template
     *
     * @param stuTem
     */
    @Override
    public void addNewStudent(StudentTemplate stuTem) {
        Student student = new Student();
        student.setFullName(stuTem.getStudentName());
        student.setStudentCode(stuTem.getStudentCode());
        student.setNote(stuTem.getNote());
        student.setTerm(stuTem.getTerm());
        student.setDateOfBirth(new Date());
        student.setEmail(stuTem.getStudentCode() + "@fpt.edu.vn");
        student.setSsn(UUID.randomUUID().toString().substring(0, 9));
        student.setPhoneNumber("-");
        student.setAddress("-");
        student.setSubMajor(stuTem.getSubMajor());
        student.setNarrowSpecialization("-");
        student.setParentEmail("-");
        student.setParentPhone("-");
        /**
         * Major
         */
        Major major = majorRepo.findByMajorCode(stuTem.getMajor());
        student.setMajor(major);
        /**
         * Financial Type
         */
        FinancialType financialType;
        if (stuTem.getFinanceType().equals("-") || stuTem.getFinanceType().equals("")) {
            financialType = financialTypeRepo.findByFinancialTypeName("BT");
        } else if (stuTem.getFinanceType().equals("NVD")) {
            financialType = financialTypeRepo.findByFinancialTypeName("NVD");
        } else {
            String[] financial = stuTem.getFinanceType().split("-");
            if (financial.length > 1) {
                int rate = Integer.parseInt(financial[1]);
                financialType = financialTypeRepo.findByFinancialTypeNameAndFinancialRate(financial[0], rate);
            } else {
                financialType = financialTypeRepo.findByFinancialTypeName("BT");
            }
        }
        student.setFinancialType(financialType);
        /**
         * Study Stage
         */
        StudyStage studyStage = null;
        if (stuTem.getEnglishLevel().equals("N/A")) {
            studyStage = studyStageRepo.findByStageCode("SEM1");
        } else {
            studyStage = studyStageRepo.findByStageCode(stuTem.getEnglishLevel());
        }

        student.setStatus("");
        /**
         * Create fake user and save student
         */
        User user = new User();
        user.setUsername(stuTem.getStudentCode());
        user.setPassword(Utils.generatePassword());
        userRepo.save(user);
        student.setUser(user);
        studentRepo.save(student);
        System.out.println(student);
        /**
         * Create new Class
         */
        //Class
        Clazz clazz = null;
        if (stuTem.getClazz().equals("-") || stuTem.getClazz().contains("Chờ lớp")) {
            clazz = classRepo.findByClassName("CHUA_XEP_LOP");
        } else {
            clazz = classRepo.findByClassName(stuTem.getClazz());
            if (clazz == null) {
                clazz = new Clazz();
                clazz.setClassName(stuTem.getClazz());
                classRepo.save(clazz);
            }

        }
        /**
         * Create Course
         */
        Term term = adminService.getCurrentTerm();
        Semester semester = semesterRepo.findByTermAndMajorAndStudyStage(term, major, studyStage);
        List<SubjectInSemester> listSubInSem = subInSemesterRepo.findBySemester(semester);
        System.out.println("List Sub in sem of " + student.getFullName() + " : " + listSubInSem.size());
        Course course;
        for (SubjectInSemester subInSem : listSubInSem) {
            course = new Course();
            course.setStudent(student);
            course.setClazz(clazz);
            course.setSubjectInSemester(subInSem);
            course.setMark(-1);
            course.setIsPass(false);
            course.setIsRetake(false);
            course.setIsActive(false);

            courseRepo.save(course);
        }

        /**
         * Semester Tuition Plan
         */
        tuitionPlanForNewStudent(student, studyStage, term);
        /**
         * Failed Course Tuition Plan
         */
        retakeCourseTuitionPlan(student, term);

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addNewStudent(StudentModel model) {
        Student student = studentRepo.findByStudentCode(model.getStudentCode());
        if (student != null) {
            //Update Student
        } else {
            student = new Student();
            student.setFullName(model.getStudentName());
            //handle unformat student code
            String studentCode = model.getStudentCode();
            if(studentCode.contains(".")){
                studentCode = studentCode.substring(0, studentCode.lastIndexOf("."));
            }
            student.setStudentCode(studentCode);
            student.setNote(model.getNote());
            student.setStatus(Constant.STUDENT_STATUS_WAITING);
            student.setTerm(model.getAcademicYear());
            student.setDateOfBirth(new Date());
            student.setEmail(model.getStudentCode() + "@fpt.edu.vn");
            student.setSsn(UUID.randomUUID().toString().substring(0, 9));
            student.setPhoneNumber(Constant.DEFAULT_STRING_VALUE);
            student.setAddress(Constant.DEFAULT_STRING_VALUE);
            student.setSubMajor(model.getSubMajor());
            student.setNarrowSpecialization(model.getNarrowSpecialization());
            student.setParentEmail(Constant.DEFAULT_STRING_VALUE);
            student.setParentPhone(Constant.DEFAULT_STRING_VALUE);
            /**
             * Major
             */
            String majorCode = model.getMajor();
            Major major;
            if ("".equals(majorCode) || majorCode.equals("-") || majorCode.length() > 10) {
                major = majorRepo.findByMajorCode(Constant.DEFAULT_MISSING_DATA);
            } else {
                major = majorRepo.findByMajorCode(majorCode);
            }
            student.setMajor(major);
            /**
             * Financial Type
             */
            FinancialType financialType;
            String fType = model.getFinancialType();
            if (fType.equals(Constant.DEFAULT_STRING_VALUE) || model.getFinancialType().equals("")) {
                financialType = financialTypeRepo.findByFinancialTypeName(Constant.FINANCE_TYPE_NORMAL);
            } else if (fType.equals(Constant.FINANCE_TYPE_NVD)) {
                financialType = financialTypeRepo.findByFinancialTypeName(Constant.FINANCE_TYPE_NVD);
            } else {
                String[] financial = fType.split("-");
                if (financial.length > 1) {
                    int rate = Integer.parseInt(financial[1]);
                    financialType = financialTypeRepo.findByFinancialTypeNameAndFinancialRate(financial[0], rate);
                } else {
                    financialType = financialTypeRepo.findByFinancialTypeName(Constant.FINANCE_TYPE_NORMAL);
                }
            }
            student.setFinancialType(financialType);
            /**
             * Study Stage
             */
            String studyStageCode = model.getStartEnglishLevel();
            if (studyStageCode.equals(Constant.DEFAULT_STRING_VALUE)) {
                student.setCurrentStudyStage("SEM1");
            } else {
                if(studyStageCode.length() > 10){
                    student.setCurrentStudyStage(Constant.DEFAULT_MISSING_DATA);
                }else{
                    student.setCurrentStudyStage(studyStageCode);
                }
            }
            /**
             * Create fake user and save student
             */
            User user = new User();
            user.setUsername(model.getStudentCode());
            user.setPassword("123456");
            userRepo.save(user);
            /**
             * User Role
             */
            UserRole userRole = new UserRole();
            userRole.setUser(user);
            Role role = roleRepo.findByRoleName(Constant.ROLE_STUDENT);
            userRole.setRole(role);
            userRoleRepo.save(userRole);

            student.setUser(user);
            studentRepo.save(student);
            /**
             * Create new Class
             */
            //Class
            Clazz clazz;
            if (model.getClazz().equals(Constant.DEFAULT_STRING_VALUE)) {
                clazz = classRepo.findByClassName("CHUA_XEP_LOP");
            } else {
                clazz = classRepo.findByClassName(model.getClazz());
                if (clazz == null) {
                    clazz = new Clazz();
                    clazz.setClassName(model.getClazz());
                    classRepo.save(clazz);
                }

            }
            /**
             * Tuition Plan for new Student
             */
            StudyStage studyStage = studyStageRepo.findByStageCode(studyStageCode);
            Term term = adminService.getCurrentTerm();
            tuitionPlanForNewStudent(student, studyStage, term);
        }

        return true;
    }


    @Override
    public void tuitionPlanForNewStudent(Student student, StudyStage stage, Term term) {
        Semester semester = semesterRepo.findByTermAndMajorAndStudyStage(term, student.getMajor(), stage);
        if (semester != null) {
            TuitionPlan semPlan = new TuitionPlan();
            semPlan.setStudent(student);
            semPlan.setTerm(term);
            semPlan.setTuitionType(Constant.PLAN_TUITION_TYPE_SEMESTER);
            semPlan.setTuitionName("ĐÓNG TIỀN HỌC PHÍ CHO KỲ " + term.getTermName());
            int totalTuition = semester.getStageTuitionUsd();
            int actualTuition;
            int financialRate = student.getFinancialType().getFinancialRate();
            if (financialRate == 100) {
                actualTuition = 0;
            } else if (financialRate == 0) {
                actualTuition = totalTuition;
            } else {
                actualTuition = (int) ((1 - (float) financialRate / 100) * totalTuition);
            }
            semPlan.setTuition(actualTuition);

            tuitionPlanRepo.save(semPlan);
        }
    }

    @Override
    public void retakeCourseTuitionPlan(Student student, Term term) {
        List<Course> listFailedCourse = courseService.getAllFailedCourseOfStudent(student);
        System.out.println("LIST FAILED SIZE : " + listFailedCourse.size());
        if (listFailedCourse.size() > 0) {
            TuitionPlan plan;
            Subject subject;
            for (Course course : listFailedCourse) {
                subject = course.getSubjectInSemester().getSubject();

                plan = new TuitionPlan();
                plan.setTerm(term);
                plan.setStudent(student);
                plan.setTuitionType(Constant.PLAN_TUITION_TYPE_SUBJECT);
                plan.setTuitionName(subject.getSubjectCode() + "-" + subject.getSubjectNameE());
                plan.setTuition(course.getSubjectInSemester().getNumberOfCredit() * Constant.COURSE_CREDIT_FEE_USD);

                tuitionPlanRepo.save(plan);
            }
        }
    }

    @Override

    public List<RetakeSubjectPayment> getListResitCourse(int studentId) {
        return retakeSubjectPaymentRepo.findByStudentStudentId(studentId);
    }

    public List<Student> getListNewStudent() {
        return studentRepo.findByStatus(Constant.STUDENT_STATUS_WAITING);
    }

    @Override
    public int updateBasicInfo(Student student) {
        return studentRepo.updateBasicInfo(student.getStudentId(), student.getPhoneNumber(), student.getParentPhone(), student.getParentEmail(), student.getAddress());
    }

    @Override
    public Student findByUsername(String username) throws EntityNotFoundException {
        User user = userRepo.findByUsername(username);
        if (user == null) {
            throw new EntityNotFoundException();
        }
        Student student = studentRepo.findOne(user.getUserId());
        return student;
    }
}
