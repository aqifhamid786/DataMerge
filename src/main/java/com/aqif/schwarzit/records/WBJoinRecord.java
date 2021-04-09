package com.aqif.schwarzit.records;

import com.aqif.schwarzit.Helper;
import org.apache.poi.ss.usermodel.Row;

public class WBJoinRecord extends BaseRecord {

    public static String COL_ID = "Record ID";
    public static String COL_VENDOR = "Vendor";
    public static String COL_DESCRIPTION = "Description";
    public static String COL_MODEL = "Model Name";
    public static String COL_MODEL_T1 = "Model Name (from Table #1)";
    public static String COL_ID_T1 = "Record ID (from Table #1)";

    public static int COL_ID_INDEX = 11;
    public static int COL_VENDOR_INDEX = 12;
    public static int COL_DESCRIPTION_INDEX = 13;
    public static int COL_MODEL_INDEX = 14;
    public static int COL_MODEL_T1_INDEX = 15;
    public static int COL_ID_T1_INDEX = 16;

    private WBLeftRecord WBLeftRecord;
    private WBRightRecord WBRightRecord;

    public WBJoinRecord(int id, WBLeftRecord WBLeftRecord, WBRightRecord WBRightRecord) {
        super(id);
        this.WBLeftRecord = WBLeftRecord;
        this.WBRightRecord = WBRightRecord;
    }

    public void storeInRow(Row row) {
        Helper.forceGetCell(row, COL_ID_INDEX).setCellValue(WBRightRecord.getId());
        Helper.forceGetCell(row, COL_VENDOR_INDEX).setCellValue(WBRightRecord.getVendor());
        Helper.forceGetCell(row, COL_DESCRIPTION_INDEX).setCellValue(WBRightRecord.getDescription());
        Helper.forceGetCell(row, COL_MODEL_INDEX).setCellValue(WBRightRecord.getModelName());
        Helper.forceGetCell(row, COL_MODEL_T1_INDEX).setCellValue(WBLeftRecord.getModelName());
        Helper.forceGetCell(row, COL_ID_T1_INDEX).setCellValue(WBLeftRecord.getId());
    }
    public static boolean isHeaderRow(Row row){
        return Helper.getCellValueAsString(row.getCell(COL_ID_INDEX)).equals(COL_ID) &&
                Helper.getCellValueAsString(row.getCell(COL_VENDOR_INDEX)).equals(COL_VENDOR) &&
                Helper.getCellValueAsString(row.getCell(COL_DESCRIPTION_INDEX)).equals(COL_DESCRIPTION) &&
                Helper.getCellValueAsString(row.getCell(COL_MODEL_INDEX)).equals(COL_MODEL) &&
                Helper.getCellValueAsString(row.getCell(COL_MODEL_T1_INDEX)).equals(COL_MODEL_T1) &&
                Helper.getCellValueAsString(row.getCell(COL_ID_T1_INDEX)).equals(COL_ID_T1) ;
    }

}
