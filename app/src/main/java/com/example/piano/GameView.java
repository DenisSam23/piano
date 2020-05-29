package com.example.piano;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.CountDownTimer;
import android.view.MotionEvent;
import android.view.View;
import java.util.ArrayList;
import java.util.Random;

public class GameView extends View {

    public byte typeOfScreen=1; //0 - 1200x800 1 - 1920x1200
    private SpriteRectangle spriteRectangle;
    private int points = 0;
    private int lostButtons = 0;
    private int difficulty;
    private final int timerInterval = 30;
    private int spawnRate;
    private int velocity;
    private int penaltyPoints;
    private int timerForNewRect = 0;
    private MenuCallback menuCallback;

    private ArrayList<SpriteRectangle> sprites = new ArrayList<SpriteRectangle>();

    public GameView(Context context, int difficulty, MenuCallback menuCallback) {
        super(context);
        this.menuCallback=menuCallback;

        this.difficulty = difficulty;
        if (typeOfScreen == 0) {
            switch (difficulty) {
                case 1:
                    spawnRate = 15;
                    velocity = 600;
                    penaltyPoints = 1;
                    break;
                case 2:
                    spawnRate = 10;
                    velocity = 900;
                    penaltyPoints = 3;
                    break;
                case 3:
                    spawnRate = 8;
                    velocity = 1000;
                    penaltyPoints = 5;
                    break;
            }
        }
        else {
            switch (difficulty) {
                case 1:
                    spawnRate = 22;
                    velocity = 600;
                    penaltyPoints = 1;
                    break;
                case 2:
                    spawnRate = 15;
                    velocity = 900;
                    penaltyPoints = 3;
                    break;
                case 3:
                    spawnRate = 13;
                    velocity = 1000;
                    penaltyPoints = 5;
                    break;
            }
        }

        Timer timer = new Timer();
        timer.start();
    }

    protected void createNewPianoRect(Context context) {
        Random random = new Random();
        spriteRectangle  = new SpriteRectangle(context, 0, 0,velocity,random.nextInt(4));
        sprites.add(spriteRectangle);
    }

    protected void update() {
        if (timerForNewRect < spawnRate) {
            timerForNewRect++;
        }
        else {
            createNewPianoRect(getContext());
            timerForNewRect=0;
        }
        for (SpriteRectangle sprite : sprites){
            if (typeOfScreen==0) {
                if (sprite.getY() <= 1200) { //1200 ||| 1920
                    sprite.update(timerInterval);
                } else if (sprite.isScored() == false) {
                    points -= penaltyPoints;
                    lostButtons++;
                    sprite.setScored(true);
                }
            }
            else {
                if (sprite.getY() <= 1920) { //1200 ||| 1920
                    sprite.update(timerInterval);
                } else if (sprite.isScored() == false) {
                    points -= penaltyPoints;
                    lostButtons++;
                    sprite.setScored(true);
                }
            }
        }
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRGB(241,243,206);
        Paint pointsText = new Paint();
        pointsText.setAntiAlias(true);
        pointsText.setSubpixelText(true);
        int pointsTextColor = getResources().getColor(R.color.pointsTextColor);
        pointsText.setColor(pointsTextColor);
        if (typeOfScreen==0) {
            pointsText.setTextSize(70.0f);
        }
        else {
            pointsText.setTextSize(105.0f);
        }
        Paint buttonCloseGame = new Paint();
        buttonCloseGame.setAntiAlias(true);
        int colorButtonCloseGame = getResources().getColor(R.color.buttonCloseGame);
        buttonCloseGame.setColor(colorButtonCloseGame);

        if (typeOfScreen==0) {
            canvas.drawRect(750, 0, 800, 50, buttonCloseGame); //750 0 800 50 ||| 1200 0 1280 80
            canvas.drawText("Очки:" + points, 480, 55, pointsText); //480 55 ||| 900 80
            canvas.drawText("Пропущено:" + lostButtons, 10, 55, pointsText); //10 55 ||| 15 80
        }
        else {
            canvas.drawRect(1200, 0, 1280, 80, buttonCloseGame); //750 0 800 50 ||| 1200 0 1280 80
            canvas.drawText("Очки:" + points, 800, 80, pointsText); //480 55 ||| 900 80
            canvas.drawText("Пропущено:" + lostButtons, 15, 80, pointsText); //10 55 ||| 15 80
        }
        for (SpriteRectangle sprite : sprites) {
            sprite.draw(canvas);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int eventAction = event.getAction();
        if (eventAction == MotionEvent.ACTION_DOWN) {

            if (typeOfScreen==0) {
                for (SpriteRectangle sprite : sprites) {
                    if (sprite.getY() <= 1200) { //1200 ||| 1920
                        if (event.getX() >= sprite.getX() + sprite.getLineToDraw() * 200 && event.getX() <= sprite.getX() + 200 + sprite.getLineToDraw() * 200 && //200 200 200 ||| 300 300 300
                                event.getY() >= sprite.getY() && event.getY() <= sprite.getY() + 200 && !sprite.isScored()) { //240 ||| 384
                            points++;
                            sprite.setScored(true);
                        }
                    }
                }
                if (event.getX() >= 750 && event.getY() >= 0 && event.getX() <= 800 && event.getY() <= 50) { //750 0 800 50 |||
                    menuCallback.endGame();
                }
            }
            else {
                for (SpriteRectangle sprite : sprites) {
                    if (sprite.getY() <= 1920) { //1200 ||| 1920
                        if (event.getX() >= sprite.getX() + sprite.getLineToDraw() * 300 && event.getX() <= sprite.getX() + 300 + sprite.getLineToDraw() * 300 && //200 200 200 ||| 300 300 300
                                event.getY() >= sprite.getY() && event.getY() <= sprite.getY() + 384 && !sprite.isScored()) { //240 ||| 384
                            points++;
                            sprite.setScored(true);
                        }
                    }
                }
                if (event.getX() >= 1200 && event.getY() >= 0 && event.getX() <= 1280 && event.getY() <= 80) { //750 0 800 50 ||| 1200 0 1280 80
                    menuCallback.endGame();
                }
            }
        }
        return true;
    }

    class Timer extends CountDownTimer {
        public Timer() {
            super(Integer.MAX_VALUE, timerInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            update();
        }

        @Override
        public void onFinish() {
        }
    }
}
