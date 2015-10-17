package com.project.fpt.sfm.web.controller.staff;

import com.project.fpt.sfm.common.Constant;
import com.project.fpt.sfm.common.Utils;
import com.project.fpt.sfm.entities.*;
import com.project.fpt.sfm.processexcel.development.ExcelParser;
import com.project.fpt.sfm.processexcel.development.model.CourseResultModel;
import com.project.fpt.sfm.processexcel.development.model.StudentModel;
import com.project.fpt.sfm.processexcel.model.StudyResultTemplate;
import com.project.fpt.sfm.processexcel.utils.AnnotatedExcelReport;
import com.project.fpt.sfm.repository.*;
import com.project.fpt.sfm.service.CourseService;
import com.project.fpt.sfm.service.SemesterService;
import com.project.fpt.sfm.service.dto.TermDto;
import com.project.fpt.sfm.web.response.UploadResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.util.*;

/**
 * Created by Khắc Vỹ on 10/12/2015.
 */
@Controller
@RequestMapping("/nhan-vien/dao-tao")
public class RegistrarStaffController {
    @Autowired
    SemesterService semesterService;
    @Autowired
    CourseService courseService;
    @Autowired
    SubInSemesterRepo subInSemesterRepo;
    @Autowired
    TermRepo termRepo;
    @Autowired
    MajorRepo majorRepo;
    @Autowired
    StudyStageRepo studyStageRepo;
    @Autowired
    SemesterRepo semesterRepo;

    @RequestMapping("")
    public String home(Model model) {
        model.addAttribute("content", "staff/staff-home");
        model.addAttribute("sidebar", "staff/staff-sidebar");

        return "home";
    }

    @RequestMapping("/quan-ly-hoc-ky")
    public String manageSemester(Model model,
                                 @RequestParam(value = "termId", defaultValue = "-1") int termId,
                                 @RequestParam(value = "majorId", defaultValue = "-1") int majorId,
                                 @RequestParam(value = "stageId", defaultValue = "-1") int stageId) {
        model.addAttribute("content", "staff/semester-management");
        model.addAttribute("sidebar", "staff/staff-sidebar");

        Sort sort = new Sort(Sort.Direction.DESC, "termId");
        List<Term> terms = termRepo.findAll(sort);
        List<Major> majors = majorRepo.findAll();
        List<StudyStage> studyStages = studyStageRepo.findAll();

        model.addAttribute("terms", terms);
        model.addAttribute("majors", majors);
        model.addAttribute("stages", studyStages);

        Term curTerm;
        if(termId < 0){
            curTerm = terms.get(0);
        }else{
            curTerm = termRepo.findOne(termId);
        }
        model.addAttribute("curTerm", curTerm);

        Major curMajor;
        if(majorId < 0){
            curMajor = majors.get(0);
        }else{
            curMajor = majorRepo.findOne(majorId);
        }
        model.addAttribute("curMajor", curMajor);

        StudyStage curStage;
        if(stageId < 0){
            curStage = studyStages.get(0);
        }else{
            curStage = studyStageRepo.findOne(stageId);
        }
        model.addAttribute("curStage", curStage);


        Semester semester = semesterRepo.findByTermAndMajorAndStudyStage(curTerm, curMajor, curStage);
        List<SubjectInSemester> listSubject = subInSemesterRepo.findBySemester(semester);
        model.addAttribute("subjects", listSubject);


        return "home";
    }

    @RequestMapping(value = "/tao-hoc-ky-mau", method = RequestMethod.POST)
    @ResponseBody
    public TermDto suggestTerm() {
        Term curTerm = semesterService.getCurrentTerm();
        String curTermName = curTerm.getTermName().toUpperCase();
        Calendar cal = Calendar.getInstance();
        cal.setTime(curTerm.getStartDate());
        int year = cal.get(Calendar.YEAR);
        Date startDate = null;
        Date endDate = null;
        String nextTermName = "";
        if (curTermName.toUpperCase().contains(Constant.TERM_FALL)) {
            nextTermName = Constant.TERM_SPRING + " " + (year + 1);
            cal.set(year + 1, Constant.TERM_SPING_START_MONTH, Constant.TERM_SPING_START_DATE);
            startDate = cal.getTime();
            cal.set(year + 1, Constant.TERM_SPING_END_MONTH, Constant.TERM_SPING_END_DATE);
            endDate = cal.getTime();
        } else if (curTermName.toUpperCase().contains(Constant.TERM_SPRING)) {
            nextTermName = Constant.TERM_SUMMER + " " + year;
            cal.set(year, Constant.TERM_SUMMER_START_MONTH, Constant.TERM_SUMMER_START_DATE);
            startDate = cal.getTime();
            cal.set(year, Constant.TERM_SUMMER_END_MONTH, Constant.TERM_SUMMER_END_DATE);
            endDate = cal.getTime();
        } else if (curTermName.toUpperCase().contains(Constant.TERM_SUMMER)) {
            nextTermName = Constant.TERM_FALL + " " + year;
            cal.set(year, Constant.TERM_FALL_START_MONTH, Constant.TERM_FALL_START_DATE);
            startDate = cal.getTime();
            cal.set(year, Constant.TERM_FALL_END_MONTH, Constant.TERM_FALL_END_DATE);
            endDate = cal.getTime();
        }
        TermDto newTerm = new TermDto();
        newTerm.setTermName(nextTermName);
        newTerm.setStartDate(startDate);
        newTerm.setEndDate(endDate);
        newTerm.setNote("-");

        return newTerm;
    }

    @RequestMapping(value = "/tao-hoc-ky", method = RequestMethod.POST)
    public String createTerm(HttpServletRequest request) {
        String termName = request.getParameter("termName");
        String startDateStr = request.getParameter("startDate");
        String endDateStr = request.getParameter("endDate");
        Date startDate = Utils.getDate(startDateStr);
        Date endDate = Utils.getDate(endDateStr);

        String note = request.getParameter("note");

        Term term = new Term();
        term.setTermName(termName);
        term.setStartDate(startDate);
        term.setEndDate(endDate);
        term.setNote(note);
        term.setIsCurrent(true);

        semesterService.createNewTerm(term);

        return "redirect:/nhan-vien/dao-tao/quan-ly-hoc-ky";
    }

    @RequestMapping("/ket-qua-hoc-tap")
    public String manageClass(Model model) {
        model.addAttribute("content", "staff/add-study-result");
        model.addAttribute("sidebar", "staff/staff-sidebar");

        Map<Clazz, List<SubjectInSemester>> map = new HashMap<>();
        List<Course> listCourse = courseService.getAllCourseInSemesterGroupByClass();
        List<SubjectInSemester> tmp;
        for(Course c : listCourse){
            tmp = new ArrayList<>();
            if(map.containsKey(c.getClazz())){
                List<SubjectInSemester> listSubInSem = map.get(c.getClazz());
                listSubInSem.add(c.getSubjectInSemester());
            }else{
                tmp.add(c.getSubjectInSemester());
                map.put(c.getClazz(), tmp);
            }
        }
        model.addAttribute("mapCourse", map);

        return "home";
    }

    @RequestMapping(value= "/import-ket-qua-hoc-tap", method = RequestMethod.POST, headers = "content-type=multipart/form-data")
    @ResponseBody
    public UploadResponse addStudyResultPage(@RequestParam("file") MultipartFile file,
                                     @RequestParam("classId") int classId,
                                     @RequestParam("subInSemId") int subInSemId) {
        UploadResponse response = new UploadResponse();

        System.out.println("Class : " + classId + " / SubInSem : " + subInSemId);


        if (!file.isEmpty()) {

            ExcelParser parser = new ExcelParser();
            List<CourseResultModel> listCourseResult = parser.parseStudyResult(file);
            if(listCourseResult.size() > 0){
                for(CourseResultModel model : listCourseResult){
                    if(!model.getMssv().equals("-")){
                       courseService.addCourseResult(classId, subInSemId, model);
                        //System.out.println(model);
                    }
                }
            }
            response.setResult("OK");

        } else {
            response.setResult("File Not Found");
        }

        return response;
    }

}
