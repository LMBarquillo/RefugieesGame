package es.riberadeltajo.refugiadosgame.ruta1.view;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import es.riberadeltajo.refugiadosgame.R;

/**
 * Created by Alex on 14/02/2017.
 */

public class DialogSalirSiNo extends Dialog implements View.OnClickListener {

    private Madrid activity;
    private ImageView si,no;
    private Typeface font;

    public DialogSalirSiNo(Madrid activity) {
        super(activity, R.style.AppTheme);
        this.activity = activity;
        Display display = activity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        getWindow().setLayout(size.x,(int)(size.y*1.2));
        getWindow().getAttributes().windowAnimations = R.style.madridDialogo;
        Drawable d = new ColorDrawable(Color.BLACK);
        d.setAlpha(150);
        getWindow().setBackgroundDrawable(d);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN );
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.dialogsalirsino_madrid);
        TextView txtMensaje = (TextView) findViewById(R.id.txtMens);
        font = Typeface.createFromAsset(getContext().getApplicationContext().getAssets(),"tipografias/madrid_dialog_font.ttf");
        txtMensaje.setTypeface(font);
        txtMensaje.setText(R.string.madrid_salirJuego);
        si = (ImageView) findViewById(R.id.imgSi);
        no = (ImageView) findViewById(R.id.imgNo);
        si.setImageResource(R.drawable.madrid_yes);
        no.setImageResource(R.drawable.madrid_no);
        si.setOnClickListener(this);
        no.setOnClickListener(this);
        si.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    si.setImageResource(R.drawable.madrid_yes_hover);
                }
                else if(motionEvent.getAction() == MotionEvent.ACTION_UP){
                    si.setImageResource(R.drawable.madrid_yes);
                    salir();
                }
                return true;
            }
        });
        no.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    no.setImageResource(R.drawable.madrid_no_hover);
                }
                else if(motionEvent.getAction() == MotionEvent.ACTION_UP){
                    no.setImageResource(R.drawable.madrid_no);
                    noSalir();
                }
                return true;
            }
        });
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
            case R.id.imgTryAgain:
                salir();
                break;
            case R.id.imgNextLevel:
                noSalir();
                break;
        }
    }

    private void salir() {
        activity.recreate();
        dismiss();
    }

    private void noSalir() {
        dismiss();
    }
}
