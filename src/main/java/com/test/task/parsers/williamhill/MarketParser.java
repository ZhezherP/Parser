package com.test.task.parsers.williamhill;

import com.test.task.beans.Outcome;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public abstract class MarketParser {

    public abstract String getKey();

    protected abstract String[] getBlockTitles();

    protected abstract String[] getBetVariations();

    public List<Outcome> findAndParseOutcomes(Elements elements) {

        ArrayList<Outcome> outcomes = new ArrayList<>();

        for (String blockTitle : getBlockTitles()) {
            String selector = "span:matches(^" + blockTitle + "$)";
            Element element = findWithChild(elements, selector);

            if (element == null) {
                System.out.println("WARN! " + this.getClass().getSimpleName() + " parser didn't find block with title " + blockTitle);
            } else {
                parseOutcomes(element, outcomes);
            }
        }

        return outcomes;
    }

    protected void parseOutcomes(Element element, ArrayList<Outcome> destination) {
        Elements elements = element.select(".eventprice");
        String[] betVariations = getBetVariations();

        for (int i = 0; i < betVariations.length; i++) {
            Outcome outcome = new Outcome(betVariations[i], Float.valueOf(elements.get(i).text()));
            destination.add(outcome);
        }
    }

    private Element findWithChild(Elements elements, String selector) {
        ListIterator<Element> iterator = elements.listIterator();

        while (iterator.hasNext()) {
            Element currentElement = iterator.next();
            Elements results = currentElement.select(selector);

            if (!results.isEmpty()) {
                iterator.remove();
                return currentElement;
            }
        }

        return null;
    }
}
