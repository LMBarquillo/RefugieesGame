package es.riberadeltajo.refugiadosgame.ruta1.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.os.Process;

import es.riberadeltajo.refugiadosgame.R;
import es.riberadeltajo.refugiadosgame.ruta2.view.Milan;

public class Madrid extends AppCompatActivity {
    private GameView gameView;
    //private Button btnPlay, btnExit;

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
        reset(); //Temporal
        /*setContentView(R.layout.activity_madrid);
        btnPlay=(Button) findViewById(R.id.btnJugar);
        btnPlay=(Button) findViewById(R.id.btnSalir);*/
    }

    public void jugar(View v){
        gameView=new GameView(this);
        setContentView(gameView);
    }

    public void reset(){
        gameView=new GameView(this);
        setContentView(gameView);
    }

    public void salir(View v){
        finish();
    }

    public void goToNextLevel() {
        startActivity(new Intent(this, Milan.class));
    }

    public void onResume(){
        super.onResume();
        /*musica=MediaPlayer.create(this,R.raw.cantina);
        musica.setLooping(true);
        musica.start();*/
    }

    public void onPause(){
        super.onPause();
        /*if(musica!=null){
            musica.stop();
            musica.release();
        }*/
        onStop();
    }

    public void onStop(){
        super.onStop();
    }

    public void onDestroy(){
        super.onDestroy();
        Process.killProcess(Process.myPid());
    }
}
