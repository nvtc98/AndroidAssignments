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
//        mSwipeLayout.setRefreshing(false);
//
//        if (success) {
//            mFeedTitleTextView.setText("Feed Title: " + mFeedTitle);
//            mFeedDescriptionTextView.setText("Feed Description: " + mFeedDescription);
//            mFeedLinkTextView.setText("Feed Link: " + mFeedLink);
//            // Fill RecyclerView
//            mRecyclerView.setAdapter(new RssFeedListAdapter(mFeedModelList));
//        } else {
//            Toast.makeText(MainActivity.this,
//                    "Enter a valid Rss feed url",
//                    Toast.LENGTH_LONG).show();
//        }
    }

    public String[] parseFeed(InputStream inputStream) throws XmlPullParserException, IOException
    {
        String title = null;
        String link = null;
        String description = null;
        boolean isItem = false;
        List<String> currencies = new ArrayList<String>();

        try {
            XmlPullParser xmlPullParser = Xml.newPullParser();
            xmlPullParser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            xmlPullParser.setInput(inputStream, null);

            xmlPullParser.nextTag();
            while (xmlPullParser.next() != XmlPullParser.END_DOCUMENT) {
                int eventType = xmlPullParser.getEventType();

                String name = xmlPullParser.getName();
                currencies.add(name);
                if(name == null)
                    continue;

                if(eventType == XmlPullParser.END_TAG) {
                    if(name.equalsIgnoreCase("item")) {
                        isItem = false;
                    }
                    continue;
                }

                if (eventType == XmlPullParser.START_TAG) {
                    if(name.equalsIgnoreCase("item")) {
                        isItem = true;
                        continue;
                    }
                }

                Log.d("MyXmlParser", "Parsing name ==> " + name);
                String result = "";
                if (xmlPullParser.next() == XmlPullParser.TEXT) {
                    result = xmlPullParser.getText();
                    xmlPullParser.nextTag();
                }

                if (name.equalsIgnoreCase("title")) {
                    title = result;
                } else if (name.equalsIgnoreCase("link")) {
                    link = result;
                } else if (name.equalsIgnoreCase("description")) {
                    description = result;
                }

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
            }

            return (String[]) currencies.toArray();
        } finally {
            inputStream.close();
//            return (String[]) currencies.toArray();
        }
    }

}
