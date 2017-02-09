package com.test.task.parsers.williamhill.markets;


import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ListIterator;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public abstract class MarketParserTest {

    protected static final double DELTA = 0.001;
    protected Elements elements;
    protected Element element;
    protected ListIterator iterator;

    protected void setUp() throws Exception {
        elements = mock(Elements.class);
        element = mock(Element.class);
        iterator = mock(ListIterator.class);

//        mocks for findWithChild() method
        when(elements.listIterator()).thenReturn(iterator);
        when(iterator.hasNext()).thenReturn(true);
        when(iterator.next()).thenReturn(element);
        when(element.select(anyString())).thenReturn(elements);
        when(elements.isEmpty()).thenReturn(false);
    }
}
