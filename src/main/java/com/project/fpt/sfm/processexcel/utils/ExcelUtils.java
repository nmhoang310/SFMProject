package com.project.fpt.sfm.processexcel.utils;

import com.project.fpt.sfm.common.Constant;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.*;

/**
 * Created by Khắc Vỹ on 10/14/2015.
 */
public class ExcelUtils {
    public static String getValueOfCell(Cell cell, int type) {
        String value = Constant.DEFAULT_STRING_VALUE;
        switch (type) {
            case Cell.CELL_TYPE_STRING:
                value = cell.getStringCellValue().trim();
                break;
            case Cell.CELL_TYPE_NUMERIC:
                value = String.valueOf(cell.getNumericCellValue());
                break;
            case Cell.CELL_TYPE_BOOLEAN:
                value = String.valueOf(cell.getBooleanCellValue());
                break;
            case Cell.CELL_TYPE_FORMULA:
                value = getValueOfCell(cell, cell.getCachedFormulaResultType());
                break;
            case Cell.CELL_TYPE_BLANK:
                value = Constant.DEFAULT_STRING_VALUE;
                break;
            default:
                value = Constant.DEFAULT_STRING_VALUE;
                break;
        }
        if("".equals(value) || value == null){
            return Constant.DEFAULT_STRING_VALUE;
        }

        return value;
    }

    public static String getValueOfCell(CellValue cell) {
        String value = "";
        if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
            value = cell.getStringValue().trim();
        } else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
            value = String.valueOf(cell.getNumberValue());
        } else if (cell.getCellType() == Cell.CELL_TYPE_BLANK) {
            value = Constant.DEFAULT_STRING_VALUE;
        } else if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
            value = cell.getBooleanValue() + "";
        }

        return value;
    }


}
