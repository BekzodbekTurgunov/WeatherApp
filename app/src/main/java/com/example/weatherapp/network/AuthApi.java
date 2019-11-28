package com.example.weatherapp.network;

import com.example.weatherapp.models.Test;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface AuthApi {

    @GET("weather/{city}")
    Flowable<Test> getWeather(@Path("city") String city);
}
