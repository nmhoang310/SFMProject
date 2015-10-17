package com.project.fpt.sfm.web.controller.admin;

import com.project.fpt.sfm.entities.*;
import com.project.fpt.sfm.repository.MajorRepo;
import com.project.fpt.sfm.repository.SemesterRepo;
import com.project.fpt.sfm.repository.SubInSemesterRepo;
import com.project.fpt.sfm.repository.SubjectRepo;
import com.project.fpt.sfm.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Khắc Vỹ on 10/12/2015.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    AdminService adminService;
    @Autowired
    MajorRepo majorRepo;
    @Autowired
    SemesterRepo semesterRepo;
    @Autowired
    SubjectRepo subjectRepo;
    @Autowired
    SubInSemesterRepo subInSemesterRepo;


    @RequestMapping("")
    public String home(Model model) {
        model.addAttribute("sidebar", "admin/admin-sidebar");
        model.addAttribute("content", "admin/admin-home");

        System.out.println(adminService.getCurrentTerm().getTermName());

        return "home";
    }

    @RequestMapping("/nhap-mon-hoc-trong-ky")
    public String subjectInSemesterPage(Model model,
                                        @RequestParam(value = "hocky", defaultValue = "-1") int selectedStageId,
                                        @RequestParam(value = "chuyennganh", defaultValue = "-1") int selectedMajorId) {
        model.addAttribute("sidebar", "admin/admin-sidebar");
        model.addAttribute("content", "admin/add-subject-in-semester");

        List<StudyStage> listStage = adminService.getAllStudyStage();
        StudyStage studyStage = null;
        if (selectedStageId < 0) {
            studyStage = listStage.get(0);
        } else {
            for (StudyStage stage : listStage) {
                if (stage.getStudyStageId() == selectedStageId) {
                    studyStage = stage;
                }
            }
        }
        List<Major> listMajor = majorRepo.findAll();
        Major selectedMajor = null;
        if(selectedMajorId < 0){
            selectedMajor = listMajor.get(0);
        }else{
            for (Major major : listMajor) {
                if (major.getMajorId() == selectedMajorId) {
                    selectedMajor = major;
                }
            }
        }

        System.out.println("SelectedMajor : " + selectedMajor.getMajorCode());

        List<Subject> listSubject = adminService.getAllSubject();

        model.addAttribute("listStage", listStage);
        model.addAttribute("listSubject", listSubject);
        model.addAttribute("listMajor", listMajor);
        model.addAttribute("selectedMajor", selectedMajor);
        model.addAttribute("selectedStage", studyStage);


        return "home";
    }

    @RequestMapping(value = "/nhap-mon-hoc-trong-ky", method = RequestMethod.POST)
    public String subjectInSemester(Model model, HttpServletRequest request) {
        String[] listSubId = request.getParameterValues("subjectId");
        String stageIdParam = request.getParameter("stageId");
        String majorIdParam = request.getParameter("majorId");

        Term currentTerm = adminService.getCurrentTerm();
        Integer stageId = Integer.parseInt(stageIdParam);
        Integer majorId = Integer.parseInt(majorIdParam);

        Semester semester = semesterRepo.findByTermAndMajorMajorIdAndStudyStageStudyStageId(currentTerm, majorId, stageId);
        Subject sub;
        SubjectInSemester subInSem;
        for(int i = 0; i < listSubId.length; i++){
            sub = subjectRepo.findOne(Integer.parseInt(listSubId[i]));
            subInSem = new SubjectInSemester();
            subInSem.setSubject(sub);
            subInSem.setSemester(semester);
            subInSem.setNumberOfCredit(3);
            subInSem.setHalf(1);
            subInSem.setStartDate(semester.getTerm().getStartDate());
            subInSem.setEndDate(semester.getTerm().getEndDate());

            subInSemesterRepo.save(subInSem);
        }

        return "redirect:/admin/nhap-mon-hoc-trong-ky";
    }
}
