package com.example.nationinfo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView lvGeoname;
    private ArrayList<String> Geoname;
    private ArrayAdapter adapter;
    private GeonameList list;
    private String url = "http://api.geonames.org/countryInfoJSON?formatted=true&username=hauvu&style=full&fbclid=IwAR2of8PmJPpcOhvDQLTIwY2PQpRBn7NlVBoOPbKWVxrUJw4e0CMvlx8eHG4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initLoadingScreen();

        if (checkInternetConnection() == true ) {
            this.fetch();
        } else Toast.makeText(this, "No internet connection.", Toast.LENGTH_SHORT).show();
    }

    void initCountries(){
        setContentView(R.layout.activity_main);
        this.Geoname = new ArrayList<>();
        this.list = new GeonameList();
        this.lvGeoname = (ListView) findViewById(R.id.lvGeoname);

        this.lvGeoname.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = Geoname.get(position);
                for (int i = 0; i < list.getLength(); i++) {
                    if (name.equals(list.get(i).getCountryName())) {
                        Intent intent = new Intent(MainActivity.this, Information.class);
                        intent.putExtra("geoname", list.get(i));
                        startActivity(intent);
                    }
                }
            }
        });
    }


    void fetch() {
        new JSONReader().execute(this.url);
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


    private class JSONReader extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            StringBuilder content = new StringBuilder();
            BufferedReader bufferedReader = null;
            try {
                URL url = new URL(strings[0]);
                InputStreamReader inputStreamReader = new InputStreamReader(url.openConnection().getInputStream());
                bufferedReader = new BufferedReader(inputStreamReader);

                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    content.append(line);
                }
                bufferedReader.close();

            } catch (MalformedURLException e) {
                return "";
            } catch (IOException e) {
                return "";
            }
            return content.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(s.length()==0)
            {
                setLoadingFailed();
                return;
            }
            try {
                initCountries();
                JSONObject object = new JSONObject(s);
                JSONArray array = object.getJSONArray("geonames");
                for (int i = 0; i < array.length(); i++) {
                    JSONObject item = array.getJSONObject(i);
                    com.example.nationinfo.Geoname geoname = new Geoname(
                            item.getInt("geonameId"),
                            item.getString("population"),
                            item.getString("areaInSqKm"),
                            item.getString("countryCode"),
                            item.getString("countryName")
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

    public static boolean isConnectedInternet(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    private boolean checkInternetConnection() {

        ConnectivityManager connManager =
                (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);


        NetworkInfo networkInfo = connManager.getActiveNetworkInfo();

        if (networkInfo == null) return false;
        if (!networkInfo.isConnected()) return false;
        if (!networkInfo.isAvailable()) return false;
        return true;
    }
}
