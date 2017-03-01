package es.riberadeltajo.refugiadosgame.ruta1.view.view;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.VideoView;

import es.riberadeltajo.refugiadosgame.R;
import es.riberadeltajo.refugiadosgame.common.view.MainActivity;
import es.riberadeltajo.refugiadosgame.ruta1.view.Madrid_Arcade;

public class Madrid_Help extends AppCompatActivity implements View.OnClickListener{
    private TextView texto;
    private Button btnAtras, btnSig;
    private VideoView videoView;

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
        setContentView(R.layout.activity_madrid_help);
        getWindow().getDecorView().setBackgroundResource(R.drawable.madrid_history_fondo); //Pon un fondo de la ciudad de tu ruta
        videoView=(VideoView) findViewById(R.id.madInstrucc);
        Uri video = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.madrid_help); //do not add any extension
        videoView.setVideoURI(video);
        videoView.start();
        texto=(TextView) findViewById(R.id.txtMens1); //TextView de la historia
        Typeface font = Typeface.createFromAsset(getApplicationContext().getAssets(), "tipografias/madrid_dialog_font.ttf");
        texto.setTypeface(font);
        texto.setText(R.string.madrid_instrucc);
        btnAtras=(Button) findViewById(R.id.btnBack);
        btnSig=(Button) findViewById(R.id.btnNext);
        btnAtras.setOnClickListener(this);
        btnSig.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btnBack:
                goBack();
                break;
            case R.id.btnNext:
                goNext();
                break;
        }
    }

    public void goBack(){
        startActivity(new Intent(this, MainActivity.class));
    }

    public void goNext(){
        startActivity(new Intent(this, Madrid_Arcade.class));
    }
}
