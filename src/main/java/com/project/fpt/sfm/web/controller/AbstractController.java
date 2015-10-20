package com.project.fpt.sfm.web.controller;

import com.project.fpt.sfm.entities.User;
import com.project.fpt.sfm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Created by Khoa on 10/9/2015.
 */
public abstract class AbstractController {

    @Autowired
    protected UserService userService;

    public org.springframework.security.core.userdetails.User getLoginUser() {
        return (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public User getInternalLoginUser() {
        return userService.findByUsername(getLoginUser().getUsername());
    }
}
