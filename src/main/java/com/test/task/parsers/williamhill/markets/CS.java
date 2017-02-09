package com.test.task.parsers.williamhill.markets;

import com.test.task.beans.Outcome;
import com.test.task.parsers.ParserToLoad;
import com.test.task.parsers.williamhill.MarketParser;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

@ParserToLoad
public class CS extends MarketParser {

    private String key = "CS";
    private String[] betVariations = {"CS"};
    private String[] blockTitles = {"Correct Score"};

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
        Elements columns = element.getElementsByTag("td");
        parseOutcomesInColumn(columns.get(0), destination, false);
        parseOutcomesInColumn(columns.get(1), destination, false);
        parseOutcomesInColumn(columns.get(2), destination, true);
    }

    private void parseOutcomesInColumn(Element column, ArrayList<Outcome> destination, boolean reverse) {
        for (Element li : column.getElementsByTag("li")) {
            Float koef = Float.valueOf(li.getElementsByClass("eventprice").first().text());
            String v = li.getElementsByClass("eventselection").first().text().replaceAll("[a-zA-z]", "").replace("-", ".");

            if (reverse) {
                v = new StringBuilder(v).reverse().toString();
            }
            Float value = Float.valueOf(v);

            destination.add(new Outcome(betVariations[0], value, koef));
        }
    }
}
