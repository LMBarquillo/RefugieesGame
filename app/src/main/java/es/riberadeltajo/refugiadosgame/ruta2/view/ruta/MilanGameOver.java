package es.riberadeltajo.refugiadosgame.ruta2.view.ruta;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import es.riberadeltajo.refugiadosgame.R;

public class MilanGameOver extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_milan_game_over);
    }

    public void intentar(View view){
        startActivity(new Intent(this, MilanTren.class)); // aqui salta el game over
    }
}
