package com.project.fpt.sfm.service;

import com.project.fpt.sfm.common.Constant;
import com.project.fpt.sfm.common.DateTimeUtils;
import com.project.fpt.sfm.common.Utils;
import com.project.fpt.sfm.entities.*;
import com.project.fpt.sfm.processexcel.development.model.TuitionPaymentModel;
import com.project.fpt.sfm.processexcel.model.TuitionPaymentTemplate;
import com.project.fpt.sfm.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Khắc Vỹ on 10/14/2015.
 */
@Service
public class TuitionServiceImpl implements TuitionService {
    @Autowired
    CourseService courseService;
    @Autowired
    StudentRepo studentRepo;
    @Autowired
    StudyStageRepo studyStageRepo;
    @Autowired
    TermRepo termRepo;
    @Autowired
    SemesterRepo semesterRepo;
    @Autowired
    SubtractTuitionRepo subtractTuitionRepo;
    @Autowired
    TuitionPaymentRepo tuitionPaymentRepo;
    @Autowired
    CourseRepo courseRepo;
    @Autowired
    ClassRepo classRepo;

    @Override
    public boolean saveTuitionPayment(TuitionPaymentModel model) {
        TuitionPayment tPayment = new TuitionPayment();
        /**
         * Student
         */
        String studentCode = model.getMssv();
        if (studentCode.contains(".")) {
            studentCode = studentCode.substring(0, studentCode.lastIndexOf("."));
        }
        Student student = studentRepo.findByStudentCode(studentCode);
        tPayment.setStudent(student);

        /**
         * Study Stage - content
         */
        StudyStage studyStage = studyStageRepo.findByStageCode(model.getNoiDungNopTien());
        Term curTerm = termRepo.findByIsCurrent(true);
        Semester semester = semesterRepo.findByTermAndMajorAndStudyStage(curTerm, student.getMajor(), studyStage);
        if (semester != null) {
            tPayment.setSemester(semester);
        }
/**
 * Bank and Transfer date
 */
        tPayment.setBank(model.getNganHang());
        if (model.getNgayChuyenKhoan() != null) {
            Date transferDate = DateTimeUtils.parseDate(model.getNgayChuyenKhoan(), "dd/MM/yyyy");
            tPayment.setTransferDate(transferDate);
        }

        int totalTuition;
        int paidTuition;
        int subtractLaptop = 0;
        int subtractMath = 0;
        int subtractOther = 0;
        /**
         * Total
         */
        try {
            Double ttTuition = Double.parseDouble(model.getMucHocPhi());
            totalTuition = ttTuition.intValue();
        } catch (Exception e) {
            totalTuition = 0;
        }
        tPayment.setTotalTuition(totalTuition);
        /**
         * Paid
         */
        try {
            Double pTuition = Double.parseDouble(model.getSoTienSvChuyenKhoan());
            paidTuition = pTuition.intValue();
        } catch (Exception e) {
            paidTuition = 0;
        }
        tPayment.setPaidTuition(paidTuition);
        /**
         * Laptop
         */
        try {
            Double subLaptop = Double.parseDouble(model.getLaptop());
            subtractLaptop = subLaptop.intValue();
        } catch (Exception e) {
            subtractLaptop = 0;
        }
        /**
         * Math
         */
        try {
            Double subMath = Double.parseDouble(model.getOnToan());
            subtractMath = subMath.intValue();
        } catch (Exception e) {
            subtractMath = 0;
        }
        /**
         * Other
         */
        try {
            Double subOther = Double.parseDouble(model.getKhac());
            subtractOther = subOther.intValue();
        } catch (Exception e) {
            subtractOther = 0;
        }

        //  int totalTuition = semester.getStageTuitionUsd() * Constant.MONEY_CHECK;
        int totalSubTuition = 0;
        /**
         * Subtract tuition
         */
        FinancialType fType = student.getFinancialType();

        if (subtractLaptop > 0) {
            SubtractTuition subTuition = new SubtractTuition();
            subTuition.setSubtractTuitionName("LAPTOP");
            subTuition.setSubtractTuition(subtractLaptop);
            subTuition.setTuitionPayment(tPayment);
            tPayment.getSubtractTuitions().add(subTuition);

            totalSubTuition += subtractLaptop;

            //subtractTuitionRepo.save(subTuition);
            // tPayment.setSubtractTuition(subTuition);
        }
        if (subtractMath > 0) {
            SubtractTuition subTuition = new SubtractTuition();
            subTuition.setSubtractTuitionName("ÔN TOÁN");
            subTuition.setSubtractTuition(subtractMath);
            subTuition.setTuitionPayment(tPayment);
            tPayment.getSubtractTuitions().add(subTuition);

            totalSubTuition += subtractMath;
        }
        if (subtractOther > 0) {
            SubtractTuition subTuition = new SubtractTuition();
            subTuition.setSubtractTuitionName("KHÁC");
            subTuition.setSubtractTuition(subtractOther);
            subTuition.setTuitionPayment(tPayment);
            tPayment.getSubtractTuitions().add(subTuition);

            totalSubTuition += subtractOther;
        }

        SubtractTuition subTuition = null;
        if (fType.getFinancialTypeName().equals(Constant.FINANCE_TYPE_SCHOLARSHIP)) {
            subTuition = new SubtractTuition();
            subTuition.setSubtractTuitionName("HOC BONG " + fType.getFinancialRate() + " %");
            float factor = (float) fType.getFinancialRate() / 100;
            int sTuition = (int) (totalTuition * factor);
            subTuition.setSubtractTuition(sTuition);
            totalSubTuition += sTuition;
        } else if (fType.getFinancialTypeName().equals(Constant.FINANCE_TYPE_LOANS_CREDIT)) {
            subTuition = new SubtractTuition();
            subTuition.setSubtractTuitionName("TIN DUNG " + fType.getFinancialRate() + " %");
            float factor = (float) fType.getFinancialRate() / 100;
            int sTuition = (int) (totalTuition * factor);
            subTuition.setSubtractTuition(sTuition);
            totalSubTuition += sTuition;
        } else if (fType.getFinancialTypeName().equals(Constant.FINANCE_TYPE_INVESTING)) {
            subTuition = new SubtractTuition();
            subTuition.setSubtractTuitionName("CUNG BAN DAU TU " + fType.getFinancialRate() + " %");
            float factor = (float) fType.getFinancialRate() / 100;
            int sTuition = (int) (totalTuition * factor);
            subTuition.setSubtractTuition(sTuition);
            totalSubTuition += sTuition;
        }
        if (subTuition != null) {
            subTuition.setTuitionPayment(tPayment);
            tPayment.getSubtractTuitions().add(subTuition);
        }

        int realTotalTuition = totalTuition - totalSubTuition;
        if (realTotalTuition - paidTuition <= Constant.MONEY_RANGE) {
            tPayment.setStatus(true);
            /**
             * Change student status
             */
            if (student.getStatus().equals(Constant.STUDENT_STATUS_WAITING)) {
                student.setStatus(Constant.STUDENT_STATUS_STUDYING);
                studentRepo.save(student);
            }
            /**
             * Increase study stage by 1
             */
            student.setCurrentStudyStage(studyStage.getStageCode());

            /**
             * Enable Student course
             */
            System.out.println("Enable class for student : " + student.getFullName());
            if (!model.getLop().equals(Constant.DEFAULT_STRING_VALUE)) {
                Clazz clazz = classRepo.findByClassName(model.getLop());
                courseService.addCourseForStudent(student, clazz);
            } else {
                System.out.println("DEFAULT CLASS ROI");
            }
        } else {
            tPayment.setStatus(false);
        }

        boolean result = (tuitionPaymentRepo.save(tPayment) != null);
        if (result) {
            System.out.printf(Constant.ANSI_RED + "DA NOP HOC PHI CHO " + student.getFullName() + Constant.ANSI_RESET);
        } else {
            System.out.println(Constant.ANSI_RED + "DA CO LOI TRONG QUA TRINH NOP HOC PHI CHO " + student.getFullName() + Constant.ANSI_RESET);
        }
        return result;
    }

    @Override
    public List<Semester> getTuitionOfSemester(Major major) {
        Term term = termRepo.findByIsCurrent(true);
        return semesterRepo.findByMajorAndTerm(major, term);
    }

}
