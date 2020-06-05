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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actions);

        location= LocationList.getActiveLocation();

        address=location.getName();
        TextView title=findViewById(R.id.title);
        title.setText(address);

        Button mapIt=findViewById(R.id.btn_mapit);
        mapIt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                address = address.replace(' ', '+');
                Intent geoIntent = new Intent(
                        android.content.Intent.ACTION_VIEW, Uri
                        .parse("geo:0,0?q=" + address));
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
