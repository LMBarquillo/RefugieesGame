package es.riberadeltajo.refugiadosgame.ruta1.view.view;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import es.riberadeltajo.refugiadosgame.R;
import es.riberadeltajo.refugiadosgame.common.models.PlayerStatus;
import es.riberadeltajo.refugiadosgame.common.view.MainActivity;
import es.riberadeltajo.refugiadosgame.common.view.MainActivity2;
import es.riberadeltajo.refugiadosgame.ruta1.view.GameView;
import es.riberadeltajo.refugiadosgame.ruta1.view.Madrid_Arcade;

public class Madrid_Fin extends AppCompatActivity implements View.OnClickListener{
    private TextView texto;
    private Button btnAtras, btnSig;
    private GameView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_fin);
        PlayerStatus.getInstancia(this).setRuta(1);
        PlayerStatus.getInstancia(this).setTramo(18);
        getWindow().getDecorView().setBackgroundResource(R.drawable.madrid_history_fondo); //Pon un fondo de la ciudad de tu ruta
        texto=(TextView) findViewById(R.id.txtMens1); //TextView de la historia
        Typeface font = Typeface.createFromAsset(getApplicationContext().getAssets(), "tipografias/madrid_dialog_font.ttf");
        texto.setTypeface(font);
        if(PlayerStatus.getInstancia(this).getDinero()==350) {
            texto.setText(R.string.madrid_fin_easy);
        }
        else if(PlayerStatus.getInstancia(this).getDinero()==250) {
            texto.setText(R.string.madrid_fin_medium);
        }
        else if(PlayerStatus.getInstancia(this).getDinero()==150) {
            texto.setText(R.string.madrid_fin_hard);
        }
        else if(PlayerStatus.getInstancia(this).getDinero()==50) {
            texto.setText(R.string.madrid_fin_extreme);
        }
        btnAtras=(Button) findViewById(R.id.btnBack);
        btnSig=(Button) findViewById(R.id.btnNext);
        btnAtras.setOnClickListener(this);
        btnSig.setOnClickListener(this);
    }

    @Override
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
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btnBack:
                goBack();
                break;
            case R.id.btnNext:
                goNext(v);
                break;
        }
    }

    public void goBack(){
        startActivity(new Intent(this, MainActivity2.class));
        finish();
    }

    public void goNext(View v){
        startActivity(new Intent(this, Madrid_Help.class));
        finish();
    }
}
