package activities;

import java.util.ArrayList;
import java.util.List;

import plurals.Catalogue;
import plurals.ListeCategories;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import beans.Produit;
import fr.tic.R;

public class AjouterProduitActivity extends Activity {

	private Catalogue monCatalogue;
	private ListeCategories listeCatego;
	
	public ListeCategories getListeCatego() {
		return listeCatego;
	}

	public void setListeCatego(ListeCategories listeCatego) {
		this.listeCatego = listeCatego;
	}

	public void setMonCatalogue(Catalogue c) {
		monCatalogue = c;
	}
	
	public Catalogue getMonCatalogue() {
		return monCatalogue;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ajouterproduit);

		Intent intent = this.getIntent();
		
		setMonCatalogue((Catalogue) intent.getSerializableExtra("monCatalogue"));
		setListeCatego((ListeCategories) intent.getSerializableExtra("listeCategories"));
	    
	    final TextView txtMsgErreur = (TextView) findViewById(R.id.msgErreur);
	    final Spinner s = (Spinner) findViewById(R.id.listeCategory);
	    
	    List<String> arraySpinner = new ArrayList<String>();
	    
	    for(int i=0; i<getListeCatego().getListeCategories().size(); i++) {
	    	arraySpinner.add(this.getListeCatego().getListeCategories().get(i).getNomCategorie());
	    }
	    
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        s.setAdapter(adapter);
		
		final Button ajoutProduit = (Button) findViewById(R.id.btnAjoutProduit);
		ajoutProduit.setOnClickListener(new OnClickListener() {
					
			public void onClick(View v) {
				boolean valide = false;
				String msgErreur = "";
				CharSequence id = ((TextView) findViewById(R.id.idProduit)).getText();
				CharSequence nom = ((TextView) findViewById(R.id.nomProduit)).getText();
				CharSequence prix = ((TextView) findViewById(R.id.prixProduit)).getText();
				CharSequence marque = ((TextView) findViewById(R.id.marqueProduit)).getText();
				CharSequence description = ((TextView) findViewById(R.id.descProduit)).getText();
				if (!id.toString().matches("") && !nom.toString().matches("") 
						&& !prix.toString().matches("") && !description.toString().matches("")) {
					valide = true;
				} else msgErreur = "Veuillez saisir tous les champs";
				
				if (valide) {
					Produit p = new Produit(Integer.parseInt(id.toString()) , nom.toString(),Integer.parseInt(prix.toString()),
							description.toString(), (marque.toString().matches(""))? "" : marque.toString() , "marque", 10);
					getMonCatalogue().ajouterProduitCatalogue(p);
					msgErreur = "Votre produit a bien été ajouté";
					getMonCatalogue().afficherCatalogue();
				}
				txtMsgErreur.setText(msgErreur);
			}
		});
		
	}

}
