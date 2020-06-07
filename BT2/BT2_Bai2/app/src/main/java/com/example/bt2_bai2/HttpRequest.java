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

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class HttpRequest {
    public static final String TAG = "HttpRequestError";
    static private String response="nothing";
    static private final String url ="https://aud.fxexchangerate.com/rss.xml"; //https://www.fxexchangerate.com/currency-converter-rss-feed.html
    static private List<RssFeedModel> mFeedModelList;
    static Context c;
    static String sendRequest(Context context, final MainActivity mainActivity){
        RequestQueue queue = Volley.newRequestQueue(context.getApplicationContext());
        c=context;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String res) {
                        response =res;
//                        Toast.makeText(c.getApplicationContext(),res,Toast.LENGTH_LONG).show();
                        mainActivity.setContentView(R.layout.activity_main);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                response= String.valueOf(error);
                mainActivity.setLoadingFailed();
            }
        });

        queue.add(stringRequest);

        return response;
    }

//    static void get(){
//        try {
//            URL url = new URL(HttpRequest.url);
//            InputStream inputStream = url.openConnection().getInputStream();
//            mFeedModelList = parseFeed(inputStream);
//        }
//        catch (IOException e) {
//            Log.e(TAG, "Error", e);
//        } catch (XmlPullParserException e) {
//            Log.e(TAG, "Error", e);
//        }
//    }

//    static List<RssFeedModel> parseFeed(InputStream inputStream) throws XmlPullParserException, IOException {
//        String title = null;
//        String link = null;
//        String description = null;
//        boolean isItem = false;
//        List<RssFeedModel> items = new ArrayList<>();
//
//        try {
//            XmlPullParser xmlPullParser = Xml.newPullParser();
//            xmlPullParser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
//            xmlPullParser.setInput(inputStream, null);
//
//            xmlPullParser.nextTag();
//            while (xmlPullParser.next() != XmlPullParser.END_DOCUMENT) {
//                int eventType = xmlPullParser.getEventType();
//
//                String name = xmlPullParser.getName();
//                if(name == null)
//                    continue;
//
//                if(eventType == XmlPullParser.END_TAG) {
//                    if(name.equalsIgnoreCase("item")) {
//                        isItem = false;
//                    }
//                    continue;
//                }
//
//                if (eventType == XmlPullParser.START_TAG) {
//                    if(name.equalsIgnoreCase("item")) {
//                        isItem = true;
//                        continue;
//                    }
//                }
//
//                Log.d("MyXmlParser", "Parsing name ==> " + name);
//                String result = "";
//                if (xmlPullParser.next() == XmlPullParser.TEXT) {
//                    result = xmlPullParser.getText();
//                    xmlPullParser.nextTag();
//                }
//
//                if (name.equalsIgnoreCase("title")) {
//                    title = result;
//                } else if (name.equalsIgnoreCase("link")) {
//                    link = result;
//                } else if (name.equalsIgnoreCase("description")) {
//                    description = result;
//                }
//
//                if (title != null && link != null && description != null) {
//                    if(isItem) {
//                        RssFeedModel item = new RssFeedModel(title, link, description);
//                        items.add(item);
//                    }
//                    else {
//                        mFeedTitle = title;
//                        mFeedLink = link;
//                        mFeedDescription = description;
//                    }
//
//                    title = null;
//                    link = null;
//                    description = null;
//                    isItem = false;
//                }
//            }
//
//            return items;
//        } finally {
//            inputStream.close();
//        }
//    }
}
