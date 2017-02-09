package com.test.task.parsers.williamhill.markets;

import com.test.task.beans.Outcome;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;


public class OneXTwoTest extends MarketParserTest {

    private OneXTwo parser;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        parser = new OneXTwo();
    }

    @Test
    public void findAndParseOutcomes() throws Exception {
//        mocks for parseOutcomes() method
        when(element.select(anyString())).thenReturn(elements);
        when(elements.get(anyInt())).thenReturn(element);
        when(element.text()).thenReturn("2.5");

        List<Outcome> outcomes = parser.findAndParseOutcomes(elements);

        verify(iterator).next();
        verify(iterator).remove();
        verify(elements, times(3)).get(anyInt());

        assertEquals(3, outcomes.size());
        assertEquals(2.5, outcomes.get(0).getKoef(), DELTA);
        assertEquals(parser.getBetVariations()[0], outcomes.get(0).getBetVariation());
    }
}