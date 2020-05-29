package com.example.piano;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

public class StartMenu extends View {
    int difficulty;
    private MenuCallback menuCallback;
    public byte typeOfScreen=1; //0 - 1200x800 1 - 1920x1200


    public StartMenu(Context context, MenuCallback menuCallback) {
        super(context);
        this.menuCallback = menuCallback;
        difficulty = 1;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRGB(247,239,226);

        Paint buttonStartGame = new Paint();
        buttonStartGame.setAntiAlias(true);
        buttonStartGame.setStyle(Paint.Style.FILL_AND_STROKE);
        int buttonsColor = getResources().getColor(R.color.buttonsStartMenu);
        buttonStartGame.setColor(buttonsColor);

        Paint textNameOfGame = new Paint();
        textNameOfGame.setAntiAlias(true);
        textNameOfGame.setSubpixelText(true);
        int textColor = getResources().getColor(R.color.textStartMenu);
        textNameOfGame.setColor(textColor);

        Paint textStartGame = new Paint();
        textStartGame.setAntiAlias(true);
        textStartGame.setSubpixelText(true);
        textStartGame.setColor(textColor);

        Paint textSetDifficulty = new Paint();
        textSetDifficulty.setAntiAlias(true);
        textSetDifficulty.setSubpixelText(true);
        textSetDifficulty.setColor(textColor);

        Paint textNameOfDifficulty = new Paint();
        textNameOfDifficulty.setAntiAlias(true);
        textNameOfDifficulty.setSubpixelText(true);
        textNameOfDifficulty.setColor(textColor);

        Paint flagDifficulty1 = new Paint();
        flagDifficulty1.setAntiAlias(true);
        flagDifficulty1.setColor(buttonsColor);

        Paint flagDifficulty2 = new Paint();
        flagDifficulty2.setAntiAlias(true);
        flagDifficulty2.setColor(buttonsColor);

        Paint flagDifficulty3 = new Paint();
        flagDifficulty3.setAntiAlias(true);
        flagDifficulty3.setColor(buttonsColor);
        if (typeOfScreen==0) {
            textNameOfGame.setTextSize(160.0f); //160 ||| 240
            textStartGame.setTextSize(80.0f); //80 ||| 120
            textSetDifficulty.setTextSize(60.0f); //60 ||| 90
            textNameOfDifficulty.setTextSize(40.0f); //40 ||| 60
        }
        else {
            textNameOfGame.setTextSize(240.0f); //160 ||| 240
            textStartGame.setTextSize(120.0f); //80 ||| 120
            textSetDifficulty.setTextSize(90.0f); //60 ||| 90
            textNameOfDifficulty.setTextSize(60.0f); //40 ||| 60
        }

        if (difficulty==1) {
            flagDifficulty1.setStyle(Paint.Style.FILL_AND_STROKE);
            flagDifficulty2.setStyle(Paint.Style.STROKE);
            flagDifficulty3.setStyle(Paint.Style.STROKE);
        }
        if (difficulty==2) {
            flagDifficulty1.setStyle(Paint.Style.STROKE);
            flagDifficulty2.setStyle(Paint.Style.FILL_AND_STROKE);
            flagDifficulty3.setStyle(Paint.Style.STROKE);
        }
        if (difficulty==3) {
            flagDifficulty1.setStyle(Paint.Style.STROKE);
            flagDifficulty2.setStyle(Paint.Style.STROKE);
            flagDifficulty3.setStyle(Paint.Style.FILL_AND_STROKE);
        }

        if (typeOfScreen==0) {
            canvas.drawRect(120, 360, 680, 560, buttonStartGame); //120 360 680 560 ||| 200 520 1000 840
            canvas.drawRect(200, 800, 280, 880, flagDifficulty1); //200 800 280 880 ||| 280 1200 408 1328
            canvas.drawRect(360, 800, 440, 880, flagDifficulty2); //360 800 440 880 ||| 536 1200 664 1328
            canvas.drawRect(520, 800, 600, 880, flagDifficulty3); //520 800 600 880 ||| 792 1200 920 1328
            canvas.drawText("Piano", 190, 200, textNameOfGame); //190 200 ||| 305 300
            canvas.drawText("Начать игру", 180, 480, textStartGame); //180 480 ||| 290 715
            canvas.drawText("Выберите сложность:", 100, 620, textSetDifficulty); //100 620 ||| 220 920
            canvas.drawText("Легко", 185, 780, textNameOfDifficulty); //200 700 ||| 240 1120
            canvas.drawText("Средне", 330, 780, textNameOfDifficulty); //320 700 ||| 496 1120
            canvas.drawText("Сложно", 485, 780, textNameOfDifficulty); //480 700 ||| 752 1120
        }
        else {
            canvas.drawRect(200, 520, 1000, 840, buttonStartGame); //120 360 680 560 ||| 200 520 1000 840
            canvas.drawRect(280, 1200, 408, 1328, flagDifficulty1); //200 800 280 880 ||| 280 1200 408 1328
            canvas.drawRect(536, 1200, 664, 1328, flagDifficulty2); //360 800 440 880 ||| 536 1200 664 1328
            canvas.drawRect(792, 1200, 920, 1328, flagDifficulty3); //520 800 600 880 ||| 792 1200 920 1328
            canvas.drawText("Piano", 305, 300, textNameOfGame); //190 200 ||| 305 300
            canvas.drawText("Начать игру", 290, 715, textStartGame); //180 480 ||| 290 715
            canvas.drawText("Выберите сложность:", 220, 920, textSetDifficulty); //100 620 ||| 220 920
            canvas.drawText("Легко", 240, 1120, textNameOfDifficulty); //200 700 ||| 240 1120
            canvas.drawText("Средне", 496, 1120, textNameOfDifficulty); //320 700 ||| 496 1120
            canvas.drawText("Сложно", 752, 1120, textNameOfDifficulty); //480 700 ||| 752 1120
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int eventAction = event.getAction();
        if (eventAction == MotionEvent.ACTION_DOWN) {
            if (typeOfScreen==0) {
                if (event.getX() >= 120 && event.getY() >= 360 && event.getX() <= 680 && event.getY() <= 560) { //120 360 680 560 ||| 200 520 1000 840
                    menuCallback.startGameViewActivity(difficulty);
                }
                if (event.getX() >= 200 && event.getY() >= 800 && event.getX() <= 280 && event.getY() <= 880) { //200 800 280 880 ||| 280 1200 408 1328
                    difficulty = 1;
                    invalidate();
                }
                if (event.getX() >= 360 && event.getY() >= 800 && event.getX() <= 440 && event.getY() <= 880) { //360 800 440 880 ||| 536 1200 664 1328
                    difficulty = 2;
                    invalidate();
                }
                if (event.getX() >= 520 && event.getY() >= 800 && event.getX() <= 600 && event.getY() <= 880) { //520 800 600 880 ||| 792 1200 920 1328
                    difficulty = 3;
                    invalidate();
                }
            }
            else {
                if (event.getX() >= 200 && event.getY() >= 520 && event.getX() <= 1000 && event.getY() <= 840) { //120 360 680 560 ||| 200 520 1000 840
                    menuCallback.startGameViewActivity(difficulty);
                }
                if (event.getX() >= 280 && event.getY() >= 1200 && event.getX() <= 408 && event.getY() <= 1328) { //200 800 280 880 ||| 280 1200 408 1328
                    difficulty = 1;
                    invalidate();
                }
                if (event.getX() >= 536 && event.getY() >= 1200 && event.getX() <= 664 && event.getY() <= 1328) { //360 800 440 880 ||| 536 1200 664 1328
                    difficulty = 2;
                    invalidate();
                }
                if (event.getX() >= 792 && event.getY() >= 1200 && event.getX() <= 920 && event.getY() <= 1328) { //520 800 600 880 ||| 792 1200 920 1328
                    difficulty = 3;
                    invalidate();
                }
            }
        }
        return true;
    }
}
