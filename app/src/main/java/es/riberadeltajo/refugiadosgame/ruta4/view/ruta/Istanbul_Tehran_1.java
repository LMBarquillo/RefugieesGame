package es.riberadeltajo.refugiadosgame.ruta4.view.ruta;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import es.riberadeltajo.refugiadosgame.R;
import es.riberadeltajo.refugiadosgame.common.models.PlayerStatus;
import es.riberadeltajo.refugiadosgame.common.view.MainActivity2;

public class Istanbul_Tehran_1 extends AppCompatActivity implements View.OnClickListener{
    private ImageView opc1,opc2,passport;
    private TextView texto,dinero,objeto,descOpc1,descOpc2;
    private Button btnAtras, btnSig;
    private final int OPCION_A=1;
    private final int OPCION_B=2;
    private int cont=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dos_opciones);
        PlayerStatus.getInstancia(this).setRuta(4);
        PlayerStatus.getInstancia(this).setTramo(2);
        getWindow().getDecorView().setBackgroundResource(R.drawable.sultanahmetcamii);
        opc1=(ImageView) findViewById(R.id.opcion1); //ImageView de la opción 1
        opc2=(ImageView) findViewById(R.id.opcion2); //ImageView de la opción 2
        texto=(TextView) findViewById(R.id.txtMens2); //TextView de la historia
        Typeface font = Typeface.createFromAsset(getApplicationContext().getAssets(), "tipografias/madrid_dialog_font.ttf");
        texto.setTypeface(font);
        // Texto principal
        texto.setText(R.string.istanbul_route_ship);
        // Rellenamos el dinero y los objetos
        dinero=(TextView) findViewById(R.id.txtMoney); //TextView del dinero
        objeto=(TextView) findViewById(R.id.txtObjeto); //TextView del objeto en caso de que tu historia lo tenga
        dinero.setTypeface(font);
        objeto.setTypeface(font);
        dinero.setText(String.valueOf(PlayerStatus.getInstancia(this).getDinero())); //Cojo el dinero del PlayerStatus
        objeto.setText(String.valueOf(PlayerStatus.getInstancia(this).getObjeto())); //Cojo el objeto del PlayerStatus

        descOpc1=(TextView) findViewById(R.id.txtOpc1Desc); //TextView para descripción de la opción 1
        descOpc2=(TextView) findViewById(R.id.txtOpc2Desc); //TextView para descripción de la opción 2
        descOpc1.setTypeface(font);
        descOpc2.setTypeface(font);
        descOpc1.setText(R.string.istanbul_route_buyit);
        descOpc2.setText(R.string.istanbul_route_stealit);
        opc1.setImageResource(R.drawable.istanbulbarcoentero); //Imagen para la opción 1
        opc2.setImageResource(R.drawable.istanbulticketbarco); //Imagen para la opción 2
        opc1.setOnClickListener(this);
        opc2.setOnClickListener(this);

        btnAtras=(Button) findViewById(R.id.btnBack);
        btnSig=(Button) findViewById(R.id.btnNext);
        btnAtras.setOnClickListener(this);
        btnSig.setOnClickListener(this);

        passport=(ImageView) findViewById(R.id.imgObject);
        passport.setImageResource(R.drawable.madrid_passport);
        passport.setVisibility(View.VISIBLE); //Si en tu historia no vas a usar ningún otro objeto, cambiar a INVISIBLE
        objeto.setVisibility(View.VISIBLE); //Si en tu historia no vas a usar ningún otro objeto, cambiar a INVISIBLE
        descOpc1.setVisibility(View.VISIBLE); //Si en tu historia no vas describir la imagen, cambiar a INVISIBLE
        descOpc2.setVisibility(View.VISIBLE); //Si en tu historia no vas describir la imagen, cambiar a INVISIBLE
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
        if(PlayerStatus.getInstancia(this).getDinero()>=200) {
            opc1.setBackgroundResource(R.drawable.menu2); //Cambio de .xml para resaltar la opción seleccionada
            opc2.setBackgroundResource(R.drawable.menu1); //Hago lo contrario para que no pueda haber 2 seleccionadas
            descOpc1.setBackgroundResource(R.drawable.menu2); //Cambio de .xml para resaltar la opción seleccionada
            descOpc2.setBackgroundResource(R.drawable.menu1); //Hago lo contrario para que no pueda haber 2 seleccionadas
            cont = 1;
        }
        else{
            Toast toast = new Toast(this);
            Typeface font = Typeface.createFromAsset(getApplicationContext().getAssets(), "tipografias/madrid_dialog_font.ttf");
            View toast_layout=getLayoutInflater().inflate(R.layout.msg_toast, (ViewGroup) findViewById(R.id.toastlayout));
            toast.setView(toast_layout);
            TextView msgtoast = (TextView) toast_layout.findViewById(R.id.txtMsgToast);
            msgtoast.setTypeface(font);
            msgtoast.setText(R.string.istanbul_route_notenoughmoney);
            toast.setGravity(Gravity.CENTER,toast_layout.getWidth()/2,toast_layout.getHeight()/2);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public void opcion2(){
        opc2.setBackgroundResource(R.drawable.menu2); //Cambio de .xml para resaltar la opción seleccionada
        opc1.setBackgroundResource(R.drawable.menu1); //Hago lo contrario para que no pueda haber 2 seleccionadas
        descOpc2.setBackgroundResource(R.drawable.menu2); //Cambio de .xml para resaltar la opción seleccionada
        descOpc1.setBackgroundResource(R.drawable.menu1); //Hago lo contrario para que no pueda haber 2 seleccionadas
        cont=2;
    }

    public void goBack(){
        startActivity(new Intent(this, MainActivity2.class));
    }

    public void goNext(){
        if(cont==OPCION_A){
            PlayerStatus.getInstancia(this).setDinero(PlayerStatus.getInstancia(this).getDinero()-200);
            startActivity(new Intent(this, Istanbul_Tehran_SinkingShip.class));
            finish();
        }
        else if(cont==OPCION_B){
            startActivity(new Intent(this, Istanbul_Tehran_TicketStealed.class));
            finish();
        }
        else{
            Toast toast = new Toast(this);
            Typeface font = Typeface.createFromAsset(getApplicationContext().getAssets(), "tipografias/madrid_dialog_font.ttf");
            View toast_layout=getLayoutInflater().inflate(R.layout.msg_toast, (ViewGroup) findViewById(R.id.toastlayout));
            toast.setView(toast_layout);
            TextView msgtoast = (TextView) toast_layout.findViewById(R.id.txtMsgToast);
            msgtoast.setTypeface(font);
            msgtoast.setText(R.string.toastSiguiente);
            toast.setGravity(Gravity.CENTER,toast_layout.getWidth()/2,toast_layout.getHeight()/2);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
