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
    private final int VIDAS_INICIO=3;
    private final int VELOCIDAD_SALTO=70;
    private final int VELOCIDAD_AVANCE=20;
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
    private boolean saltando;
    private boolean descendiendo;
    private int corxinicio, coryinicio;
    private int vidas, monedas;



    public Jugador(GameView2 gameview2, Bitmap bmp, int cory, int corx) {


        setWidth(bmp.getWidth()/BMP_COLUMNS);
        setHeight(bmp.getHeight()/BMP_ROWS);
        setGameview2(gameview2);
        setBmp(bmp);
        setCurrentFrame(0);
        setxSpeed(VELOCIDAD_AVANCE);
        setySpeed(VELOCIDAD_SALTO);
        setCorx(corx);
        setCorxinicio(corx);
        setCory(cory);
        setCoryinicio(cory);
        setPuntos(0);
        setSalto(getHeight());
        setEnMarcha(false);
        setDelante(true);
        setDetras(false);
        setSaltando(false);
        setDescendiendo(false);
        setMonedas(0);
        setVidas(VIDAS_INICIO);

    }

    public int getVidas() {
        return vidas;
    }

    public void setVidas(int vidas) {
        this.vidas = vidas;
    }

    public int getMonedas() {
        return monedas;
    }

    public void setMonedas(int monedas) {
        this.monedas = monedas;
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

    public int getCorxinicio() {
        return corxinicio;
    }

    public void setCorxinicio(int corxinicio) {
        this.corxinicio = corxinicio;
    }

    public int getCoryinicio() {
        return coryinicio;
    }

    public void setCoryinicio(int coryinicio) {
        this.coryinicio = coryinicio;
    }

    public boolean isSaltando() {
        return saltando;
    }

    public void setSaltando(boolean saltando) {
        this.saltando = saltando;
    }

    public boolean isDescendiendo() {
        return descendiendo;
    }

    public void setDescendiendo(boolean descendiendo) {
        this.descendiendo = descendiendo;
    }

    //Actualiza a la siguiente posición en la que va a aparecer
    public void update(){
    currentFrame = ++currentFrame % BMP_COLUMNS;

    }
    private int getDirection(){

        if(!isDelante()) return DIRECCION[1];
        else return DIRECCION[3];

    }


    public void onDraw(Canvas canvas){

        Paint paint=new Paint();
        paint.setColor(Color.RED);
        paint.setTextSize(60);
        int srcx, srcy;
        srcx = currentFrame * width;
        srcy = getDirection() * height;

        if(isSaltando()){
            if (isDescendiendo())
                setCory(getCory()+VELOCIDAD_SALTO);
            else
                setCory(getCory()-VELOCIDAD_SALTO);
            if(getCory()<=getSalto()){
                setDescendiendo(true);

            }
            if(getCory()>=getCoryinicio()){
                setDescendiendo(false);
                setSaltando(false);
                setEnMarcha(false);

            }

        }
        if(isEnMarcha() && !isSaltando()) {

        update();

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
        setEnMarcha(true);
        setDelante(true);
        setDetras(false);



    }
    public void saltar(){
        setEnMarcha(true);
        setSaltando(true);



    }
    public void atras(){
        setEnMarcha(true);
        setDelante(false);
        setDetras(true);



    }
    public void colocar(){
        setCorx(getCorxinicio());
        setCory(getCoryinicio());
        setEnMarcha(false);
    }
    public void parar(){
        setEnMarcha(false);
    }
}


