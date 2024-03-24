package com.example.whowantstobeamillionaire;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AmountToBeWonActivity extends AppCompatActivity {

    private TextView amountToBeWonTextView;
    private Button continueButton;
    private int currentQuestionIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amounttobewon);

        amountToBeWonTextView = findViewById(R.id.amountToBeWonTextView);
        continueButton = findViewById(R.id.continueButton);


        currentQuestionIndex = getIntent().getIntExtra("currentQuestionIndex", 1);


        String amountKey = "amt" + currentQuestionIndex;
        int amountResId = getResources().getIdentifier(amountKey, "string", getPackageName());
        String amountToBeWon = getString(amountResId);


        amountToBeWonTextView.setText(amountToBeWon);


        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGameActivity();
            }
        });
    }

    private void startGameActivity() {
        Intent intent = new Intent(AmountToBeWonActivity.this, GameActivity.class);
        intent.putExtra("currentQuestionIndex", currentQuestionIndex);
        startActivity(intent);
        finish();
    }
}
