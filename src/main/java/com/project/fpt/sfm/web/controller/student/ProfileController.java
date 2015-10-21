package com.project.fpt.sfm.web.controller.student;

import com.project.fpt.sfm.entities.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * Created by Hoang on 10/19/2015.
 */
@Controller
@RequestMapping("/sinh-vien")
public class ProfileController extends AbstractStudentController {
    @RequestMapping(value = {"/thong-tin-ca-nhan", "/thong-tin-ca-nhan/{tid}"}, method = RequestMethod.GET)
    public String viewProfile(Model model, @PathVariable Map<String, String> pathVariables) {
        model.addAttribute("sidebar", "student/student-sidebar");
        model.addAttribute("content", "student/profile/index");
        int tid = 1;
        if (pathVariables.containsKey("tid")) {
            try {
                tid = Integer.parseInt(pathVariables.get("tid"));
            } catch(Exception e) {
                tid = 1;
            }
        }
        if (tid < 1 || tid > 3) {
            tid = 1;
        }
        model.addAttribute("tabId", tid);
        Student student = studentService.findByUsername(getLoginUser().getUsername());
        model.addAttribute("student", student);
        return "home";
    }

    @RequestMapping("/updateProfile")
     public String saveProfile(Student student){
        student.setStudentId(getInternalLoginUser().getUserId());
        studentService.updateBasicInfo(student);
        return "redirect:/sinh-vien/thong-tin-ca-nhan/2";
    }

    @RequestMapping("/updatePassword")
    public String savePassword(String oldPassword, String newPassword, String confirmNewPassword){
        userService.updatePasswordByUserId(getInternalLoginUser().getUserId(), newPassword);
        return "redirect:/sinh-vien/thong-tin-ca-nhan/3";
    }
}
