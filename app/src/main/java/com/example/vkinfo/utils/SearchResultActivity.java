package com.example.vkinfo.utils;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.vkinfo.R;

public class SearchResultActivity extends AppCompatActivity {

    private TextView tvResult;

    //TODO: Recycle View
    //TODO: Button "open in browser"

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        tvResult = findViewById(R.id.tv_result);

        Intent intentThatStartActivity = getIntent();

        if (intentThatStartActivity.hasExtra(Intent.EXTRA_TEXT)){
            String result = intentThatStartActivity.getStringExtra(Intent.EXTRA_TEXT);
            tvResult.setText(result);
        }
    }
}
