package com.project.fpt.sfm.common;

import org.springframework.stereotype.Component;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by Khắc Vỹ on 10/6/2015.
 */
public class NumberUtils {
    public static String formatCurrency(int num){
        NumberFormat usa = NumberFormat.getNumberInstance(Locale.US);
        return usa.format(num);
    }
    public static String calculateTuition(int tuition, int rate){
        int total;
        if (rate!=0) {
            float factor = (float) rate / 100;
            total = (int) (factor * tuition);
        }else{
            total = tuition;
        }
        NumberFormat usa = NumberFormat.getNumberInstance(Locale.US);
        return usa.format(total);
    }
}
