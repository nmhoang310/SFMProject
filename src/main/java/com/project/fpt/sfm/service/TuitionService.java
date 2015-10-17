package com.project.fpt.sfm.service;

import com.project.fpt.sfm.entities.Major;
import com.project.fpt.sfm.entities.Semester;
import com.project.fpt.sfm.entities.Student;
import com.project.fpt.sfm.processexcel.development.model.TuitionPaymentModel;

import java.util.List;

/**
 * Created by Khắc Vỹ on 10/14/2015.
 */
public interface TuitionService {
    boolean saveTuitionPayment(TuitionPaymentModel model);
    List<Semester> getTuitionOfSemester(Major major);
}
