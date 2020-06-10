package com.example.bt2_bai2;

import android.util.Xml;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class RssCurrenciesParser extends RssParser {
    public RssCurrenciesParser(String url, MainActivity mainActivity) {
        super(url, mainActivity);
//        this.url = url;
//        this.mainActivity = mainActivity;
    }

    @Override
    protected void onPostExecute(Boolean success) {
        if(success)
        {
            String [] currencies = (String []) payload;
            mainActivity.setLoadingSuccess();
            mainActivity.initSpinners(currencies);
        }
        else
        {
            Toast.makeText(mainActivity,
                    "Enter a valid Rss feed url",
                    Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public Object parseFeed(InputStream inputStream) throws XmlPullParserException, IOException
    {
        List<String> currencies = new ArrayList<String>();

        try {
            XmlPullParser xmlPullParser = super.initXmlPullParser(inputStream);
            String name="";
            Boolean isReadingItem=false;
            int event = xmlPullParser.getEventType();
            while (event != XmlPullParser.END_DOCUMENT) {
                if(xmlPullParser.getName()!=null)
                    name = xmlPullParser.getName();
                if(isReadingItem)
                {
                    switch (event) {
                        case XmlPullParser.START_TAG:
                            if(name.equalsIgnoreCase("title"))
                            {
                                currencies.add(parseCurrency(xmlPullParser.nextText()));
                            }
                            break;
                    }
                }
                else if(name.equalsIgnoreCase("item"))
                {
                    isReadingItem=true;
                }
                event = xmlPullParser.next();
            }
        } finally {
            inputStream.close();
            return currencies.toArray(new String[currencies.size()]);
        }
    }

    String parseCurrency(String input){
        String [] frags = input.split("/");
        return frags[1];
    }

}
