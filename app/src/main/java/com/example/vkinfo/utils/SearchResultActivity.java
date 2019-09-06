package com.example.vkinfo.utils;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vkinfo.R;

import java.io.InputStream;

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


        //TODO: рефакторинг, мне не нравится как это выглядит.
        if (intentThatStartActivity.hasExtra("profilePicUrl")) {
            String picUrl = intentThatStartActivity.getStringExtra("profilePicUrl");
            new DownloadImageFromInternet((ImageView) findViewById(R.id.iw_pic))
                    .execute(picUrl);
        }

        if (intentThatStartActivity.hasExtra(Intent.EXTRA_TEXT)) {
            String result = intentThatStartActivity.getStringExtra(Intent.EXTRA_TEXT);
            tvResult.setText(result);
        }

    }

    private class DownloadImageFromInternet extends AsyncTask<String, Void, Bitmap> {
        ImageView imageView;

        public DownloadImageFromInternet(ImageView imageView) {
            this.imageView = imageView;
        }

        protected Bitmap doInBackground(String... urls) {
            String imageURL = urls[0];
            Bitmap bimage = null;
            try {
                InputStream in = new java.net.URL(imageURL).openStream();
                bimage = BitmapFactory.decodeStream(in);

            } catch (Exception e) {
                Log.e("Error Message", e.getMessage());
                e.printStackTrace();
            }
            return bimage;
        }

        protected void onPostExecute(Bitmap result) {
            imageView.setImageBitmap(result);
        }
    }
}
