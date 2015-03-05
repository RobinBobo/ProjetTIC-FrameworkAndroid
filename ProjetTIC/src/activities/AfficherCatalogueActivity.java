package activities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import fr.tic.R;
import beans.Produit;
import plurals.Catalogue;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class AfficherCatalogueActivity extends Activity {
	
	ListView vue;
		
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_affichercatalogue);

		// R�cup�ration des donn�es
		Bundle b     = getIntent().getExtras();
		final Catalogue monCatalogue    = b.getParcelable("monCatalogue");
		vue = (ListView) findViewById(R.id.catalogueProduits);
	    List<HashMap<String, String>> liste = new ArrayList<HashMap<String, String>>();
	    
	    HashMap<String, String> element;
	    //Pour chaque personne dans notre r�pertoire�
	    for(int i = 0 ; i < monCatalogue.getMesProduits().size() ; i++) {
	    	Produit p = monCatalogue.getMesProduits().get(i);
	    	element = new HashMap<String, String>();
	    	element.put("idProduit", Integer.toString(p.getIdProduit()));
	    	element.put("nomProduit", p.getNomProduit());
	    	element.put("stockProduit", Integer.toString(p.getStockProduit()));
	    	liste.add(element);
	    }
	    
	    ListAdapter adapter = new SimpleAdapter(this,  
	    		//Valeurs � ins�rer
	    		liste,	      
	    		// Layout de chaque �l�ment (l�, il s'agit d'un layout par d�faut
	    		android.R.layout.activity_list_item,
	    		// Les cl�s des informations � afficher pour chaque �l�ment
	    		new String[] {"idProduit", "nomProduit", "stockProduit"},
	    		// Enfin, les layouts � appliquer � chaque widget de notre �l�ment
	    		new int[] {android.R.id.text1, android.R.id.text2, android.R.id.text2 });
	    
	    //Pour finir, on donne � la ListView le SimpleAdapter
	    vue.setAdapter(adapter);
	}

}