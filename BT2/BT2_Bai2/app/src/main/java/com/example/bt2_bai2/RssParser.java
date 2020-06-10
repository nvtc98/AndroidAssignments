package com.example.bt2_bai2;

import android.os.AsyncTask;
import android.util.Log;
import android.util.Xml;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class RssParser extends AsyncTask<Void, Void, Boolean> {

    private String url;
    private MainActivity mainActivity;
    public static final String TAG = "HttpRequestError";
    String [] currencies=new String[0];

    public RssParser(String url, MainActivity mainActivity) {
        this.url = url;
        this.mainActivity = mainActivity;
    }

//    @Override
//    protected void onPreExecute() {
//    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        try {
            if(!url.startsWith("http://") && !url.startsWith("https://"))
                url = "http://" + url;

            URL url = new URL(this.url);
            InputStream inputStream = url.openConnection().getInputStream();
            currencies = parseFeed(inputStream);
            return true;
        } catch (IOException e) {
            Log.e(TAG, "Error", e);
        } catch (XmlPullParserException e) {
            Log.e(TAG, "Error", e);
        }
        return false;
    }

    @Override
    protected void onPostExecute(Boolean success) {
        if(success)
        {
            mainActivity.initSpinner(currencies);
        }
        else
        {
            Toast.makeText(mainActivity,
                    "Enter a valid Rss feed url",
                    Toast.LENGTH_LONG).show();
        }
    }

    public String[] parseFeed(InputStream inputStream) throws XmlPullParserException, IOException
    {
        List<String> currencies = new ArrayList<String>();

        try {
            XmlPullParser xmlPullParser = Xml.newPullParser();
            xmlPullParser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            xmlPullParser.setInput(inputStream, null);

            String name="";
            int event = xmlPullParser.getEventType();
            while (event != XmlPullParser.END_DOCUMENT) {
                if(xmlPullParser.getName()!=null)
                    name = xmlPullParser.getName();

                switch (event) {
                    case XmlPullParser.TEXT:
                        String text = xmlPullParser.getText();
                        currencies.add(text+" @ "+(name==null?"":name));
                        break;
                    case XmlPullParser.START_TAG:
                        if(name.equalsIgnoreCase("description"))
                        {
//                            currencies.add(xmlPullParser.nextText());
                        }
                        break;
                }
                event = xmlPullParser.next();
            }
        } finally {
            inputStream.close();
            return currencies.toArray(new String[currencies.size()]);
        }
    }

}
