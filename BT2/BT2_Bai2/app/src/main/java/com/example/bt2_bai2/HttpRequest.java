package com.example.bt2_bai2;

import android.app.DownloadManager;
import android.content.Context;
import android.util.Log;
import android.util.Xml;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class HttpRequest {
    public static final String TAG = "HttpRequestError";
    static private String response="nothing";
    static private final String url ="https://aud.fxexchangerate.com/rss.xml"; //https://www.fxexchangerate.com/currency-converter-rss-feed.html
    static Context c;
    static String sendRequest(Context context, final MainActivity mainActivity){
        RequestQueue queue = Volley.newRequestQueue(context.getApplicationContext());
        c=context;
//        mainActivity.setContentView(R.layout.activity_main);
//        mainActivity.initSpinner();
//        RssParser rssParser = new RssParser(url, mainActivity);
//        rssParser.execute((Void) null);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String res) {
                        response =res;
//                        Toast.makeText(c.getApplicationContext(),res,Toast.LENGTH_LONG).show();
                        mainActivity.setContentView(R.layout.activity_main);
                        mainActivity.initSpinner();

//                        Document doc = convertStringToXMLDocument(response);
//                        Toast.makeText(c.getApplicationContext(),doc.getFirstChild().getNodeName(),Toast.LENGTH_LONG).show();
                        Toast.makeText(c.getApplicationContext(),"go parse",Toast.LENGTH_LONG).show();

                        RssParser rssParser = new RssParser(url, mainActivity);
                        rssParser.execute((Void) null);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                response= String.valueOf(error);
                Toast.makeText(mainActivity,"fail: "+response,Toast.LENGTH_LONG).show();
                mainActivity.setLoadingFailed();
            }
        });

        queue.add(stringRequest);

        return response;
    }

    static void get(){
        try {
//            URL url = new URL(HttpRequest.url);
//            InputStream inputStream = url.openConnection().getInputStream();
            InputStream input = new URL(url).openStream();
//            mFeedModelList = parseFeed(inputStream);
        }
        catch (IOException e) {
            Log.e(TAG, "Erroraaa", e);
//        } catch (XmlPullParserException e) {
//            Log.e(TAG, "Erroraaa", e);
        }
    }

    private static Document convertStringToXMLDocument(String xmlString)
    {
        //Parser that produces DOM object trees from XML content
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        //API to obtain DOM Document instance
        DocumentBuilder builder = null;
        try
        {
            //Create DocumentBuilder with default configuration
            builder = factory.newDocumentBuilder();

            //Parse the content to Document object
            Document doc = builder.parse(new InputSource(new StringReader(xmlString)));
            return doc;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
