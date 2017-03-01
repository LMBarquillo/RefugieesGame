package es.riberadeltajo.refugiadosgame.ruta3.view.arcade;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import es.riberadeltajo.refugiadosgame.R;
import es.riberadeltajo.refugiadosgame.common.view.MainActivity2;

/**
 * Created by Alex on 14/02/2017.
 */

public class MenuDialog extends Dialog implements View.OnClickListener {

    private Activity activity;

    public MenuDialog(Activity activity) {
        super(activity, R.style.AppTheme);
        this.activity = activity;
        Display display = activity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getRealSize(size);
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
        setContentView(R.layout.sarajevo_menu_dialog);
        Button btnSi = (Button) findViewById(R.id.btnSi);
        Button btnNo = (Button) findViewById(R.id.btnNo);
        btnSi.setOnClickListener(this);
        btnNo.setOnClickListener(this);
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
    public void onBackPressed() {

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btnSi:
                si();
                break;
            case R.id.btnNo:
                no();
                break;
        }
    }

    private void si() {
        activity.startActivity(new Intent(activity, MainActivity2.class));
    }

    private void no() {
        dismiss();
    }

}
