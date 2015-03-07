package configuratormanagement;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import org.xmlpull.v1.XmlSerializer;

import android.util.Log;
import beans.Categorie;
import beans.Client;
import beans.Marque;
import beans.Produit;

public class XmlCreator {

	private final static String NAMESPACE = "";
	private String text;

	public void create (Configurator config, ArrayList<Produit> listeProduits, File newxmlfile) throws XmlPullParserException, IllegalArgumentException, IllegalStateException, IOException {

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
		addConfiguration(serializer, config);
		for (int i=0; i<listeProduits.size(); i++) {
			addProduct(serializer, listeProduits.get(i)); // APPELLER CETTE METHODE POUR AJOUTER PLUSIEURS PRODUITS
		}

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

	public void addCustomer ( XmlSerializer serializer, Client c) throws IllegalArgumentException, IllegalStateException, IOException {			
		serializer.startTag(NAMESPACE, "Client");
		writeLine(serializer, "id", String.valueOf(c.getIdClient()) );
		writeLine(serializer, "nom", c.getNomClient());
		writeLine(serializer, "prenom", c.getPrenomClient() );
		writeLine(serializer, "adresse", c.getAdresseClient());
		writeLine(serializer, "sexe", Boolean.toString(c.getSexeClient()));
		serializer.endTag(NAMESPACE, "Client");
		serializer.text("\n");
	}

	public void addCategory ( XmlSerializer serializer, Categorie c) throws IllegalArgumentException, IllegalStateException, IOException {			
		serializer.startTag(NAMESPACE, "Categorie");
		writeLine(serializer, "id", String.valueOf(c.getIdCategorie()));
		writeLine(serializer, "nom", c.getNomCategorie());	
		serializer.endTag(NAMESPACE, "Categorie");
		serializer.text("\n");
	}

	public void addBrand( XmlSerializer serializer, Marque m) throws IllegalArgumentException, IllegalStateException, IOException {			
		serializer.startTag(NAMESPACE, "Marque");
		writeLine(serializer, "id", String.valueOf(m.getIdMarque()));
		writeLine(serializer, "nom", m.getNomMarque());	
		serializer.endTag(NAMESPACE, "Marque");
		serializer.text("\n");
	}

	public void addProduct( XmlSerializer serializer, Produit p) throws IllegalArgumentException, IllegalStateException, IOException {		
		//Quid Categorie ??
		serializer.startTag(NAMESPACE, "Produit");
		writeLine(serializer, "id", String.valueOf(p.getIdProduit()));
		writeLine(serializer, "nom", p.getNomProduit());
		writeLine(serializer, "prix", String.valueOf(p.getPrixProduit()));
		writeLine(serializer, "description", p.getDescriptionProduit());
		writeLine(serializer, "marque", p.getMarqueProduit());
		writeLine(serializer, "stock", String.valueOf(p.getStockProduit()));
		writeLine(serializer, "Categorie",p.getCategorieProduit());
		serializer.endTag(NAMESPACE, "Produit");
		serializer.text("\n");
	}

	public void addConfiguration (XmlSerializer serializer, Configurator c) throws IOException {
		serializer.startTag(NAMESPACE, "Configuration");
		writeLine(serializer, "isSoppingCart", Boolean.toString(c.getShoppingCart()));
		writeLine(serializer, "customerNotice", Boolean.toString(c.getCustomerNotice()));
		writeLine(serializer, "order", Boolean.toString(c.getOrder()));
		writeLine(serializer, "websiteName",c.getWebsiteName());
		serializer.endTag(NAMESPACE, "Configuration");
		serializer.text("\n");
	}
}
