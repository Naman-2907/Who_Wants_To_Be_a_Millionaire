package com.example.whowantstobeamillionaire;

import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity {

    private int currentQuestionIndex = 1;
    private int score = 0;

    private TextView questionTextView;
    private Button answer1Button, answer2Button, answer3Button, answer4Button;

    private static final int SPLASH_SCREEN_DELAY = 2000; // 2 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        questionTextView = findViewById(R.id.questionTextView);
        answer1Button = findViewById(R.id.answer1Button);
        answer2Button = findViewById(R.id.answer2Button);
        answer3Button = findViewById(R.id.answer3Button);
        answer4Button = findViewById(R.id.answer4Button);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                displayQuestion(); // Call displayQuestion after splash screen
            }
        }, SPLASH_SCREEN_DELAY);
    }

    private void displayQuestion() {

        Log.d("DEBUG", "Current Question Index: " + currentQuestionIndex);

        int questionResId = getResources().getIdentifier("question_" + currentQuestionIndex, "string", getPackageName());
        Log.d("DEBUG", "Question Resource ID: " + questionResId);

        for (int i = 1; i <= 4; i++) {
            int answerResId = getResources().getIdentifier("answer_" + currentQuestionIndex + "_" + i, "string", getPackageName());
            Log.d("DEBUG", "Answer " + i + " Resource ID: " + answerResId);
        }

        // Set question text
        String question = getString(questionResId);
        questionTextView.setText(question);

        // Set answer texts
        for (int i = 1; i <= 4; i++) {
            int answerResId = getResources().getIdentifier("answer_" + currentQuestionIndex + "_" + i, "string", getPackageName());
            String answer = getString(answerResId);
            switch (i) {
                case 1:
                    answer1Button.setText(answer);
                    break;
                case 2:
                    answer2Button.setText(answer);
                    break;
                case 3:
                    answer3Button.setText(answer);
                    break;
                case 4:
                    answer4Button.setText(answer);
                    break;
            }
        }

        answer1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(answer1Button.getText().toString());
            }
        });

        answer2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(answer2Button.getText().toString());
            }
        });

        answer3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(answer3Button.getText().toString());
            }
        });

        answer4Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(answer4Button.getText().toString());
            }
        });

        // Increment the currentQuestionIndex here
        currentQuestionIndex++;
    }

    private void checkAnswer(String selectedAnswer) {
        String correctAnswer = getString(getResources().getIdentifier("correct_answer_" + currentQuestionIndex, "string", getPackageName()));

        // Show confirmation of the selected answer
        Toast.makeText(this, "Selected Answer: " + selectedAnswer, Toast.LENGTH_SHORT).show();

        // Check if the selected answer is correct and update the score
        if (selectedAnswer.equals(correctAnswer)) {
            score++;
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Incorrect!", Toast.LENGTH_SHORT).show();
        }


        if (currentQuestionIndex < 10) {
            currentQuestionIndex++;
            displayQuestion();
        } else {
            showResult();
        }
    }

    private void showResult() {

        Toast.makeText(this, "Game Over. Your score is: " + score, Toast.LENGTH_SHORT).show();
    }
}
