package com.project.fpt.sfm.web.controller.staff;


import com.project.fpt.sfm.common.Utils;
import com.project.fpt.sfm.entities.*;
import com.project.fpt.sfm.processexcel.development.ExcelParser;
import com.project.fpt.sfm.processexcel.development.model.CourseResultModel;
import com.project.fpt.sfm.processexcel.development.model.StudentModel;
import com.project.fpt.sfm.processexcel.utils.AnnotatedExcelReport;
import com.project.fpt.sfm.repository.*;
import com.project.fpt.sfm.service.AdminService;
import com.project.fpt.sfm.service.ScheduledTaskExample;
import com.project.fpt.sfm.service.StudentService;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.hibernate.event.spi.SaveOrUpdateEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by Khắc Vỹ on 10/4/2015.
 */
@Controller
@RequestMapping("/nhan-vien")
public class StaffController {
    @Autowired
    SubjectRepo subjectRepo;
    @Autowired
    AdminService adminService;
    @Autowired
    StudentService studentService;

    @RequestMapping("")
    public String home(Model model) {
        model.addAttribute("content", "staff/staff-home");
        model.addAttribute("sidebar", "staff/staff-sidebar");

        return "home";
    }

    @RequestMapping("/test/subject")
    public String subject(Model model) {
        model.addAttribute("content", "staff/add-study-result");
        model.addAttribute("sidebar", "staff/staff-sidebar");

        return "home";
    }

    @Autowired
    ScheduledTaskExample example;

    @RequestMapping(value = "/test/student", method = RequestMethod.POST)
    public String importSubject(@RequestParam("file") MultipartFile file, Model model) {
        model.addAttribute("content", "staff/add-study-result");
        model.addAttribute("sidebar", "staff/staff-sidebar");

        if (!file.isEmpty()) {
          /*  ExcelParser parser = new ExcelParser();

            List<StudentModel> list = null;
            try {
                list = parser.parseStudentInformation(file);
            } catch (InvalidFormatException e) {
                e.printStackTrace();
            }
            for(StudentModel m : list){
                studentService.addNewStudent(m);
            }
            System.out.println("Size : " + list.size());*/

            Runtime.getRuntime().addShutdownHook(new Thread() {
                public void run() {
                    example.shutdowh();
                }
            });
            example.startScheduleTask();


            System.out.println("DONE IN WEB");

//            List<TuitionPaymentModel> list = parser.parseListTuitionPayment(file);

          //  List<CourseResultModel> list = parser.parseStudyResult(file);

        } else {
            model.addAttribute("error", "File not found !!!");
        }

        return "home";
    }


    @RequestMapping("/nhap-thong-tin-sinh-vien")
    public String addStudentInformation(Model model) {
        model.addAttribute("content", "staff/add-student-information");
        model.addAttribute("sidebar", "staff/staff-sidebar");

        return "home";
    }

    @RequestMapping(value = "/upload-student-information", method = RequestMethod.POST)
    public String importStudentInformation(@RequestParam("file") MultipartFile file, Model model) {
        model.addAttribute("content", "staff/add-student-information");
        model.addAttribute("sidebar", "staff/staff-sidebar");
        if (!file.isEmpty()) {
            ByteArrayInputStream is = null;
            try {
                is = new ByteArrayInputStream(file.getBytes());
                AnnotatedExcelReport report = new AnnotatedExcelReport(is);
                List<StudentModel> listStudent = report.readData("com.project.fpt.sfm.processexcel.development.model.StudentModel");

                Student student = null;
                for (StudentModel studentDto : listStudent) {
                    //System.out.println(studentDto);
                    student = toStudent(studentDto);
                    if (student == null) {
                        break;
                    }
                }


                model.addAttribute("error", "OK");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            model.addAttribute("error", "File not found !!!");
        }

        return "home";
    }

    @Autowired
    MajorRepo majorRepo;
    @Autowired
    FinancialTypeRepo financialTypeRepo;
    @Autowired
    StudyStageRepo studyStageRepo;
    @Autowired
    TermRepo termRepo;
    @Autowired
    SemesterRepo semesterRepo;
    @Autowired
    ClassRepo classRepo;
    @Autowired
    CourseRepo courseRepo;
    @Autowired
    SubInSemesterRepo subInSemesterRepo;
    @Autowired
    UserRepo userRepo;
    @Autowired
    StudentRepo studentRepo;

    private Student toStudent(StudentModel model) {
        Student student = new Student();
        student.setFullName(model.getStudentName());
        student.setStudentCode(model.getStudentCode());
        student.setNote(model.getNote());
        student.setTerm(model.getAcademicYear());
        student.setDateOfBirth(new Date());
        student.setEmail(model.getStudentCode() + "@fpt.edu.vn");
        student.setSsn(UUID.randomUUID().toString().substring(0, 9));
        student.setPhoneNumber("-");
        student.setAddress("-");
        student.setSubMajor("-");
        student.setNarrowSpecialization("-");
        student.setParentEmail("-");
        student.setParentPhone("-");
        //ChuyenNganh SE,SB
        Major major;
        if (model.getSubMajor().equals("TKDH")) {
            major = majorRepo.findByMajorCode("TKDH");
        } else if (model.getSubMajor().equals("ANATTT")) {
            major = majorRepo.findByMajorCode("IA");
        } else if (model.getSubMajor().equals("QTKD") || model.getSubMajor().equals("TCNH")) {
            major = majorRepo.findByMajorCode("SB");
        } else {
            major = majorRepo.findByMajorCode("SE");
        }
        student.setMajor(major);
        //Financial type
        FinancialType financialType;
        if (model.getFinancialType().equals("-") || model.getFinancialType().equals("")) {
            financialType = financialTypeRepo.findByFinancialTypeName("BT");
        } else if (model.getFinancialType().equals("NVD")) {
            financialType = financialTypeRepo.findByFinancialTypeName("NVD");
        } else {
            String[] financial = model.getFinancialType().split("-");
            if (financial.length > 1) {
                int rate = Integer.parseInt(financial[1]);
                financialType = financialTypeRepo.findByFinancialTypeNameAndFinancialRate(financial[0], rate);
            } else {
                financialType = financialTypeRepo.findByFinancialTypeName("BT");
            }
        }
        student.setFinancialType(financialType);
        //HOC KY
        StudyStage studyStage = null;
        if (model.getTerm().equals("PreG")) {
            return null;
        } else {
            studyStage = studyStageRepo.findByStageCode(model.getTerm());
        }

        //Student status
        student.setStatus("");
        //Create User
        User user = new User();
        user.setUsername(model.getStudentCode());
        user.setPassword(Utils.generatePassword());
        userRepo.save(user);
        student.setUser(user);
        studentRepo.save(student);

        Term term = adminService.getCurrentTerm();
        System.out.println("StudyStage : " + studyStage.getStudyStageId());
        System.out.println("Major : " + major.getMajorId());
        System.out.println("Term : " + term.getTermId());
        Semester semester = semesterRepo.findByTermAndMajorAndStudyStage(term, major, studyStage);
        List<SubjectInSemester> listSubInSem = subInSemesterRepo.findBySemester(semester);
        System.out.println("List SubInSem size : " + listSubInSem.size());
        //Class
        Clazz clazz = null;
        if (model.getClazz().equals("-") || model.getClazz().contains("Chờ lớp")) {
            clazz = classRepo.findByClassName("CHUA_XEP_LOP");
        } else {
            clazz = classRepo.findByClassName(model.getClazz());
            if (clazz == null) {
                clazz = new Clazz();
                clazz.setClassName(model.getClazz());
                classRepo.save(clazz);
            }

        }

        Course course;
        for (SubjectInSemester subInSem : listSubInSem) {
            course = new Course();
            course.setStudent(student);
            course.setClazz(clazz);
            course.setSubjectInSemester(subInSem);
            course.setMark(-1);
            course.setIsPass(false);
            course.setIsRetake(false);

            courseRepo.save(course);
        }


        return student;
    }

}
