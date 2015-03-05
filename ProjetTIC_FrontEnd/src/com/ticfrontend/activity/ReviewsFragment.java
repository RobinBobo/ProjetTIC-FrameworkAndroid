package com.ticfrontend.activity;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.example.projettic.R;
import com.ticfrontend.adapter.AvisListAdapter;
import com.ticfrontend.adapter.ProductListAdapter;
import com.ticfrontend.comparator.ProductPriceComparator;
import com.ticfrontend.comparator.ReviewDateComparator;
import com.ticfrontend.magasin.Avis;
import com.ticfrontend.magasin.Produit;

public class ReviewsFragment  extends Fragment {
	private View rootView;
	private Activity activity;
	private List<Avis> listeAvis;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        this.rootView = inflater.inflate(R.layout.fragment_reviews, container, false);
        this.activity = this.getActivity();
		init();
        
        
        return rootView;
    }

	private void init() {
		testAjoutAvis();	
		
		Button sortDate = (Button) rootView.findViewById(R.id.buttonSortDate);
    	sortDate.setOnClickListener(new OnClickListener() {
    		@Override
 			public void onClick(View arg0) {
    			ListView listviewAvis = (ListView) rootView.findViewById(R.id.listviewReviews);	
    			AvisListAdapter adapter = (AvisListAdapter) listviewAvis.getAdapter();
    			List<Avis> listeAllAvis = adapter.getAvis();
    			Collections.sort(listeAllAvis, new ReviewDateComparator());
    			adapter.updateAvis(listeAllAvis);
    		}
 		});
    	
    	Button sortNote = (Button) rootView.findViewById(R.id.buttonSortNote);
    	sortNote.setOnClickListener(new OnClickListener() {
    		@Override
 			public void onClick(View arg0) {
    			// Trier par note
    			ListView list = (ListView) rootView.findViewById(R.id.listviewReviews);
    			AvisListAdapter adapter = (AvisListAdapter) list.getAdapter();
    			List<Avis> listeAllAvis = adapter.getAvis();
    			Collections.sort(listeAllAvis, new Comparator<Avis>() {
    		        @Override
    		        public int compare(Avis avis1, Avis avis2) {
    		        	int res = (int) (avis1.getNote() - avis2.getNote());
    		        	return res;
    		        }
    		    });
    			
    			adapter.updateAvis(listeAllAvis);
 			}
 		});
		
		
	}
	
	private void testAjoutAvis(){
		Produit product = ProductFragment.product;
		listeAvis = product.getListeAvisProduit();
		//Creation et initialisation de l'Adapter pour les catégories
		AvisListAdapter ala = new AvisListAdapter(activity, listeAvis);
		//Récupération du composant ListView
		ListView listviewAvis = (ListView) rootView.findViewById(R.id.listviewReviews);	
		//Initialisation de la liste avec les données
		listviewAvis.setAdapter(ala);
	}
}

