package com.project.fpt.sfm.processexcel.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Khắc Vỹ on 10/5/2015.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value= ElementType.TYPE)
public @interface ExcelReport {
    String className();
    String documentName();
    String sheetName();
}
