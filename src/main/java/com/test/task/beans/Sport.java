package com.test.task.beans;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Sport {

    @JsonProperty("sport")
    private String name;

    private List<League> leagues;

    public Sport() {
    }

    public Sport(String name, List<League> leagues) {
        this.name = name;
        this.leagues = leagues;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<League> getLeagues() {
        return leagues;
    }

    public void setLeagues(List<League> leagues) {
        this.leagues = leagues;
    }
}
