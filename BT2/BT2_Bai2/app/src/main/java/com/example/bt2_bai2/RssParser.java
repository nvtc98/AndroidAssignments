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

public class RssParser extends AsyncTask<Void, Void, Boolean> {
    protected String url;
    protected MainActivity mainActivity;
    protected static final String TAG = "HttpRequestError";
    protected Object payload;

    public RssParser(String url, MainActivity mainActivity) {
        this.url = url;
        this.mainActivity = mainActivity;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        try {
            if(!url.startsWith("http://") && !url.startsWith("https://"))
                url = "http://" + url;

            URL url = new URL(this.url);
            InputStream inputStream = url.openConnection().getInputStream();
            payload = parseFeed(inputStream);
            if(payload==null)
                return false;
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
            Toast.makeText(mainActivity,
                    "Success",
                    Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(mainActivity,
                    "Enter a valid Rss feed url",
                    Toast.LENGTH_LONG).show();
        }
    }

    public Object parseFeed(InputStream inputStream) throws XmlPullParserException, IOException
    {
        return null;
    }

    protected XmlPullParser initXmlPullParser(InputStream inputStream) throws XmlPullParserException {
        XmlPullParser xmlPullParser = Xml.newPullParser();
        xmlPullParser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
        xmlPullParser.setInput(inputStream, null);
        return xmlPullParser;
    }

}
