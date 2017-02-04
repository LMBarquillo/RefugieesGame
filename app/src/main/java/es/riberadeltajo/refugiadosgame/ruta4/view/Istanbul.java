package es.riberadeltajo.refugiadosgame.ruta4.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import es.riberadeltajo.refugiadosgame.ruta4.view.engine.GameView;


public class Istanbul extends AppCompatActivity {
    private GameView gameView;

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
        gameView = new GameView(this);
        setContentView(gameView);
    }

    @Override
    protected void onPause() {
        gameView.finish();
        super.onPause();
    }
}
