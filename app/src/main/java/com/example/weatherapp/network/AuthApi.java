package com.example.weatherapp.network;

import com.example.weatherapp.models.Weather;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface AuthApi {

    @GET("weather")
    Flowable<Weather> getWeather(@Query("city") String city);
}
