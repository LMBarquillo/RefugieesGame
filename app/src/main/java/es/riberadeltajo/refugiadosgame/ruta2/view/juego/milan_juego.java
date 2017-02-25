package es.riberadeltajo.refugiadosgame.ruta2.view.juego;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;



public class milan_juego extends Activity {

    private int reques_code=1;
    GameView pantalla;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pantalla=new GameView(this);
        setContentView(pantalla);


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==reques_code){
            if(resultCode==RESULT_OK){
                Bundle bundle=data.getExtras();
                boolean resultado=bundle.getBoolean("resultado");
                if(resultado){
                    Intent i=new Intent();
                    i.putExtra("resultado", resultado);
                   setResult(Activity.RESULT_OK, i);
                   finish();
                }
                else{
                    setContentView(new GameView(this));
                }


            }
        }
    }
    @Override
    protected void onPause(){

        pantalla.salir();
        super.onPause();


    }
    @Override
    protected void onStop(){
        pantalla.salir();
        super.onStop();
    }
    @Override
    protected void onDestroy(){
        pantalla.salir();
        super.onDestroy();
    }
}
