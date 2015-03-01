package com.ticfrontend.activity;

import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.example.projettic.R;
import com.ticfrontend.adapter.CategorieListAdapter;
import com.ticfrontend.adapter.ProductListAdapter;
import com.ticfrontend.magasin.Categorie;
import com.ticfrontend.magasin.Produit;
import com.ticfrontent.comparator.ProductPriceComparator;

public class MainActivity extends Activity {

	private static final int idMenuItemConnexion = R.id.connexion;
	private static final int idMenuItemRegister = R.id.enregister;
	private static final int idMenuItemAccount = R.id.mon_compte;
	private static final int idMenuItemCard = R.id.mon_panier;
	private static final int idMenuItemLogout = R.id.se_deconnecter;
	
	public static final String EXTRA_KEY_PRODUCT = "EXTRA_KEY_PRODUCT";
	
	private boolean isInitialised = false;
	
	private Menu menu = null;
	
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		this.menu = menu;
		getMenuInflater().inflate(R.menu.main, menu);
		
		isInitialised = true;
		return true;
	}
	
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		updateMenu();
		return super.onPrepareOptionsMenu(menu);
	}

	@Override
	protected void onResume() {
		super.onResume();
		if(menu != null)
			updateMenu();
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		
		if (id == R.id.connexion) {
			startAnActivity(LoginActivity.class);
			return true;
		} else if (id == R.id.enregister) {
			startAnActivity(RegisterActivity.class);
			return true;
		} else if (id == R.id.mon_compte) {
			startAnActivity(AccountActivity.class);
			return true;
		} else if (id == R.id.mon_panier) {
			startAnActivity(CartActivity.class);
			return true; 
		} else if (id == R.id.action_settings) {	
			return true;
		} else if (id == R.id.a_propos) {
			startAnActivity(AboutActivity.class);
			return true;
		} else if (id == R.id.se_deconnecter){
			logout();
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void init(){
    	
    	// On enlève le focus à l'ouverture de l'app sur le editText de recherche
    	findViewById(R.id.editTextRecherche).setSelected(false);
    	
    	// Bouton Mon Compte
    	Button account = (Button) findViewById(R.id.boutonCompte);
    	account.setOnClickListener(new OnClickListener() {
    		@Override
 			public void onClick(View arg0) {
    			startAnActivity(AccountActivity.class);
 			}
 		});
    	
    	Button cart = (Button) findViewById(R.id.boutonPanier);
    	cart.setOnClickListener(new OnClickListener() {
    		@Override
 			public void onClick(View arg0) {
    			startAnActivity(CartActivity.class);
 			}
 		});
    	
    	Button login = (Button) findViewById(R.id.boutonConnexion);
    	login.setOnClickListener(new OnClickListener() {
    		@Override
 			public void onClick(View arg0) {
    			startAnActivity(LoginActivity.class);
 			}
 		});
    	
    	Button register = (Button) findViewById(R.id.boutonEnregistrer);
    	register.setOnClickListener(new OnClickListener() {
    		@Override
 			public void onClick(View arg0) {
    			startAnActivity(RegisterActivity.class);
 			}
 		});
    	
    	Button search = (Button) findViewById(R.id.boutonValiderRecherche);
    	search.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) { 
				// On change les textview
				findViewById(R.id.layoutSort).setVisibility(View.VISIBLE);
				findViewById(R.id.textListCategorie).setVisibility(View.GONE);
				findViewById(R.id.textSearchResult).setVisibility(View.VISIBLE);
				findViewById(R.id.layoutSort).setVisibility(View.VISIBLE);
				
				// Requete de recherche dans la BDD
				
				
				// Affichage de la liste
				testAjoutItemsListProduct();
					
			}
		});
    	
    	Button sortPrice = (Button) findViewById(R.id.buttonSortPrice);
    	sortPrice.setOnClickListener(new OnClickListener() {
    		@Override
 			public void onClick(View arg0) {
    			// Trier par prix
    			ListView list = (ListView) findViewById(R.id.listviewCat);
    			ProductListAdapter adapter = (ProductListAdapter) list.getAdapter();
    			List<Produit> products = adapter.getProducts();
    			Collections.sort(products, new ProductPriceComparator());
    			adapter.updateProduct(products);
    		}
 		});
    	
    	Button sortName = (Button) findViewById(R.id.buttonSortName);
    	sortName.setOnClickListener(new OnClickListener() {
    		@Override
 			public void onClick(View arg0) {
    			// Trier par nom
    			ListView list = (ListView) findViewById(R.id.listviewCat);
    			ProductListAdapter adapter = (ProductListAdapter) list.getAdapter();
    			List<Produit> products = adapter.getProducts();
    			Collections.sort(products, new Comparator<Produit>() {
    		        @Override
    		        public int compare(Produit product1, Produit product2) {
    		        	return product1.getNomProduit().compareTo(product2.getNomProduit());
    		        }
    		    });
    			
    			adapter.updateProduct(products);
 			}
 		});
    	
    	// Permet le test de l'appli (widgets, activité, list, etc...)
    	testAjoutItemsListCategorie();	
    }

	public <T> void startAnActivity(Class<T> activity){
    	Intent intent = new Intent(MainActivity.this, activity);
    	startActivity(intent);
    }
    
	private void testAjoutItemsListCategorie(){
		List<Categorie> listCategorie = Categorie.getAListOfCategorie();
		
		//Création et initialisation de l'Adapter pour les catégories
		CategorieListAdapter categorieListAdapter = new CategorieListAdapter(this, listCategorie);
		
		//Récupération du composant ListView
		ListView categorieList = (ListView) findViewById(R.id.listviewCat);
		
		//Initialisation de la liste avec les données
		categorieList.setAdapter(categorieListAdapter);
		
		categorieList.setAdapter(categorieListAdapter);
		
		categorieList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				findViewById(R.id.layoutSort).setVisibility(View.VISIBLE);
				findViewById(R.id.textListCategorie).setVisibility(View.GONE);
				findViewById(R.id.textSearchResult).setVisibility(View.VISIBLE);
				findViewById(R.id.layoutSort).setVisibility(View.VISIBLE);
				
				testAjoutItemsListProduct();
			}
		});
	}
	
	private void testAjoutItemsListProduct(){
		List<Produit> listProduct = Produit.getAListOfProducts();
		
		//Création et initialisation de l'Adapter pour les catégories
		ProductListAdapter productsListAdapter = new ProductListAdapter(this, listProduct);
		
		//Récupération du composant ListView
		ListView productsList = (ListView) findViewById(R.id.listviewCat);
		
		//Initialisation de la liste avec les données
		productsList.setAdapter(productsListAdapter);
		
		productsList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				Intent intent = new Intent(MainActivity.this, ProductActivity.class);
				Bundle extras = new Bundle();								
				Produit product = (Produit) arg0.getItemAtPosition(arg2);
			    extras.putSerializable(EXTRA_KEY_PRODUCT, product); 
			    intent.putExtras(extras); 
			    
			    startActivity(intent);
			}
		});
	}
	
    
    private void logout() {
    	// On affiche une boite de dialogue pour confirmer
    	DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
    	    @Override
    	    public void onClick(DialogInterface dialog, int which) {
    	        if(which == DialogInterface.BUTTON_POSITIVE){
    	        	LoginActivity.ISCONNECTED = false;  	
//    	    		Toast.makeText(this, "ISCONNECTED ? : " + LoginActivity.ISCONNECTED, Toast.LENGTH_LONG).show();
    	        	updateMenu();  
    	        }
    	    }
    	};

    	// On construit la boite et on set les textes des boutons oui et non
    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
    	builder.setMessage("En êtes-vous sûr ?").setPositiveButton("Oui", dialogClickListener)
    		.setNegativeButton("Non", dialogClickListener).show();
    	
    	updateMenu();
	}
    
    private void updateMenu() {
		if(LoginActivity.ISCONNECTED){
			// Boutons
			findViewById(R.id.layoutBoutonConnexion).setVisibility(View.GONE);
			findViewById(R.id.layoutBoutonMain).setVisibility(View.VISIBLE);
			// MenuItem
			hideOption(idMenuItemConnexion);
			hideOption(idMenuItemRegister);
			showOption(idMenuItemLogout);
			showOption(idMenuItemAccount);
			showOption(idMenuItemCard);    			
		} else {
			findViewById(R.id.layoutBoutonConnexion).setVisibility(View.VISIBLE);
			findViewById(R.id.layoutBoutonMain).setVisibility(View.GONE);
			showOption(idMenuItemConnexion);
			showOption(idMenuItemRegister);
			hideOption(idMenuItemLogout);
			hideOption(idMenuItemAccount);
			hideOption(idMenuItemCard);
		}	
	}

    private void hideOption(int id) {
        MenuItem item = menu.findItem(id);
        item.setVisible(false);
    }

    private void showOption(int id) {
        MenuItem item = menu.findItem(id);
        item.setVisible(true);
    }
    

//    private void testAjoutItemsListCategorieWithLinear() {
//    	List<Categorie> listCategorie = Categorie.getAListOfCategorie();
//    	LinearLayout categorieList = (LinearLayout) findViewById(R.id.layoutListCategorie);
//        CategorieView viewItem = null;
//	    
//	    for(int i = 0; i < listCategorie.size(); i++){
//	    	viewItem = new CategorieView(this, listCategorie.get(i));	    	
//	    	categorieList.addView(viewItem);
//	    }
//    }
}
