package com.ticfrontend.activity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import android.webkit.WebView.FindListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.projettic.R;
import com.ticfrontend.adapter.AvisListAdapter;
import com.ticfrontend.adapter.ProductListAdapter;
import com.ticfrontend.comparator.ProductPriceComparator;
import com.ticfrontend.comparator.ReviewDateComparator;
import com.ticfrontend.comparator.ReviewNoteComparator;
import com.ticfrontend.magasin.Avis;
import com.ticfrontend.magasin.Produit;

public class ReviewsFragment  extends Fragment {
	private View rootView;
	private Activity activity;
	private List<Avis> listeAvis;
	
	private Produit product = null;
	
	
	public ReviewsFragment(Produit prd){
		this.product = prd;
	}
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        this.rootView = inflater.inflate(R.layout.fragment_reviews, container, false);
        this.activity = this.getActivity();
       // this.product = ProductDetailsFragment.product;
        this.listeAvis = product.getListeAvisProduit();
		
        init();
        
        return rootView;
    }

	private void init() {
		TextView title = (TextView) rootView.findViewById(R.id.titleReviews);
		title.setText("Tous les avis (" + listeAvis.size() + ")");
		
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
    	
    	try {
    		listeAvis.get(0).setDate(new SimpleDateFormat("dd/MM/yyyy").parse("31/02/2010"));
    		listeAvis.get(1).setDate(new SimpleDateFormat("dd/MM/yyyy").parse("31/02/2008"));
    		listeAvis.get(2).setDate(new SimpleDateFormat("dd/MM/yyyy").parse("31/02/2006"));
			listeAvis.get(3).setDate(new SimpleDateFormat("dd/MM/yyyy").parse("31/02/2000"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	Button sortNote = (Button) rootView.findViewById(R.id.buttonSortNote);
    	sortNote.setOnClickListener(new OnClickListener() {
    		@Override
 			public void onClick(View arg0) {
    			// Trier par note
    			ListView list = (ListView) rootView.findViewById(R.id.listviewReviews);
    			AvisListAdapter adapter = (AvisListAdapter) list.getAdapter();
    			List<Avis> listeAllAvis = adapter.getAvis();
    			Collections.sort(listeAllAvis, new ReviewNoteComparator());
    			adapter.updateAvis(listeAllAvis);
 			}
 		});
		
    	testAjoutAvis();	
	}
	
	private void testAjoutAvis(){
		//Creation et initialisation de l'Adapter pour les catégories
		AvisListAdapter ala = new AvisListAdapter(activity, listeAvis);
		//Récupération du composant ListView
		ListView listviewAvis = (ListView) rootView.findViewById(R.id.listviewReviews);	
		//Initialisation de la liste avec les données
		listviewAvis.setAdapter(ala);
	}
}

