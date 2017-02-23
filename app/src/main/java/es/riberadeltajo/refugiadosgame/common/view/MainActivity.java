package es.riberadeltajo.refugiadosgame.common.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import es.riberadeltajo.refugiadosgame.R;
import es.riberadeltajo.refugiadosgame.common.models.PlayerStatus;
import es.riberadeltajo.refugiadosgame.ruta1.view.Madrid;
import es.riberadeltajo.refugiadosgame.ruta2.view.Milan;
import es.riberadeltajo.refugiadosgame.ruta3.view.Sarajevo;
import es.riberadeltajo.refugiadosgame.ruta4.view.Istanbul;
import es.riberadeltajo.refugiadosgame.ruta5.view.Tehran;
import es.riberadeltajo.refugiadosgame.ruta5.view.teheran_main2;
import es.riberadeltajo.refugiadosgame.ruta5.view.tehran1;

public class MainActivity extends AppCompatActivity {
    private final int RUTA1 = 1;
    private final int RUTA2 = 2;
    private final int RUTA3 = 3;
    private final int RUTA4 = 4;
    private final int RUTA5 = 5;

    //private PlayerStatus status;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Instanciamos el estado del jugador. Este objeto le iremos pasando de una a otra activity.
        //status = new PlayerStatus();
    }

    public void ruta1(View view) {
        Intent i = new Intent(this,Madrid.class);
        /*i.putExtra("STATUS",status);
        startActivityForResult(i,RUTA1);*/
        startActivity(i);
    }

    public void ruta2(View view) {
        Intent i = new Intent(this,Milan.class);
        /*i.putExtra("STATUS",status);
        startActivityForResult(i,RUTA2);
        startActivity(i);*/
    }

    public void ruta3(View view) {
        Intent i = new Intent(this,Sarajevo.class);
        /*i.putExtra("STATUS",status);
        startActivityForResult(i,RUTA3);*/
        startActivity(i);
    }

    public void ruta4(View view) {
        Intent i = new Intent(this,Istanbul.class);
        /*i.putExtra("STATUS",status);
        startActivityForResult(i,RUTA4);*/
        startActivity(i);
    }

    public void ruta5(View view) {
        Intent i = new Intent(this,tehran1.class);
        /*i.putExtra("STATUS",status);
        startActivityForResult(i,RUTA5);*/
        startActivity(i);
    }

    public void menu(View view) {
        startActivity(new Intent(this, MainActivity2.class));
    }
}
