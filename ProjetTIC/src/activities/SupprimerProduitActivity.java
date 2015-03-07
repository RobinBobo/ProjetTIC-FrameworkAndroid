package activities;

import plurals.Catalogue;
import plurals.ListeClients;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import beans.Produit;
import fr.tic.R;

public class SupprimerProduitActivity extends Activity {

	private Produit p = null;
	private Catalogue monCatalogue;
	
	public Catalogue getMonCatalogue() {
		return monCatalogue;
	}

	public void setMonCatalogue(Catalogue monCatalogue) {
		this.monCatalogue = monCatalogue;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_supprimerproduit);
		
		// Récupération des données
		Intent intent = this.getIntent();
		
		setMonCatalogue((Catalogue) intent.getSerializableExtra("monCatalogue"));
		
		final EditText idPdt = (EditText) findViewById(R.id.idProduit);
		final TextView nomPdt = (TextView) findViewById(R.id.resNomProduit);
		final TextView descPdt = (TextView) findViewById(R.id.resDescProduit);
		final TextView prixPdt = (TextView) findViewById(R.id.resPrixProduit);
		final TextView stockPdt = (TextView) findViewById(R.id.resStockProduit);
		final TextView erreurRecherche = (TextView) findViewById(R.id.erreurRecherche);
		final TextView msgErreur = (TextView) findViewById(R.id.msgErreur);
		final RelativeLayout infoProduit = (RelativeLayout) findViewById(R.id.formSupprimerPdt);
		Button btnRechercher = (Button) findViewById(R.id.btnRechercher);
		Button btnSupprimer = (Button) findViewById(R.id.btnSupprimer);
		
		// Masquage des infos produits
		infoProduit.setVisibility(View.INVISIBLE);
		
		// Validation de la recherche
		btnRechercher.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				String msgErreur = "";
				if (!idPdt.getText().toString().matches("")) {
					p = monCatalogue.rechercherProduit(Integer.parseInt(idPdt.getText().toString()));
					if (p != null) {
						msgErreur += "Produit trouvé !";
						afficherInfo(p);
					} else msgErreur += "Le produit recherché n'est pas référencé dans la base...";
				} else msgErreur = "Veuillez saisir un identifiant";
				erreurRecherche.setText(msgErreur);
			}

			private void afficherInfo(Produit p) {
				// TODO Auto-generated method stub
				infoProduit.setVisibility(View.VISIBLE);
				nomPdt.setText(p.getNomProduit());
				prixPdt.setText(Double.toString(p.getPrixProduit()));
				descPdt.setText(p.getDescriptionProduit());
				stockPdt.setText(Integer.toString(p.getStockProduit()));				
			}
		});
		
		btnSupprimer.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				monCatalogue.supprimerProduitCatalogue(p);
				MainActivity.getMonCatalogue().supprimerProduitCatalogue(p);
				msgErreur.setText("Le produit a bien été supprimé !");
			}
		});
	}

}
