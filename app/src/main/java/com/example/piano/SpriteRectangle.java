package com.example.piano;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

public class SpriteRectangle extends View{
    private double x;
    private double y;
    public byte typeOfScreen = 1; //0 - 1200x800 1 - 1920x1200
    private int widthOfScreen; //800 ||| 1200
    private int heightOfScreen; //1200 ||| 1920
    private double velocityY;
    private int lineToDraw;
    private boolean scored;

    public SpriteRectangle(Context context, double x, double y, double velocityY, int lineToDraw) {
        super(context);
        this.x = x;
        this.y = y;
        this.velocityY = velocityY;
        this.lineToDraw = lineToDraw;
        if (typeOfScreen==0) {
            setWidthOfScreen(800);
            setHeightOfScreen(1200);
        }
        else {
            setWidthOfScreen(1200);
            setHeightOfScreen(1920);
        }
    }

    public void update(int ms) {
        y = y + velocityY * ms/1000.0;
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);
        Paint keyButton = new Paint();
        int blueNotScored = getResources().getColor(R.color.blueNotScored);
        int blueScored = getResources().getColor(R.color.blueScored);
        if (isScored()==true) {
            keyButton.setColor(blueNotScored);
        }
        else {
            keyButton.setColor(blueScored);
        }
        keyButton.setStyle(Paint.Style.FILL_AND_STROKE);
        keyButton.setAntiAlias(true);
        int width = getWidthOfScreen()/4;
        int height = getHeightOfScreen()/5;
        canvas.drawRect((int)getX()+(width*lineToDraw), (int)getY(), width*lineToDraw+width+getX(), height+getY(), keyButton);
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setVelocityY(double velocityY) {
        this.velocityY = velocityY;
    }


    public float getY() {
        return (float) y;
    }

    public double getVelocityY() {
        return velocityY;
    }


    public float getX() {
        return (float) x;
    }

    public int getWidthOfScreen() {
        return widthOfScreen;
    }

    public void setWidthOfScreen(int widthOfScreen) {
        this.widthOfScreen = widthOfScreen;
    }

    public int getHeightOfScreen() {
        return heightOfScreen;
    }

    public void setHeightOfScreen(int heightOfScreen) {
        this.heightOfScreen = heightOfScreen;
    }

    public boolean isScored() {
        return scored;
    }

    public void setScored(boolean scored) {
        this.scored = scored;
    }

    public int getLineToDraw() {
        return lineToDraw;
    }

    public void setLineToDraw(int lineToDraw) {
        this.lineToDraw = lineToDraw;
    }

}
