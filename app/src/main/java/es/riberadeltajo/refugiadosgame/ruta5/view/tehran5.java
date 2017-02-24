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

public class tehran5 extends AppCompatActivity implements View.OnClickListener{
    private ImageView opc1,opc2,passport;
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
        setContentView(R.layout.activity_dos_opciones);
        getWindow().getDecorView().setBackgroundResource(R.drawable.fondoteheran); //Pon un fondo de la ciudad de tu ruta
        opc1=(ImageView) findViewById(R.id.opcion1); //ImageView de la opción 1
        opc2=(ImageView) findViewById(R.id.opcion2); //ImageView de la opción 2
        texto=(TextView) findViewById(R.id.txtMens2); //TextView de la historia
        Typeface font = Typeface.createFromAsset(getApplicationContext().getAssets(), "tipografias/madrid_dialog_font.ttf");
        texto.setTypeface(font);
        texto.setText(R.string.tehran_text4_2);
        dinero=(TextView) findViewById(R.id.txtMoney); //TextView del dinero
        objeto=(TextView) findViewById(R.id.txtObjeto); //TextView del objeto en caso de que tu historia lo tenga
        dinero.setTypeface(font);
        dinero.setVisibility(View.INVISIBLE);
        objeto.setTypeface(font);
        dinero.setText(String.valueOf(PlayerStatus.getInstancia(this).getDinero())); //Cojo el dinero del PlayerStatus
        objeto.setText(String.valueOf(PlayerStatus.getInstancia(this).getObjeto())); //Cojo el objeto del PlayerStatus
        opc1.setImageResource(R.drawable.tehranyes); //Imagen para la opción 1
        opc2.setImageResource(R.drawable.tehranno); //Imagen para la opción 2
        opc1.setOnClickListener(this);
        opc2.setOnClickListener(this);
        btnAtras=(Button) findViewById(R.id.btnBack);
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
            case R.id.opcion1:
                opcion1();
                break;
            case R.id.opcion2:
                opcion2();
                break;
            case R.id.btnBack:
                goBack();
                break;
            case R.id.btnNext:
                goNext();
                break;
        }
    }

    public void opcion1(){
        opc1.setBackgroundResource(R.drawable.menu2); //Cambio de .xml para resaltar la opción seleccionada
        opc2.setBackgroundResource(R.drawable.menu1); //Hago lo contrario para que no pueda haber 2 seleccionadas
        cont=1;
    }

    public void opcion2(){
        opc2.setBackgroundResource(R.drawable.menu2); //Cambio de .xml para resaltar la opción seleccionada
        opc1.setBackgroundResource(R.drawable.menu1); //Hago lo contrario para que no pueda haber 2 seleccionadas
        cont=2;
    }

    public void goBack(){
        Intent i = new Intent(this,tehran4.class);
        startActivity(i);
        finish();
    }

    public void goNext(){
        if(cont==OPCION_A){
            //Activity siguiente si se cumple opcion A
        }
        else if(cont==OPCION_B){
            //Activity siguiente si se cumple opcion B
        }
    }
}
