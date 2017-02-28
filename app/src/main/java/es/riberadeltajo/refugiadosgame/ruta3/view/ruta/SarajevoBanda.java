package es.riberadeltajo.refugiadosgame.ruta3.view.ruta;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import es.riberadeltajo.refugiadosgame.common.models.PlayerStatus;

public class SarajevoBanda extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PlayerStatus.getInstancia(this).setRuta(3);
        PlayerStatus.getInstancia(this).setTramo(7);
    }

}
