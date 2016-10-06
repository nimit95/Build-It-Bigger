package com.udacity.gradle.jokedisplay;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class JokeActivity extends AppCompatActivity {
    private TextView jokeDisplay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);
        jokeDisplay= (TextView) findViewById(R.id.joke_display);
        jokeDisplay.setText(getIntent().getStringExtra("joke"));
    }
}
