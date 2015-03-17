package activities;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.Color;
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
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ajouterproduit);
	    
	    final TextView txtMsgErreur = (TextView) findViewById(R.id.msgErreur);
	    final Spinner s = (Spinner) findViewById(R.id.listeCategory);
	    
	    List<String> arraySpinner = new ArrayList<String>();
    	arraySpinner.add("Aucune");
	    for(int i=0; i<MainActivity.getListeCategories().getListeCategories().size(); i++) {
	    	arraySpinner.add(MainActivity.getListeCategories().getListeCategories().get(i).getNomCategorie());
	    }
	    
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        s.setAdapter(adapter);
		
		final Button ajoutProduit = (Button) findViewById(R.id.btnValider);
		ajoutProduit.setOnClickListener(new OnClickListener() {
					
			public void onClick(View v) {
				boolean valide = false;
				String msgErreur = "";
				CharSequence nom = ((TextView) findViewById(R.id.nomProduit)).getText();
				CharSequence prix = ((TextView) findViewById(R.id.prixProduit)).getText();
				CharSequence marque = ((TextView) findViewById(R.id.marqueProduit)).getText();
				CharSequence description = ((TextView) findViewById(R.id.descProduit)).getText();
				if (!nom.toString().matches("") && !prix.toString().matches("") && !description.toString().matches("")) {
					valide = true;
				} else {
					msgErreur = "Veuillez saisir tous les champs";
					txtMsgErreur.setTextColor(Color.RED);
				}
				
				if (valide) {
					Produit p = new Produit(nom.toString(),Integer.parseInt(prix.toString()),
							description.toString(), (s.getSelectedItem().toString().equals("Aucune")) ? "" : s.getSelectedItem().toString() ,
							(marque.toString().matches(""))? "" : marque.toString(), 10);
					MainActivity.getMonCatalogue().ajouterProduitCatalogue(p);
					msgErreur = "Votre produit a bien été ajouté";
					txtMsgErreur.setTextColor(Color.rgb(20, 148, 20));
				}
				txtMsgErreur.setText(msgErreur);
			}
		});
		
	}

}
