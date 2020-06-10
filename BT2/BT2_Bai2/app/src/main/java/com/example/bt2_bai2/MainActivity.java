package com.example.bt2_bai2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    final private String url = "https://aud.fxexchangerate.com/rss.xml";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initLoadingScreen();
        fetch();
    }

    void initLoadingScreen(){
        setContentView(R.layout.activity_main_loading);
        final Button button = findViewById(R.id.btn_retry);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                fetch();
            }
        });
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
        RssCurrenciesParser rssParser = new RssCurrenciesParser(url, this);
        rssParser.execute((Void) null);
    }

    void setLoadingSuccess(){
        setContentView(R.layout.activity_main);
    }

    void initSpinners(String[] arr){
        Spinner spinnerFrom = findViewById(R.id.spn_from);
        Spinner spinnerTo = findViewById(R.id.spn_to);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arr);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFrom.setAdapter(adapter);
        spinnerTo.setAdapter(adapter);
    }
}
