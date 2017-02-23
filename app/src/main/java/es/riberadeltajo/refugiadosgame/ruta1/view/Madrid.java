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
import es.riberadeltajo.refugiadosgame.ruta1.view.view.Madrid_Maletas;
import es.riberadeltajo.refugiadosgame.ruta1.view.view.Madrid_Trans;
import es.riberadeltajo.refugiadosgame.ruta2.view.Milan;

public class Madrid extends AppCompatActivity {
    public static final int MENU = 1;
    public static final int JUEGO_EASY = 2;
    public static final int JUEGO_MEDIUM = 3;
    public static final int JUEGO_HARD = 4;
    public static final int JUEGO_EXTREME = 5;

    private GameView gameView;
    private Button btnPlay, btnExit, btnMaletas;
    private MediaPlayer musica;

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
        //reset(); //Temporal
        setContentView(R.layout.activity_madrid);
        btnPlay=(Button) findViewById(R.id.btnJugar);
        btnPlay=(Button) findViewById(R.id.btnSalir);
        btnMaletas=(Button) findViewById(R.id.btnMaletas);
    }

    public void jugar(View v){
        musica.stop();
        musica.release();
        musica=MediaPlayer.create(this,R.raw.madrid_easy);
        musica.setLooping(true);
        musica.start();
        gameView=new GameView(this);
        setContentView(gameView);
    }

    public void reset(){
        if(musica!=null){
            musica.stop();
            musica.release();
        }
        musica=MediaPlayer.create(this,R.raw.madrid_easy);
        musica.setLooping(true);
        musica.start();
        gameView=new GameView(this);
        setContentView(gameView);
    }

    public void salir(View v){
        finish();
    }

    public void goToNextLevel() {
        startActivity(new Intent(this, Milan.class));
    }

    public void irMaletas(View v){
        startActivity(new Intent(this, Madrid_Maletas.class));
    }

    public void onResume(){
        super.onResume();
        musica=MediaPlayer.create(this,R.raw.madrid_historia);
        musica.setLooping(true);
        musica.start();
    }

    public void onPause(){
        super.onPause();
        if(musica!=null){
            musica.stop();
            musica.release();
        }
        onStop();
    }

    public void onStop(){
        super.onStop();
    }

    public void onDestroy(){
        super.onDestroy();
        Process.killProcess(Process.myPid());
    }

    @Override
    public void onBackPressed () {
        new DialogSalirSiNo(gameView.getContexto()).show();
    }

    public MediaPlayer getMusica() {
        return musica;
    }

    public void setMusica(MediaPlayer musica) {
        this.musica = musica;
    }
}
