package es.riberadeltajo.refugiadosgame.ruta5.view;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import es.riberadeltajo.refugiadosgame.R;
import es.riberadeltajo.refugiadosgame.common.models.PlayerStatus;

public class tehranentrevistabien extends AppCompatActivity implements View.OnClickListener{
    private ImageView opc1,passport;
    private TextView texto,dinero,objeto;
    private Button btnAtras, btnSig;
    private int activity;

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
        setContentView(R.layout.activity_una_opcion);
        getWindow().getDecorView().setBackgroundResource(R.drawable.fondoteheran); //Pon un fondo de la ciudad de tu ruta
        setActivity(getIntent().getIntExtra("activity",0));
        opc1=(ImageView) findViewById(R.id.opcion1); //ImageView de la opción 1
        texto=(TextView) findViewById(R.id.txtMens1); //TextView de la historia
        Typeface font = Typeface.createFromAsset(getApplicationContext().getAssets(), "tipografias/madrid_dialog_font.ttf");
        texto.setTypeface(font);
        texto.setText(getString(R.string.tehran_text_bien));
        dinero=(TextView) findViewById(R.id.txtMoney); //TextView del dinero
        objeto=(TextView) findViewById(R.id.txtObjeto); //TextView del objeto en caso de que tu historia lo tenga
        dinero.setTypeface(font);
        dinero.setVisibility(View.INVISIBLE);
        objeto.setTypeface(font);
        dinero.setText(String.valueOf(PlayerStatus.getInstancia(this).getDinero())); //Cojo el dinero del PlayerStatus
        objeto.setText(String.valueOf(PlayerStatus.getInstancia(this).getObjeto())); //Cojo el objeto del PlayerStatus
        opc1.setImageResource(R.drawable.tehranentrevistabien); //Imagen para la opción 1
        btnAtras=(Button) findViewById(R.id.btnBack);
        btnAtras.setVisibility(View.INVISIBLE);
        btnSig=(Button) findViewById(R.id.btnNext);
        btnAtras.setOnClickListener(this);
        btnSig.setOnClickListener(this);
        passport=(ImageView) findViewById(R.id.imgObject);
        passport.setImageResource(R.drawable.madrid_passport);
        passport.setVisibility(View.INVISIBLE); //Si en tu historia no vas a usar ningún otro objeto, cambiar a INVISIBLE
        objeto.setVisibility(View.INVISIBLE); //Si en tu historia no vas a usar ningún otro objeto, cambiar a INVISIBLE
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
        //Activity anterior
    }

    public void goNext(){
        if(getActivity()==5){
            Intent i = new Intent(this,tehran6.class);
            startActivity(i);
            finish();
        }
        else if(getActivity()==6){
            Intent i = new Intent(this,tehran7.class);
            startActivity(i);
            finish();
        }
        else if(getActivity()==7){
            Intent i = new Intent(this,tehran8.class);
            startActivity(i);
            finish();
        }
        else if(getActivity()==8){
            Intent i = new Intent(this,tehran9.class);
            startActivity(i);
            finish();
        }
        else if(getActivity()==9){
            Intent i = new Intent(this,tehran10.class);
            startActivity(i);
            finish();
        }
        else if(getActivity()==0){
            Intent i = new Intent(this,tehran4.class);
            startActivity(i);
            finish();
        }
    }

    public int getActivity() {
        return activity;
    }

    public void setActivity(int activity) {
        this.activity = activity;
    }
}
