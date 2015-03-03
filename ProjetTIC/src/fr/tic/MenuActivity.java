package fr.tic;

import plurals.Catalogue;
import plurals.ListeClients;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MenuActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
		
		Bundle b    = getIntent().getExtras();
		final ListeClients mesClients    = b.getParcelable("mesClients");
		final Catalogue monCatalogue    = b.getParcelable("monCatalogue");
		
		// Lien vers l'activité d'affichage du catalogue
		final Button afficherCatalogue = (Button) findViewById(R.id.afficherCatalogue);
		afficherCatalogue.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MenuActivity.this,
						AfficherCatalogueActivity.class);
		        intent.putExtra("monCatalogue", monCatalogue);
				startActivity(intent);
			}
		});
		
		// Lien vers l'activité d'ajout de produit
		final Button ajouterProduit = (Button) findViewById(R.id.ajouterProduit);
		ajouterProduit.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MenuActivity.this,
						AjouterProduitActivity.class);
		        intent.putExtra("monCatalogue", monCatalogue);
				startActivity(intent);
			}
		});
		
		// Lien vers l'activité de modification de produit
		final Button modifierProduit = (Button) findViewById(R.id.modifierProduit);
		modifierProduit.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MenuActivity.this,
						ModifierProduitActivity.class);
		        intent.putExtra("monCatalogue", monCatalogue);
				startActivity(intent);
			}
		});
		
		// Lien vers l'activité d'ajout de client
		final Button ajouterClient = (Button) findViewById(R.id.ajouterClient);
		ajouterClient.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MenuActivity.this,
						AjouterClientActivity.class);
		        intent.putExtra("mesClients", mesClients);
				startActivity(intent);
			}
		});
	}

}
