package com.example.weatherapp.ui.auth;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;

import com.example.weatherapp.SessionManager;
import com.example.weatherapp.models.Data;
import com.example.weatherapp.network.AuthApi;

import javax.inject.Inject;

import dagger.Module;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

@Module
public class AuthViewModel extends ViewModel {
    private static final String TAG = "AuthViewModel";

    private final AuthApi authApi;
    private SessionManager sessionManager;

    @Inject
    public AuthViewModel(AuthApi authApi, SessionManager sessionManager){
        this.sessionManager = sessionManager;
        this.authApi = authApi;

    }
    public void authenticationWithCityName(String city){
        Log.d(TAG, "authenticationWithCityName: attenpting to login");
        sessionManager.authenticationWithCityName(queryWheatherCityName(city));
       
    }
    private LiveData<AuthResource<Data>> queryWheatherCityName(String city){
        return LiveDataReactiveStreams.fromPublisher(
                authApi.getWeather(city)
                        .onErrorReturn(new Function<Throwable, Data>() {
                            @Override
                            public Data apply(Throwable throwable) throws Exception {
                                Data errorData =  new Data();
                                errorData.setWeather(null);

                                Log.d(TAG, "apply: ERROR"+ throwable.getMessage());
                                return errorData;
                            }
                        })
                        .map(new Function<Data, AuthResource<Data>>() {
                            @Override
                            public AuthResource<Data> apply(Data data) throws Exception {
                                if (data.getWeather()==null){
                                    return AuthResource.error("Could not authenticate", (Data)null);
                                }
                                return AuthResource.authenticated(data);
                            }
                        })
                        .subscribeOn(Schedulers.io()));
    }
    public LiveData<AuthResource<Data>> observeWeather(){
        return sessionManager.getData();
    }
}



  /* authApi.getWeather("bukhara")
                .toObservable()
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Data>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Data test) {
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
                });*/