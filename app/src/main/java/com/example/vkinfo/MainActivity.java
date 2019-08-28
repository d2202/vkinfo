package com.example.vkinfo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.net.URL;

import static com.example.vkinfo.utils.NetworkUtils.generateURL;
import static com.example.vkinfo.utils.NetworkUtils.getResponseFromUrl;

public class MainActivity extends AppCompatActivity {
    private EditText searchField;
    private Button searchButton;
    private TextView result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchField = findViewById(R.id.et_search_field); //TODO: всплывающее сообщение об ошибке, если поле ввода id осталось пустым, либо null.
        searchButton = findViewById(R.id.b_search_vk);
        result = findViewById(R.id.tv_result);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                URL generatedURL;
                String response = null;
                try {
                    generatedURL = generateURL(searchField.getText().toString());
                    response = getResponseFromUrl(generatedURL);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                result.setText(response);
            }
        };

        searchButton.setOnClickListener(onClickListener);
    }
}
