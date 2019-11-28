package com.example.weatherapp.ui.auth;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;

import com.example.weatherapp.models.Test;
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

    private MediatorLiveData<Test> weather = new MediatorLiveData<>();
    @Inject
    public AuthViewModel(AuthApi authApi){
        this.authApi = authApi;

        authApi.getWeather("bukhara")
                .toObservable()
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Test>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Test test) {
                        Log.d(TAG, "onNext: Success");
                        Log.d(TAG, "onNext: " + test.getWeather().get(0).getDescription());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: ",e );
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    public void authenticationWithCityName(String city){
        final LiveData<Test> source = LiveDataReactiveStreams.fromPublisher(
                authApi.getWeather(city)
                .subscribeOn(Schedulers.io())

        );
        weather.addSource(source, new androidx.lifecycle.Observer<Test>() {
            @Override
            public void onChanged(Test weathers) {
                weather.setValue(weathers); // handle..
                weather.removeSource(source);
            }
        });

    }
    public LiveData<Test> observeWeather(){
        return weather;
    }
}
