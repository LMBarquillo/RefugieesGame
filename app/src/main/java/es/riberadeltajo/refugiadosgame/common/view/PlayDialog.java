package es.riberadeltajo.refugiadosgame.common.view;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import es.riberadeltajo.refugiadosgame.R;
import es.riberadeltajo.refugiadosgame.common.models.PlayerStatus;
import es.riberadeltajo.refugiadosgame.ruta1.view.Madrid;
import es.riberadeltajo.refugiadosgame.ruta1.view.view.Madrid_Main;
import es.riberadeltajo.refugiadosgame.ruta3.view.arcade.Player;
import es.riberadeltajo.refugiadosgame.ruta3.view.arcade.SarajevoArcade;
import es.riberadeltajo.refugiadosgame.ruta3.view.ruta.SarajevoAfricano;
import es.riberadeltajo.refugiadosgame.ruta3.view.ruta.SarajevoAlojamiento;
import es.riberadeltajo.refugiadosgame.ruta3.view.ruta.SarajevoBanda;
import es.riberadeltajo.refugiadosgame.ruta3.view.ruta.SarajevoChino;
import es.riberadeltajo.refugiadosgame.ruta3.view.ruta.SarajevoHabitacion;
import es.riberadeltajo.refugiadosgame.ruta3.view.ruta.SarajevoMejicano;
import es.riberadeltajo.refugiadosgame.ruta3.view.ruta.SarajevoPasillo;

/**
 * Created by Alex on 14/02/2017.
 */

public class PlayDialog extends Dialog implements View.OnClickListener {

    private final Class[][] activities = {
        {null},
        {null},
        {SarajevoAlojamiento.class, SarajevoPasillo.class, SarajevoChino.class, SarajevoMejicano.class, SarajevoAfricano.class, SarajevoHabitacion.class, SarajevoBanda.class, SarajevoArcade.class},
        {null},
        {null}
    };

    private final int[] banderas = {
            R.drawable.bandera_madrid,
            R.drawable.bandera_italia,
            R.drawable.bandera_sarajevo,
            R.drawable.bandera_estambul,
            R.drawable.bandera_teheran
    };

    private MainActivity2 activity;

    public PlayDialog(MainActivity2 activity) {
        super(activity, R.style.AppTheme);
        this.activity = activity;
        Display display = activity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        //getWindow().setLayout((int)(size.x * 0.7), (int)(size.y * 0.5));
        getWindow().setLayout(size.x, size.y);
        getWindow().getAttributes().windowAnimations = R.style.sarajevoDialogo;
        //getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#80000000")));
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN );
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.menu_play_dialog2);
        ImageView imgBandera = (ImageView) findViewById(R.id.imgBandera);
        imgBandera.setImageResource(banderas[PlayerStatus.getInstancia(activity).getRuta() - 1]);
        Button btnContinuar = (Button) findViewById(R.id.btnContinuar);
        Button btnNuevo = (Button) findViewById(R.id.btnNuevo);
        Button btnSalir = (Button) findViewById(R.id.btnSalir);
        btnContinuar.setOnClickListener(this);
        btnNuevo.setOnClickListener(this);
        btnSalir.setOnClickListener(this);
    }

    @Override
    public void show() {
        // Set the dialog to not focusable.
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);

        //Copiamos la visibilidad de la actividad (Full Screen)
        getWindow().getDecorView().setSystemUiVisibility(activity.getWindow().getDecorView().getSystemUiVisibility());

        // Show the dialog with NavBar hidden.
        super.show();

        // Set the dialog to focusable again.
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btnNuevo:
                nuevo();
                break;
            case R.id.btnContinuar:
                continuar();
                break;
            case R.id.btnSalir:
                dismiss();
        }
    }

    private void nuevo() {
        PlayerStatus.getInstancia(activity).resetStatus();
        activity.startActivity(new Intent(activity, Madrid.class));
    }

    private void continuar() {
        Class clase = Madrid_Main.class;
        int ruta = PlayerStatus.getInstancia(activity).getRuta() - 1;
        int tramo = PlayerStatus.getInstancia(activity).getTramo() - 1;
        if(ruta >= 0 && ruta < activities.length){
            if(tramo >= 0 && tramo < activities[ruta].length) {
                if(activities[ruta][tramo] != null) {
                    clase = activities[ruta][tramo];
                }
            }
        }
        activity.startActivity(new Intent(activity, clase));
    }
}
