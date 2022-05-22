package com.google.sample.cloudvision;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

public class CompleteActivity extends AppCompatActivity {
    private Button retryButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete);
        retryButton = findViewById(R.id.retryButton);

        retryButton.setOnClickListener(view -> {
            Intent intent = new Intent(CompleteActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }
}
