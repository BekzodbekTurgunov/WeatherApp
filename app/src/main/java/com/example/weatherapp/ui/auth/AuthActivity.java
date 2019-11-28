package com.example.weatherapp.ui.auth;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.weatherapp.R;
import com.example.weatherapp.models.Data;
import com.example.weatherapp.ui.main.MainActivity;
import com.example.weatherapp.viewmodels.ViewModelProviderFactory;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import es.dmoral.toasty.Toasty;

public class AuthActivity extends DaggerAppCompatActivity implements View.OnClickListener {
    private static final String TAG = "AuthActivity";
    private AuthViewModel viewModel;
    private EditText cityEt;
    private ProgressBar progressBar;

    @Inject
    ViewModelProviderFactory providerFactory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        viewModel = ViewModelProviders.of(this,providerFactory).get(AuthViewModel.class);
        cityEt = findViewById(R.id.wheather_city_input);
        progressBar = findViewById(R.id.progress_bar);
        findViewById(R.id.login_button).setOnClickListener(this);
        subscribeObserver();
    }
    private void subscribeObserver(){
        viewModel.observeWeather()
               .observe(this, new Observer<AuthResource<Data>>() {
                   @Override
                   public void onChanged(AuthResource<Data> dataAuthResource) {
                       if (dataAuthResource !=null){
                           switch (dataAuthResource.status){
                               case LOADING: {
                                   showProgress(true);
                                   break;
                               }
                               case AUTHENTICATED:{
                                   showProgress(false);
                                 //  Toasty.success(AuthActivity.this,"LOGIN SUCCESS ", Toast.LENGTH_SHORT).show();
                                  onLoginSuccess();
                                   Log.d(TAG, "onChanged: LOGIN SUCCESS Temperature" + dataAuthResource.data.getMain().getTemp());

                                   break;
                               }
                               case ERROR:{
                                Toasty.error(AuthActivity.this,"Error " , Toasty.LENGTH_SHORT).show();
                                   showProgress(false);
                                   break;
                               }
                               case NOT_AUTHENTICATED:{
                                   Toasty.warning(AuthActivity.this,"Authentication failed", Toasty.LENGTH_SHORT).show();
                                   showProgress(false);
                                   break;
                               }

                           }
                       }
                   }
               });
    }
    private void onLoginSuccess(){
        Intent intent =  new Intent(AuthActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
    private void showProgress(boolean isVisable){
        if (isVisable){
            progressBar.setVisibility(View.VISIBLE);
        }
        else {
            progressBar.setVisibility(View.INVISIBLE);
        }

    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.login_button){
            login();
        }
    }
    private void login(){
        if (TextUtils.isEmpty(cityEt.getText().toString())){
            return;
        }else {
            viewModel.authenticationWithCityName(cityEt.getText().toString());
        }
    }
}
