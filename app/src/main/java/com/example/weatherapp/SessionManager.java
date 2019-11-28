package com.example.weatherapp;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

import com.example.weatherapp.models.Data;
import com.example.weatherapp.ui.auth.AuthResource;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class SessionManager {

    private MediatorLiveData<AuthResource<Data>> cachedData = new MediatorLiveData<>();
    private static final String TAG = "SessionManager";

    @Inject
    public SessionManager() {
    }

    public void authenticationWithCityName(final LiveData<AuthResource<Data>> source){
            if (cachedData !=null){
                cachedData.setValue(AuthResource.loading((Data)null));
                cachedData.addSource(source, new Observer<AuthResource<Data>>() {
                    @Override
                    public void onChanged(AuthResource<Data> dataAuthResource) {
                        cachedData.setValue(dataAuthResource);
                        cachedData.removeSource(source);
                    }
                });
            }
           
    }
    public void logOut(){
        Log.d(TAG, "logOut: Logging out !");
        cachedData.setValue(AuthResource.<Data>logout());
    }
    public LiveData<AuthResource<Data>> getData(){
        return cachedData;
    }
}
