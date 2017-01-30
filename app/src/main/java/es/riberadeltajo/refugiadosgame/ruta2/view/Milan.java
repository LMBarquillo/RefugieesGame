package es.riberadeltajo.refugiadosgame.ruta2.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import es.riberadeltajo.refugiadosgame.R;
import es.riberadeltajo.refugiadosgame.ruta2.view.juego.milan_juego;

public class Milan extends AppCompatActivity {
    private int reques_code=1;
    private TextView texto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_milan);
        Intent i=new Intent(this, milan_juego.class);
        startActivityForResult(i, reques_code);
        texto=(TextView)findViewById(R.id.texto);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==reques_code){
            if(resultCode==RESULT_OK){
                Bundle bundle=data.getExtras();
                boolean resultado=bundle.getBoolean("resultado");
                texto.setText("Devuelto el juego");

            }
        }
    }
}
