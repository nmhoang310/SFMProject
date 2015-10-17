package com.project.fpt.sfm.web.controller.staff;

import com.project.fpt.sfm.common.Constant;
import com.project.fpt.sfm.common.DateTimeUtils;
import com.project.fpt.sfm.common.Utils;
import com.project.fpt.sfm.entities.Student;
import com.project.fpt.sfm.entities.Subject;
import com.project.fpt.sfm.entities.Term;
import com.project.fpt.sfm.entities.TuitionPlan;
import com.project.fpt.sfm.processexcel.development.ExcelParser;
import com.project.fpt.sfm.processexcel.development.model.StudentModel;
import com.project.fpt.sfm.processexcel.model.StudentDto;
import com.project.fpt.sfm.processexcel.model.StudentTemplate;
import com.project.fpt.sfm.processexcel.model.SubjectDto;
import com.project.fpt.sfm.processexcel.utils.AnnotatedExcelReport;
import com.project.fpt.sfm.repository.StudentRepo;
import com.project.fpt.sfm.repository.TermRepo;
import com.project.fpt.sfm.repository.TuitionPlanRepo;
import com.project.fpt.sfm.service.AdminService;
import com.project.fpt.sfm.service.StudentService;
import com.project.fpt.sfm.web.response.UploadResponse;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.concurrent.ScheduledFuture;

/**
 * Created by Khắc Vỹ on 10/12/2015.
 */
@Controller
@RequestMapping("/nhan-vien/cong-tac-sinh-vien")
public class StudentServiceStaffController {
    @Autowired
    StudentService studentService;
    @Autowired
    TaskScheduler taskScheduler;
    @Autowired
    AdminService adminService;
    @Autowired
    TuitionPlanRepo tuitionPlanRepo;

    @Autowired
    StudentRepo studentRepo;
    @Autowired
    TermRepo termRepo;

    @RequestMapping("")
    public String home(Model model) {
        model.addAttribute("content", "staff/staff-home");
        model.addAttribute("sidebar", "staff/staff-sidebar");

        return "home";
    }

    @RequestMapping("/nhap-thong-tin-sinh-vien")
    public String addStudentInformationPage(Model model) {
        model.addAttribute("content", "staff/add-student-information");
        model.addAttribute("sidebar", "staff/staff-sidebar");

        return "home";
    }

    @RequestMapping(value = "/import-thong-tin-nhap-hoc", method = RequestMethod.POST, headers = "content-type=multipart/form-data")
    @ResponseBody
    public UploadResponse importStudentEntrollmentInformation(@RequestParam("file") MultipartFile file) {
        UploadResponse response = new UploadResponse();
        if (!file.isEmpty()) {
            try {
                ExcelParser parser = new ExcelParser();
                List<StudentModel> listStudentModel = parser.parseStudentInformation(file);
                if (listStudentModel.size() > 0) {
                    for (StudentModel model : listStudentModel) {
                        if (!model.getStudentCode().equals("")) {
                            studentService.addNewStudent(model);
                        }
                    }
                }

                /**
                 * Scheduler
                 */

                taskScheduler.schedule(new Runnable() {
                    @Override
                    public void run() {
                        getDataFromDatabase();
                    }
                }, new Date(System.currentTimeMillis() + 5*1000));


                response.setResult("OK");
            } catch (InvalidFormatException e) {
                e.printStackTrace();
            }
        } else {
            response.setResult("File Not Found");
        }

        return response;
    }

    @RequestMapping("/thong-tin-sinh-vien-moi")
    public String viewNewStudent(Model model) {
        model.addAttribute("content", "staff/view-new-student");
        model.addAttribute("sidebar", "staff/staff-sidebar");

        List<Student> listStudent = studentService.getListNewStudent();
        model.addAttribute("listStudent", listStudent);

        return "home";
    }


    /**
     * Make Tuition Plan For Current Student
     */
    private void getDataFromDatabase() {
        /**
         * Get all Student is studying
         */
        System.out.println("GET ALL STUDENT");
        List<Student> listStudent = studentRepo.findByIsActive(true);
        System.out.println("Size : " + listStudent.size());
        for (Student student : listStudent) {
            makeTuitionPlanForStudent(student);
        }

    }

    public void makeTuitionPlanForStudent(Student student) {
        Term curTerm = termRepo.findByIsCurrent(true);
        List<TuitionPlan> listPlan = tuitionPlanRepo.findByTermAndStudent(curTerm, student);
        System.out.println("------------------------");
        for (TuitionPlan plan : listPlan) {
            System.out.println("Len Plan Cho : " + student.getFullName() + " Tien hoc phi la : " + plan.getTuitionName());
        }
    }
}
