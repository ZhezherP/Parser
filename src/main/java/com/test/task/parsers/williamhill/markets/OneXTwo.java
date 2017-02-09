package com.test.task.parsers.williamhill.markets;

import com.test.task.parsers.ParserToLoad;
import com.test.task.parsers.williamhill.MarketParser;

@ParserToLoad
public class OneXTwo extends MarketParser {

    private String key = "1X2";
    private String[] blockTitles = {"90 Minutes"};
    private String[] betVariations = {"1", "X", "2"};

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
