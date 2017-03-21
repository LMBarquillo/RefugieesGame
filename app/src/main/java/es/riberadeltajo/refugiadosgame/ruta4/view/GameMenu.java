package es.riberadeltajo.refugiadosgame.ruta4.view;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import es.riberadeltajo.refugiadosgame.R;
import es.riberadeltajo.refugiadosgame.ruta4.view.engine.GameView;
import es.riberadeltajo.refugiadosgame.ruta4.view.engine.OptionsView;

public class GameMenu extends AppCompatActivity{
    private static final int REQUEST_GAME = 0;
    private static final int REQUEST_END = 1;
    private OptionsView optionsView;
    private MediaPlayer musicaFondo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        iniciarMenu();
    }

    protected void onResume() {
        super.onResume();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON); //Evita que se apague la pantalla
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                        | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }

    public void jugar(int cancion, int dificultad, int lugar) {
        optionsView.getLoop().setRunning(false);
        stopMusic();
        Intent i = new Intent(this,StreetGuitar.class);
        i.putExtra("cancion",cancion);      // Comentar esta línea para usar la canción de test
        i.putExtra("dificultad",dificultad);
        i.putExtra("lugar",lugar);
        startActivityForResult(i,REQUEST_GAME);
        optionsView.setFase(OptionsView.Fase.INICIO);
    }

    public void finalJuego(int puntos) {
        optionsView.getLoop().setRunning(false);
        Intent i = new Intent(this,EndGame.class);
        i.putExtra("puntos",puntos);
        startActivityForResult(i,REQUEST_END);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onBackPressed() {
        stopMusic();
        super.onBackPressed();
    }

    public MediaPlayer getMusicaFondo() {
        return musicaFondo;
    }

    public void setMusicaFondo(MediaPlayer musicaFondo) {
        this.musicaFondo = musicaFondo;
    }

    public void stopMusic() {
        if(getMusicaFondo() != null && getMusicaFondo().isPlaying()) {
            getMusicaFondo().stop();
            getMusicaFondo().release();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_GAME) {
            if(resultCode == RESULT_OK) {
                int puntos = data.getIntExtra("puntos",0);
                finalJuego(puntos);
            } else {
                iniciarMenu();
            }
        } else {
            iniciarMenu();
        }
    }

    private void iniciarMenu() {
        optionsView = new OptionsView(this);
        setContentView(optionsView);
        setMusicaFondo(new MediaPlayer().create(this,R.raw.kindoflight));
        getMusicaFondo().start();
    }
}
