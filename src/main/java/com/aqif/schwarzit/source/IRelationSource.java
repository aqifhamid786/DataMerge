package com.aqif.schwarzit.source;

import com.aqif.schwarzit.records.BaseRecord;

import java.util.Iterator;

public interface IRelationSource<V extends BaseRecord> {
    Iterator<V> iterator();
}
