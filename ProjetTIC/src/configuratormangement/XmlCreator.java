package configuratormangement;

import java.io.*;

import org.jdom2.*;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

//WARNING : The JDom Jar Files, can be found in the folder libs
public class XmlCreator
{
	//Nous allons commencer notre arborescence en cr�ant la racine XML
	private static Element root = new Element("configuration");

	//On cr�e un nouveau Document JDOM bas� sur la racine que l'on vient de cr�er
	private static Document document = new Document(root);

	public void create (Configurator config) throws DataConversionException
	{
		//On cr�e un nouvel Element etudiant et on l'ajoute
		//en tant qu'Element de racine
		Element parameters = new Element("parameters");
		root.addContent(parameters);
/*

		//On cr�e un nouvel Attribut classe et on l'ajoute � etudiant
		//gr�ce � la m�thode setAttribute
		Attribute classe = new Attribute("shoppingCart",Boolean.toString(config.getIsShoppingCart()));
		parameters.setAttribute(classe);	
		boolean test;
		test = classe.getBooleanValue();*/

		//On cr�e un nouvel Element nom, on lui assigne du texte
		//et on l'ajoute en tant qu'Element de etudiant
		Element shoppingCart = new Element("isSoppingCart");
		shoppingCart.setText(Boolean.toString(config.getIsShoppingCart()));
		
		Element customerNotice = new Element("customerNotice");
		customerNotice.setText(Boolean.toString(config.getIsCustomerNotice()));
		
		Element order = new Element("order");
		order.setText(Boolean.toString(config.getIsOrder()));	
		
		Element websiteName = new Element("websiteName");
		websiteName.setText(config.getWebsiteName());		
		
		parameters.addContent(shoppingCart);
		parameters.addContent(customerNotice);
		parameters.addContent(order);
		parameters.addContent(websiteName);		
		
		//boolean test2 = Boolean.valueOf(shoppingCart.getText()).booleanValue(); 
	
		//Les deux m�thodes qui suivent seront d�finies plus loin dans l'article
		//affiche();
		save("configuration.xml");
	}


	public void save(String file)
	{
		try
		{
			//On utilise ici un affichage classique avec getPrettyFormat()
			XMLOutputter out = new XMLOutputter(Format.getPrettyFormat());
			//Remarquez qu'il suffit simplement de cr�er une instance de FileOutputStream
			//avec en argument le nom du fichier pour effectuer la s�rialisation.
			out.output(document, new FileOutputStream(file));
		}
		catch (java.io.IOException e){}
	}
	
	public void display()
	{
	   try
	   {
	      //On utilise ici un affichage classique avec getPrettyFormat()
	      XMLOutputter out = new XMLOutputter(Format.getPrettyFormat());
	      out.output(document, System.out);
	   }
	   catch (java.io.IOException e){}
	}
}
