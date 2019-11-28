package com.example.weatherapp.models;

import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Data {
    @Nullable
    @SerializedName("weather")
    private ArrayList<Weather> weather;

    private Main main;

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public Data(ArrayList<Weather> weather) {
        this.weather = weather;
    }

    public Data() {
    }

    public ArrayList<Weather> getWeather() {
        return weather;
    }

    public void setWeather(ArrayList<Weather> weather) {
        this.weather = weather;
    }
}
