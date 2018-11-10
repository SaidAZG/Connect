package mx.dot.connect;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class Inicio extends AppCompatActivity implements View.OnClickListener{

    TextView NumeroTel;
    TextView Ubicacion;
    TextView DefparaAdd;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.inicio);

        NumeroTel = (TextView) this.findViewById(R.id.id_TelDetOff);
        Ubicacion = (TextView) this.findViewById(R.id.id_UbiDetOff);

        DefparaAdd =(TextView) this.findViewById(R.id.id_DescDetOff);



        //TODO remplazar con metodo para validar y restringuir acceso a usuarios no registrados
        RecuperarDat();

        NumeroTel.setOnClickListener(this);
        Ubicacion.setOnClickListener(this);





    }

    private void RecuperarDat() {
        Intent intent;

        intent = this.getIntent();

        Bundle extras;

        extras = intent.getExtras();

        String Correorev = extras.getString("CorreoAValidar");
        String Passwrev = extras.getString("PassAVerificar");
        String Holi= DefparaAdd.getText().toString()+" "+Correorev+" "+Passwrev;

        DefparaAdd.setText(Holi);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.id_TelDetOff:

                callMeHDE_Plis();

                break;
            case R.id.id_UbiDetOff:

                buscarenelMap();

                break;


        }
    }

    @SuppressLint("MissingPermission")
    private void callMeHDE_Plis() {

        String urltel;
        urltel= "tel:"+NumeroTel.getText().toString();

        Intent impintent;

        impintent = new Intent (Intent.ACTION_CALL, Uri.parse(urltel));

        this.startActivity(impintent);

    }

    private void buscarenelMap() {

        String urlUbi;
        urlUbi = "geo:"+Ubicacion;

        Intent impintent;

        impintent = new Intent(Intent.ACTION_VIEW,Uri.parse(urlUbi));
        this.startActivity(impintent);

    }
}
