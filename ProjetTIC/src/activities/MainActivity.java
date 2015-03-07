package activities;


import java.io.Serializable;

import plurals.Catalogue;
import plurals.ListeCategories;
import plurals.ListeClients;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import beans.Categorie;
import beans.Client;
import beans.Produit;
import fr.tic.R;

public class MainActivity extends Activity {
	
	private ListeClients mesClients;
	private Catalogue monCatalogue;
	
	public ListeClients getMesClients() {
		return mesClients;
	}

	public void setMesClients(ListeClients mesClients) {
		this.mesClients = mesClients;
	}

	public Catalogue getMonCatalogue() {
		return monCatalogue;
	}

	public void setMonCatalogue(Catalogue monCatalogue) {
		this.monCatalogue = monCatalogue;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// _________________________________________________________
		// Jeux de test :
		// __________________________________________________________

		ListeCategories mesCategories = new ListeCategories();
		setMesClients(new ListeClients());
		setMonCatalogue(new Catalogue());

		/*mesCategories.findCategories();
		mesClients.findClients();
		monCatalogue.findProduits();*/
//
//		monCatalogue.ajoutObserver(mesClients);
		Client c = new Client(0,"nom","prenom", "adresse", false);
		mesClients.ajouterClient(c);
		Categorie categoTelephone = new Categorie(0, "Telephone");
		getMonCatalogue().ajouterProduitCatalogue(new Produit(0, 
				"Sony Xperia Z3 Compact", 150.0, "Téléphone Sony Xperia Z3 Compact 16Go",
				categoTelephone.getNomCategorie(), "Sony", 10));
		getMonCatalogue().ajouterProduitCatalogue(new Produit(1, 
				"Sony Xperia Z3", 150.0, "Téléphone Sony Xperia Z3 16Go",
				categoTelephone.getNomCategorie(), "Sony", 10));
		
		getMonCatalogue().afficherCatalogue();

		// Test Parser XML
		/*
				Configurator c = new Configurator();
				Configurator c2 = new Configurator();
				c.setShoppingCart(true);
				c.setCustomerNotice(true);
				c.setOrder(false);
				c.setWebsiteName("SiteDeLaMort");	
				
				File newxmlfile = new File(Environment.getExternalStorageDirectory(), "configuration.xml");	

				try {
					XmlCreator x = new XmlCreator ();
					x.create(c,newxmlfile);			
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
		*/
		// Test Catégorie et Produit


		/*Categorie C1 = new Categorie(0, "Salle de bain");
		Categorie C2 = new Categorie(0, "Salon");

		Produit P1 = new Produit(0, "Tapis", 38, "Tapis de salle de bain", "Michelin", 100);
		Produit P2 = new Produit(0, "Bureau", 67, "Bureau en chêne", "Lacoste", 3);
		Produit P3 = new Produit(0, "Douche", 400, "Douche italienne", "Nike", 12);

		P1.addObserver(mesClients);
		P2.addObserver(mesClients);
		P3.addObserver(mesClients);

		C1.ajouterProduitCategorie(P1);
		C2.ajouterProduitCategorie(P2);
		C1.ajouterProduitCategorie(P3);

		mesCategories.ajouterCategorie(C1);
		mesCategories.ajouterCategorie(C2);


		//Test Client et Panier

		Client CLT1 = new Client(0, "Slava", "Hubert", "36 rue des Lilas", true);
		Client CLT2 = new Client(0, "Dutilleux", "Sophie", "27 avenue des marchands", false);

		Panier PN1 = new Panier(0, false, "");
		Panier PN2 = new Panier(0, false, "");
		Panier PN3 = new Panier(0, false, "");
		Panier PN4 = new Panier(0, false, "");

		CLT1.ajouterPanierClient(PN1);
		CLT1.getMesPanier().get(0).commander("Carte");
		CLT1.ajouterPanierClient(PN2);

		CLT2.ajouterPanierClient(PN3);
		CLT2.ajouterPanierClient(PN4);
		CLT2.supprimerPanierClient(PN3);

		//Test Panier

		PN1.ajouterProduitPanier(P1, 25);
		PN2.ajouterProduitPanier(P2, 5);
		PN4.ajouterProduitPanier(P3, 3);
		PN1.supprimerProduitPanier(P1);
		PN1.ajouterProduitPanier(P3, 4);

		CLT1.supprimerPanierClient(PN1);



		//Test affichage
		for(Produit p : monCatalogue.getMesProduits()){
			System.out.println("Nom : " + p.getNomProduit() + " & " + " Prix : " + p.getStockProduit());

		}

		for(Client c : mesClients.getListeClients()){
			System.out.println("Nom : " + c.getNomClient() + " & " + " Taille panier : " + c.getMesPanier().size());
		}

		for(Categorie ca : mesCategories.getListeCategories()){
			System.out.println("Nom : " + ca.getNomCategorie() + " & " + " Nombre de produits : " + ca.getMesProduits().size());
		}*/
		
		
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
			
		// Menu
		final Button menuLink = (Button) findViewById(R.id.menuLink);
		menuLink.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, MenuActivity.class);
				//Bundle extras = new Bundle();
				intent.putExtra("monCatalogue", getMonCatalogue());
				intent.putExtra("mesClients", getMesClients());
		        startActivity(intent);
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
