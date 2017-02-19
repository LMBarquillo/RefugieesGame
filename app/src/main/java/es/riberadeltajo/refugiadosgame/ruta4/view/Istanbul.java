package es.riberadeltajo.refugiadosgame.ruta4.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import es.riberadeltajo.refugiadosgame.ruta4.view.engine.GameView;
import es.riberadeltajo.refugiadosgame.ruta4.view.engine.OptionsView;
import es.riberadeltajo.refugiadosgame.ruta4.view.models.Distribuidor;


public class Istanbul extends AppCompatActivity implements Distribuidor {
    public static final int MENU = 1;
    public static final int JUEGO = 2;

    private OptionsView optionsView;
    private GameView gameView;
    private int faseActual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Luna, te copio esto :)
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                        | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        super.onCreate(savedInstanceState);
        optionsView = new OptionsView(this,this);
        gameView = new GameView(this,OptionsView.SONG_SOTW,OptionsView.DIF_EASY,OptionsView.PLACE_AZADI);
        // Primero cargamos el view de las opciones
        setFaseActual(MENU);
        setContentView(optionsView);
    }
/*
    @Override
    public void onBackPressed() {
        if(getFaseActual() == JUEGO) {
            gameView.finish();
            cambiarFase(MENU);
        }
    } */

    @Override
    protected void onPause() {
        gameView.finish();
        super.onPause();
    }

    @Override
    public void setOpciones(int cancion, int dificultad, int lugar) {
        gameView.setCancion(cancion);
        gameView.setDificultad(dificultad);
        gameView.setLugar(lugar);
    }

    @Override
    public void cambiarFase(int fase) {
        switch(fase) {
            case MENU:
                setContentView(optionsView);
                setFaseActual(MENU);
                break;
            case JUEGO:
                setContentView(gameView);
                setFaseActual(JUEGO);
                break;
        }
    }

    @Override
    public void finalizar() {
        finish();
    }

    public int getFaseActual() {
        return faseActual;
    }

    public void setFaseActual(int faseActual) {
        this.faseActual = faseActual;
    }
}
