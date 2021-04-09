package com.aqif.schwarzit.strategy;

import com.aqif.schwarzit.records.WBJoinRecord;
import com.aqif.schwarzit.records.WBLeftRecord;
import com.aqif.schwarzit.records.WBRightRecord;
import com.aqif.schwarzit.source.IRelationSource;

import java.util.ArrayList;
import java.util.Iterator;
//30031 matches

public class CrossJoinMergeStrategy implements IMergeStrategy<WBLeftRecord, WBRightRecord, WBJoinRecord>{

    public ArrayList<WBJoinRecord> merge(IRelationSource<WBLeftRecord> leftRelation, IRelationSource<WBRightRecord> rightRelation) {
        int joinRecordId = 0;
        ArrayList<WBJoinRecord> joinRecords = new ArrayList<>();

        Iterator<WBRightRecord> rightRelationIterator = rightRelation.iterator();
        while (rightRelationIterator.hasNext()) { // iterator over records of right relation
            WBRightRecord wbRightRecord = rightRelationIterator.next();
            String modelNameRight = wbRightRecord.getModelName().toLowerCase();
            String descriptionRight = wbRightRecord.getDescription().toLowerCase();

            Iterator<WBLeftRecord> leftRelationIterator = leftRelation.iterator();
            while (leftRelationIterator.hasNext()) { // For each record in right relation, iterator over records of left relation.
                WBLeftRecord wbLeftRecord = leftRelationIterator.next();
                String modelNameLeft = wbLeftRecord.getModelName().toLowerCase();
                if(modelNameRight.contains(modelNameLeft) || descriptionRight.contains(modelNameLeft)) {
                    joinRecords.add(new WBJoinRecord(joinRecordId, wbLeftRecord, wbRightRecord));
                    break;
                }
            }
            joinRecordId++;
        }
        return joinRecords;
    }

}
