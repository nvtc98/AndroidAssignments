package com.example.bt2_bai2;

import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class RssExchangeParser extends RssParser {
    public RssExchangeParser(String url, MainActivity mainActivity) {
        super(url, mainActivity);
    }

    @Override
    protected void onPostExecute(Boolean success) {
        if(success)
        {
            mainActivity.showResult((BigDecimal) payload);
        }
        else
        {
            mainActivity.setLoadingFailed();
        }
    }

    @Override
    public Object parseFeed(InputStream inputStream) throws XmlPullParserException, IOException
    {
        try {
            XmlPullParser xmlPullParser = super.initXmlPullParser(inputStream);
            String name="";
            Boolean isReadingItem=false;
            int event = xmlPullParser.getEventType();
            Currency c = new Currency();
            while (event != XmlPullParser.END_DOCUMENT) {
                if(xmlPullParser.getName()!=null)
                    name = xmlPullParser.getName();
                switch (event) {
                    case XmlPullParser.START_TAG:
                        if(name.equalsIgnoreCase("item"))
                        {
                            isReadingItem=true;
                            break;
                        }
                        if(isReadingItem)
                        {
                            if(name.equalsIgnoreCase("description"))
                            {
                                String desc = xmlPullParser.nextText();
                                return parse(desc);
                            }
                        }
                        break;
                }
                event = xmlPullParser.next();
            }
            return null;
//            return new Object();
        }
        catch (Exception e){
            return null;
        } finally {
            inputStream.close();
        }
    }

    BigDecimal parse(String desc){
//        String s = desc.substring(desc.indexOf("\n"),desc.indexOf("<br/>")).trim();
        String [] frags = desc.split(" ");
        BigDecimal result = new BigDecimal(frags[11]);
        return result;
    }
}
