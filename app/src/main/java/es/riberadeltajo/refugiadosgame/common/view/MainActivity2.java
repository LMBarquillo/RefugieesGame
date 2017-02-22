package es.riberadeltajo.refugiadosgame.common.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import es.riberadeltajo.refugiadosgame.R;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener {

    private PlayDialog dialogoJugar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                        | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        dialogoJugar = new PlayDialog(this);
        Button btnPlay = (Button) findViewById(R.id.btnJugar);
        btnPlay.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btnJugar:
                jugar();
                break;
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        dialogoJugar.dismiss();
    }

    private void jugar() {
        dialogoJugar.show();
    }
}
