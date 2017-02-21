package es.riberadeltajo.refugiadosgame.ruta3.view;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.media.MediaPlayer;

import java.util.ArrayList;

import es.riberadeltajo.refugiadosgame.R;

/**
 * Created by Profesor on 26/01/2017.
 */

public class Juego {

    private GameView gameView;
    private Bitmap terreno;
    private MediaPlayer musica;
    private final float playerWidthScale = .092f; //sobre 100px
    private final float playerHeightScale = .104f; //sobre 200px
    private final float playerSpeedYScale = .007f; // sobre 10px
    private final float nenufarWidthScale = .133f; // sobre 145px
    private final float nenufarHeightScale = .074f; //sobre 145px
    private final float nenufarPosYScale = .1935f; //sobre 362px
    private final float nenufarSpeedXScale = .009f; //sobre 10px
    private final float aguaYScale = .192f; //sobre 369px
    private final float aguaHeightScale = .593f; //sobre 1140px
    private final float cocheYScale = .862f;
    //private Salpicadura salpicadura;
    //private Coche coche;
    private Player player;
    private Bitmap bmpNenufar;
    private ArrayList<Nenufar> nenufares;

    public Juego(GameView gameView) {
        setGameView(gameView);
        //agua = BitmapFactory.decodeResource(getGameView().getResources(), R.drawable.sarajevoagua);
        //agua = Bitmap.createScaledBitmap(agua, getGameView().getWidth(), getGameView().getHeight(), false);
        terreno = BitmapFactory.decodeResource(getGameView().getResources(), R.drawable.sarajevocarretera);
        terreno = Bitmap.createScaledBitmap(terreno, getGameView().getWidth(), getGameView().getHeight(), false);
        //coche = new Coche(getGameView(), R.drawable.sarajevocoche, -getGameView().getWidth(), (int)(getGameView().getHeight() * cocheYScale), 15, 0, 4, 4, 250, 150);
        player = new Player(getGameView(), R.drawable.milan_personajeuno, 4, 4, (int)(getGameView().getWidth() * playerWidthScale), (int)(getGameView().getHeight() * playerHeightScale), (getGameView().getWidth() - 100) / 2, getGameView().getHeight() - 200, 0, 0);
        nenufares = new ArrayList<Nenufar>();
        crearNenufares();
    }

    public GameView getGameView() {
        return gameView;
    }

    public void setGameView(GameView gameView) {
        this.gameView = gameView;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Bitmap getTerreno() {
        return terreno;
    }

    public void setTerreno(Bitmap terreno) {
        this.terreno = terreno;
    }

    private void crearNenufares() {
        int width = (int)(getGameView().getWidth() * nenufarWidthScale);
        int height = (int)(getGameView().getHeight() * nenufarHeightScale);
        int y = (int)(getGameView().getHeight() * nenufarPosYScale);
        bmpNenufar = BitmapFactory.decodeResource(getGameView().getResources(), R.drawable.sarajevonenufar);
        int speed;
        for(int i = 0; i < 8; i++) {
            speed = (int)(Math.random()*(getGameView().getWidth() * nenufarSpeedXScale) + (getGameView().getWidth() * nenufarSpeedXScale));
            nenufares.add(new Nenufar(getGameView(), bmpNenufar, width, height, ((i+1)%2==0?-width:getGameView().getWidth()), y+(height*i), speed, 0));
        }
    }

    public void start() {
        musica = MediaPlayer.create(getGameView().getContext(), R.raw.sarajevoswamp);
        musica.start();
    }

    public void draw(Canvas canvas) {
        //canvas.drawBitmap(agua, 0, 0, null);
        canvas.drawBitmap(terreno, 0, 0, null);
        player.setSpeedX(0);
        if(player.isWatterCollition((int)(getGameView().getHeight() * aguaYScale), (int)(getGameView().getHeight() * aguaHeightScale))) {
            player.setSafe(false);
        } else {
            player.setSafe(true);
            if(player.getPosY() < getGameView().getHeight() * aguaYScale) {
                getGameView().setFin(true);
                getGameView().getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                    /*AlertDialog.Builder dialog = new AlertDialog.Builder(getGameView().getActivity());
                    dialog.setTitle("You Lost");
                    dialog.setMessage("You lost because time is up");
                    dialog.setPositiveButton("Reintentar", null);
                    dialog.setNegativeButton("Salir", null);
                    dialog.create();
                    dialog.show();*/
                        new Dialogo(getGameView().getActivity(), Dialogo.Tipo.WIN).show();
                    }
                });
            }
        }
        for(Nenufar s : nenufares) {
            if(s.getPosX() + s.getSpeedX() < 0) {
                s.setPosX(0);
                s.setSpeedX(-s.getSpeedX());
            } else
            if(s.getPosX() + s.getWidth() + s.getSpeedX() > getGameView().getWidth()) {
                s.setPosX(getGameView().getWidth() - s.getWidth());
                s.setSpeedX(-s.getSpeedX());
            }
            if(s.isCollition(player)) {
                player.setSafe(true);
                player.setSpeedX(s.getSpeedX());
            }
            s.draw(canvas);
        }
        player.draw(canvas);
        //coche.draw(canvas);
    }

    public void andarUp() {
        getPlayer().setSpeedY((int)(-getGameView().getHeight() * playerSpeedYScale));
    }

    public void andarDown() {
        getPlayer().setSpeedY((int)(getGameView().getHeight() * playerSpeedYScale));
    }

    public void andarStop() {
        getPlayer().setSpeedY(0);
    }

    public void touch(int x, int y) {

    }

    public void unTouch(int x, int y) {

    }

    public void stop() {
        if (musica != null) {
            musica.stop();
            musica.release();
            musica = null;
        }
    }

}
