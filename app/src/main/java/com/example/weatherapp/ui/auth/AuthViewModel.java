package com.example.weatherapp.ui.auth;

import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import dagger.Module;

@Module
public class AuthViewModel extends ViewModel {
    private static final String TAG = "AuthViewModel";

    @Inject
    public AuthViewModel(){
        Log.d(TAG, "AuthViewModel: Model is working ...");
    }
}
