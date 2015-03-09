package activities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import fr.tic.R;
import beans.Produit;
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
		
		vue = (ListView) findViewById(R.id.catalogueProduits);
	    List<HashMap<String, String>> liste = new ArrayList<HashMap<String, String>>();
	    
	    HashMap<String, String> element;
	    //Pour chaque personne dans notre r�pertoire�
	    for(int i = 0 ; i < MainActivity.getMonCatalogue().getMesProduits().size() ; i++) {
	    	Produit p = MainActivity.getMonCatalogue().getMesProduits().get(i);
	    	element = new HashMap<String, String>();
	    	element.put("idProduit", Integer.toString(p.getIdProduit()));
	    	element.put("nomProduit", p.getNomProduit());
	    	liste.add(element);
	    }
	    
	    ListAdapter adapter = new SimpleAdapter(this,  
	    		//Valeurs � ins�rer
	    		liste,	      
	    		// Layout de chaque �l�ment (l�, il s'agit d'un layout par d�faut)
	    		android.R.layout.simple_list_item_2,
	    		// Les cl�s des informations � afficher pour chaque �l�ment
	    		new String[] {"idProduit", "nomProduit"},
	    		// Enfin, les layouts � appliquer � chaque widget de notre �l�ment
	    		new int[] {android.R.id.text2, android.R.id.text1});
	    
	    //Pour finir, on donne � la ListView le SimpleAdapter
	    vue.setAdapter(adapter);
	}

}
