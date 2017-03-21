package es.riberadeltajo.refugiadosgame.ruta5.view;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import es.riberadeltajo.refugiadosgame.R;
import es.riberadeltajo.refugiadosgame.common.models.PlayerStatus;
import es.riberadeltajo.refugiadosgame.common.view.MainActivity;
import es.riberadeltajo.refugiadosgame.ruta3.view.arcade.MenuDialog;

public class Tehran extends Activity {

    private MediaPlayer musica;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new GameView(this, PlayerStatus.getInstancia(this).isFabrica()));

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

        musica= MediaPlayer.create(this,R.raw.tehranmusica);
        musica.setLooping(true);
        musica.start();
    }

    public void onPause(){
        super.onPause();
        musica.stop();
        stopMusic();
    }

    public void onStop(){
        super.onStop();
        //android.os.Process.killProcess(android.os.Process.myPid());
    }

    /*public void onDestroy(){
        super.onDestroy();
        //releaseInstance();
    }*/

    public void stopMusic(){
        musica.stop();
    }

    public void reset(){
        if(musica!=null){
            musica.release();
        }
        setContentView(new GameView(this, PlayerStatus.getInstancia(this).isFabrica()));
        onResume();
    }

    public void goToNextLevel() {
        Intent i = new Intent(this,tehran11.class);
        startActivity(i);
        finish();
    }

    public void menuPrincipal(){
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    public void onBackPressed() {
        new MenuDialog(this).show();
    }

    public void fin(){
        //ABRIR PAGINA DE FIN JUEGO/CREDITOS
        onDestroy();
        finish();
    }
}
