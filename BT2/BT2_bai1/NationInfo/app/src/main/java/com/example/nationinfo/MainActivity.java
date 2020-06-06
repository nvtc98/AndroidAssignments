package com.example.nationinfo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lvGeoname;
    ArrayList<String> Geoname;
    ArrayAdapter adapter;
    GEONAME_LIST list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.Geoname = new ArrayList<>();
        this.list = new GEONAME_LIST();
        this.lvGeoname = (ListView) findViewById(R.id.lvGeoname);
        new ReadJSON().execute("http://api.geonames.org/" +
                "countryInfoJSON?formatted=true&username=hauvu&style=full&" +
                "fbclid=IwAR2of8PmJPpcOhvDQLTIwY2PQpRBn7NlVBoOPbKWVxrUJw4e0CMvlx8eHG4");

        this.lvGeoname.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = Geoname.get(position);
                for (int i = 0; i < list.getLength(); i++){
                    if (name.equals(list.get(i).getCountryName())){
                        //làm thêm trang hiện thị thông tin
                        Intent intent = new Intent(MainActivity.this, Information.class);
                        intent.putExtra("geoname", list.get(i));
                        startActivity(intent);
                    }
                }
            }
        });
    }


    private class ReadJSON extends AsyncTask<String, Void, String>
    {
        @Override
        protected String doInBackground(String... strings) {
            StringBuilder content = new StringBuilder();
            try {
                URL url = new URL(strings[0]);
                InputStreamReader inputStreamReader = new InputStreamReader(url.openConnection().getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    content.append(line);
                }

                bufferedReader.close();
            } catch (MalformedURLException e) {
                Toast.makeText(MainActivity.this, "Error 01", Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                Toast.makeText(MainActivity.this, "Error 02", Toast.LENGTH_SHORT).show();
            }

            return content.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                JSONObject object = new JSONObject(s);
                JSONArray array = object.getJSONArray("geonames");
                for (int i = 0; i < array.length(); i++) {
                    JSONObject item = array.getJSONObject(i);
                    GEONAME geoname = new GEONAME(
                            item.getString("continent"),
                            item.getString("capital"),
                            item.getString("languages"),
                            item.getInt("geonameId"),
                            item.getString("south"),
                            item.getString("isoAlpha3"),
                            item.getString("north"),
                            item.getString("fipsCode"),
                            item.getString("population"),
                            item.getString("east"),
                            item.getString("isoNumeric"),
                            item.getString("areaInSqKm"),
                            item.getString("countryCode"),
                            item.getString("west"),
                            item.getString("countryName"),
                            item.getString("continentName"),
                            item.getString("currencyCode")
                    );
                    list.addOneElement(geoname);
                    Geoname.add(geoname.getCountryName());
                }
                adapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, Geoname);
                lvGeoname.setAdapter(adapter);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }





}

//Toast.makeText(this, "Không thể kết nối Internet", Toast.LENGTH_SHORT).show();
