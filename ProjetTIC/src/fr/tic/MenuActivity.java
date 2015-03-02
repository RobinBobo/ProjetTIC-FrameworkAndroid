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
	    System.out.println(mesClients);
	    System.out.println(monCatalogue);
		
		// Lien vers l'activité d'ajout de produit
		final Button ajouterProduit = (Button) findViewById(R.id.ajouterProduit);
		ajouterProduit.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MenuActivity.this,
						AjouterProduitActivity.class);
				startActivity(intent);
			}
		});
		
		// Lien vers l'activité d'ajout de produit
		final Button ajouterClient = (Button) findViewById(R.id.ajouterClient);
		ajouterClient.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MenuActivity.this,
						AjouterClientActivity.class);
		        intent.putExtra("monCatalogue", monCatalogue);
				startActivity(intent);
			}
		});
	}

}
