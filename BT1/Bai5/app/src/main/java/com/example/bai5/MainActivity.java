package com.example.bai5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Context;
import java.util.*;
import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class MainActivity extends AppCompatActivity {
    private String lastData="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onPause(){
        super.onPause();

        EditText et=findViewById(R.id.input);
        String data=et.getText().toString();
        if(!data.equals(lastData)) writeToFile(getCurrentTime()+"@"+data,this);
    }

    @Override
    protected void onResume(){
        super.onResume();

        String data= readFromFile(this);
        int spl = data.indexOf("@");
        if (spl<0) return;
        TextView title=findViewById(R.id.title);
        title.setText("Last update:\n"+data.substring(0,spl));
//        Toast.makeText(getApplicationContext(),data,Toast.LENGTH_LONG).show();
        EditText et=findViewById(R.id.input);
        lastData=data.substring(spl+1);
        et.setText(lastData);
    }

    private void writeToFile(String data,Context context) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("config.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    private String readFromFile(Context context) {
        String ret = "";
        try {
            InputStream inputStream = context.openFileInput("config.txt");

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        return ret;
    }

    String getCurrentTime(){
        final DateFormat df = new SimpleDateFormat("HH:mm dd/MM/yyyy");
        final Calendar c = Calendar.getInstance();
        String s = df.format(c.getTime());
        return s;
    }
}
