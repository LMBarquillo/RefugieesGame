package es.riberadeltajo.refugiadosgame.ruta5.view;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import es.riberadeltajo.refugiadosgame.R;

public class Tehran extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new GameView(this));
    }
}
