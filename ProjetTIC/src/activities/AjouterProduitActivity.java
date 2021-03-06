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
import beans.Categorie;
import beans.Marque;
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
				CharSequence stock = ((TextView) findViewById(R.id.stockProduit)).getText();
				if (!nom.toString().matches("") && !prix.toString().matches("") && 
						!description.toString().matches("") && !stock.toString().matches("") ) {
					valide = true;
				} else {
					msgErreur = "Veuillez saisir tous les champs ! (La marque et la cat�gorie sont facultatives)";
					txtMsgErreur.setTextColor(Color.RED);
				}
				
				if (valide) {
					String catego = (s.getSelectedItem().toString().equals("Aucune")) ? "Divers" : s.getSelectedItem().toString();
					Produit p = new Produit(nom.toString(),Integer.parseInt(prix.toString()),
							description.toString(), catego ,
							(marque.toString().matches(""))? "" : marque.toString(), 
							Integer.parseInt(stock.toString()));
					if (MainActivity.getListeCategories().rechCategorie(catego) != null) {
						MainActivity.getListeCategories().rechCategorie(catego).ajouterProduitCategorie(p);
					}
					
					// Ajout de la marque dans la liste si elle n'existe pas encore
					// ou bien ajoute le produit � la liste de la marque existante
					if(!marque.toString().matches("")) {
						Marque m = MainActivity.getListeMarques().containMarque(marque.toString());
						if(m == null) {
							m = new Marque(marque.toString());
							m.getMesProduits().add(p);
							MainActivity.getListeMarques().ajouterMarque(m);
						} else m.getMesProduits().add(p);
					}
					
					// Ajout du produit dans le catalogue
					MainActivity.getMonCatalogue().ajouterProduitCatalogue(p);
					msgErreur = "Le produit '" + p.getNomProduit()+ "' a bien �t� ajout�";
					txtMsgErreur.setTextColor(Color.rgb(20, 148, 20));
					((TextView) findViewById(R.id.nomProduit)).setText("");
					((TextView) findViewById(R.id.prixProduit)).setText("");
					((TextView) findViewById(R.id.marqueProduit)).setText("");
					((TextView) findViewById(R.id.descProduit)).setText("");
					((TextView) findViewById(R.id.stockProduit)).setText("");
					s.setSelection(0);
				}
				txtMsgErreur.setText(msgErreur);
			}
		});
		
	}

}
