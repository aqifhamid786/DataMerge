package com.aqif.schwarzit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

import static org.apache.poi.ss.usermodel.CellType.NUMERIC;

public class Helper {

    public static boolean isValidId(Cell cell) {
        return cell != null && cell.getCellType() == NUMERIC && cell.getNumericCellValue()>0;
    }

    public static String getCellValueAsString(Cell cell) {
        String value = null;
        if(cell==null || cell.getCellType()==CellType.BLANK)
            value = "";
        else if(cell.getCellType()==CellType.STRING)
            value = cell.getStringCellValue();
        else // we are assuming that cell type is either numeric or string. This function should be extended to support rest of the cell data types.
            value = Integer.toString((int) cell.getNumericCellValue()); // Currently, we are treating numeric value as integer.
        return value;
    }


    public static Cell forceGetCell(Row row, int cellNum) {
        Cell cell = row.getCell(cellNum);
        if(cell==null)
            cell = row.createCell(cellNum);
        return cell;
    }

}
