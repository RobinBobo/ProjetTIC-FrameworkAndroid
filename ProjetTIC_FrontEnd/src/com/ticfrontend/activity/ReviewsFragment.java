package com.ticfrontend.activity;

import java.util.List;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.projettic.R;
import com.ticfrontend.adapter.AvisListAdapter;
import com.ticfrontend.magasin.Avis;
import com.ticfrontend.magasin.Produit;

public class ReviewsFragment  extends Fragment {
	private View rootView;
	private Activity activity;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        this.rootView = inflater.inflate(R.layout.fragment_reviews, container, false);
        this.activity = this.getActivity();
		init();
        
        
        return rootView;
    }

	private void init() {
		testAjoutAvis();	
	}
	
	private void testAjoutAvis(){
		Produit product = ProductFragment.product;
		List<Avis> listeAvis = product.getListeAvisProduit();
		//Creation et initialisation de l'Adapter pour les catégories
		AvisListAdapter ala = new AvisListAdapter(activity, listeAvis);
		//Récupération du composant ListView
		ListView listviewAvis = (ListView) rootView.findViewById(R.id.listviewReviews);	
		//Initialisation de la liste avec les données
		listviewAvis.setAdapter(ala);
	}
}

