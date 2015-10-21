package com.project.fpt.sfm.web.controller.student;

import com.project.fpt.sfm.service.StudentService;
import com.project.fpt.sfm.web.controller.AbstractController;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Khoa on 10/19/2015.
 */
public abstract class AbstractStudentController extends AbstractController {
    @Autowired
    protected StudentService studentService;
}
