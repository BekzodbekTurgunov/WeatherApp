package com.example.weatherapp.ui.main;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.weatherapp.BaseActivity;
import com.example.weatherapp.R;

import es.dmoral.toasty.Toasty;

public class MainActivity extends BaseActivity {
    private static final String TAG = "MainActivity";
    private TextView temp, description;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toasty.success(this,"Success",Toasty.LENGTH_SHORT).show();
        temp = findViewById(R.id.temp);
        description = findViewById(R.id.description);
        init();
    }
    private void init(){
      temp.setText(String.valueOf((int)sessionManager.getData().getValue().data.getMain().getTemp()-273));
      description.setText("Description : " + String.valueOf(sessionManager.getData().getValue().data.getWeather().get(0).getDescription()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
       switch (item.getItemId()){
           case R.id.logout:{
               sessionManager.logOut();
           }
       }
        return super.onOptionsItemSelected(item);
    }
}
