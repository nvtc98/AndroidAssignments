package com.example.bai2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.*;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    RowAdapter adapter;
    ArrayList<Row> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    void init(){
        list= new ArrayList<Row>();
        addData(list);
        listView = (ListView) findViewById(R.id.listView);
        adapter = new RowAdapter(this,R.layout.row, list);
        listView.setAdapter(adapter);
    }

    void addData(ArrayList<Row> list){
        list.add(new Row("SALAD MỰC ỐNG",R.drawable.img_0,R.drawable.thump_0,"https://monngonmoingay.com/salad-muc-ong/"));
        list.add(new Row("ỚT CHUÔNG HẢI SẢN PHÔ MAI",R.drawable.img_1,R.drawable.thump_1,"https://monngonmoingay.com/ot-chuong-hai-san-pho-mai/"));
        list.add(new Row("CÁ DỨA MỘT NẮNG CHUA NGỌT",R.drawable.img_2,R.drawable.thump_2,"https://monngonmoingay.com/ca-dua-mot-nang-chua-ngot/"));
        list.add(new Row("CANH SƯỜN BÍ ĐỎ RONG BIỂN",R.drawable.img_3,R.drawable.thump_3,"https://monngonmoingay.com/canh-suon-bi-do-rong-bien/"));
        list.add(new Row("ĐẬU HŨ CUỘN CHIÊN GIÒN",R.drawable.img_4,R.drawable.thump_4,"https://monngonmoingay.com/dau-hu-cuon-chien-gion/"));
    }
}
