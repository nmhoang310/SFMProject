package com.project.fpt.sfm.web.controller.staff;

import com.project.fpt.sfm.processexcel.development.ExcelParser;
import com.project.fpt.sfm.processexcel.development.model.StudentModel;
import com.project.fpt.sfm.processexcel.development.model.TuitionPaymentModel;
import com.project.fpt.sfm.processexcel.model.StudentTemplate;
import com.project.fpt.sfm.processexcel.model.TuitionPaymentTemplate;
import com.project.fpt.sfm.processexcel.utils.AnnotatedExcelReport;
import com.project.fpt.sfm.service.TuitionService;
import com.project.fpt.sfm.web.response.UploadResponse;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.util.List;

/**
 * Created by Khắc Vỹ on 10/14/2015.
 */
@Controller
@RequestMapping("/nhan-vien/tai-chinh")
public class FinancialStaffController {
    @Autowired
    TuitionService tuitionService;

    @RequestMapping("")
    public String home(Model model) {
        model.addAttribute("content", "staff/staff-home");
        model.addAttribute("sidebar", "staff/staff-sidebar");

        return "home";
    }

    @RequestMapping("/nhap-danh-sach-dong-hoc-phi")
    public String financialInformationPage(Model model) {
        model.addAttribute("content", "staff/add-finance-information");
        model.addAttribute("sidebar", "staff/staff-sidebar");

        return "home";
    }

    @RequestMapping(value = "/nhap-danh-sach-dong-hoc-phi", method = RequestMethod.POST, headers = "content-type=multipart/form-data")
    @ResponseBody
    public UploadResponse financialInformation(@RequestParam("file") MultipartFile file, Model model) {
        model.addAttribute("content", "staff/add-finance-information");
        model.addAttribute("sidebar", "staff/staff-sidebar");
        UploadResponse response = new UploadResponse();
        if (!file.isEmpty()) {
            try {
                ExcelParser parser = new ExcelParser();
                List<TuitionPaymentModel> list = parser.parseListTuitionPayment(file);
                if (list.size() > 0) {
                    for (TuitionPaymentModel m : list) {
                        System.out.println(m);
                        tuitionService.saveTuitionPayment(m);
                    }
                }

                response.setResult("OK");
            } catch (InvalidFormatException e) {
                e.printStackTrace();
            }

            response.setResult("OK");
        } else {
            response.setResult("File Not Found");
        }

        return response;
    }


}
