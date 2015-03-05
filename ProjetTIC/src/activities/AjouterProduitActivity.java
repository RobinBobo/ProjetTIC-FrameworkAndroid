package activities;

import fr.tic.R;
import plurals.Catalogue;
import plurals.ListeClients;
import beans.Categorie;
import beans.Client;
import beans.Produit;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

public class AjouterProduitActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ajouterproduit);
		
		Bundle b    = getIntent().getExtras();
		final Catalogue monCatalogue    = b.getParcelable("monCatalogue");
	    System.out.println(monCatalogue);
		
		final Button ajoutProduit = (Button) findViewById(R.id.btnAjoutProduit);
		ajoutProduit.setOnClickListener(new OnClickListener() {
					
			public void onClick(View v) {
				boolean valide = false;
				CharSequence id = ((TextView) findViewById(R.id.idProduit)).getText();
				CharSequence nom = ((TextView) findViewById(R.id.nomProduit)).getText();
				CharSequence prix = ((TextView) findViewById(R.id.prixProduit)).getText();
				CharSequence description = ((TextView) findViewById(R.id.descProduit)).getText();
				if (id != null && nom != null && prix != null && description != null) {
					valide = true;
				}
				
				if (valide) {
					Produit p = new Produit(Integer.parseInt(id.toString()) , nom.toString(),Integer.parseInt(prix.toString()),
							description.toString(), new Categorie(), "marque", 0);
					monCatalogue.ajouterProduitCatalogue(p);
				}
			}
		});
		
	}

}
