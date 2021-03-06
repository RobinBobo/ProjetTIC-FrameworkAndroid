package activities;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import beans.Produit;
import fr.tic.R;

public class ModifierProduitActivity extends Activity {

	private Produit pdt = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_modifierproduit);
		
		// R�cup�ration des donn�es		
		final RelativeLayout formModifPdt = (RelativeLayout) findViewById(R.id.formModifierPdt);		
		final Button rechPdt = (Button) findViewById(R.id.btnRechercher);
		final Button btnValider = (Button) findViewById(R.id.btnValider);
		final TextView nom = (TextView) findViewById(R.id.rechIdPdt);
		final TextView erreurRech = (TextView) findViewById(R.id.erreurRecherche);
		final TextView resNom = (TextView) findViewById(R.id.resNomProduit);
		final TextView resPrix = (TextView) findViewById(R.id.resPrixProduit);
		final TextView resDesc = (TextView) findViewById(R.id.resDescProduit);
		final TextView resStock = (TextView) findViewById(R.id.resStockProduit);
		final TextView msgResModif = (TextView) findViewById(R.id.msgResModif);
		final EditText newNom = (EditText) findViewById(R.id.newNomProduit);
		final EditText newPrix = (EditText) findViewById(R.id.newPrixProduit);
		final EditText newDesc = (EditText) findViewById(R.id.newDescProduit);
		final EditText newStock = (EditText) findViewById(R.id.newStockProduit);
		final CheckBox checkNom = (CheckBox) findViewById(R.id.checkNomProduit);
		final CheckBox checkPrix = (CheckBox) findViewById(R.id.checkPrixProduit);
		final CheckBox checkDesc = (CheckBox) findViewById(R.id.checkDescProduit);
		final CheckBox checkStock = (CheckBox) findViewById(R.id.checkStockProduit);
		
		// On masque les zones de saisies
		formModifPdt.setVisibility(View.INVISIBLE);
		newNom.setVisibility(View.INVISIBLE);
		newPrix.setVisibility(View.INVISIBLE);
		newDesc.setVisibility(View.INVISIBLE);
		newStock.setVisibility(View.INVISIBLE);
		btnValider.setVisibility(View.INVISIBLE);
		
		// Recherche d'un produit et actualisation de la vue
		rechPdt.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				
				if (!nom.getText().toString().matches("")) {
					pdt = MainActivity.getMonCatalogue().rechercherProduit(nom.getText().toString());
					if (pdt == null) {
						erreurRech.setText("Le produit recherch� n'existe pas !");
						erreurRech.setTextColor(Color.RED);
					} else {
						erreurRech.setText("Produit trouv� !");
						erreurRech.setTextColor(Color.rgb(20, 148, 20));
						// Remplissage des informations produit
						resNom.setText(pdt.getNomProduit());
						resPrix.setText(Double.toString(pdt.getPrixProduit()));
						resDesc.setText(pdt.getDescriptionProduit());
						resStock.setText(Integer.toString(pdt.getStockProduit()));
						formModifPdt.setVisibility(View.VISIBLE);
					}
				}
				else {
					erreurRech.setText("Veuillez saisir un nom de produit !");
					erreurRech.setTextColor(Color.RED);
				}
			}
			
		});
		
		// Affichage des zones de saisies selon les checkbox
		checkNom.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if (checkNom.isChecked()) {
		            newNom.setVisibility(View.VISIBLE);
		            btnValider.setVisibility(View.VISIBLE);
		        } else newNom.setVisibility(View.INVISIBLE);
			}
		});		
		checkPrix.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if (checkPrix.isChecked()) {
		            newPrix.setVisibility(View.VISIBLE);
		            btnValider.setVisibility(View.VISIBLE);
		        } else newPrix.setVisibility(View.INVISIBLE);
			}
		});
		checkDesc.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if (checkDesc.isChecked()) {
		            newDesc.setVisibility(View.VISIBLE);
		            btnValider.setVisibility(View.VISIBLE);
		        } else newDesc.setVisibility(View.INVISIBLE);
			}
		});
		checkStock.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if (checkStock.isChecked()) {
		            newStock.setVisibility(View.VISIBLE);
		            btnValider.setVisibility(View.VISIBLE);
		        } else newStock.setVisibility(View.INVISIBLE);
			}
		});
        
        // Ajout des nouvelles donn�es dans la base
        btnValider.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				String msgErreur = "";
				if (pdt != null) {
					if (!checkNom.isChecked() && !checkDesc.isChecked() && !checkPrix.isChecked() && !checkStock.isChecked()) {
						msgErreur = "Veuillez s�lectionner au moins un champs � modifier.";
						msgResModif.setTextColor(Color.RED);
					} else {
						if (checkNom.isChecked()) {
							if (!newNom.getText().toString().matches("")) {
				        		MainActivity.getMonCatalogue().rechercherProduit(pdt.getNomProduit()).setNomProduit(newNom.getText().toString());
				        		msgErreur += "Le nom du produit a bien �t� modifi�. \n";
				        		msgResModif.setTextColor(Color.rgb(20, 148, 20));
				        	} else {
				        		msgErreur += "Veuillez saisir le nouveau nom du produit. \n";
				        		msgResModif.setTextColor(Color.RED);
				        	}
						}

						if (checkPrix.isChecked()) {
				        	if (!newPrix.getText().toString().matches("")) {
				        		MainActivity.getMonCatalogue().rechercherProduit(pdt.getNomProduit()).setPrixProduit(Double.parseDouble(newPrix.getText().toString()));
				        		msgErreur += "Le prix du produit a bien �t� modifi�. \n";
				        		msgResModif.setTextColor(Color.rgb(20, 148, 20));
				        	} else {
				        		msgErreur += "Veuillez saisir le nouveau prix du produit. \n";
				        		msgResModif.setTextColor(Color.RED);
				        	}
						}
						if (checkDesc.isChecked()) {
				        	if (!newDesc.getText().toString().matches("")) {
				        		MainActivity.getMonCatalogue().rechercherProduit(pdt.getNomProduit()).setDescriptionProduit(newDesc.getText().toString());
				        		msgErreur += "La description du produit a bien �t� modifi�e. \n";
				        		msgResModif.setTextColor(Color.rgb(20, 148, 20));
				        	} else {
				        		msgErreur += "Veuillez saisir la nouvelle description du produit. \n";
				        		msgResModif.setTextColor(Color.RED);
				        	}
						}
						if (checkStock.isChecked()) {
				        	if (!newStock.getText().toString().matches("")) {
				        		MainActivity.getMonCatalogue().rechercherProduit(pdt.getNomProduit()).setStockProduit(Integer.parseInt(newStock.getText().toString()));
				        		msgErreur += "Le stock du produit a bien �t� modifi�. \n";
				        		msgResModif.setTextColor(Color.rgb(20, 148, 20));
				        	} else {
				        		msgErreur += "Veuillez saisir le nouveau stock du produit. \n";
				        		msgResModif.setTextColor(Color.RED);
				        	}
				        }
					}
		        }
				msgResModif.setText(msgErreur);
			}
		});
        
        

		
	}

}
