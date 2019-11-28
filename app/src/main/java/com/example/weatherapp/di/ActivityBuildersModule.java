package com.example.weatherapp.di;

import com.example.weatherapp.di.auth.AuthModule;
import com.example.weatherapp.di.auth.AuthViewModelsModule;
import com.example.weatherapp.di.main.MainViewModelsModule;
import com.example.weatherapp.ui.auth.AuthActivity;
import com.example.weatherapp.ui.main.MainActivity;
import com.example.weatherapp.ui.main.MainViewModel;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(
            modules = {AuthViewModelsModule.class,
                    AuthModule.class}
    )
    abstract AuthActivity contributeAuthActivity();

    @ContributesAndroidInjector(
            modules = {
                    MainViewModelsModule.class,
                    MainViewModel.class}
    )
    abstract MainActivity contributeMainActivity();
}
