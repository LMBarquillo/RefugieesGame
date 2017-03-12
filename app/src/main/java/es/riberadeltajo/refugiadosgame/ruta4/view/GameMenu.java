package es.riberadeltajo.refugiadosgame.ruta4.view;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import es.riberadeltajo.refugiadosgame.R;
import es.riberadeltajo.refugiadosgame.ruta4.view.engine.GameView;
import es.riberadeltajo.refugiadosgame.ruta4.view.engine.OptionsView;

public class GameMenu extends AppCompatActivity{
    private final static String TAG = "GameMenu";
    private OptionsView optionsView;
    private MediaPlayer musicaFondo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
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
            setMusicaFondo(new MediaPlayer().create(this,R.raw.kindoflight));
            getMusicaFondo().start();
        } catch(Exception ex) {

        }
    }

    public void jugar(int cancion, int dificultad, int lugar) {
        optionsView.getLoop().setRunning(false);
        stopMusic();
        Intent i = new Intent(this,StreetGuitar.class);
        i.putExtra("cancion",cancion);      // Comentar esta línea para usar la canción de test
        i.putExtra("dificultad",dificultad);
        i.putExtra("lugar",lugar);
        startActivity(i);
        optionsView.setFase(OptionsView.Fase.INICIO);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public synchronized MediaPlayer getMusicaFondo() {
        return musicaFondo;
    }

    public void setMusicaFondo(MediaPlayer musicaFondo) {
        this.musicaFondo = musicaFondo;
    }

    public void stopMusic() {
        if(getMusicaFondo() != null) {
            getMusicaFondo().stop();
            getMusicaFondo().release();
        }
    }
}
