package com.example.bai1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.CheckBox;
import android.telephony.SmsManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_SEND_SMS = 1;
    private final String address = "5556"; //"5554","5556"
    private CheckBox [][] cbList=new CheckBox[3][5];
    private boolean canSendSms=false, pressSend=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        initData();
        initSubmitBtn();
        checkPermission();
    }

    private void initData(){
        String[][] fillingsData={{"Beef", "Chicken","White Fish", "Cheese", "Sea food"},{"Rice", "Beans","Pico de Gallo", "Guacamole", "LBT"}};
        String[][] beverageData={{"Soda", "Cerveza"},{"Margarita", "Tequila"}};

        LinearLayout lnCheckBoxLeft = findViewById(R.id.chb_fillings_left);
        LinearLayout lnCheckBoxRight = findViewById(R.id.chb_fillings_right);
        for (int i = 0; i < fillingsData[0].length; ++i) {
            CheckBox chBL = new CheckBox(this);
            chBL.setText(fillingsData[0][i]);
            cbList[0][i]=chBL;
            lnCheckBoxLeft.addView(chBL);
            CheckBox chBR = new CheckBox(this);
            chBR.setText(fillingsData[1][i]);
            lnCheckBoxRight.addView(chBR);
            cbList[1][i]=chBR;
        }

        LinearLayout lnBL = findViewById(R.id.chb_b_l);
        LinearLayout lnBR = findViewById(R.id.chb_b_r);
        for (int i = 0; i < beverageData[0].length; ++i) {
            CheckBox chBL = new CheckBox(this);
            chBL.setText(beverageData[0][i]);
            cbList[2][i]=chBL;
            lnBL.addView(chBL);
            CheckBox chBR = new CheckBox(this);
            chBR.setText(beverageData[1][i]);
            lnBR.addView(chBR);
            cbList[2][i+2]=chBR;
        }
    }

    private void initSubmitBtn(){
        final Button button = findViewById(R.id.btn_submit);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                pressSend=true;
                if(!canSendSms)
                {
                    checkPermission();
                    return;
                }
                sendSms();
                pressSend=false;
            }
        });
    }

    private void sendSms(){
        String msg = "I want a " + getSize() + " " + getTortilla() + " Taco with " + getFillings() + getBeverage() + ".";
        if(msg.length()>=160)
        {
            sendLongSms(msg);
            return;
        }
        Toast.makeText(getApplicationContext(), "Sending SMS", Toast.LENGTH_LONG).show();
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(address, null, msg, null, null);
    }

    private void sendLongSms2(String msg){ //send directly like the short one
        SmsManager sms = SmsManager.getDefault();
        ArrayList<String> parts = sms.divideMessage(msg);
        sms.sendMultipartTextMessage(address, null, parts, null, null);
    }

    private void sendLongSms(String msg){ //via intent
        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("sms:" + address));
        intent.putExtra("sms_body", msg); startActivity(intent);
    }

    private void checkPermission(){
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.SEND_SMS},REQUEST_SEND_SMS);
        }
        else{
            canSendSms=true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        Button button = findViewById(R.id.btn_submit);
        switch (requestCode){
            case REQUEST_SEND_SMS:
            {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    canSendSms=true;
                    if(pressSend) sendSms();
                } else {
                    Toast.makeText(getApplicationContext(),"You can't order taco because you didn't accept permission SEND_SMS.",Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    private String getSize(){
        RadioGroup rgSize=findViewById(R.id.radiogroup_size);
        RadioButton rbSize = findViewById(rgSize.getCheckedRadioButtonId());
        return rbSize.getText().toString();
    }

    private String getTortilla(){
        RadioGroup rgTor=findViewById(R.id.radiogroup_tortilla);
        RadioButton rbTor = findViewById(rgTor.getCheckedRadioButtonId());
        return rbTor.getText().toString();
    }

    private String getFillings(){
        String result="";
        boolean comma=false;
        for(int i=0;i<2;++i)
            for(int j=0;j<cbList[i].length;++j)
            {
                CheckBox x = cbList[i][j];
                if(x.isChecked()==true)
                {
                    if(comma)
                    {
                        result+=", ";
                    }
                    else {
                        comma=true;
                    }
                    result+=x.getText().toString();
                }
            }
        if(result.length()<1) return "no fillings";
        return "these fillings: "+result;
    }

    private String getBeverage(){
        String result="";
        boolean comma=false;
        for(int i=0;i<4;++i){
            CheckBox x = cbList[2][i];
            if(x.isChecked()==true)
            {
                if(comma)
                {
                    result+=", ";
                }
                else {
                    comma=true;
                }
                result+=x.getText().toString();
            }
        }
        if(result.length()<1) return "; and no beverage";
        return "; and beverage: "+result;
    }
}
