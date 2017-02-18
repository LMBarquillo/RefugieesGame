package es.riberadeltajo.refugiadosgame.ruta2.view.juego;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;



public class milan_juego extends Activity {

    private int reques_code=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new GameView(this));


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
}
