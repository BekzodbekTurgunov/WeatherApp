package com.example.weatherapp.models;

import java.util.ArrayList;

public class Test {
    private ArrayList<Weather> weather;

    public Test(ArrayList<Weather> weather) {
        this.weather = weather;
    }

    public ArrayList<Weather> getWeather() {
        return weather;
    }

    public void setWeather(ArrayList<Weather> weather) {
        this.weather = weather;
    }
}
