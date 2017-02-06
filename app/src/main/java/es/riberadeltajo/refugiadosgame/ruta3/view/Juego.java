package es.riberadeltajo.refugiadosgame.ruta3.view;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;

import es.riberadeltajo.refugiadosgame.R;

/**
 * Created by Profesor on 26/01/2017.
 */

public class Juego {

    private GameView gameView;

    private Bitmap agua;
    private Bitmap terreno;

    private float playerWidthRatio = .092f; //sobre 100px
    private float playerHeightRatio = .104f; //sobre 200px
    private float nenufarWidthRatio = .138f; // sobre 150px
    private float nenufarHeightRatio = .078f; //sobre 150px
    private float nenufarPosYRatio = .088f; //sobre 170px

    private Sprite player;
    private Bitmap bmpNenufar;
    private ArrayList<Sprite> nenufares;

    public Juego(GameView gameView) {
        setGameView(gameView);
        agua = BitmapFactory.decodeResource(getGameView().getResources(), R.drawable.sarajevoagua);
        agua = Bitmap.createScaledBitmap(agua, getGameView().getWidth(), getGameView().getHeight(), false);
        terreno = BitmapFactory.decodeResource(getGameView().getResources(), R.drawable.sarajevoterreno);
        terreno = Bitmap.createScaledBitmap(terreno, getGameView().getWidth(), getGameView().getHeight(), false);
        player = new Sprite(getGameView(), R.drawable.sarajevodarthvader, 4, 4, (int)(getGameView().getWidth() * playerWidthRatio), (int)(getGameView().getHeight() * playerHeightRatio), (getGameView().getWidth() - 100) / 2, getGameView().getHeight() - 200, 0, 0);
        nenufares = new ArrayList<Sprite>();
        crearNenufares();
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

    private void crearNenufares() {
        int width = (int)(getGameView().getWidth() * nenufarWidthRatio);
        int height = (int)(getGameView().getHeight() * nenufarHeightRatio);
        int y = (int)(getGameView().getHeight() * nenufarPosYRatio);
        bmpNenufar = BitmapFactory.decodeResource(getGameView().getResources(), R.drawable.sarajevonenufar);
        for(int i = 1; i < 10; i++) {
            nenufares.add(new Sprite(getGameView(), bmpNenufar, 1, 1, width, height, (i%2==0?-150:1080), y+(width*(i-1)), (i%2==0?1:-1), 0));
        }
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(agua, 0, 0, null);
        player.setSpeedX(0);
        for(Sprite s : nenufares) {
            if(s.isCollition(player)) {
                player.setSpeedX(s.getSpeedX());
            }
            s.draw(canvas);
        }
        canvas.drawBitmap(terreno, 0, 0, null);
        player.draw(canvas);
    }

    public void touch(int x, int y) {

    }

    public void unTouch(int x, int y) {

    }

}
