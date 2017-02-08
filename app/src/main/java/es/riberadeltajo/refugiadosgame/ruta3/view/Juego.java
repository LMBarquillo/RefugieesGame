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
    private float playerWidthScale = .092f; //sobre 100px
    private float playerHeightScale = .104f; //sobre 200px
    private float nenufarWidthScale = .133f; // sobre 145px
    private float nenufarHeightScale = .074f; //sobre 145px
    private float nenufarPosYScale = .191f; //sobre 362px
    private float aguaYScale = .192f; //sobre 369
    private float aguaHeightScale = .593f; //sobre 1140

    private Sprite player;
    private Bitmap bmpNenufar;
    private ArrayList<Sprite> nenufares;

    public Juego(GameView gameView) {
        setGameView(gameView);
        //agua = BitmapFactory.decodeResource(getGameView().getResources(), R.drawable.sarajevoagua);
        //agua = Bitmap.createScaledBitmap(agua, getGameView().getWidth(), getGameView().getHeight(), false);
        terreno = BitmapFactory.decodeResource(getGameView().getResources(), R.drawable.sarajevocarretera);
        terreno = Bitmap.createScaledBitmap(terreno, getGameView().getWidth(), getGameView().getHeight(), false);
        player = new Sprite(getGameView(), R.drawable.milan_personajeuno, 4, 4, (int)(getGameView().getWidth() * playerWidthScale), (int)(getGameView().getHeight() * playerHeightScale), (getGameView().getWidth() - 100) / 2, getGameView().getHeight() - 200, 0, 0);
        nenufares = new ArrayList<Sprite>();
        crearNenufares();
        musica = MediaPlayer.create(getGameView().getContext(), R.raw.sarajevoswamp);
        musica.start();
    }

    public GameView getGameView() {
        return gameView;
    }

    public void setGameView(GameView gameView) {
        this.gameView = gameView;
    }

    public Sprite getPlayer() {
        return player;
    }

    public void setPlayer(Sprite player) {
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
        for(int i = 1; i < 9; i++) {
            nenufares.add(new Sprite(getGameView(), bmpNenufar, 1, 1, width, height, (i%2==0?0:getGameView().getWidth() - width), y+(width*(i-1)), (int)(Math.random()*5 + 5), 0));
        }

    }

    public void draw(Canvas canvas) {
        //canvas.drawBitmap(agua, 0, 0, null);
        canvas.drawBitmap(terreno, 0, 0, null);
        player.setSpeedX(0);
        if(player.isWatterCollition((int)(getGameView().getHeight() * aguaYScale), (int)(getGameView().getHeight() * aguaHeightScale))) {
            player.setSafe(false);
        } else {
            player.setSafe(true);
        }
        for(Sprite s : nenufares) {
            if(s.getPosX() < 0 - s.getWidth() || s.getPosX() > getGameView().getWidth()) {
                s.setSpeedX(-s.getSpeedX());
            }
            if(s.isCollition(player)) {
                player.setSafe(true);
                player.setSpeedX(s.getSpeedX());
            }
            s.draw(canvas);
        }
        player.draw(canvas);
    }

    public void touch(int x, int y) {

    }

    public void unTouch(int x, int y) {

    }

    public void stopMusica() {
        musica.stop();
        musica.release();
    }

}
