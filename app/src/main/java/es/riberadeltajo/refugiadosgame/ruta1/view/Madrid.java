package es.riberadeltajo.refugiadosgame.ruta1.view;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.os.Process;

import es.riberadeltajo.refugiadosgame.R;
import es.riberadeltajo.refugiadosgame.common.models.PlayerStatus;
import es.riberadeltajo.refugiadosgame.ruta1.view.view.Madrid_Fin;
import es.riberadeltajo.refugiadosgame.ruta1.view.view.Madrid_Main;
import es.riberadeltajo.refugiadosgame.ruta1.view.view.Madrid_Maletas;
import es.riberadeltajo.refugiadosgame.ruta1.view.view.Madrid_Trans;
import es.riberadeltajo.refugiadosgame.ruta2.view.Milan;

public class Madrid extends AppCompatActivity {
    private Button btnPlay, btnExit, btnMaletas;
    private Button easy,med,hard,ex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON); //Evita que se apague la pantalla
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                        | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_madrid);
        btnPlay=(Button) findViewById(R.id.btnJugar);
        btnPlay=(Button) findViewById(R.id.btnSalir);
        btnMaletas=(Button) findViewById(R.id.btnMaletas);
        easy=(Button) findViewById(R.id.btnEasy);
        med=(Button) findViewById(R.id.btnMed);
        hard=(Button) findViewById(R.id.btnHard);
        ex=(Button) findViewById(R.id.btnExtreme);
    }

    public void jugar(View v){
        startActivity(new Intent(this, Madrid_Arcade.class));
    }

    public void reset(){
        startActivity(new Intent(this, Madrid_Arcade.class));
    }

    public void salir(View v){
        finish();
    }

    public void goToNextLevel() {
        startActivity(new Intent(this, Milan.class));
    }

    public void irMaletas(View v){
        startActivity(new Intent(this, Madrid_Main.class));
    }

    public void onResume(){
        super.onResume();
    }

    public void onPause(){
        super.onPause();
        onStop();
    }

    public void onStop(){
        super.onStop();
    }


    public void modoFacil(View v){
        PlayerStatus.getInstancia(this).setDinero(350);
        jugar(v);
    }
    public void modoMedio(View v){
        PlayerStatus.getInstancia(this).setDinero(250);
        jugar(v);
    }
    public void modoDificil(View v){
        PlayerStatus.getInstancia(this).setDinero(150);
        jugar(v);
    }
    public void modoExtremo(View v){
        PlayerStatus.getInstancia(this).setDinero(50);
        jugar(v);
    }
}
