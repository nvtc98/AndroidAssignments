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
    }

    @Override
    protected void onPostExecute(Boolean success) {
        if(success)
        {
            Currency [] currencies = (Currency []) payload;
            String [] titles= new String[currencies.length];
            for(int i=0;i<currencies.length;++i)
            {
                titles[i]=currencies[i].getTitle();
            }
            mainActivity.setLoadingSuccess();
            mainActivity.initSpinners(titles);
            mainActivity.setCurrencies(currencies);
        }
        else
        {
            mainActivity.setLoadingFailed();
        }
    }

    @Override
    public Object parseFeed(InputStream inputStream) throws XmlPullParserException, IOException
    {
        List<Currency> currencies = new ArrayList<Currency>();

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
                            c = new Currency();
                            break;
                        }
                        if(isReadingItem)
                        {
                            if(name.equalsIgnoreCase("title"))
                            {
                                c.setTitle(parseCurrency(xmlPullParser.nextText()));
                                break;
                            }
                            if(name.equalsIgnoreCase("link"))
                            {
                                c.setLink(xmlPullParser.nextText());
                                break;
                            }
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if(name.equalsIgnoreCase("item"))
                        {
                            currencies.add(c);
                            isReadingItem=false;
                        }
                        break;
                }
                event = xmlPullParser.next();
            }
            return currencies.toArray(new Currency[currencies.size()]);
        }
        catch (Exception e){
            return null;
        } finally {
            inputStream.close();
        }
    }

    String parseCurrency(String input){
        String [] frags = input.split("/");
        return frags[1];
    }

}
