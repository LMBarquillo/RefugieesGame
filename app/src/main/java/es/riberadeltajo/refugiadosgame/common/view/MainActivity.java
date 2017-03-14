package es.riberadeltajo.refugiadosgame.common.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import es.riberadeltajo.refugiadosgame.R;
import es.riberadeltajo.refugiadosgame.ruta1.view.Madrid;
import es.riberadeltajo.refugiadosgame.ruta2.view.Milan;
import es.riberadeltajo.refugiadosgame.ruta3.view.ruta.SarajevoAlojamiento;
import es.riberadeltajo.refugiadosgame.ruta4.view.GameMenu;
import es.riberadeltajo.refugiadosgame.ruta4.view.ruta.Istanbul_Main;
import es.riberadeltajo.refugiadosgame.ruta5.view.tehran1;

public class MainActivity extends AppCompatActivity {
    //private PlayerStatus status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void ruta1(View view) {
        Intent i = new Intent(this,Madrid.class);
        startActivity(i);
    }

    public void ruta2(View view) {
        Intent i = new Intent(this,Milan.class);
        startActivity(i);
    }

    public void ruta3(View view) {
        //Intent i = new Intent(this,SarajevoArcade.class);
        Intent i = new Intent(this, SarajevoAlojamiento.class);
        startActivity(i);
    }

    public void ruta4(View view) {
        //Intent i = new Intent(this, Istanbul_Main.class);
        Intent i = new Intent(this, GameMenu.class);
        startActivity(i);
    }

    public void ruta5(View view) {
        Intent i = new Intent(this,tehran1.class);
        startActivity(i);
    }

    public void menu(View view) {
        startActivity(new Intent(this, MainActivity2.class));
    }
}
