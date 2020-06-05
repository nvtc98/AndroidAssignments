package com.example.bai2;

import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityImage extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int src = extras.getInt("SRC");
//            Toast.makeText(getApplicationContext(), src, Toast.LENGTH_LONG).show();
            ImageView iv = findViewById(R.id.img_fullscreen);
            iv.setImageResource(src);
        }
    }
}
