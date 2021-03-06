package es.riberadeltajo.refugiadosgame.ruta3.view.arcade;

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
    private boolean fin;
    private SarajevoArcade activity;

    public GameView(SarajevoArcade activity) {
        super(activity);
        setActivity(activity);
        setFin(false);
        getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                start();
            }
            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {}
            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                stop();
            }
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

    public boolean isFin() {
        return fin;
    }

    public void setFin(boolean fin) {
        this.fin = fin;
    }

    public SarajevoArcade getActivity() {
        return activity;
    }

    public void setActivity(SarajevoArcade activity) {
        this.activity = activity;
    }

    @Override
    public void draw(Canvas canvas) {
        if(fin) {
            stop();
        } else {
            getJuego().draw(canvas);
            getGui().draw(canvas);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            synchronized (getHolder()) {
                getJuego().touch((int)event.getX(), (int)event.getY());
                getGui().touch((int)event.getX(), (int)event.getY());
            }
        } else
        if(event.getAction() == MotionEvent.ACTION_UP) {
            synchronized (getHolder()) {
                getJuego().unTouch((int)event.getX(), (int)event.getY());
                getGui().unTouch((int)event.getX(), (int)event.getY());
            }
        }
        return true;
    }

    public void start() {
        setJuego(new Juego(this));
        setGui(new GUI(this, getJuego()));
        setLoop(new GameLoop(this));
        getJuego().start();
        getGui().start();
        getLoop().setRunning(true);
    }

    public void stop() {
        getLoop().setRunning(false);
        getJuego().stop();
    }
}
