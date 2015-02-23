package com.ticfrontend.activity;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.projettic.R;
import com.example.projettic.RegisterActivity;
import com.ticfront.adapter.CategorieListAdapter;
import com.ticfront.ui.CategorieView;
import com.ticfrontend.magasin.Categorie;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
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
				// On affiche le TextView "Trier par"
				findViewById(R.id.layoutSort).setVisibility(View.VISIBLE);
				
				// On change le textview "Liste des catégories" par "Résultat de votre recherche
				findViewById(R.id.textListCategorie).setVisibility(View.GONE);
				findViewById(R.id.textSearchResult).setVisibility(View.VISIBLE);
				
				// Requete de recherche dans la BDD
				
			}
		});
    	
    	//testAjoutItemsListCategorie();
    	
    	testAjoutItemsListCategorieWithLinear();
    	
    	
    	
    }


	public <T> void startAnActivity(Class<T> activity){
    	Intent intent = new Intent(MainActivity.this, activity);
    	startActivity(intent);
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
        
        if (id == R.id.connexion) {
        	startAnActivity(LoginActivity.class);
            return true;
        }else if (id == R.id.enregister) {
        	startAnActivity(RegisterActivity.class);
            return true;
        }else if (id == R.id.mon_compte) {
        	startAnActivity(AccountActivity.class);
            return true;
        }else if (id == R.id.mon_panier) {
        	startAnActivity(CartActivity.class);
            return true; 
        }else if (id == R.id.action_settings) {	
            return true;
        }else if (id == R.id.a_propos) {
        	startAnActivity(AboutActivity.class);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    private void testAjoutItemsListCategorie(){
    	List<Categorie> listCategorie = Categorie.getAListOfCategorie();

    	//Création et initialisation de l'Adapter pour les personnes
    	CategorieListAdapter categorieListAdapter = new CategorieListAdapter(this, listCategorie);
    	
    	//Récupération du composant ListView
    	ListView categorieList = (ListView) findViewById(R.id.listviewCat);

    	//Initialisation de la liste avec les données
    	categorieList.setAdapter(categorieListAdapter);
    	
    	categorieList.setAdapter(categorieListAdapter);
    	    	
    	categorieList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				startAnActivity(ProductActivity.class);
			}
		});
    }

    private void testAjoutItemsListCategorieWithLinear() {
    	List<Categorie> listCategorie = Categorie.getAListOfCategorie();
    	
    	LayoutInflater inflater = (LayoutInflater) this.getSystemService(this.LAYOUT_INFLATER_SERVICE);
    	
    	LinearLayout categorieList = (LinearLayout) findViewById(R.id.layoutListCategorie);
    	
        CategorieView viewItem = null;
        
	    
	    for(int i = 0; i < listCategorie.size(); i++){
	    	viewItem = new CategorieView(this, listCategorie.get(i));	    	
	    	categorieList.addView(viewItem);
	    }
    }
}
