package configuratormanagement;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import org.xmlpull.v1.XmlSerializer;

import android.util.Log;
import beans.Categorie;
import beans.Client;
import beans.Marque;
import beans.Produit;

public class XmlManagor {

	private final static String NAMESPACE = "";
	private String text;

	public void create (Configurator config, ArrayList<Produit> listeProduits, ArrayList<Categorie> listeCategories, File newxmlfile) throws XmlPullParserException, IllegalArgumentException, IllegalStateException, IOException {

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

		for (int i=0; i<listeCategories.size(); i++) {
			addCategory(serializer, listeCategories.get(i)); // APPELLER CETTE METHODE POUR AJOUTER PLUSIEURS PRODUITS
		}


		for (int i=0; i<listeProduits.size(); i++) {
			addProduct(serializer, listeProduits.get(i), listeCategories); // APPELLER CETTE METHODE POUR AJOUTER PLUSIEURS PRODUITS
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

	public void addProduct( XmlSerializer serializer, Produit p, ArrayList<Categorie> listeCategories) throws IllegalArgumentException, IllegalStateException, IOException {		
		//Quid Categorie ??
		boolean isFinish = false;
		serializer.startTag(NAMESPACE, "Produit");
		writeLine(serializer, "id", String.valueOf(p.getIdProduit()));
		writeLine(serializer, "nom", p.getNomProduit());
		writeLine(serializer, "prix", String.valueOf(p.getPrixProduit()));
		writeLine(serializer, "description", p.getDescriptionProduit());
		writeLine(serializer, "marque", p.getMarqueProduit());
		writeLine(serializer, "stock", String.valueOf(p.getStockProduit()));
		for ( Categorie c : listeCategories) {
			for (Produit pC : c.getMesProduits()) {
				if (pC.getNomProduit() == p.getNomProduit()) {
					writeLine(serializer, "Categorie",c.getNomCategorie());
					isFinish = true;
					break;
				}
			}
			if (isFinish)
				break;
		}		
		serializer.endTag(NAMESPACE, "Produit");
		serializer.text("\n");
	}

	public void addConfiguration (XmlSerializer serializer, Configurator c) throws IOException {
		serializer.startTag(NAMESPACE, "Configuration");
		writeLine(serializer, "isSoppingCart", Boolean.toString(c.getShoppingCart()));
		writeLine(serializer, "customerNotice", Boolean.toString(c.getCustomerNotice()));
		writeLine(serializer, "order", Boolean.toString(c.getOrder()));

		if (c.getWebsiteName() == null) 
			c.setWebsiteName("");	

		writeLine(serializer, "websiteName",c.getWebsiteName());
		writeLine(serializer, "buttonsColor",String.valueOf(c.getButtonsColor()));
		serializer.endTag(NAMESPACE, "Configuration");
		serializer.text("\n");
	}

	public void load (InputStream is, Configurator config, ArrayList<Produit> listProduit, ArrayList<Categorie> listCategorie) {

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
				case XmlPullParser.START_TAG:	//Ouverture de la balise exemple : <1> <1>
					if(tagname.equalsIgnoreCase("Configuration")) // Si de type configuration
						parseIntoConfiguration(eventType, parser, config);
					else if(tagname.equalsIgnoreCase("Produit")){ // Si de type client
						Produit p = new Produit(); // Création Produit vide
						parseIntoProduct(eventType, parser, p,listCategorie);
						listProduit.add(p); // On ajoute notre produit parsé dans notre liste
					}
					else if(tagname.equalsIgnoreCase("Categorie")){ // Si de type categorie
						Categorie c = new Categorie(); // Création categorie vide
						parseIntoCategory(eventType, parser, c);
						listCategorie.add(c); // On ajoute notre categorie parsé dans notre liste
					}					
					/*else if(tagname.equalsIgnoreCase("Marque"))
						//Créer nouvelle Marque + parseInto
					else if(tagname.equalsIgnoreCase("Produit"))
						//Créer nouveau Produit + parseInto*/

					break;

				case XmlPullParser.TEXT:	// Texte compris entre 2 balises				
					text = parser.getText();
					break;

				case XmlPullParser.END_TAG:		//Fermeture de la balise exemple : <1> <1>	

					/*	if(tagname.equalsIgnoreCase("Client"))   TODO
						//Ajouter à la liste des clients
					else if(tagname.equalsIgnoreCase("Categorie"))
						//Ajouter à la liste des Categorie
					else if(tagname.equalsIgnoreCase("Marque"))
						//Ajouter à la liste des Marque
					else if(tagname.equalsIgnoreCase("Produit"))
						//Ajouter à la liste des Produit*/
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

	public void parseIntoConfiguration (int eventType, XmlPullParser parser, Configurator c) throws XmlPullParserException, IOException{
		eventType = parser.next();
		String tagname = parser.getName();
		while (!tagname.equalsIgnoreCase("Configuration")) {
			switch (eventType) {
			case XmlPullParser.TEXT:					
				text = parser.getText();
				break;
			case XmlPullParser.END_TAG:	
				if(tagname.equalsIgnoreCase("isSoppingCart"))
					c.setShoppingCart(Boolean.valueOf(text));
				else if(tagname.equalsIgnoreCase("customerNotice"))
					c.setCustomerNotice(Boolean.valueOf(text));
				else if(tagname.equalsIgnoreCase("order"))
					c.setOrder(Boolean.valueOf(text));
				else if(tagname.equalsIgnoreCase("websiteName"))
					c.setWebsiteName(text);
				else if(tagname.equalsIgnoreCase("buttonsColor"))
					c.setButtonsColor(Integer.valueOf(text));
				break;
			default:
				break;
			}
			eventType = parser.next();
			if (parser.getName() != null)
				tagname = parser.getName();
		}
		eventType--;
	}

	public void parseIntoProduct (int eventType, XmlPullParser parser, Produit p, List<Categorie> listCategorie) throws XmlPullParserException, IOException{
		eventType = parser.next();
		String tagname = parser.getName();
		while (!tagname.equalsIgnoreCase("Produit")) { // <client> <id> 1 <id> <nom> exemple <nom> ... <client>
			switch (eventType) {
			case XmlPullParser.TEXT:					
				text = parser.getText();
				break;
			case XmlPullParser.END_TAG:	
				if(tagname.equalsIgnoreCase("id"))
					p.setIdProduit(Integer.parseInt(text));
				else if(tagname.equalsIgnoreCase("nom"))
					p.setNomProduit(text);
				else if(tagname.equalsIgnoreCase("prix"))
					p.setPrixProduit(Double.parseDouble(text));
				else if(tagname.equalsIgnoreCase("description"))
					p.setDescriptionProduit(text);
				else if(tagname.equalsIgnoreCase("marque"))
					p.setMarqueProduit(text);
				else if(tagname.equalsIgnoreCase("stock"))	
					p.setStockProduit(Integer.valueOf(text));
				else if(tagname.equalsIgnoreCase("Categorie")) {
					for (Categorie c : listCategorie) {
						if (c.getNomCategorie() == text) {
							c.ajouterProduitCategorie(p);
							p.setCategorieProduit(text);
						}
					}						
				}

				break;
			default:
				break;
			}
			eventType = parser.next();
			if (parser.getName() != null)
				tagname = parser.getName();
		}
		eventType--;
	}

	public void parseIntoCategory (int eventType, XmlPullParser parser, Categorie c) throws XmlPullParserException, IOException{
		eventType = parser.next();
		String tagname = parser.getName();
		while (!tagname.equalsIgnoreCase("Categorie")) { // <client> <id> 1 <id> <nom> exemple <nom> ... <client>
			switch (eventType) {
			case XmlPullParser.TEXT:					
				text = parser.getText();
				break;
			case XmlPullParser.END_TAG:	
				if(tagname.equalsIgnoreCase("id"))
					c.setIdCategorie(Integer.parseInt(text));
				else if(tagname.equalsIgnoreCase("nom"))
					c.setNomCategorie(text);			
				break;
			default:
				break;
			}
			eventType = parser.next();
			if (parser.getName() != null)
				tagname = parser.getName();
		}
		eventType--;
	}
}
