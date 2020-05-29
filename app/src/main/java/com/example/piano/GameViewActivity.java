package com.example.piano;

import android.os.Bundle;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;

public class GameViewActivity extends AppCompatActivity implements MenuCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        int difficulty = getIntent().getIntExtra("Difficulty",1);
        setContentView(new GameView(this, difficulty, this));
    }

    @Override
    public void startGameViewActivity(int difficulty) {

    }

    public void endGame() {
        finish();
    }
}
