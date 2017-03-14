package es.riberadeltajo.refugiadosgame.common.models;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import es.riberadeltajo.refugiadosgame.R;

public class Modelo_Una_Opcion extends AppCompatActivity implements View.OnClickListener{
    private ImageView opc1,passport;
    private TextView texto,dinero,objeto,descOpc1;
    private Button btnAtras, btnSig;

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
        getWindow().getDecorView().setBackgroundResource(R.drawable.madrid_history_fondo); //Pon un fondo de la ciudad de tu ruta
        opc1=(ImageView) findViewById(R.id.opcion1); //ImageView de la opción 1
        texto=(TextView) findViewById(R.id.txtMens1); //TextView de la historia
        Typeface font = Typeface.createFromAsset(getApplicationContext().getAssets(), "tipografias/madrid_dialog_font.ttf");
        texto.setTypeface(font);
        texto.setText("Historia");
        dinero=(TextView) findViewById(R.id.txtMoney); //TextView del dinero
        objeto=(TextView) findViewById(R.id.txtObjeto); //TextView del objeto en caso de que tu historia lo tenga
        dinero.setTypeface(font);
        objeto.setTypeface(font);
        dinero.setText(String.valueOf(PlayerStatus.getInstancia(this).getDinero())); //Cojo el dinero del PlayerStatus
        objeto.setText(String.valueOf(PlayerStatus.getInstancia(this).getObjeto())); //Cojo el objeto del PlayerStatus
        descOpc1=(TextView) findViewById(R.id.txtOpcDesc); //TextView para descripción de la opción 1
        descOpc1.setTypeface(font);
        descOpc1.setText("Descripción");
        opc1.setImageResource(R.drawable.madrid_dibujo_camion); //Imagen para la opción 1
        btnAtras=(Button) findViewById(R.id.btnBack);
        btnSig=(Button) findViewById(R.id.btnNext);
        btnAtras.setOnClickListener(this);
        btnSig.setOnClickListener(this);
        passport=(ImageView) findViewById(R.id.imgObject);
        passport.setImageResource(R.drawable.madrid_passport);
        passport.setVisibility(View.VISIBLE); //Si en tu historia no vas a usar ningún otro objeto, cambiar a INVISIBLE
        objeto.setVisibility(View.VISIBLE); //Si en tu historia no vas a usar ningún otro objeto, cambiar a INVISIBLE
        descOpc1.setVisibility(View.VISIBLE); //Si en tu historia no vas describir la imagen, cambiar a INVISIBLE
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
        //Activity siguiente
    }
}
