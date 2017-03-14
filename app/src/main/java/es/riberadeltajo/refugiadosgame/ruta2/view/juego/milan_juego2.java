package es.riberadeltajo.refugiadosgame.ruta2.view.juego;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import es.riberadeltajo.refugiadosgame.R;

public class milan_juego2 extends Activity {
    GameView2 pantalla;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         pantalla = new GameView2((this));
        setContentView(pantalla);
    }
    @Override
    protected void onPause(){

        pantalla.finalizar();
        super.onPause();


    }
    @Override
    protected void onStop(){
        pantalla.finalizar();
        super.onStop();
    }
    @Override
    protected void onDestroy(){
        pantalla.finalizar();
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        pantalla.finalizar();
    }
}

