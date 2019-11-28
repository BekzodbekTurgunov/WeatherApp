package com.example.weatherapp.network;

import com.example.weatherapp.models.Data;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface AuthApi {

    @GET("weather/{city}")
    Flowable<Data> getWeather(@Path("city") String city);
}
