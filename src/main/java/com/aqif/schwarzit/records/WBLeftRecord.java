package com.aqif.schwarzit.records;

import com.aqif.schwarzit.Helper;
import org.apache.poi.ss.usermodel.Row;

public class WBLeftRecord extends BaseRecord {
    public static String COL_ID = "Record ID";
    public static String COL_VENDOR = "Vendor";
    public static String COL_MODEL = "Model Name";

    public static int COL_ID_INDEX = 1;
    public static int COL_VENDOR_INDEX = 2;
    public static int COL_MODEL_INDEX = 3;

    private String vendor;
    private String modelName;


    public WBLeftRecord(Row row) {
        super((int) row.getCell(COL_ID_INDEX).getNumericCellValue());
        this.vendor = Helper.getCellValueAsString(row.getCell(COL_VENDOR_INDEX));
        this.modelName = Helper.getCellValueAsString(row.getCell(COL_MODEL_INDEX));
    }

    public WBLeftRecord(int id, String vendor, String modelName) {
        super(id);
        this.vendor = vendor;
        this.modelName = modelName;
    }

    public String getVendor() {
        return vendor;
    }

    public String getModelName() {
        return modelName;
    }

    public static boolean isHeaderRow(Row row){
        return Helper.getCellValueAsString(row.getCell(COL_ID_INDEX)).equals(COL_ID) &&
                Helper.getCellValueAsString(row.getCell(COL_VENDOR_INDEX)).equals(COL_VENDOR) &&
                Helper.getCellValueAsString(row.getCell(COL_MODEL_INDEX)).equals(COL_MODEL);
    }

    @Override
    public String toString() {
        return "com.aqif.schwarzit.datasource.LeftRecord{" +
                "id=" + id +
                ", vendor='" + vendor +
                ", modelName='" + modelName +
                '}';
    }
}
