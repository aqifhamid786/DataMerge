package com.aqif.schwarzit.strategy;

import com.aqif.schwarzit.records.WBJoinRecord;
import com.aqif.schwarzit.records.WBLeftRecord;
import com.aqif.schwarzit.records.WBRightRecord;
import com.aqif.schwarzit.source.IRelationSource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class HashedNGramMergeStrategy implements IMergeStrategy<WBLeftRecord, WBRightRecord, WBJoinRecord>{

    private int minNGramSize = Integer.MAX_VALUE;
    private int maxNGramSize = Integer.MIN_VALUE;
    private HashMap<String, WBLeftRecord> leftRelationMap = new HashMap<>();

    //30031 matches
    public ArrayList<WBJoinRecord> merge(IRelationSource<WBLeftRecord> leftRelation, IRelationSource<WBRightRecord> rightRelation) {

        Iterator<WBRightRecord> rightRelationIterator = rightRelation.iterator();
        Iterator<WBLeftRecord> leftRelationIterator = leftRelation.iterator();
        createHashTableForLeftRelation(leftRelationIterator);

        int joinRecordId = 0;
        ArrayList<WBJoinRecord> joinRecords = new ArrayList<>();

        while (rightRelationIterator.hasNext()) {
            WBRightRecord wbRightRecord = rightRelationIterator.next();

            String documentStr = wbRightRecord.getModelName().toLowerCase();
            boolean isFound = probeNGrams(documentStr, joinRecordId, wbRightRecord, joinRecords);
            if(!isFound) {
                documentStr = wbRightRecord.getDescription().toLowerCase();
                probeNGrams(documentStr, joinRecordId, wbRightRecord, joinRecords);
            }
            joinRecordId++;
        }

        return joinRecords;
    }

    private void createHashTableForLeftRelation(Iterator<WBLeftRecord> leftRelationIterator) {
        while (leftRelationIterator.hasNext()) {
            WBLeftRecord wbLeftRecord = leftRelationIterator.next();
            String token = wbLeftRecord.getModelName().toLowerCase().trim();
            leftRelationMap.putIfAbsent(token, wbLeftRecord);

            if(token.length() < minNGramSize)
                minNGramSize = token.length();

            if(token.length() > maxNGramSize)
                maxNGramSize = token.length();
        }
    }

    private boolean probeNGrams(String documentStr, int joinRecordId, WBRightRecord wbRightRecord, ArrayList<WBJoinRecord> joinRecords) {

        boolean isFound = false;
        for(int gramSize = minNGramSize; gramSize<=maxNGramSize; gramSize++) {
            if(isFound)
                break;

            for(int tokenId=0; tokenId<documentStr.length()-gramSize+1; tokenId++){
                String token = documentStr.substring(tokenId, tokenId+gramSize);
                WBLeftRecord record = leftRelationMap.get(token);
                if(record!=null) {
                    joinRecords.add(new WBJoinRecord(joinRecordId, record, wbRightRecord));
                    isFound = true;
                    break;
                }
            }
        }
        return isFound;
    }

}
