package mx.dot.connect;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener{

    EditText edtCorrhome;
    EditText edtPasshome;
    Button buttonenterHome;
    TextView TitConect;
    TextView politicasLink;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtCorrhome= (EditText) this.findViewById(R.id.id_EdittxtCrrHome);
        edtPasshome= (EditText) this.findViewById(R.id.id_EdittxtPassHome);

        buttonenterHome= (Button) this.findViewById(R.id.id_ButtonEntrarHome);

        TitConect= (TextView) this.findViewById(R.id.id_titConnectHome);
        politicasLink= (TextView) this.findViewById(R.id.id_PoliticHome);


        buttonenterHome.setOnClickListener(this);
        politicasLink.setOnClickListener(this);




    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.id_ButtonEntrarHome:

                entarAlSistema();

                break;
            case R.id.id_PoliticHome:

                revisarPoliticas();

                break;


        }
    }

    private void revisarPoliticas() {

        String urlPolitic = "https://latitude.to/articles-by-country/general/3193/hp-lovecraft-rlyeh";

        Intent impintent;

        impintent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlPolitic));

        this.startActivity(impintent);

    }

    private void entarAlSistema() {

        ///TODO if else donde impida pasar y mande un Toast en caso de que no se hayan llenado los campos

            Toast.makeText(this, "bienvenido "+ (edtCorrhome.getText().toString()), Toast.LENGTH_LONG).show();

            mandarLasCosasEIrnos();



    }

    private void mandarLasCosasEIrnos() {

        String Correoaenv;
        String Passwordaenv;

        Correoaenv = edtCorrhome.getText().toString();
        Passwordaenv = edtPasshome.getText().toString();

        Intent intent;
        intent = new Intent (this, Inicio.class);

        intent.putExtra("CorreoAValidar", Correoaenv);
        intent.putExtra("PassAVerificar", Passwordaenv);

        this.startActivity(intent);

    }
}
