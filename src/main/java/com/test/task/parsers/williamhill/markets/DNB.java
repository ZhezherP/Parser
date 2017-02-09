package com.test.task.parsers.williamhill.markets;

import com.test.task.parsers.ParserToLoad;
import com.test.task.parsers.williamhill.MarketParser;

@ParserToLoad
public class DNB extends MarketParser {

    private String key = "DNB";
    private String[] blockTitles = {"Draw No Bet"};
    private String[] betVariations = {"DNB1", "DNB2"};

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
