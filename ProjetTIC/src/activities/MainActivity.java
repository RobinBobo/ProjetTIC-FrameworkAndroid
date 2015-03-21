package activities;

import java.io.File;
import java.io.IOException;

import org.xmlpull.v1.XmlPullParserException;

import colorpicker.ColorPicker;
import plurals.Catalogue;
import plurals.ListeCategories;
import plurals.ListeClients;
import plurals.ListeMarques;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import beans.Categorie;
import beans.Client;
import beans.Marque;
import beans.Produit;
import configuratormanagement.Configurator;
import configuratormanagement.XmlCreator;
import fr.tic.R;


public class MainActivity extends Activity {

	private static ListeClients mesClients;
	private static Catalogue monCatalogue;
	private static ListeCategories listeCategories;
	private static ListeMarques listeMarques;
	private static String nomSite; 
	private static int buttonsColor;
	
	public static ListeMarques getListeMarques() {
		return listeMarques;
	}

	public static void setListeMarques(ListeMarques listeMarques) {
		MainActivity.listeMarques = listeMarques;
	}

	public static int getButtonsColor() {
		return buttonsColor;
	}

	public static void setButtonsColor(int buttonsColor) {
		MainActivity.buttonsColor = buttonsColor;
	}

	public static ListeCategories getListeCategories() {
		return listeCategories;
	}

	public static void setListeCategories(ListeCategories listeCategories) {
		MainActivity.listeCategories = listeCategories;
	}

	public static String getNomSite() {
		return nomSite;
	}

	public static void setNomSite(String nomSite) {
		MainActivity.nomSite = nomSite;
	}

	public static ListeClients getMesClients() {
		return mesClients;
	}

	public static void setMesClients(ListeClients mesClients) {
		MainActivity.mesClients = mesClients;
	}

	public static Catalogue getMonCatalogue() {
		return monCatalogue;
	}

	public static void setMonCatalogue(Catalogue monCatalogue) {
		MainActivity.monCatalogue = monCatalogue;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setTitle((getNomSite() != null) ? (getNomSite()) : "Application de gestion");	

		// _________________________________________________________
		// Jeux de test :
		// __________________________________________________________

		setListeCategories(new ListeCategories());
		setMesClients(new ListeClients());
		setMonCatalogue(new Catalogue());
		setListeMarques(new ListeMarques());
		

		/*mesCategories.findCategories();
		mesClients.findClients();
		monCatalogue.findProduits();*/
		//
		//		monCatalogue.ajoutObserver(mesClients);
		Client c = new Client(0,"nom","prenom", "adresse", false);
		mesClients.ajouterClient(c);
		Categorie categoTelephone = new Categorie("Telephone");
		Categorie categoDivers = new Categorie("Divers");
		Marque sony = new Marque("Sony");
		getListeCategories().ajouterCategorie(categoTelephone);
		getListeCategories().ajouterCategorie(categoDivers);
		getMonCatalogue().ajouterProduitCatalogue(new Produit(0, 
				"Sony Xperia Z3 Compact", 150.0, "Téléphone Sony Xperia Z3 Compact 16Go",
				categoTelephone.getNomCategorie(), sony.getNomMarque(), 10));
		getMonCatalogue().ajouterProduitCatalogue(new Produit(1, 
				"Sony Xperia Z3", 150.0, "Téléphone Sony Xperia Z3 16Go",
				categoTelephone.getNomCategorie(), sony.getNomMarque(), 10));

		getMonCatalogue().afficherCatalogue();

		//
		// TODO: Page d'accueil
		//

		// Affichage du nombre de produits
		final TextView nbProduits = (TextView) findViewById(R.id.nbProduits);
		if (monCatalogue.getMesProduits().size() != 0) {
			nbProduits.setText("Il y a " + monCatalogue.getMesProduits().size() + " produits référencés dans le catalogue");
		} else {
			nbProduits.setText("Il n'y a aucun produit de référencé dans le catalogue...");
		}

		// Affichage du nombre de clients
		final TextView nbClients = (TextView) findViewById(R.id.nbClients);
		if (mesClients.getListeClients().size() != 0) {
			nbClients.setText("Il y a " + mesClients.getListeClients().size() + " clients référencés dans la base");
		} else {
			nbClients.setText("Il n'y a aucun client de référencé dans le catalogue...");
		}

		// TODO: Affichage du nombre de commande effectuée (basé sur les paniers pour l'instant)
		final TextView nbCde = (TextView) findViewById(R.id.nbCde);
		int i_nbCde = 0;
		if (mesClients.getListeClients().size() != 0) {
			for(int i=0; i< mesClients.getListeClients().size(); i++) {
				Client leClient = mesClients.getListeClients().get(i);
				i_nbCde += leClient.getMesPanier().size();
			}
			if (i_nbCde != 0) nbCde.setText(i_nbCde + " commandes ont été effectuées");
			else nbClients.setText("Il n'y pas encore de commande effectuée...");
		}

		//Affichage des alerte stock (moins de 5 articles en stock)
		final TextView alerteStock = (TextView) findViewById(R.id.alerteStock);
		String listePdtAlerteStock = "";
		for(int i = 0; i < (monCatalogue.getMesProduits().size()); i++) {
			Produit p = monCatalogue.getMesProduits().get(i);
			if (p.getStockProduit() < 5) {
				listePdtAlerteStock += p.getNomProduit() + " : plus que " + p.getStockProduit() + " produits en stock !\n";
			}
		}
		if (listePdtAlerteStock != "") alerteStock.setText(listePdtAlerteStock);
		else alerteStock.setText("Vous n'avez aucune alerte au niveau des stocks");

		// Nom du site
		final Button nomSiteLink = (Button) findViewById(R.id.btnNomSite);
		nomSiteLink.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, ChoixNomSiteActivity.class);
				startActivity(intent);
			}
		});

		// Menu
		final Button menuLink = (Button) findViewById(R.id.menuLink);
		menuLink.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, MenuActivity.class);
				startActivity(intent);
			}
		});		


		// Extraction des données
		final Button extract = (Button) findViewById(R.id.btnExtract);
		extract.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Configurator c = new Configurator();
				c.setWebsiteName(nomSite);
				c.setShoppingCart(true);
				c.setCustomerNotice(true);
				c.setOrder(false);
				c.setButtonsColor(buttonsColor);
				File newxmlfile = new File(Environment.getExternalStorageDirectory(), "configuration.xml");
				XmlCreator xml = new XmlCreator();
				try {
					xml.create(c, getMonCatalogue().getMesProduits(), getListeCategories().getListeCategories(), newxmlfile);
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (XmlPullParserException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}	
}