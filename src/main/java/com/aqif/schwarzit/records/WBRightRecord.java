package com.aqif.schwarzit.records;

import com.aqif.schwarzit.Helper;
import org.apache.poi.ss.usermodel.Row;

public class WBRightRecord extends BaseRecord {
    public static String COL_ID = "Record ID";
    public static String COL_VENDOR = "Vendor";
    public static String COL_MODEL = "Model Name";
    public static String COL_DESCRIPTION = "Description";

    public static int COL_ID_INDEX = 6;
    public static int COL_VENDOR_INDEX = 7;
    public static int COL_DESCRIPTION_INDEX = 8;
    public static int COL_MODEL_INDEX = 9;

    private String vendor;
    private String modelName;
    private String description;

    public WBRightRecord(Row row) {
        super((int) row.getCell(COL_ID_INDEX).getNumericCellValue());
        this.vendor = Helper.getCellValueAsString(row.getCell(COL_VENDOR_INDEX));
        this.modelName = Helper.getCellValueAsString(row.getCell(COL_MODEL_INDEX));
        this.description = Helper.getCellValueAsString(row.getCell(COL_DESCRIPTION_INDEX));
    }

    public WBRightRecord(int id, String vendor, String description, String modelName) {
        super(id);
        this.vendor = vendor;
        this.description = description;
        this.modelName = modelName;
    }

    public String getVendor() {
        return vendor;
    }

    public String getModelName() {
        return modelName;
    }

    public String getDescription() {
        return description;
    }

    public static boolean isHeaderRow(Row row){
        return Helper.getCellValueAsString(row.getCell(COL_ID_INDEX)).equals(COL_ID) &&
                Helper.getCellValueAsString(row.getCell(COL_VENDOR_INDEX)).equals(COL_VENDOR) &&
                Helper.getCellValueAsString(row.getCell(COL_MODEL_INDEX)).equals(COL_MODEL) &&
                Helper.getCellValueAsString(row.getCell(COL_DESCRIPTION_INDEX)).equals(COL_DESCRIPTION);
    }

    @Override
    public String toString() {
        return "com.aqif.schwarzit.datasource.RightRecord{" +
                "id=" + id +
                ", vendor='" + vendor +
                ", modelName='" + modelName +
                ", description='" + description +
                '}';
    }
}
