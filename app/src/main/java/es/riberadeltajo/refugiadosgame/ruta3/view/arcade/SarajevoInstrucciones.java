package es.riberadeltajo.refugiadosgame.ruta3.view.arcade;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import es.riberadeltajo.refugiadosgame.R;
import es.riberadeltajo.refugiadosgame.common.models.PlayerStatus;

public class SarajevoInstrucciones extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PlayerStatus.getInstancia(this).setRuta(3);
        PlayerStatus.getInstancia(this).setTramo(8);
        setContentView(R.layout.activity_sarajevo_instrucciones);
        Button btnJugar = (Button) findViewById(R.id.btnJugar);
        btnJugar.setOnClickListener(this);
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
    }

    @Override
    public void onBackPressed() {
        new MenuDialog(this).show();
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnJugar) {
            startActivity(new Intent(this, SarajevoArcade.class));
        }
    }
}
