package es.riberadeltajo.refugiadosgame.ruta1.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import es.riberadeltajo.refugiadosgame.common.models.PlayerStatus;
import es.riberadeltajo.refugiadosgame.common.view.MainActivity;
import es.riberadeltajo.refugiadosgame.ruta2.view.Milan;

public class Madrid_Arcade extends AppCompatActivity {
    private GameView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                        | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        super.onCreate(savedInstanceState);
        PlayerStatus.getInstancia(this).setRuta(1);
        //PlayerStatus.getInstancia(this).setTramo(0);
        gameView = new GameView(this);
        setContentView(gameView);
    }

    public void start() {
        gameView = new GameView(this);
        setContentView(gameView);
    }

    public void continuar() {
        startActivity(new Intent(this, Milan.class));
    }

    public void goMenu(){
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public void onBackPressed () {
        new DialogSalirSiNo(gameView.getContexto()).show();
    }
}
