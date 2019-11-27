package com.example.weatherapp.di;

import com.example.weatherapp.ui.auth.AuthActivity;
import com.example.weatherapp.ui.auth.AuthViewModelsModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(
            modules = {AuthViewModelsModule.class}
    )
    abstract AuthActivity contributeAuthActivity();
}
