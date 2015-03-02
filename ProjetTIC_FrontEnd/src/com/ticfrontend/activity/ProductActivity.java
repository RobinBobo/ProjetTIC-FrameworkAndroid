package com.ticfrontend.activity;

import java.util.List;

import com.example.projettic.R;
import com.example.projettic.R.id;
import com.example.projettic.R.layout;
import com.example.projettic.R.menu;
import com.ticfrontend.adapter.AvisListAdapter;
import com.ticfrontend.adapter.CategorieListAdapter;
import com.ticfrontend.magasin.Avis;
import com.ticfrontend.magasin.Categorie;
import com.ticfrontend.magasin.Produit;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.NumberPicker.OnValueChangeListener;
import android.widget.RatingBar;
import android.widget.TextView;


public class ProductActivity extends Activity  {

	private static TextView tv;
	static Dialog d ;
	private static Button b;
	
	private Produit product;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_product);
		
		// On r�cup�re l'objet
		product = new Produit((Produit) getIntent().getSerializableExtra(MainActivity.EXTRA_KEY_PRODUCT)); 
		
		init();

		// On peuple la vue
		populateScreen();
	}

	@Override
	protected void onResume() {
		super.onResume();
		updateButton();
	}
	
	public void init(){
		tv = (TextView) findViewById(R.id.availableQuantity);	
		b = (Button) findViewById(R.id.boutonAjouter);  
		b.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				show();
			}
		});
		
		
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
	}
	
	private <T> void startAnActivity(Class<T> activity) {
		Intent intent = new Intent(ProductActivity.this, activity);
    	startActivity(intent);
	}

	private void populateScreen() {
		// Nom produit
		TextView name = (TextView) findViewById(R.id.textProductName);
		name.setText(product.getNomProduit());
		
		// Prix produit
		TextView price = ((TextView) findViewById(R.id.textPriceProduct));
		price.setText(String.valueOf(product.getPrixProduit()) + " �");
		
		// Description produit
		TextView desc = (TextView) findViewById(R.id.textInfoProduct);
		desc.setText(product.getDescriptionProduit());
		
		// Marque produit
		TextView brand = (TextView) findViewById(R.id.textProductMarque);
		brand.setText("Marque : " + product.getMarqueProduit());
		
		// Cat�gorie produit
		TextView cat = (TextView) findViewById(R.id.textProductCategorie);
		cat.setText("Cat�gorie : " + product.getCategorieProduit().getNomCategorie());
		
		//Note produit
		double noteGenerale = 0;
		int tailleListe = product.getListeAvisProduit().size();
		
		for(Avis a : product.getListeAvisProduit())
			noteGenerale += a.getNote();
		
		noteGenerale = noteGenerale/tailleListe;
		
		((RatingBar) findViewById(R.id.ratingBarReview)).setRating((float)noteGenerale);
		((TextView) findViewById(R.id.NombreVotes)).setText(String.valueOf(tailleListe) + ((tailleListe==1) ? " vote": " votes"));
		
		// Test liste d'avis
		testAjoutAvis();
		
	}
	
	private void testAjoutAvis(){
		List<Avis> listeAvis = product.getListeAvisProduit();
		
		//Cr�ation et initialisation de l'Adapter pour les cat�gories
		AvisListAdapter ala = new AvisListAdapter(this, listeAvis);
		
		//R�cup�ration du composant ListView
		ListView listviewAvis = (ListView) findViewById(R.id.listviewAvis);
		
		//Initialisation de la liste avec les donn�es
		listviewAvis.setAdapter(ala);
		
//		listviewAvis.setOnItemClickListener(new OnItemClickListener() {
//			@Override
//			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {				
//				
//			}
//		});
	}
	
	public void show() {
		final Dialog d = new Dialog(ProductActivity.this);
		d.setTitle("Choisir la quantit�");
		d.setContentView(R.layout.dialog_quantity);
		Button b1 = (Button) d.findViewById(R.id.button1);
		Button b2 = (Button) d.findViewById(R.id.button2);
		final NumberPicker np = (NumberPicker) d.findViewById(R.id.numberPicker1);
		np.setMaxValue(30);
		np.setMinValue(1);
		np.setWrapSelectorWheel(false);
		np.setOnValueChangedListener((OnValueChangeListener) this);
		b1.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v) {
				tv.setText("Qte: "+String.valueOf(np.getValue()));
				d.dismiss();
			}    
		});
		b2.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v) {
				d.dismiss();
		}    
		});
		d.show();
	}
	
	public void updateButton() {	
		if(LoginActivity.ISCONNECTED){
			// Boutons
			findViewById(R.id.layoutBoutonConnexion).setVisibility(View.GONE);
			findViewById(R.id.layoutBoutonMain).setVisibility(View.VISIBLE);
	
		} else {
			findViewById(R.id.layoutBoutonConnexion).setVisibility(View.VISIBLE);
			findViewById(R.id.layoutBoutonMain).setVisibility(View.GONE);
		}	
	}
	
//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		//getMenuInflater().inflate(R.menu.product, menu);
//		return true;
//	}
//
//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//		// Handle action bar item clicks here. The action bar will
//		// automatically handle clicks on the Home/Up button, so long
//		// as you specify a parent activity in AndroidManifest.xml.
////		int id = item.getItemId();
////		if (id == R.id.action_settings) {
////			return true;
////		}
//		return super.onOptionsItemSelected(item);
//	}
}
