package com.aqif.schwarzit;

import com.aqif.schwarzit.records.WBJoinRecord;
import com.aqif.schwarzit.records.WBLeftRecord;
import com.aqif.schwarzit.records.WBRightRecord;
import com.aqif.schwarzit.sink.IRelationSink;
import com.aqif.schwarzit.sink.WBJoinRelationSink;
import com.aqif.schwarzit.source.IRelationSource;
import com.aqif.schwarzit.source.WBLeftRelationSource;
import com.aqif.schwarzit.source.WBRightRelationSource;
import com.aqif.schwarzit.strategy.IMergeStrategy;
import com.aqif.schwarzit.strategy.MergeStrategyFactory;
import com.aqif.schwarzit.workbook.WBManager;

import java.util.List;

public class DataMergerApplication {

    private IRelationSource<WBLeftRecord> leftRelationSource;
    private IRelationSource<WBRightRecord> rightRelationSource;
    private IRelationSink<WBJoinRecord> joinRecordSink;

    //TODO: Channel configurable properties from CL Args.
    private String sinkFilePath = Configuration.sinkFilePath;
    private String wbSrcFilePath = Configuration.sourceFilePath;
    private MergeStrategyFactory.MergeStrategyType mergeStrategyType = Configuration.mergeStrategyType;

    public DataMergerApplication() {
        initialize();
    }

    private void initialize() {
        WBManager wbManager = new WBManager(wbSrcFilePath);
        leftRelationSource = new WBLeftRelationSource(wbManager, 0);
        rightRelationSource = new WBRightRelationSource(wbManager, 0);
        joinRecordSink = new WBJoinRelationSink(wbManager, sinkFilePath, 0);
    }

    public void run() {
        long cTime = System.currentTimeMillis();
        IMergeStrategy strategy = MergeStrategyFactory.getInstance().getMergeStrategy(mergeStrategyType);
        List<WBJoinRecord> joinRecords = strategy.merge(leftRelationSource, rightRelationSource);
        System.out.println(String.format("%d millisec", System.currentTimeMillis()-cTime));

        for(WBJoinRecord joinRecord: joinRecords)
            joinRecordSink.putRecord(joinRecord);
        System.out.println("Join records: "+joinRecords.size());

        joinRecordSink.sink();
    }

}
