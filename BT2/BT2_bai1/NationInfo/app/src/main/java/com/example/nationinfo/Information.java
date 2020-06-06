package com.example.nationinfo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class Information extends AppCompatActivity {
    private TextView tvCountryName;
    private TextView tvPopulation;
    private TextView tvAreaInSqKm;
    private ImageView imgFlag;

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

        String countryCode = geoname.getCountryCode().toLowerCase();
        new LoadImageFromInternet().execute("https://img.geonames.org/flags/x/" + countryCode + ".gif");
    }

    private void anhxa(){
        this.tvCountryName = (TextView) findViewById(R.id.tvCountryName);
        this.tvPopulation = (TextView) findViewById(R.id.tvPopulation);
        this.tvAreaInSqKm = (TextView) findViewById(R.id.tvAreaInSqKm);
        this.imgFlag = (ImageView) findViewById(R.id.imgFlag);
    }

    private class LoadImageFromInternet extends AsyncTask<String, Void, Bitmap>
    {
        Bitmap bitmapImage = null;
        @Override
        protected Bitmap doInBackground(String... strings) {
            try {
                URL url = new URL(strings[0]);
                InputStream inputStream = url.openConnection().getInputStream();
                bitmapImage = BitmapFactory.decodeStream(inputStream);
            } catch (MalformedURLException e) {
                Toast.makeText(Information.this, "Error 01", Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                Toast.makeText(Information.this, "Error 02", Toast.LENGTH_SHORT).show();
            }

            return bitmapImage;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            imgFlag.setImageBitmap(bitmap);
        }
    }
}
