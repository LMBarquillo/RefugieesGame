package es.riberadeltajo.refugiadosgame.ruta1.view.view;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import org.w3c.dom.Text;

import es.riberadeltajo.refugiadosgame.R;
import es.riberadeltajo.refugiadosgame.common.models.PlayerStatus;
import es.riberadeltajo.refugiadosgame.common.view.MainActivity;
import es.riberadeltajo.refugiadosgame.common.view.MainActivity2;
import es.riberadeltajo.refugiadosgame.ruta1.view.Madrid_Arcade;

public class Madrid_Help extends AppCompatActivity implements View.OnClickListener{
    private TextView goal,texto,nivel;
    private Button btnAtras, btnSig;
    private VideoView videoView;
    private ImageView instrucc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_madrid_help);
        PlayerStatus.getInstancia(this).setRuta(1);
        PlayerStatus.getInstancia(this).setTramo(19);
        getWindow().getDecorView().setBackgroundResource(R.drawable.madrid_history_fondo); //Pon un fondo de la ciudad de tu ruta
        Typeface font = Typeface.createFromAsset(getApplicationContext().getAssets(), "tipografias/madrid_dialog_font.ttf");
        goal=(TextView) findViewById(R.id.txtGoal);
        goal.setTypeface(font);
        goal.setText(R.string.madrid_help_goal);
        nivel=(TextView) findViewById(R.id.txtNivel);
        nivel.setTypeface(font);
        videoView=(VideoView) findViewById(R.id.madInstrucc);
        Uri video = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.madrid_help_video); //do not add any extension
        videoView.setVideoURI(video);
        videoView.start();
        videoView.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(!videoView.isPlaying()) {
                    videoView.start();
                }
                return true;
            }
        });
        instrucc=(ImageView) findViewById(R.id.imgInstrucc);
        if(PlayerStatus.getInstancia(this).getDinero()==350) {
            instrucc.setImageResource(R.drawable.madrid_instrucc_easy);
            nivel.setText(R.string.madrid_help_easy);
            nivel.setTextColor(Color.argb(255,0,105,7));
        }
        else if(PlayerStatus.getInstancia(this).getDinero()==250) {
            instrucc.setImageResource(R.drawable.madrid_instrucc_medium);
            nivel.setText(R.string.madrid_help_medium);
            nivel.setTextColor(Color.argb(255,178,101,0));
        }
        else if(PlayerStatus.getInstancia(this).getDinero()==150) {
            instrucc.setImageResource(R.drawable.madrid_instrucc_hard);
            nivel.setText(R.string.madrid_help_hard);
            nivel.setTextColor(Color.argb(255,210,0,0));
        }
        else if(PlayerStatus.getInstancia(this).getDinero()==50) {
            instrucc.setImageResource(R.drawable.madrid_instrucc_extreme);
            nivel.setText(R.string.madrid_help_extreme);
            nivel.setTextColor(Color.argb(255,107,0,0));
        }
        texto=(TextView) findViewById(R.id.txtMens1); //TextView de la historia
        texto.setTypeface(font);
        texto.setText(R.string.madrid_instrucc);
        btnAtras=(Button) findViewById(R.id.btnBack);
        btnSig=(Button) findViewById(R.id.btnNext);
        btnSig.setText(R.string.madrid_jugar);
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
                goNext();
                break;
        }
    }

    public void goBack(){
        startActivity(new Intent(this, MainActivity2.class));
        finish();
    }

    public void goNext(){
        startActivity(new Intent(this, Madrid_Arcade.class));
        finish();
    }
}
