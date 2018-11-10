package com.example.said.connect_v2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Created by Alumno on 05/11/2018.
 */

public class Additional extends AppCompatActivity implements View.OnClickListener {
    ImageButton Personal;
    ImageButton Profesional;
    Button Omitir;
    TextView Nombre;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add);
        getSupportActionBar().hide();
        Nombre = (TextView) this.findViewById(R.id.TV_Name);
        Personal = (ImageButton) this.findViewById(R.id.IB_Personal);
        Profesional = (ImageButton) this.findViewById(R.id.IB_Professional);
        Personal.setOnClickListener(this);
        Profesional.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        int id;
        id = v.getId();

        switch (id){
            case R.id.IB_Personal:
                LaunchPer();
                break;
            case R.id.IB_Professional:
                LaunchPro();
                break;
            case R.id.B_Omit:
                LaunchIndex();
                break;
        }
    }

    private void LaunchIndex() {
        Intent intent;
        intent = new Intent(this, MainActivity.class);
        this.startActivity(intent);
    }

    private void LaunchPer() {
        Intent intent;
        intent = new Intent(this, PersonalInfo.class);
        this.startActivity(intent);
    }

    private void LaunchPro(){
        Intent intent;
        intent = new Intent(this, ProfessionalInfo.class);
        this.startActivity(intent);
    }
}
