package es.riberadeltajo.refugiadosgame.ruta4.view.engine;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

/**
 * Implementa los métodos para que cualquier View de juego pueda ser reconocida por el gameloop
 * Created by Luismi on 12/03/2017.
 */

public interface GameSurface {
    public void actualizar();
    public void dibujar(Canvas canvas);
    public SurfaceHolder getHolder();
    public void detener();
}
