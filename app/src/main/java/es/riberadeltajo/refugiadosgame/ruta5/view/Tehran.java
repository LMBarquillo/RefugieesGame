package es.riberadeltajo.refugiadosgame.ruta5.view;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import es.riberadeltajo.refugiadosgame.R;

public class Tehran extends Activity {

    private boolean camarero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        camarero=getIntent().getBooleanExtra("camarero",true);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                        | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        super.onCreate(savedInstanceState);
        setContentView(new GameView(this,camarero));

    }

    public void fin(){
        //ABRIR PAGINA DE FIN JUEGO/CREDITOS
        finish();
    }
}
