package es.riberadeltajo.refugiadosgame.ruta4.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import es.riberadeltajo.refugiadosgame.R;
import es.riberadeltajo.refugiadosgame.ruta4.view.engine.GameView;
import es.riberadeltajo.refugiadosgame.ruta4.view.engine.OptionsView;

public class GameMenu extends AppCompatActivity{
    private OptionsView optionsView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                        | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        super.onCreate(savedInstanceState);
        optionsView = new OptionsView(this);
        setContentView(optionsView);
    }

    public void jugar(int cancion, int dificultad, int lugar) {
        optionsView.getLoop().setRunning(false);
        Intent i = new Intent(this,StreetGuitar.class);
        //i.putExtra("cancion",cancion);
        i.putExtra("dificultad",dificultad);
        i.putExtra("lugar",lugar);
        startActivity(i);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
