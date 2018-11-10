package com.example.said.connect_v2;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.jar.Attributes;

public class SignIn extends AppCompatActivity implements View.OnClickListener {
    SQLiteDatabase db;
    TextView Correo;
    EditText Nombre;
    EditText ApellidoP;
    EditText ApellidoM;
    EditText Telefono;
    EditText Nacimiento;
    Button Continuar;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin);
        getSupportActionBar().hide();
        //Parámetros de XML
        Nombre = (EditText)this.findViewById(R.id.ET_Name);
        ApellidoP = (EditText)this.findViewById(R.id.ET_SurnameP);
        ApellidoM = (EditText)this.findViewById(R.id.ET_SurnameM);
        Telefono = (EditText)this.findViewById(R.id.ET_Phone);
        Nacimiento = (EditText)this.findViewById(R.id.ET_BirthDate);
        Continuar = (Button)this.findViewById(R.id.B_SignIn2);
        //Setear el clicklistener
        Continuar.setOnClickListener(this);
        //Instanciar la base de datos
        db = this.openOrCreateDatabase("connect.db", MODE_PRIVATE, null);
        this.verDatos();
    }
    //Método para desplegar el nombre del correo ingresado para el registro
    private void verDatos() {
        Intent intent;
        intent = this.getIntent();
        intent.getExtras();
        Bundle extras;
        extras = intent.getExtras();

        String Mail = extras.getString("UM");

        Correo = this.findViewById(R.id.TV_Mail);
        Correo.setText(Mail);
    }

    @Override
    //Validación e inicialización del metodo para subir los datos
    public void onClick(View view) {
        int id;
        id = view.getId();
        //Validación de Campos Vacios
        if (Nombre.getText().toString().isEmpty() || ApellidoP.getText().toString().isEmpty() ||
                ApellidoP.getText().toString().isEmpty() || Telefono.getText().toString().isEmpty() ||
                Nacimiento.getText().toString().isEmpty()) {
            Toast.makeText(this, "Datos Vacíos", Toast.LENGTH_SHORT).show();
        } else {
            switch (id) {
                case R.id.B_SignIn2:
                    Subir();
                    //Reenviar a la segunda etapa de registro
                    Intent intent;
                    intent = new Intent(this, Additional.class);
                    this.startActivity(intent);
                    break;
            }
        }
    }

    private void Subir() {
        //Enviar los datos a la base
        String sqlUpdate = "";
        sqlUpdate = "UPDATE Datos SET NombreU='"+Nombre.getText().toString()+"' WHERE CorreoU='"+Correo.getText().toString()+"';";
        db.execSQL(sqlUpdate);
        sqlUpdate = "UPDATE Datos SET ApellidoPU='"+ApellidoP.getText().toString()+"' WHERE CorreoU='"+Correo.getText().toString()+"';";
        db.execSQL(sqlUpdate);
        sqlUpdate = "UPDATE Datos SET ApellidoMU='"+ApellidoM.getText().toString()+"' WHERE CorreoU='"+Correo.getText().toString()+"';";
        db.execSQL(sqlUpdate);
        sqlUpdate = "UPDATE Datos SET FechaNU='"+Nacimiento.getText().toString()+"' WHERE CorreoU='"+Correo.getText().toString()+"';";
        db.execSQL(sqlUpdate);
        sqlUpdate = "UPDATE Datos SET TelefonoU='"+Telefono.getText().toString()+"' WHERE CorreoU='"+Correo.getText().toString()+"';";
        db.execSQL(sqlUpdate);
        db.close();
        Toast.makeText(this, "Datos subidos",Toast.LENGTH_SHORT).show();
    }
}
