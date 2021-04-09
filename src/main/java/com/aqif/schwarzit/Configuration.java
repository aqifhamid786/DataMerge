package com.aqif.schwarzit;

import com.aqif.schwarzit.strategy.MergeStrategyFactory;

public interface Configuration {
    String sourceFilePath = "/Users/aqif/Downloads/java-assessment-test.xlsx";
    String sinkFilePath = "/Users/aqif/Downloads/out.xlsx";
    MergeStrategyFactory.MergeStrategyType mergeStrategyType = MergeStrategyFactory.MergeStrategyType.HashedNGram;
}