package com.project.fpt.sfm.web.controller.student;

import com.project.fpt.sfm.entities.*;
import com.project.fpt.sfm.repository.TermRepo;
import com.project.fpt.sfm.repository.TuitionPaymentRepo;
import com.project.fpt.sfm.repository.TuitionPlanRepo;
import com.project.fpt.sfm.service.TrungStudentService;
import com.project.fpt.sfm.service.TuitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by TrungHD on 15/10/2015.
 */
@Controller
@RequestMapping("/sinh-vien")
public class StudentController extends AbstractStudentController {

    @Autowired
    TrungStudentService trungStudentService;
    @Autowired
    TuitionService tuitionService;
    @Autowired
    TermRepo termRepo;
    @Autowired
    TuitionPlanRepo tuitionPlanRepo;
    @Autowired
    TuitionPaymentRepo tuitionPaymentRepo;

    @RequestMapping("")
    public String home(Model model, HttpServletRequest request) {
        model.addAttribute("sidebar", "student/student-sidebar");
        model.addAttribute("content", "student/student-home");

        /**
         * Get current user login
         */
        Student student = trungStudentService.viewProfile();
        Term curTerm = termRepo.findByIsCurrent(true);
        List<TuitionPlan> listPlan = tuitionPlanRepo.findByTermAndStudent(curTerm, student);
        if (listPlan.size() > 0) {
            HttpSession session = request.getSession(true);
            session.setAttribute("plan", listPlan);
        }

        return "home";
    }


    @RequestMapping("/ke-hoach-nop-hoc-phi")
    public String tuitionPlan(Model model, HttpServletRequest request) {
        model.addAttribute("sidebar", "student/student-sidebar");
        model.addAttribute("content", "student/tuition-plan");

        /**
         * Get current user login
         */
        Student student = trungStudentService.viewProfile();
        Term curTerm = termRepo.findByIsCurrent(true);

        model.addAttribute("term", curTerm);
        model.addAttribute("student", student);


        return "home";
    }

    @RequestMapping(value = "/cac-ky-da-hoc")
    public String studyHistory(Model model, @RequestParam(value = "maso", defaultValue = "-1", required = false) int semesterId) {
        model.addAttribute("sidebar", "student/student-sidebar");
        model.addAttribute("content", "student/study-history");

        Student student = trungStudentService.viewProfile();
        List<Semester> listSemester = trungStudentService.getListSemester(student);

        if (listSemester.size() > 0) {
            Semester currentSemester;
            if (semesterId < 0) {
                currentSemester = listSemester.remove(0);
            } else {
                currentSemester = trungStudentService.findSemesterById(semesterId);
                if (currentSemester != null) {
                    listSemester.remove(currentSemester);
                } else {
                    currentSemester = listSemester.remove(0);
                }
            }
            model.addAttribute("currentSemester", currentSemester);
            /**
             * Get List Course
             */
            List<Course> listStudyCourse = trungStudentService.getListCourse(student, currentSemester);
            model.addAttribute("listCourse", listStudyCourse);
        }
        model.addAttribute("listStudentSemester", listSemester);

        return "home";
    }

    @RequestMapping(value = "/hoc-phi-du-kien")
    public String tuitionOverview(Model model) {
        model.addAttribute("sidebar", "student/student-sidebar");
        model.addAttribute("content", "student/tuition-overview");
        /**
         * Get current user login
         */
        Student student = trungStudentService.viewProfile();
        List<Semester> semesters = tuitionService.getTuitionOfSemester(student.getMajor());
        model.addAttribute("semesters", semesters);
        model.addAttribute("student", student);
        return "home";
    }

    @RequestMapping(value = "/cac-mon-no")
    public String resitcourse(Model model){
        model.addAttribute("sidebar", "student/student-sidebar");
        model.addAttribute("content", "student/resit-course-history");

        Student student = trungStudentService.viewProfile();
        List<RetakeSubjectPayment> retakeSubjectPayments = studentService.getListResitCourse(student.getStudentId());

        model.addAttribute("student",student);
        model.addAttribute("retakeSubjectPayments",retakeSubjectPayments);

        return "home";
    }


    @RequestMapping(value = "/hoc-phi-cac-ky")
    public String tuitionInformation(Model model) {
        model.addAttribute("sidebar", "student/student-sidebar");
        model.addAttribute("content", "student/tuition-history");
        /**
         * Get current user login
         */
        Student student = trungStudentService.viewProfile();
        List<TuitionPayment> list = tuitionPaymentRepo.findByStudent(student);

        model.addAttribute("list", list);


        return "home";
    }

}
