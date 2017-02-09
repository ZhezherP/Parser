package com.test.task.parsers.williamhill;

import com.test.task.beans.*;
import com.test.task.parsers.Parser;
import com.test.task.parsers.ParserToLoad;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.reflections.Reflections;

import java.io.IOException;
import java.util.*;

public class WilliamhillParser implements Parser {

    private static final String MARKET_PARSERS_PACKAGE = "com.test.task.parsers.williamhill.markets";
    private final String PAGE_URL = "http://sports.williamhill.com/bet/en-gb/betting/e/10582329/Newport+v+Doncaster.html";
    private List<MarketParser> marketParsers;

    public WilliamhillParser() {
        initializeMarketParsers();
    }

    @Override
    public Sport parse() {
        Sport sport = null;
        try {
            Document document = Jsoup.connect(PAGE_URL).cookie("cust_prefs", "en|DECIMAL").get();
            Element headEl = document.getElementById("contentHead");

            Event event = parseEvent(headEl);

            Elements tablesEl = document.getElementById("allMarketsTab").select("table");
            Map<String, List<Outcome>> markets = parseMarkets(tablesEl);
            event.setMarkets(markets);

            Element breadcrumb = headEl.getElementById("breadcrumb");
            String sportName = breadcrumb.child(1).text();
            String leagueName = breadcrumb.child(3).text();

            League league = new League(leagueName, Collections.singletonList(event));
            sport = new Sport(sportName, Collections.singletonList(league));

        } catch (IOException e) {
            System.out.println("Can't open url: " + PAGE_URL);
            e.printStackTrace();
        }

        return sport;
    }

    private Event parseEvent(Element headEl) {
        Event event = new Event();
        String title = headEl.getElementsByTag("h2").first().text();
        String[] sides = title.split(" - ")[0].split(" v ");
        event.setHome(sides[0]);
        event.setAway(sides[1]);

        String dateTitle = headEl.getElementById("eventDetailsHeader").getElementsByTag("span").first().text();
        String date = dateTitle.split(" : ")[1];
        event.setStartedAt(date);

        return event;
    }

    private Map<String, List<Outcome>> parseMarkets(Elements tablesEl) {
        Map<String, List<Outcome>> markets = new HashMap<>();
        for (MarketParser marketParser : marketParsers) {
            List<Outcome> outcomes = marketParser.findAndParseOutcomes(tablesEl);
            markets.put(marketParser.getKey(), outcomes);
        }

        return markets;
    }

    private void initializeMarketParsers() {
        marketParsers = new ArrayList<>();
        Reflections reflections = new Reflections(MARKET_PARSERS_PACKAGE);

        reflections.getTypesAnnotatedWith(ParserToLoad.class).forEach((clazz) -> {
            try {
                MarketParser parser = (MarketParser) Class.forName(clazz.getName()).newInstance();
                marketParsers.add(parser);
            } catch (ReflectiveOperationException e) {
                System.out.println("Can't load market parser!");
                e.printStackTrace();
            }
        });
    }
}
