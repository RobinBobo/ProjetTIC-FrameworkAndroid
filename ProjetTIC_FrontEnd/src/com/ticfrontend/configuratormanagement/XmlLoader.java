package com.ticfrontend.configuratormanagement;

import java.io.InputStream;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;


import android.util.Log;

public class XmlLoader {

	private String text;

	public void load (InputStream is, Configurator config) {

		XmlPullParserFactory factory = null;
		XmlPullParser parser = null;

		try {
			factory = XmlPullParserFactory.newInstance();
			factory.setNamespaceAware(true);

			parser = factory.newPullParser();
			parser.setInput(is, null);

			int eventType = parser.getEventType();
			while(eventType != XmlPullParser.END_DOCUMENT) {
				String tagname = parser.getName();
				switch (eventType) {
				case XmlPullParser.START_TAG:					
					break;

				case XmlPullParser.TEXT:					
					text = parser.getText();
					break;

				case XmlPullParser.END_TAG:				
					if(tagname.equalsIgnoreCase("isSoppingCart"))
						config.setShoppingCart(Boolean.valueOf(text));
					else if(tagname.equalsIgnoreCase("customerNotice"))
						config.setCustomerNotice(Boolean.valueOf(text));
					else if(tagname.equalsIgnoreCase("order"))
						config.setOrder(Boolean.valueOf(text));
					else if(tagname.equalsIgnoreCase("websiteName"))
						config.setWebsiteName(text);
					break;
				default:
					break;
				}
				eventType = parser.next();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}