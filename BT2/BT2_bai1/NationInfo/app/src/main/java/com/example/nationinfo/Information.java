package com.example.nationinfo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Information extends AppCompatActivity {
    private TextView tvCountryName;
    private TextView tvPopulation;
    private TextView tvAreaInSqKm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        this.anhxa();
        Intent intent = getIntent();
        GEONAME geoname = (GEONAME) intent.getSerializableExtra("geoname");
        this.tvCountryName.setText(geoname.getCountryName());
        this.tvPopulation.setText(geoname.getPopulation());
        this.tvAreaInSqKm.setText(geoname.getAreaInSqKm() + "km²");
    }

    private void anhxa(){
        this.tvCountryName = (TextView) findViewById(R.id.tvCountryName);
        this.tvPopulation = (TextView) findViewById(R.id.tvPopulation);
        this.tvAreaInSqKm = (TextView) findViewById(R.id.tvAreaInSqKm);
    }
}
