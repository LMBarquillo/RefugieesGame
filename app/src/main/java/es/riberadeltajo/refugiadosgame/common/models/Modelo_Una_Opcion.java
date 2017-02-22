package es.riberadeltajo.refugiadosgame.common.models;

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
    private TextView texto;
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
        getWindow().getDecorView().setBackgroundResource(R.drawable.madrid_history_fondo); //Pon un fondo de la ciudad de tu ruta
        opc1=(ImageView) findViewById(R.id.opcion1); //ImageView de la opción 1
        texto=(TextView) findViewById(R.id.texto); //TextView de la historia
        opc1.setImageResource(R.drawable.madrid_dibujo_camion); //Imagen para la opción 1
        btnAtras=(Button) findViewById(R.id.btnBack);
        btnSig=(Button) findViewById(R.id.btnNext);
        btnAtras.setOnClickListener(this);
        btnSig.setOnClickListener(this);
        passport=(ImageView) findViewById(R.id.imgpassport);
        passport.setImageResource(R.drawable.madrid_passport);
        setVisible(true); //Sólo visible en Madrid-Milan, si en tu historia no vas a usar ningún otro objeto, cambiar a false
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
        if(cont==OPCION_A){
            //Activity siguiente si se cumple opcion A
        }
        else if(cont==OPCION_B){
            //Activity siguiente si se cumple opcion B
        }
    }
}
