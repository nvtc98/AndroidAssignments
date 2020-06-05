package com.example.bai3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Location [] data= LocationList.getLocations();
        LinearLayout content= findViewById(R.id.view_content);

        String titleText="Visiting Cleveland Landmarks";
        TextView titleOfList = new TextView(this);
        titleOfList.setText(titleText);
        titleOfList.setTextSize(20);
        titleOfList.setTextColor(getResources().getColor(R.color.white));
        titleOfList.setBackgroundResource(R.drawable.border_title);
        content.addView(titleOfList);

        for (int i=0;i<data.length;++i)
        {
            TextView title = new TextView(this);
            title.setText((i+1)+". "+data[i].getName());
            title.setTag(i);
            title.setBackgroundResource(R.drawable.border);
            title.setTextSize(20);
            title.setTextColor(getResources().getColor(R.color.black));
            content.addView(title);
            title.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                LocationList.setActiveOption((int)v.getTag());
                Intent myIntent = new Intent(v.getContext(), ActivityActions.class);
                startActivityForResult(myIntent, 0);
            }
        });
        }
    }
}
