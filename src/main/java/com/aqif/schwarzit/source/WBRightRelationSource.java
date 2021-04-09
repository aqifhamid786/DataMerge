package com.aqif.schwarzit.source;

import com.aqif.schwarzit.Helper;
import com.aqif.schwarzit.workbook.WBManager;
import com.aqif.schwarzit.records.WBRightRecord;
import org.apache.poi.ss.usermodel.Row;

import java.util.Iterator;

public class WBRightRelationSource extends WBBaseRelationSource<WBRightRecord>{


    public WBRightRelationSource(WBManager wbManager, int sheetNumber) {
        super(wbManager, sheetNumber);
        System.out.println("Right records: "+records.size());
    }

    protected boolean isHeaderRowFound(Row row) {
        return WBRightRecord.isHeaderRow(row);
    }

    protected void addRecord(Row row) {
        if(Helper.isValidId(row.getCell(WBRightRecord.COL_ID_INDEX))) // We assume that, if we have a valid record id, we have a valid record available in this particular row.
            records.add(new WBRightRecord(row));
    }

    public Iterator<WBRightRecord> iterator() {
        return records.iterator();
    }
}
