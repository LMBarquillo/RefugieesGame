package es.riberadeltajo.refugiadosgame.ruta3.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import es.riberadeltajo.refugiadosgame.R;

public class Sarajevo extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new GameView(this));
    }
}
