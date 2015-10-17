package com.project.fpt.sfm.common;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Khắc Vỹ on 10/10/2015.
 */
public class DateTimeUtils {
    public static Date parseDate(String strDate){
        DateFormat fmt = new SimpleDateFormat("dd MMMMM yyyy", Locale.US);
        Date date = null;
        try {
            date = fmt.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static Date parseDate(String strDate, String pattern){
        DateFormat fmt = new SimpleDateFormat(pattern, Locale.US);
        Date date = null;
        try {
            date = fmt.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
