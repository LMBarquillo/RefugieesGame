package es.riberadeltajo.refugiadosgame.ruta2.view.juego;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import es.riberadeltajo.refugiadosgame.R;

public class milan_juego2 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GameView2 pantalla = new GameView2((this));
        setContentView(pantalla);
    }
}

