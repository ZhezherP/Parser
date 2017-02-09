package com.test.task.parsers.williamhill.markets;

import com.test.task.parsers.ParserToLoad;
import com.test.task.parsers.williamhill.MarketParser;

@ParserToLoad
public class BTS extends MarketParser{

    private String key = "BTS";
    private String[] blockTitles = {"Both Teams To Score"};
    private String[] betVariations = {"Y", "N"};

    @Override
    public String getKey() {
        return key;
    }

    @Override
    protected String[] getBlockTitles() {
        return blockTitles;
    }

    @Override
    protected String[] getBetVariations() {
        return betVariations;
    }
}
