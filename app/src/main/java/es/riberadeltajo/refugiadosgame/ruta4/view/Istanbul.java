package es.riberadeltajo.refugiadosgame.ruta4.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import es.riberadeltajo.refugiadosgame.R;

public class Istanbul extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estambul);
    }

    // Miki, este método es para iniciar el juego, llámalo desde tu último activity.
    public void letsPlay() {
        Intent i = new Intent(this,GameMenu.class);
        startActivity(i);
    }
}
