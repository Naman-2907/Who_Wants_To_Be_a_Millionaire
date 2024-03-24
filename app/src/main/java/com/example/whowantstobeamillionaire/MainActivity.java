package com.example.whowantstobeamillionaire;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Handler;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int SPLASH_SCREEN_TIMEOUT = 2000; // 2 seconds
    private boolean backPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startGameActivity();
            }
        }, SPLASH_SCREEN_TIMEOUT);
    }

    private void startGameActivity() {
        Intent intent = new Intent(MainActivity.this, GameActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        if (backPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.backPressedOnce = true;
        Toast.makeText(this, "Press back again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                backPressedOnce = false;
            }
        }, 2000);
    }
}
