package com.example.bt2_bai2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;

public class MainActivity extends AppCompatActivity {
    final private String url = "https://aud.fxexchangerate.com/rss.xml";

    private Currency[] currencies;
    private int indexTo;

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
        final Button btnExchange = findViewById(R.id.btn_exchange);
        btnExchange.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                btnExchange.setText("Calculating...");
                if (checkInternetConnection() == true) {
                    exchange();
                }
                else Toast.makeText(MainActivity.this, "No Internet", Toast.LENGTH_SHORT).show();
                btnExchange.setText("EXCHANGE");
            }
        });
    }

    void initSpinners(String[] arr){
        Spinner spinnerFrom = findViewById(R.id.spn_from);
        Spinner spinnerTo = findViewById(R.id.spn_to);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arr);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFrom.setAdapter(adapter);
        spinnerTo.setAdapter(adapter);
    }

    void exchange(){
        Spinner spinnerFrom = findViewById(R.id.spn_from);
        Spinner spinnerTo = findViewById(R.id.spn_to);
        int indexFrom = spinnerFrom.getSelectedItemPosition();
        int indexTo = spinnerTo.getSelectedItemPosition();
        this.indexTo = indexTo;
        if(indexFrom == indexTo)
        {
            showResult(new BigDecimal(1));
            return;
        }
        String exchangeLink = currencies[indexFrom].getLink() + currencies[indexTo].getISO().toLowerCase() + ".xml";
        RssExchangeParser rssParser = new RssExchangeParser(exchangeLink, this);
        rssParser.execute((Void) null);
    }

    public void setCurrencies(Currency[] currencies) {
        this.currencies = currencies;
    }

    void showNetworkError(){
        Toast.makeText(this, "Network error. Please try again later.", Toast.LENGTH_LONG).show();
    }

    public void showResult(BigDecimal exRate) {
        try {
            EditText et = findViewById(R.id.input);
            TextView txtResult = findViewById(R.id.txt_result);
            BigDecimal input = new BigDecimal(et.getText().toString());
            txtResult.setText(exRate.toString());
            txtResult.setText(input.multiply(exRate) + " " + currencies[indexTo].getISO());
        }
        catch (Exception e)
        {
            Toast.makeText(this, "Cannot exchange. Make sure you you enter a valid input.", Toast.LENGTH_LONG).show();
        }
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
