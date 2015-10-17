package com.project.fpt.sfm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Khắc Vỹ on 10/12/2015.
 */
@Service
public class StaffServiceImpl implements StaffService{
    @Autowired
    SemesterService semesterService;


}
