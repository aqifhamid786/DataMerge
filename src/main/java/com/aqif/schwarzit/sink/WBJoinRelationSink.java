package com.aqif.schwarzit.sink;

import com.aqif.schwarzit.Configuration;
import com.aqif.schwarzit.workbook.WBManager;
import com.aqif.schwarzit.records.WBJoinRecord;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileOutputStream;
import java.io.IOException;

public class WBJoinRelationSink implements IRelationSink<WBJoinRecord> {

    private final Workbook workbook;
    private final Sheet sheet;

    private int firstRecordRowNum;
    private int sinkOffset = 0;
    private String sinkFilePath;

    public WBJoinRelationSink(WBManager wbManager, String sinkFilePath, int sheetNumber) {
        this.workbook = wbManager.getWorkbook();
        this.sheet = wbManager.getWorkbook().getSheetAt(sheetNumber);
        this.sinkFilePath = sinkFilePath;

        for(Row row: sheet) {
            if(WBJoinRecord.isHeaderRow(row)) { // we assume that the header for join relation exists in the sheet.
                firstRecordRowNum = row.getRowNum()+1;
                break;
            }
        }
    }

    public void putRecord(WBJoinRecord record) {
        Row row = sheet.getRow(firstRecordRowNum + sinkOffset);
        record.storeInRow(row);
        sinkOffset ++;
    }

    public void sink() {
        try {
            FileOutputStream out = new FileOutputStream(sinkFilePath);
            workbook.write(out);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
