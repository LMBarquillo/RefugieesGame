package es.riberadeltajo.refugiadosgame.ruta4.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import es.riberadeltajo.refugiadosgame.R;
import es.riberadeltajo.refugiadosgame.ruta4.view.engine.GameView;
import es.riberadeltajo.refugiadosgame.ruta4.view.engine.OptionsView;
import es.riberadeltajo.refugiadosgame.ruta5.view.tehran1;

public class StreetGuitar extends AppCompatActivity {
    private GameView gameView;

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
        Intent entrada = getIntent();
        // Instanciamos el gameview con los datos recibidos de las opciones
        gameView = new GameView(this,
                entrada.getIntExtra("cancion",OptionsView.SONG_TEST),
                entrada.getIntExtra("dificultad",OptionsView.DIF_MEDIUM),
                entrada.getIntExtra("lugar",OptionsView.PLACE_AZADI));
        setContentView(gameView);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onBackPressed() {
        // Detenemos todos los hilos
        gameView.getLoop().setRunning(false);
        gameView.getGenerador().setRunning(false);

        if(gameView.getMusica() != null) {
            if(gameView.getMusica().isPlaying()) gameView.getMusica().stop();
            gameView.getMusica().release();
        }
        /*Intent i = new Intent();
        setResult(RESULT_CANCELED,i);*/
        finish();
    }

    public void gameEnd(int puntos, int result) {
        gameView.getLoop().setRunning(false);

        Intent i = new Intent(this,EndGame.class);
        i.putExtra("puntos",puntos);
        startActivity(i);
    }
}
