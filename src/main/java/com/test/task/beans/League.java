package com.test.task.beans;

import java.util.List;

public class League {

    private String name;
    private List<Event> events;

    public League() {
    }

    public League(String name, List<Event> events) {
        this.name = name;
        this.events = events;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }
}
