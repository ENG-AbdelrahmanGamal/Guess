package com.example.myapplication;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class ScoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ListView scoreListView = findViewById(R.id.scoreListView);

        // Retrieve the player list from the intent
        ArrayList<Player> playerList = getIntent().getParcelableArrayListExtra("playerList");

        // Convert the player data to a list of strings
        ArrayList<String> scoreList = new ArrayList<>();
        for (Player player : playerList) {
            scoreList.add(player.getName() + ": " + player.getAttempts() + " tries");
        }

        // Display the scores in the ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, scoreList);
        scoreListView.setAdapter(adapter);
    }
}