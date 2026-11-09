package com.example.laptrinhdidong;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button part1Button = findViewById(R.id.btnPart1);
        Button part2Button = findViewById(R.id.btnPart2);

        part1Button.setOnClickListener(view ->
                startActivity(new Intent(MainActivity.this, Part1Activity.class)));

        part2Button.setOnClickListener(view ->
                startActivity(new Intent(MainActivity.this, Part2Activity.class)));
    }
}
