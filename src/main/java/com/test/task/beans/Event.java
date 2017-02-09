package com.test.task.beans;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Event {

    @JsonProperty("started_at")
    private String startedAt;

    private String home;
    private String away;
    private Map<String, Object> periods;

    public Event() {
        periods = new HashMap<>();
        periods.put("time1", ImmutableMap.of());
        periods.put("time2", ImmutableMap.of());
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getAway() {
        return away;
    }

    public void setAway(String away) {
        this.away = away;
    }

    public String getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(String startedAt) {
        this.startedAt = startedAt;
    }

    public Map<String, Object> getPeriods() {
        return periods;
    }

    public void setPeriods(Map<String, Object> periods) {
        this.periods = periods;
    }

    public void setMarkets(Map<String, List<Outcome>> markets) {
        periods.put("main", markets);
    }
}
