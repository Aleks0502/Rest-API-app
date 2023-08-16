package ru.restapi.testtask.RestAppForCosy.dto;

public class WeatherDTO {
    private String name;
    private double temp;

    WeatherDTO() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }
}
