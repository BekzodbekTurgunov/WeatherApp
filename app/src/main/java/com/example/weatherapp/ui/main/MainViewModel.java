package com.example.weatherapp.ui.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.weatherapp.models.Data;

import javax.inject.Inject;

import dagger.Module;

@Module
public class MainViewModel extends ViewModel {
    @Inject
    public MainViewModel() {
    }
   /* public LiveData<Data> observeMain(){

    } */
}
