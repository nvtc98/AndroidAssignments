package com.example.bt2_bai2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String s=REST.sendRequest(this);
        Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();

    }
}
