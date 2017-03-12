package es.riberadeltajo.refugiadosgame.ruta4.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import es.riberadeltajo.refugiadosgame.R;
import es.riberadeltajo.refugiadosgame.common.models.PlayerStatus;
import es.riberadeltajo.refugiadosgame.common.view.MainActivity;
import es.riberadeltajo.refugiadosgame.ruta5.view.tehran1;

public class EndGame extends AppCompatActivity {
    private TextView quantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_end_game);
        quantity = (TextView) findViewById(R.id.textQuantity);
        Intent entrada = getIntent();
        int monedas = entrada.getIntExtra("puntos",0);
        quantity.setText(String.valueOf(monedas));
        // Añadimos el dinero ganado al status
        PlayerStatus status = PlayerStatus.getInstancia(this);
        status.setDinero(status.getDinero()+monedas);
    }

    protected void nextRoute(View view) {
        Intent i = new Intent(this,tehran1.class);
        startActivity(i);
    }

    protected void playAgain(View view) {
        Intent i = new Intent(this,StreetGuitar.class);
        startActivity(i);
    }

    protected void mainMenu(View view) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    public void onBackPressed() {
        // super.onBackPressed();
    }
}
