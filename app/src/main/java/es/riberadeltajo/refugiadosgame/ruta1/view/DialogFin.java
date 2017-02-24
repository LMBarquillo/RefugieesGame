package es.riberadeltajo.refugiadosgame.ruta1.view;

import android.app.Dialog;
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

public class DialogFin extends Dialog implements View.OnClickListener {

    public enum Tipo {
        WIN,
        LOSE
    }

    private Tipo tipo;
    private Madrid activity;
    private ImageView ganaPierde,reintentar,irMenu,sigNiv;
    private Typeface font;

    public DialogFin(Madrid activity, Tipo tipo) {
        super(activity, R.style.AppTheme);
        this.activity = activity;
        this.tipo = tipo;
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
        setContentView(R.layout.dialogfin_madrid);
        ganaPierde = (ImageView) findViewById(R.id.imgAcc);
        TextView txtMensaje = (TextView) findViewById(R.id.txtMens);
        font = Typeface.createFromAsset(getContext().getApplicationContext().getAssets(),"tipografias/madrid_dialog_font.ttf");
        txtMensaje.setTypeface(font);
        reintentar = (ImageView) findViewById(R.id.imgTryAgain);
        irMenu = (ImageView) findViewById(R.id.imgMenu);
        sigNiv = (ImageView) findViewById(R.id.imgNextLevel);
        reintentar.setImageResource(R.drawable.madrid_tryagain);
        irMenu.setImageResource(R.drawable.madrid_menu);
        sigNiv.setImageResource(R.drawable.madrid_nextlevel);
        reintentar.setOnClickListener(this);
        irMenu.setOnClickListener(this);
        sigNiv.setOnClickListener(this);
        reintentar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    reintentar.setImageResource(R.drawable.madrid_tryagain_hover);
                }
                else if(motionEvent.getAction() == MotionEvent.ACTION_UP){
                    reintentar.setImageResource(R.drawable.madrid_tryagain);
                    reset();
                }
                return true;
            }
        });
        irMenu.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    irMenu.setImageResource(R.drawable.madrid_menu_hover);
                }
                else if(motionEvent.getAction() == MotionEvent.ACTION_UP){
                    irMenu.setImageResource(R.drawable.madrid_menu);
                    goToMenu();
                }
                return true;
            }
        });
        sigNiv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    sigNiv.setImageResource(R.drawable.madrid_nextlevel_hover);
                }
                else if(motionEvent.getAction() == MotionEvent.ACTION_UP){
                    sigNiv.setImageResource(R.drawable.madrid_nextlevel);
                    goToNextLevel();
                }
                return true;
            }
        });
        if(tipo == Tipo.WIN) {
            ganaPierde.setImageResource(R.drawable.madrid_win);
            txtMensaje.setText(R.string.madrid_win_mensaje);
        } else
        if(tipo == Tipo.LOSE) {
            ganaPierde.setImageResource(R.drawable.madrid_lose);
            txtMensaje.setText(R.string.madrid_lose_mensaje);
            sigNiv.setImageResource(R.drawable.madrid_nextlevel_disabled);
            sigNiv.setEnabled(false);
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
            case R.id.imgTryAgain:
                reset();
                break;
            case R.id.imgMenu:
                goToMenu();
                break;
            case R.id.imgNextLevel:
                goToNextLevel();
                break;
        }
    }

    private void reset() {
        dismiss();
        activity.reset();
    }

    private void goToMenu(){
        activity.onPause();
        dismiss();
    }

    private void goToNextLevel() {
        dismiss();
        activity.goToNextLevel();
    }
}
