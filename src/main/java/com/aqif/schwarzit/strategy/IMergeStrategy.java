package com.aqif.schwarzit.strategy;

import com.aqif.schwarzit.records.BaseRecord;
import com.aqif.schwarzit.source.IRelationSource;

import java.util.List;

public interface IMergeStrategy <U extends BaseRecord, V extends BaseRecord, W extends BaseRecord> {
    List<W> merge(IRelationSource<U> leftRelation, IRelationSource<V> rightRelation);
}
