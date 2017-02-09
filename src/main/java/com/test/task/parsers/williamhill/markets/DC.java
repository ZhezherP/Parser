package com.test.task.parsers.williamhill.markets;

import com.test.task.parsers.ParserToLoad;
import com.test.task.parsers.williamhill.MarketParser;

@ParserToLoad
public class DC extends MarketParser {

    private String key = "DC";
    private String[] blockTitles = {"Double Chance"};
    private String[] betVariations = {"1X", "X2", "12"};

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
