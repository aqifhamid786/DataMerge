package com.aqif.schwarzit.strategy;

public class MergeStrategyFactory {
    public enum MergeStrategyType {
        HashedNGram,
        CrossJoin
    }

    private MergeStrategyFactory(){}

    private static MergeStrategyFactory factory;
    public static MergeStrategyFactory getInstance() {
        if(factory==null)
            factory = new MergeStrategyFactory();
        return  factory;
    }

    public IMergeStrategy getMergeStrategy(MergeStrategyType mergeStrategyType) {
        IMergeStrategy mergeStrategy = null;
        switch (mergeStrategyType) {
            case HashedNGram:
                mergeStrategy = new HashedNGramMergeStrategy();
                break;
            case CrossJoin:
                mergeStrategy = new CrossJoinMergeStrategy();
                break;
        }
        return mergeStrategy;
    }

}
