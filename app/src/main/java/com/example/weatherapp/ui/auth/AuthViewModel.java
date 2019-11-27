package com.example.weatherapp.ui.auth;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.example.weatherapp.models.Weather;
import com.example.weatherapp.network.AuthApi;

import javax.inject.Inject;

import dagger.Module;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

@Module
public class AuthViewModel extends ViewModel {
    private static final String TAG = "AuthViewModel";

    private final AuthApi authApi;

    @Inject
    public AuthViewModel(AuthApi authApi){
        this.authApi = authApi;
        Log.d(TAG, "AuthViewModel: Model is working ...");
        authApi.getWeather("bukhara")
                .toObservable()
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Weather>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        
                    }

                    @Override
                    public void onNext(Weather weather) {
                        Log.d(TAG, "onNext: " + weather.getDescription());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: Eror...",e );
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
