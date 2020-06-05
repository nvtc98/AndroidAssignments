package com.example.bai4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    List<String> listViewArr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState!=null){
            listViewArr = savedInstanceState.getStringArrayList("listviewarr");
        }
        else {
            listViewArr = new ArrayList<String>();
        }

        init();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putStringArrayList("listviewarr", (ArrayList<String>) listViewArr);
    }

    private void init(){
        listView = findViewById(R.id.history);
        listView.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listViewArr));

        final Button button = findViewById(R.id.btn_submit);
        button.setOnClickListener(new View.OnClickListener() {
            String output="";
            TextView txtOutput= findViewById(R.id.output);
            RadioButton rbcCtf = findViewById(R.id.radio_ctof);
            EditText input = findViewById(R.id.input);

            public void onClick(View v) {
                try {
                    Boolean ctof = rbcCtf.isChecked();
                    if (ctof) {
                        output = String.valueOf(Math.round(((Double.parseDouble(input.getText().toString()) * 9 / 5) + 32) * 10) / 10.0);
                    } else {
                        output = String.valueOf(Math.round(((Double.parseDouble(input.getText().toString()) - 32) * 5 / 9) * 10) / 10.0);
                    }
                    txtOutput.setText(output);
                    addToListView((ctof ? "C to F: " : "F to C: ") + Double.parseDouble(input.getText().toString()) + " -> " + output);
                }
                catch (Exception e)
                {
                    Toast.makeText(getApplicationContext(),"Please enter a valid value.",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void addToListView(String s)
    {
        listViewArr.add(s);
        ArrayAdapter<String> arrayAdapter
                = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listViewArr);

        listView.setAdapter(arrayAdapter);
    }
}
