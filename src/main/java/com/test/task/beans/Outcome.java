package com.test.task.beans;

public class Outcome {

    private String betVariation;
    private Float value;
    private Float koef;

    public Outcome() {
    }

    public Outcome(String betVariation, Float value, Float koef) {
        this.betVariation = betVariation;
        this.value = value;
        this.koef = koef;
    }

    public Outcome(String betVariation, Float koef) {
        this(betVariation, null, koef);
    }

    public String getBetVariation() {
        return betVariation;
    }

    public void setBetVariation(String betVariation) {
        this.betVariation = betVariation;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }

    public Float getKoef() {
        return koef;
    }

    public void setKoef(Float koef) {
        this.koef = koef;
    }
}
