package com.aqif.schwarzit.source;

import com.aqif.schwarzit.workbook.WBManager;
import com.aqif.schwarzit.records.BaseRecord;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.ArrayList;

abstract class WBBaseRelationSource<L extends BaseRecord> implements IRelationSource<L> {

    protected ArrayList<L> records = new ArrayList<L>();

    public WBBaseRelationSource(WBManager wbManager, int sheetNumber) {
        Sheet sheet = wbManager.getWorkbook().getSheetAt(sheetNumber);

        boolean isHeaderRowFound = false; // cache the flag.

        for(Row row: sheet) {
            if(!isHeaderRowFound) { // We skip rows unless we have landed the header row. We will start reading records from this row forward.
                isHeaderRowFound = isHeaderRowFound(row);
                continue;
            }
            addRecord(row);
        }
    }

    protected abstract boolean isHeaderRowFound(Row row);
    protected abstract void addRecord(Row row);
}
