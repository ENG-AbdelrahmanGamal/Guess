package com.example.myapplication;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private int randomNumber;
    private int guessCount;

    private EditText guessInput;
    private Button submitButton;
    private TextView feedbackText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        guessInput = findViewById(R.id.guessInput);
        submitButton = findViewById(R.id.submitButton);
        feedbackText = findViewById(R.id.feedbackText);

        // Start a new game
        startNewGame();

        submitButton.setOnClickListener(view -> {
            String guessString = guessInput.getText().toString();
            if (!guessString.isEmpty()) {
                int guess = Integer.parseInt(guessString);
                guessCount++;
                checkGuess(guess);
            } else {
                Toast.makeText(MainActivity.this, "Please enter a valid number!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void startNewGame() {
        Random random = new Random();
        randomNumber = random.nextInt(100) + 1;
        guessCount = 0;
        feedbackText.setText("Guess a number between 1 and 100");
        guessInput.getText().clear();
    }

    private void checkGuess(int guess) {
        if (guess > randomNumber) {
            feedbackText.setText("Too high! Try again.");
        } else if (guess < randomNumber) {
            feedbackText.setText("Too low! Try again.");
        } else {
            feedbackText.setText("Correct! You guessed it in " + guessCount + " tries.");
            showPlayAgainDialog();
        }
    }

    private void showPlayAgainDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Congratulations!")
                .setMessage("You guessed the number in " + guessCount + " tries. Do you want to play again?")
                .setPositiveButton("Yes", (dialog, which) -> startNewGame())
                .setNegativeButton("No", (dialog, which) -> finish())
                .show();
    }
}
