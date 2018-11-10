package com.example.said.connect_v2;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.stetho.Stetho;

import java.util.regex.Pattern;

public class LogIn extends AppCompatActivity implements View.OnClickListener {
    EditText Correo;
    EditText Clave;
    Cursor cursor;
    //Declarar un objeto variable de referencia a la base de datos
    SQLiteDatabase db;
    //Validación de formato de correo correcto
    private boolean validarEmail(String email){
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }
    @Override
    protected void onCreate(Bundle SavedInstanceState) {
        super.onCreate(SavedInstanceState);
        setContentView(R.layout.login);
        Stetho.initializeWithDefaults(this);
        //Ocultar barra superior
        getSupportActionBar().hide();

        //Parametros de XML
        Button Entrar;
        Button Registrar;
        Entrar = (Button) this.findViewById(R.id.B_LogIn);
        Registrar = (Button) this.findViewById(R.id.B_SignIn);
        Correo = (EditText) this.findViewById(R.id.ET_Mail);
        Clave = (EditText) this.findViewById(R.id.ET_Pass);
        Entrar.setOnClickListener(this);
        Registrar.setOnClickListener(this);
        //La base de datos es creada o abierta al momento que se ejecuta la app
        db = this.openOrCreateDatabase("connect.db", MODE_PRIVATE, null);
        this.crearTablas(db);
    }

    private void crearTablas(SQLiteDatabase db) {
        //Crear la tabla si no existe
        String sqlCreate;
        sqlCreate = "CREATE TABLE IF NOT EXISTS Datos(" +
                "_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                "CorreoU TEXT," +
                "ClaveU TEXT," +
                "NombreU TEXT," +
                "ApellidoPU TEXT," +
                "ApellidoMU TEXT," +
                "SexoU TEXT," +
                "FechaNU TEXT," +
                "TelefonoU INTEGER" +
                ");";
        db.execSQL(sqlCreate);
    }

    public void onClick(View view) {
        EditText Correo;
        EditText Clave;
        Correo = (EditText) this.findViewById(R.id.ET_Mail);
        Clave = (EditText) this.findViewById(R.id.ET_Pass);
        int id;
        id = view.getId();

        if (Correo.getText().toString().isEmpty() || Clave.getText().toString().isEmpty()) {
            Toast.makeText(this, "Datos vacios", Toast.LENGTH_SHORT).show();
        } else if(!validarEmail(Correo.getText().toString())) {
            Correo.setError("Formato no válido :(");
        }else{
            switch (id) {
                case R.id.B_LogIn:
                    ValidarInicio();
                    break;
                case R.id.B_SignIn:
                    ValidarRegistro();
                    break;
            }
        }
    }

    private void ValidarInicio() {
        String sqlSearch;
        String Contra;
        sqlSearch = "SELECT COUNT(ClaveU) AS counter FROM Datos WHERE CorreoU = '"+Correo.getText().toString()+"'";
        cursor = db.rawQuery(sqlSearch, null);
        boolean exists;
        cursor.moveToNext();
        exists = cursor.getInt(cursor.getColumnIndexOrThrow("counter")) > 0 ? true : false;
        if (exists){
            String sqlPass;
            sqlPass = "SELECT ClaveU FROM Datos WHERE CorreoU ='"+Correo.getText().toString()+"'";
            cursor = db.rawQuery(sqlPass, null);
            cursor.moveToNext();
            Contra = cursor.getString(cursor.getColumnIndex("ClaveU"));
            if(Contra.equals(Clave.getText().toString())) {
                Iniciar();
            }else{
                Toast.makeText(this, "Contraseña Incorrecta", Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(this, "Cuenta No Registrada", Toast.LENGTH_LONG).show();
        }
    }

    private void ValidarRegistro() {
        //Validar datos de la base
        String sqlSearch;
        sqlSearch = "SELECT COUNT(CorreoU) AS counter FROM Datos WHERE CorreoU = '" + Correo.getText().toString() + "';";
        cursor = db.rawQuery(sqlSearch, null);

        //Declarar Variables
        boolean isMailAvailable;

        //Tratamiento del cursor
        cursor.moveToNext();
        isMailAvailable = cursor.getInt(cursor.getColumnIndexOrThrow("counter")) == 0 ? true : false;

        if (isMailAvailable) {
            Registrar();
        }else {
            Toast.makeText(this, "Ya hay una cuenta registrada con este correo", Toast.LENGTH_LONG).show();
        }
    }

    private void Iniciar() {

        //Validar datos con la base

        db.close();

        Intent intent;
        intent = new Intent(this, MainActivity.class);
        this.startActivity(intent);
        Correo.setText("");
        Clave.setText("");
    }

    private void Registrar() {
        //Enviar datos a la base
        String sqlInsert = "";
        sqlInsert = "INSERT INTO Datos(CorreoU, ClaveU) " +
                "VALUES ('" + Correo.getText().toString() + "','" + Clave.getText().toString() + "')";
        db.execSQL(sqlInsert);
        db.close();

        String UMail;
        UMail = Correo.getText().toString();
        Intent intent;
        intent = new Intent(LogIn.this, SignIn.class);
        intent.putExtra("UM", UMail);
        this.startActivity(intent);
        Correo.setText("");
        Clave.setText("");
    }

}
