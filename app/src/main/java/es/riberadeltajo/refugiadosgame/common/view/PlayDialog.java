package es.riberadeltajo.refugiadosgame.common.view;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import es.riberadeltajo.refugiadosgame.R;
import es.riberadeltajo.refugiadosgame.ruta1.view.Madrid;
import es.riberadeltajo.refugiadosgame.ruta3.view.Sarajevo;

/**
 * Created by Alex on 14/02/2017.
 */

public class PlayDialog extends Dialog implements View.OnClickListener {

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
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN );
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.menu_play_dialog);
        Button btnContinuar = (Button) findViewById(R.id.btnContinuar);
        Button btnNuevo = (Button) findViewById(R.id.btnNuevo);
        btnContinuar.setOnClickListener(this);
        btnNuevo.setOnClickListener(this);
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
        }
    }

    private void nuevo() {
        activity.startActivity(new Intent(activity, Madrid.class));
    }

    private void continuar() {
        activity.startActivity(new Intent(activity, Madrid.class));
    }
}