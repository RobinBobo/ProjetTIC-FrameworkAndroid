package configuratormanagement;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import org.xmlpull.v1.XmlSerializer;

import android.content.Context;
import android.util.Log;

public class XmlManagor {

	private final static String NAMESPACE = "";
	private String text;

	public void create (Configurator config, File newxmlfile) throws XmlPullParserException, IllegalArgumentException, IllegalStateException, IOException {

		XmlPullParserFactory factory = XmlPullParserFactory.newInstance(
				System.getProperty(XmlPullParserFactory.PROPERTY_NAME), null);
		XmlSerializer serializer = factory.newSerializer();		

		FileOutputStream fileos = null;
		try{
			fileos = new FileOutputStream(newxmlfile);

		} catch(FileNotFoundException e) {
			Log.e("FileNotFoundException",e.toString());
		}

		// set output
		serializer.setOutput(fileos, "UTF-8");
		// first write XML declaration
		serializer.startDocument(null, null);
		// add some empty lines before first start tag
		serializer.ignorableWhitespace("\n\n");

		// if prefix is not set serializer will generate automatically prefix
		// we overwrite this mechanism and manually namespace to be default (empty prefix)
		serializer.setPrefix("", NAMESPACE);

		// or writing can be delegate to specialized functions
		writeLine(serializer, "isSoppingCart", Boolean.toString(config.getShoppingCart()));
		writeLine(serializer, "customerNotice", Boolean.toString(config.getCustomerNotice()));
		writeLine(serializer, "order", Boolean.toString(config.getOrder()));
		writeLine(serializer, "websiteName",config.getWebsiteName());

		// this will ensure that output is flushed and prevent from writing to serializer
		serializer.endDocument();
		serializer.flush();
		fileos.close();
	}

	public void writeLine(XmlSerializer serializer, String tag, String data)
			throws IOException {
		serializer.startTag(NAMESPACE, tag);
		serializer.text(data);
		serializer.endTag(NAMESPACE, tag);
		serializer.text("\n");
	}

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
