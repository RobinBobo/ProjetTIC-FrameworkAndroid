package activities;


import fr.tic.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MenuActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
		
		// Lien vers l'activité d'affichage du catalogue
		final Button afficherCatalogue = (Button) findViewById(R.id.afficherCatalogue);
		afficherCatalogue.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MenuActivity.this,
						AfficherCatalogueActivity.class);
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
				startActivity(intent);
			}
		});
		
		// Lien vers l'activité de suppression de produit
		final Button supprimerProduit = (Button) findViewById(R.id.supprimerProduit);
		supprimerProduit.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MenuActivity.this,
						SupprimerProduitActivity.class);
				startActivity(intent);
			}
		});
		
		// Lien vers l'activité d'ajout d'une catégorie
		final Button ajouterCategorie = (Button) findViewById(R.id.ajouterCategorie);
		ajouterCategorie.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MenuActivity.this,
						AjouterCategorieActivity.class);
				startActivity(intent);
			}
		});
	}

}
