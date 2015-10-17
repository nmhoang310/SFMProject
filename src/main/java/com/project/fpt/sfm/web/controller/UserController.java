package com.project.fpt.sfm.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by Khắc Vỹ on 10/4/2015.
 */
@Controller
public class UserController {

    @RequestMapping(value = {"/","/login"})
    public String login(@RequestParam(value = "error", required = false) String error, Model model){
        model.addAttribute("error", error);
        return "login";
    }

    @RequestMapping("/error/403")
    public String accessDenied(){
        return "error/403";
    }
}
