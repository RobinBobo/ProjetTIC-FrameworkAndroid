package activities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import fr.tic.R;
import beans.Produit;
import plurals.Catalogue;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class AfficherCatalogueActivity extends Activity {
	
	ListView vue;
	Catalogue monCatalogue = null;
		
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_affichercatalogue);

		// Récupération des données
		Intent intent = getIntent();
		Bundle b = intent.getExtras();
		monCatalogue = (Catalogue) b.getSerializable("monCatalogue");
		
		
		vue = (ListView) findViewById(R.id.catalogueProduits);
	    List<HashMap<String, String>> liste = new ArrayList<HashMap<String, String>>();
	    
	    HashMap<String, String> element;
	    //Pour chaque personne dans notre répertoire…
	    for(int i = 0 ; i < monCatalogue.getMesProduits().size() ; i++) {
	    	Produit p = monCatalogue.getMesProduits().get(i);
	    	element = new HashMap<String, String>();
	    	element.put("idProduit", Integer.toString(p.getIdProduit()));
	    	element.put("nomProduit", p.getNomProduit());
	    	liste.add(element);
	    }
	    
	    ListAdapter adapter = new SimpleAdapter(this,  
	    		//Valeurs à insérer
	    		liste,	      
	    		// Layout de chaque élément (là, il s'agit d'un layout par défaut)
	    		android.R.layout.simple_list_item_2,
	    		// Les clés des informations à afficher pour chaque élément
	    		new String[] {"idProduit", "nomProduit"},
	    		// Enfin, les layouts à appliquer à chaque widget de notre élément
	    		new int[] {android.R.id.text2, android.R.id.text1});
	    
	    //Pour finir, on donne à la ListView le SimpleAdapter
	    vue.setAdapter(adapter);
	}

}
