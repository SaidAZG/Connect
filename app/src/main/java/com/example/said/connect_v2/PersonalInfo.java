package com.example.said.connect_v2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Alumno on 05/11/2018.
 */

public class PersonalInfo extends AppCompatActivity {
    @Override
    protected void onCreate( Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.perinfo);
        getSupportActionBar().hide();
    }
}