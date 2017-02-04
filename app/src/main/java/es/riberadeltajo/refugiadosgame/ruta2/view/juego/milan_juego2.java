package es.riberadeltajo.refugiadosgame.ruta2.view.juego;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import es.riberadeltajo.refugiadosgame.R;

public class milan_juego2 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new GameView2(this));
    }
}
