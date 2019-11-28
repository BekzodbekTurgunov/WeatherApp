package com.example.weatherapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;

import com.example.weatherapp.models.Data;
import com.example.weatherapp.ui.auth.AuthActivity;
import com.example.weatherapp.ui.auth.AuthResource;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public abstract class  BaseActivity extends DaggerAppCompatActivity {
    private static final String TAG = "BaseActivity";

    @Inject
   public SessionManager sessionManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        subscribeObservers();
    }
    private void subscribeObservers(){
        sessionManager.getData().observe(this, new Observer<AuthResource<Data>>() {
            @Override
            public void onChanged(AuthResource<Data> dataAuthResource) {
                if (dataAuthResource !=null){
                    switch (dataAuthResource.status){
                        case LOADING: {
                            break;
                        }
                        case AUTHENTICATED:{
                            Log.d(TAG, "onChanged: LOGIN SUCCESS Temperature" + dataAuthResource.data.getMain().getTemp());

                            break;
                        }
                        case ERROR:{
                            Log.d(TAG, "onChanged: " + dataAuthResource.message);
                            break;
                        }
                        case NOT_AUTHENTICATED:{
                            loginScreen();
                            break;
                        }

                    }
                }
            }
        });

    }
    private void loginScreen(){
        Intent intent  =  new Intent(this, AuthActivity.class);
        startActivity(intent);
        finish();
    }
}
