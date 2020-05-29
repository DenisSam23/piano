package com.example.piano;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

public class StartMenuActivity extends AppCompatActivity implements MenuCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(new StartMenu(this, this));
    }

    @Override
    public void startGameViewActivity(int difficulty) {
        Intent gameViewActivity = new Intent(this, GameViewActivity.class);
        gameViewActivity.putExtra("Difficulty", difficulty);
        startActivity(gameViewActivity);
    }

    @Override
    public void endGame() {

    }

}
