package es.riberadeltajo.refugiadosgame.ruta5.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import es.riberadeltajo.refugiadosgame.R;

public class teheran_main2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teheran_main2);
    }

    public void camarero(View v){
        Intent i = new Intent(this, Tehran.class);
        i.putExtra("camarero",true);
        startActivity(i);
    }

    public void limpiador(View v){
        Intent i = new Intent(this, Tehran.class);
        i.putExtra("camarero",false);
        startActivity(i);
    }

    public void volver(View v){
        finish();
    }
}
