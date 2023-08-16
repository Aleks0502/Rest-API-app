package ru.restapi.testtask.RestAppForCosy.utils;

public class OWMResponse {
    private String name;
    private MainData main;

    OWMResponse() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MainData getMain() {
        return main;
    }

    public void setMain(MainData main) {
        this.main = main;
    }
}
