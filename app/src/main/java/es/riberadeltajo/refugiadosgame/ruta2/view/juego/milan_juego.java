package es.riberadeltajo.refugiadosgame.ruta2.view.juego;

import android.app.Activity;
import android.os.Bundle;



public class milan_juego extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new GameView(this));


    }
}
