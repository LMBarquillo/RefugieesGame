package es.riberadeltajo.refugiadosgame.ruta3.view;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by Profesor on 26/01/2017.
 */

public class GameView extends SurfaceView {

    private GUI gui;
    private Juego juego;
    private GameLoop loop;

    public GameView(Context context) {
        super(context);
        setGui(new GUI(this));
        setJuego(new Juego(this));
        setLoop(new GameLoop(this));
        getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                getLoop().setRunning(true);
            }
            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {}
            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {}
        });
    }

    public GUI getGui() {
        return gui;
    }

    public void setGui(GUI gui) {
        this.gui = gui;
    }

    public Juego getJuego() {
        return juego;
    }

    public void setJuego(Juego juego) {
        this.juego = juego;
    }

    public GameLoop getLoop() {
        return loop;
    }

    public void setLoop(GameLoop loop) {
        this.loop = loop;
    }

    @Override
    public void draw(Canvas canvas) {
        getGui().draw(canvas);
        getJuego().draw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            synchronized (getHolder()) {
                getGui().touch((int)event.getX(), (int)event.getY());
                getJuego().touch((int)event.getX(), (int)event.getY());
            }
        }
        return true;
    }
}
