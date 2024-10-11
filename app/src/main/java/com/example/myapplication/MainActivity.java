package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class MainActivity extends AppCompatActivity {

    private int randomNumber;
    private int guessCount;
    private EditText guessInput;
    private Button submitButton;
    private TextView feedbackText;

    // Create a list to hold player data
    private List<Player> playerList = new ArrayList<>();

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
            showPlayerNameDialog();
        }
    }

    private void showPlayerNameDialog() {
        EditText input = new EditText(this);
        new AlertDialog.Builder(this)
                .setTitle("Enter your name")
                .setView(input)
                .setPositiveButton("OK", (dialog, which) -> {
                    String playerName = input.getText().toString();
                    if (!playerName.isEmpty()) {
                        playerList.add(new Player(playerName, guessCount));
                        showPlayAgainDialog();
                    } else {
                        Toast.makeText(MainActivity.this, "Please enter a valid name!", Toast.LENGTH_SHORT).show();
                    }
                })
                .show();
    }

    private void showPlayAgainDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Congratulations!")
                .setMessage("You guessed the number in " + guessCount + " tries. Do you want to play again or view scores?")
                .setPositiveButton("Play Again", (dialog, which) -> startNewGame())
                .setNegativeButton("View Scores", (dialog, which) -> {
                    Intent intent = new Intent(MainActivity.this, ScoreActivity.class);
                    intent.putParcelableArrayListExtra("playerList", (ArrayList<? extends Parcelable>) new ArrayList<>(playerList));
                    startActivity(intent);
                })
                .show();
    }
}
