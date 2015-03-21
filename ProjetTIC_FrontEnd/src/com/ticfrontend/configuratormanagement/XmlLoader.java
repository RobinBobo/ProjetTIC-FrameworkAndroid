package com.ticfrontend.configuratormanagement;

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

import com.ticfrontend.magasin.Categorie;
import com.ticfrontend.magasin.Client;
import com.ticfrontend.magasin.Produit;



public class XmlLoader {

	private String text;
	private final static String NAMESPACE = "";

	public void load (InputStream is, Configurator config, List<Produit> listProduit, List<Categorie> listCategorie) {

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

	public void loadCustomer (InputStream is, List<Client> listClient) {

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
					if(tagname.equalsIgnoreCase("Client")){ // Si de type client
						Client cl = new Client(); // Création Produit vide
						parseIntoCustomer(eventType, parser, cl);
						listClient.add(cl); // On ajoute notre produit parsé dans notre liste
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

	public void writeLine(XmlSerializer serializer, String tag, String data)
			throws IOException {
		serializer.startTag(NAMESPACE, tag);
		serializer.text(data);
		serializer.endTag(NAMESPACE, tag);
		serializer.text("\n");
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
				else if(tagname.equalsIgnoreCase("triProduit"))
					c.setSortProduct(Boolean.valueOf(text));
				else if(tagname.equalsIgnoreCase("triCategorie"))
					c.setSortCategory(Boolean.valueOf(text));
				else if(tagname.equalsIgnoreCase("rechercheProduit"))
					c.setProductSearch(Boolean.valueOf(text));
				else if(tagname.equalsIgnoreCase("rechercheCategorie"))
					c.setCategorySearch(Boolean.valueOf(text));
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

	public void parseIntoCustomer (int eventType, XmlPullParser parser, Client c) throws XmlPullParserException, IOException{
		eventType = parser.next();
		String tagname = parser.getName();
		while (!tagname.equalsIgnoreCase("Client")) {
			switch (eventType) {
			case XmlPullParser.TEXT:					
				text = parser.getText();
				break;
			case XmlPullParser.END_TAG:	
				if(tagname.equalsIgnoreCase("id"))
					c.setIdClient(Integer.valueOf(text));
				else if(tagname.equalsIgnoreCase("login"))
					c.setLogin(text);
				else if(tagname.equalsIgnoreCase("nomClient"))
					c.setNomClient(text);
				else if(tagname.equalsIgnoreCase("prenomClient"))
					c.setPrenomClient(text);
				else if(tagname.equalsIgnoreCase("adresseClient"))
					c.setAdresseClient(text);
				else if(tagname.equalsIgnoreCase("adresseMail"))
					c.setAdresseMail(text);
				else if(tagname.equalsIgnoreCase("sexeClient"))
					c.setSexeClient(Boolean.valueOf(text));
				else if(tagname.equalsIgnoreCase("mdpClient"))				
					c.setMdpClient(text);
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
				//else if(tagname.equalsIgnoreCase("stock"))					
				else if(tagname.equalsIgnoreCase("Categorie")) {
					for (Categorie c : listCategorie) {
						if (c.getNomCategorie() == text)
							p.setCategorieProduit(c);
					}	
					p.setIconRessource(Produit.getRandomImage());
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
				c.setIconRessource(Categorie.getNoRandomImage());
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

	public void createCustomer  (File newxmlfile, ArrayList<Client> listeClient) throws XmlPullParserException, IllegalArgumentException, IllegalStateException, IOException {

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

		for (int i=0; i<listeClient.size(); i++) {
			addCustomer(serializer, listeClient.get(i)); // APPELLER CETTE METHODE POUR AJOUTER PLUSIEURS PRODUITS
		}	

		// this will ensure that output is flushed and prevent from writing to serializer
		serializer.endDocument();
		serializer.flush();
		fileos.close();
	}	

	public void addCustomer( XmlSerializer serializer, Client c) throws IllegalArgumentException, IllegalStateException, IOException {		
		//Quid Categorie ??		
		serializer.startTag(NAMESPACE, "Client");
		writeLine(serializer, "id", String.valueOf(c.getIdClient()));
		writeLine(serializer, "login", c.getLogin() );
		writeLine(serializer, "nomClient", c.getNomClient());
		writeLine(serializer, "prenomClient", c.getPrenomClient());
		writeLine(serializer, "adresseClient", c.getAdresseClient() );	
		writeLine(serializer, "adresseMail", c.getAdresseMail() );
		writeLine(serializer, "sexeClient", c.getSexeClientToString() );
		writeLine(serializer, "mdpClient", c.getMdpClient());		
		serializer.endTag(NAMESPACE, "Client");
		serializer.text("\n");
	}
}