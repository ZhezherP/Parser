package com.test.task.parsers.williamhill.markets;

import com.test.task.beans.Outcome;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class CSTest extends MarketParserTest {

    private CS parser;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        parser = new CS();
    }

    @Test
    public void parseOutcomes() throws Exception {
        Elements columns = mock(Elements.class);
        Element column1 = mock(Element.class);
        Element column2 = mock(Element.class);
        Element column3 = mock(Element.class);
        Element outcome1 = mock(Element.class, RETURNS_DEEP_STUBS);
        Element outcome2 = mock(Element.class);
        Element outcome3 = mock(Element.class);

        when(element.getElementsByTag("td")).thenReturn(columns);
        when(columns.get(0)).thenReturn(column1);
        when(columns.get(1)).thenReturn(column2);
        when(columns.get(2)).thenReturn(column3);
        when(column1.getElementsByTag("li")).thenReturn(new Elements(outcome1));
        when(column2.getElementsByTag("li")).thenReturn(new Elements(outcome2));
        when(column3.getElementsByTag("li")).thenReturn(new Elements(outcome3));

        mockOutcome(outcome1, "0.5", "Newport 1-0");
        mockOutcome(outcome2, "1", "Draw 0-0");
        mockOutcome(outcome3, "2", "Doncaster 1-0");

        List<Outcome> outcomes = parser.findAndParseOutcomes(elements);

        assertEquals(3, outcomes.size());
        assertOutcome(outcomes, 0, 0.5, 1.0);
        assertOutcome(outcomes, 1, 1, 0.0);
        assertOutcome(outcomes, 2, 2, 0.1);

    }

    private void mockOutcome(Element outcome, String koef, String value) {
        Element outcomeKoef = mock(Element.class);
        Element outcomeValue = mock(Element.class);
        when(outcome.getElementsByClass("eventprice")).thenReturn(new Elements(outcomeKoef));
        when(outcome.getElementsByClass("eventselection")).thenReturn(new Elements(outcomeValue));
        when(outcomeKoef.text()).thenReturn(koef);
        when(outcomeValue.text()).thenReturn(value);
    }

    private void assertOutcome(List<Outcome> outcomes, int index, double koef, double value) {
        assertEquals(koef, outcomes.get(index).getKoef(), DELTA);
        assertEquals(value, outcomes.get(index).getValue(), DELTA);
        assertEquals("CS", outcomes.get(0).getBetVariation());
    }
}