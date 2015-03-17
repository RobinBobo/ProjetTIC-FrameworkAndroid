package com.ticfrontend.configuratormanagement;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import com.ticfrontend.magasin.Categorie;
import com.ticfrontend.magasin.Client;
import com.ticfrontend.magasin.Produit;

public class XmlLoader {

	private String text;

	public void load (InputStream is, Configurator config, List<Produit> listProduit) {

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
						parseIntoProduct(eventType, parser, p);//Créer nouveau client + parseInto   TODO
						listProduit.add(p); // On ajoute notre produit parsé dans notre liste
					}
					/*else if(tagname.equalsIgnoreCase("Categorie"))
						//Créer nouvelle categorie + parseInto
					else if(tagname.equalsIgnoreCase("Marque"))
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

	public void parseIntoProduct (int eventType, XmlPullParser parser, Produit p) throws XmlPullParserException, IOException{
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
				else if(tagname.equalsIgnoreCase("Categorie"))
					p.setCategorieProduit(new Categorie(1,text));
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