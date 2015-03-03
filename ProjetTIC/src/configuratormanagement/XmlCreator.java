package configuratormanagement;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import org.xmlpull.v1.XmlSerializer;

import android.util.Log;

public class XmlCreator {

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
}
