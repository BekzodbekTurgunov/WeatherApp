package com.example.weatherapp.di.auth;

import androidx.lifecycle.ViewModel;

import com.example.weatherapp.di.annotation.ViewModelKey;
import com.example.weatherapp.ui.auth.AuthViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class AuthViewModelsModule {
    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel.class)
    public abstract ViewModel bindAuthViewModel(AuthViewModel authViewModel);

}
