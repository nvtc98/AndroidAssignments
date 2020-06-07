package com.example.bt2_bai2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_loading);
        fetch();

        final Button button = findViewById(R.id.btn_retry);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                fetch();
            }
        });
//        String s= HttpRequest.sendRequest(this,this);
//        Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
//        setContentView(R.layout.activity_main_loading);
    }

    void setLoadingFailed(){
        TextView txtLoading = findViewById(R.id.txt_loading);
        txtLoading.setText("Failed to connect.");

        ProgressBar progressBar = findViewById(R.id.pb_loading);
        progressBar.setVisibility(View.GONE);
    }

    void fetch(){
        TextView txtLoading = findViewById(R.id.txt_loading);
        txtLoading.setText("Loading...");
        ProgressBar progressBar = findViewById(R.id.pb_loading);
        progressBar.setVisibility(View.VISIBLE);
        HttpRequest.sendRequest(this,this);
    }
}
