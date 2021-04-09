package com.aqif.schwarzit.sink;

import com.aqif.schwarzit.records.BaseRecord;

public interface IRelationSink <V extends BaseRecord> {
    void putRecord(V record);
    void sink();
}
