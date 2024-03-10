package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {
    private TextView textViewDetail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        textViewDetail = findViewById(R.id.text_view_detail);

        Intent receivedIntent =  getIntent();

        if (receivedIntent != null) {
            String data = receivedIntent.getStringExtra("number");
            textViewDetail.setText(data);
        }
    }
}