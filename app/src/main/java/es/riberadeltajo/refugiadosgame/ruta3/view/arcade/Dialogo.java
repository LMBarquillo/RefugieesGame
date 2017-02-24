package es.riberadeltajo.refugiadosgame.ruta3.view.arcade;

import android.app.Dialog;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import es.riberadeltajo.refugiadosgame.R;

/**
 * Created by Alex on 14/02/2017.
 */

public class Dialogo extends Dialog implements View.OnClickListener {

    public enum Tipo {
        WIN,
        LOSE
    }

    private Tipo tipo;
    private SarajevoArcade activity;

    public Dialogo(SarajevoArcade activity, Tipo tipo) {
        super(activity, R.style.AppTheme);
        this.activity = activity;
        this.tipo = tipo;
        Display display = activity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        getWindow().setLayout((int)(size.x * 0.8), (int)(size.y * 0.8));
        getWindow().getAttributes().windowAnimations = R.style.sarajevoDialogo;
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN );
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.dialogo_sarajevo);
        TextView txtTitulo = (TextView) findViewById(R.id.txtTitulo);
        TextView txtMensaje = (TextView) findViewById(R.id.txtMensaje);
        Button btnReiniciar = (Button) findViewById(R.id.btnReiniciar);
        Button btnContinuar = (Button) findViewById(R.id.btnContinuar);
        btnReiniciar.setOnClickListener(this);
        btnContinuar.setOnClickListener(this);
        if(tipo == Tipo.WIN) {
            txtTitulo.setText(R.string.dialogo_win_titulo);
            txtMensaje.setText(R.string.dialogo_win_mensaje);
            btnContinuar.setEnabled(true);
        } else
        if(tipo == Tipo.LOSE) {
            txtTitulo.setText(R.string.dialogo_lose_titulo);
            txtMensaje.setText(R.string.dialogo_lose_mensaje);
            btnContinuar.setEnabled(false);
        }
    }

    @Override
    public void show() {
        // Set the dialog to not focusable.
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);

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
            case R.id.btnReiniciar:
                reiniciar();
                break;
            case R.id.btnContinuar:
                continuar();
                break;
        }
    }

    private void reiniciar() {
        dismiss();
        activity.recreate();
    }

    private void continuar() {
        dismiss();
        activity.continuar();
    }
}
