package com.example.weatherapp.di.main;

import androidx.lifecycle.ViewModel;

import com.example.weatherapp.di.annotation.ViewModelKey;
import com.example.weatherapp.ui.main.MainViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class MainViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel.class)
    public abstract ViewModel bindViewModel(MainViewModel mainViewModel);
}
