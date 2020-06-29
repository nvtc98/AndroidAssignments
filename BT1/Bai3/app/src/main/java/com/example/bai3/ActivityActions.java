package com.example.bai3;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityActions extends AppCompatActivity {
    Location location;
    String address;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actions);

        location= LocationList.getActiveLocation();

        name=location.getName();
        address=location.getGeo();
        TextView title=findViewById(R.id.title);
        title.setText(name);

        Button mapIt=findViewById(R.id.btn_mapit);
        mapIt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent geoIntent = new Intent(Intent.ACTION_VIEW);
                geoIntent.setData(Uri.parse(address));
                startActivity(geoIntent);

            };
        });

        Button moreInfo=findViewById(R.id.btn_moreinfo);
        moreInfo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(location.getUrl()));
                startActivity(intent);
            };
        });
    }
}
