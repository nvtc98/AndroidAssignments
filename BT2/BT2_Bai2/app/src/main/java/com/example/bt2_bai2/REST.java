package com.example.bt2_bai2;

import android.app.DownloadManager;
import android.content.Context;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class REST {
    static private String response="nothing";
    static Context c;
    static String sendRequest(Context context){
        RequestQueue queue = Volley.newRequestQueue(context.getApplicationContext());
        c=context;
        String url ="https://aud.fxexchangerate.com/rss.xml"; //https://www.fxexchangerate.com/currency-converter-rss-feed.html
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String res) {
                        response =res;
                        Toast.makeText(c.getApplicationContext(),res,Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                response= String.valueOf(error);
                Toast.makeText(c.getApplicationContext(),response,Toast.LENGTH_LONG).show();
            }
        });

        queue.add(stringRequest);

        return response;
    }
}
