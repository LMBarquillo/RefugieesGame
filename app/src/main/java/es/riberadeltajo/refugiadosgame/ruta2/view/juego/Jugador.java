package es.riberadeltajo.refugiadosgame.ruta2.view.juego;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by Profesor on 02/02/2017.
 */

public class Jugador {

    private final int VELOCIDAD_SALTO=10;
    private final int VELOCIDAD_AVANCE=10;
    private final int[] DIRECCION={3,1,0,2}; //Para saber en qué dirección tiene que pintar
    private final int  BMP_COLUMNS=4; //Cantidad de columnas de movimiento
    private final int BMP_ROWS=4; //Cantidad de filas de movimiento
    private int corx, cory; //Coordenadas
    private GameView2 gameview2; //Dónde aparece
    private Bitmap bmp; //Imagen de las figuritas
    private int currentFrame; //Figura que ha aparecido última
    private int width; //Ancho del muñeco
    private int height; //Alto del muñeco
    private int xSpeed, ySpeed; //Velocidades
    private int puntos;
    private int salto;
    private boolean enMarcha;
    private boolean delante;
    private boolean detras;


    public Jugador(GameView2 gameview2, Bitmap bmp, int cory, int corx) {


        setWidth(bmp.getWidth()/BMP_COLUMNS);
        setHeight(bmp.getHeight()/BMP_ROWS);

        setGameview2(gameview2);
        setBmp(bmp);
        setCurrentFrame(0);
        setxSpeed(VELOCIDAD_AVANCE);
        setySpeed(VELOCIDAD_SALTO);
        setCorx(corx);
        setCory(cory);
        //setCorx((int)(getGameview2().getWidth()*0.50));
        //setCory((getGameview2().getHeight()-height)+20);
        setPuntos(0);
        setSalto(getHeight()*3);
        setEnMarcha(false);
        setDelante(true);
        setDetras(false);


    }

    public int getSalto() {
        return salto;
    }

    public void setSalto(int salto) {
        this.salto = salto;
    }
    public boolean isEnMarcha() {
        return enMarcha;
    }

    public void setEnMarcha(boolean enMarcha) {
        this.enMarcha = enMarcha;
    }

    public boolean isDelante() {
        return delante;
    }

    public void setDelante(boolean delante) {
        this.delante = delante;
    }

    public boolean isDetras() {
        return detras;
    }

    public void setDetras(boolean detras) {
        this.detras = detras;
    }

    public int getCorx() {
        return corx;
    }

    public void setCorx(int corx) {
        this.corx = corx;
    }

    public int getCory() {
        return cory;
    }

    public void setCory(int cory) {
        this.cory = cory;
    }

    public GameView2 getGameview2() {
        return gameview2;
    }

    public void setGameview2(GameView2 gameview2) {
        this.gameview2 = gameview2;
    }

    public Bitmap getBmp() {
        return bmp;
    }

    public void setBmp(Bitmap bmp) {
        this.bmp = bmp;
    }

    public int getCurrentFrame() {
        return currentFrame;
    }

    public void setCurrentFrame(int currentFrame) {
        this.currentFrame = currentFrame;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getxSpeed() {
        return xSpeed;
    }

    public void setxSpeed(int xSpeed) {
        this.xSpeed = xSpeed;
    }

    public int getySpeed() {
        return ySpeed;
    }

    public void setySpeed(int ySpeed) {
        this.ySpeed = ySpeed;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    //Actualiza a la siguiente posición en la que va a aparecer
    public void update(){

       /*if(corx>=gameview2.getWidth()-width-xSpeed || corx+xSpeed<=0){
            xSpeed=-xSpeed; //Se le cambia la dirección
        }
        else {
            corx+=xSpeed; //Se le incrementa la dirección
        }
        if(cory>=gameview2.getHeight()-height-ySpeed || cory+ySpeed<=0){
            ySpeed=-ySpeed; //Se le cambia la dirección
        }
        else {
            cory+=ySpeed; //Se le incrementa la dirección a las coordenadas
        }*/

    currentFrame = ++currentFrame % BMP_COLUMNS;




    }
    private int getDirection(){

        if(isDelante()) return DIRECCION[3];
        else return DIRECCION[1];

    }


    public void onDraw(Canvas canvas){

        Paint paint=new Paint();
        paint.setColor(Color.RED);
        paint.setTextSize(60);
        int srcx, srcy;

    if(isEnMarcha()) {
         srcx = currentFrame * width;
         srcy = getDirection() * height;
    }else{
         srcx=0;
         srcy=DIRECCION[3]*height;
    }

    Rect src = new Rect(srcx, srcy, srcx + width, srcy + height);
    Rect dst = new Rect(corx, cory, corx + width, cory + height);
    canvas.drawBitmap(bmp, src, dst, null);





        //canvas.drawBitmap(bmp, getCorx(), getCory(), null);
    }
    public boolean isCollition(float x2, float y2){
        //Comprueba si está en las coordenadas que se le pasan, que son las del dedo pulsado
        boolean comprobar=false;
        if(x2>corx && x2<(corx+width) && y2>cory && y2<(cory+height)){
            comprobar=true;


        }
        return comprobar;

    }
    public void avanzar(){
        update();
        setEnMarcha(true);
        setDelante(true);
        setDetras(false);



    }
    public void saltar(){
        update();

    }
    public void atras(){
        update();
        setEnMarcha(true);
        setDelante(false);
        setDetras(true);



    }
    public void parar(){
        setEnMarcha(false);
    }
}


