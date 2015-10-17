package com.project.fpt.sfm.common;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Khắc Vỹ on 10/3/2015.
 */
public class Constant {
    /**
     * ROLE NAME
     */
    public static final String ROLE_ADMIN = "ADMIN";
    public static final String ROLE_MANAGER = "MANAGER";
    public static final String ROLE_STAFF = "STAFF";
    public static final String ROLE_STUDENT = "STUDENT";
    public static final String ROLE_SCHOLARSHIP_STUDENT = "SCHOLARSHIP_STUDENT";
    public static final String ROLE_LOANS_CREDIT_STUDENT = "LOANSCREDIT_STUDENT";
    public static final String ROLE_INVESTING_STUDENT = "INVESTING_STUDENT";

    /**
     * FINANCE TYPE
     */
    public static final String FINANCE_TYPE_NORMAL = "BT";
    public static final String FINANCE_TYPE_NVD = "NVD";
    public static final String FINANCE_TYPE_SCHOLARSHIP = "HB";
    public static final String FINANCE_TYPE_LOANS_CREDIT = "TD";
    public static final String FINANCE_TYPE_INVESTING = "DT";

    /**
     * Max Page Size
     */
    public static final int MAX_PAGE_SIZE = 10;
    /**
     *
     */
    public static int COURSE_CREDIT_FEE_USD = 50;

    public static int MONEY_CHECK = 22440;

    public static int MONEY_RANGE = 50000;

    /**
     * TERM
     */
    public static String TERM_SPRING = "SPRING";
    public static int TERM_SPING_START_DATE = 5;
    public static int TERM_SPING_START_MONTH = Calendar.JANUARY;
    public static int TERM_SPING_END_DATE = 5;
    public static int TERM_SPING_END_MONTH = Calendar.MAY;
    public static String TERM_SUMMER = "SUMMER";
    public static int TERM_SUMMER_START_DATE = 5;
    public static int TERM_SUMMER_START_MONTH = Calendar.MAY;
    public static int TERM_SUMMER_END_DATE = 5;
    public static int TERM_SUMMER_END_MONTH = Calendar.SEPTEMBER;
    public static String TERM_FALL = "FALL";
    public static int TERM_FALL_START_DATE = 5;
    public static int TERM_FALL_START_MONTH =  Calendar.SEPTEMBER;
    public static int TERM_FALL_END_DATE = 5;
    public static int TERM_FALL_END_MONTH =  Calendar.DECEMBER;

    /**
     * PLAN TUITION TYPE
     */
    public static String PLAN_TUITION_TYPE_SEMESTER = "PLAN_TUITION_SEMESTER";
    public static String PLAN_TUITION_TYPE_SUBJECT = "PLAN_TUITION_SUBJECT";


    public static String DEFAULT_STRING_VALUE = "-";

    public static String DEFAULT_MISSING_DATA = "N/A";

    /**
     * Student Status
     */
    public static final String STUDENT_STATUS_WAITING = "CHƯA NHẬP HỌC";
    public static final String STUDENT_STATUS_STUDYING = "HỌC ĐI";
    public static final String STUDENT_STATUS_RETAKE = "HỌC LẠI";
    public static final String STUDENT_STATUS_STOP = "TẠM DỪNG";



    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

}
