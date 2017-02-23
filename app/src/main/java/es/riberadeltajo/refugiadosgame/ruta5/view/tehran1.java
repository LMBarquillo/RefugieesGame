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
import es.riberadeltajo.refugiadosgame.common.view.MainActivity;

public class tehran1 extends AppCompatActivity implements View.OnClickListener{
    private ImageView opc1,passport;
    private TextView texto,dinero,objeto;
    private Button btnAtras, btnSig;
    private final int OPCION_A=1;
    private final int OPCION_B=2;
    private int cont=0;

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
        opc1=(ImageView) findViewById(R.id.opcion1); //ImageView de la opción 1
        texto=(TextView) findViewById(R.id.texto); //TextView de la historia
        Typeface font = Typeface.createFromAsset(getApplicationContext().getAssets(), "tipografias/madrid_dialog_font.ttf");
        texto.setTypeface(font);
        texto.setTextSize(22);
        dinero=(TextView) findViewById(R.id.txtMoney); //TextView del dinero
        objeto=(TextView) findViewById(R.id.txtObjeto); //TextView del objeto en caso de que tu historia lo tenga
        dinero.setText(String.valueOf(PlayerStatus.getInstancia(this).getDinero())); //Cojo el dinero del PlayerStatus
        objeto.setText(String.valueOf(PlayerStatus.getInstancia(this).getObjeto())); //Cojo el objeto del PlayerStatus
        opc1.setImageResource(R.drawable.tehranperiodico1); //Imagen para la opción 1
        btnAtras=(Button) findViewById(R.id.btnBack);
        btnSig=(Button) findViewById(R.id.btnNext);
        btnAtras.setOnClickListener(this);
        btnSig.setOnClickListener(this);
        passport=(ImageView) findViewById(R.id.imgObject);
        passport.setImageResource(R.drawable.madrid_passport);
        texto.setText(String.format("%s%n%s",getString(R.string.tehran_text1),getString(R.string.tehran_text1_2)));
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
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
        finish();
    }

    public void goNext(){
        Intent i = new Intent(this,tehran2.class);
        startActivity(i);
        finish();
    }
}
