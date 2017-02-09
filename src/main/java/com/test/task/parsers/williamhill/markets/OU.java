package com.test.task.parsers.williamhill.markets;

import com.test.task.beans.Outcome;
import com.test.task.parsers.ParserToLoad;
import com.test.task.parsers.williamhill.MarketParser;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

@ParserToLoad
public class OU extends MarketParser {

    private String key = "OU";
    private String[] betVariations = {"TU", "TO"};
    private String blockTitleFormat = "Total Match Goals Over/Under %s Goals";
    private String[] blockTitles;
    private float min = 0.5f;
    private float max = 5.5f;
    private float range = 1f;

    public OU() {
        List<String> blockTitles = new ArrayList<>();
        while (min < max) {
            blockTitles.add(String.format(blockTitleFormat, min));
            min += range;
        }
        this.blockTitles = blockTitles.toArray(new String[blockTitles.size()]);
    }

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

    @Override
    protected void parseOutcomes(Element element, ArrayList<Outcome> destination) {
        Elements elements = element.select(".eventprice");
        String[] betVariations = getBetVariations();

        for (int i = 0; i < betVariations.length; i++) {
            Float koef = Float.valueOf(elements.get(i).text());
            Float value = Float.valueOf(elements.get(i).nextElementSibling().text().replaceAll("[a-zA-z]", ""));
            Outcome outcome = new Outcome(betVariations[i], value, koef);
            destination.add(outcome);
        }
    }
}
