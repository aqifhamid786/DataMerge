package com.aqif.schwarzit.source;

import com.aqif.schwarzit.Helper;
import com.aqif.schwarzit.workbook.WBManager;
import com.aqif.schwarzit.records.WBLeftRecord;
import org.apache.poi.ss.usermodel.Row;

import java.util.Iterator;

public class WBLeftRelationSource extends WBBaseRelationSource<WBLeftRecord>{


    public WBLeftRelationSource(WBManager wbManager, int sheetNumber) {
        super(wbManager, sheetNumber);
        System.out.println("Left Records: "+records.size());
    }

    protected boolean isHeaderRowFound(Row row) {
        return WBLeftRecord.isHeaderRow(row);
    }

    protected void addRecord(Row row) {
        if(Helper.isValidId(row.getCell(WBLeftRecord.COL_ID_INDEX))) // We assume that, if we have a valid record id, we have a valid record available in this particular row.
            records.add(new WBLeftRecord(row));
    }

    public Iterator<WBLeftRecord> iterator() {
        return records.iterator();
    }
}
