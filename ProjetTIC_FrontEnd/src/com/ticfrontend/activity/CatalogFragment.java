package com.ticfrontend.activity;

import java.util.List;

import com.example.projettic.R;
import com.ticfrontend.adapter.CategorieListAdapter;
import com.ticfrontend.magasin.Catalogue;
import com.ticfrontend.magasin.Categorie;
import com.ticfrontend.magasin.Produit;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class CatalogFragment extends Fragment {

	public static final String EXTRA_KEY_CATEGROY = "EXTRA_KEY_CATEGORY";
	private View rootView;
	private Activity activity;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        this.rootView = inflater.inflate(R.layout.fragment_catalog, container, false);
        this.activity = this.getActivity();
        testAjoutItemsListCategorie();
        
        return rootView;
    }
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
    }
	
	public void init(){
		rootView.findViewById(R.id.editTextRecherche).setSelected(false);
		
		Button search = (Button) rootView.findViewById(R.id.boutonValiderRecherche);
    	search.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) { 
				// On change les textview
				rootView.findViewById(R.id.layoutSort).setVisibility(View.VISIBLE);
				rootView.findViewById(R.id.textListCategorie).setVisibility(View.GONE);
				//rootView.findViewById(R.id.textSearchResult).setVisibility(View.VISIBLE);
				rootView.findViewById(R.id.layoutSort).setVisibility(View.VISIBLE);
				
				// Requete de recherche dans la BDD
				
				// Affichage de la liste
				//testAjoutItemsListCategorie();
			}
		});	
    	testAjoutItemsListCategorie();
	}
	
	private void testAjoutItemsListCategorie(){
		List<Categorie> listCategorie = Categorie.getAListOfCategorieBeta();
		
		//Création et initialisation de l'Adapter pour les catégories
		CategorieListAdapter categorieListAdapter = new CategorieListAdapter(this.getActivity(), listCategorie);
		
		//Récupération du composant ListView
		ListView categorieList = (ListView) rootView.findViewById(R.id.listviewCat);
		
		//Initialisation de la liste avec les données
		categorieList.setAdapter(categorieListAdapter);
				
		categorieList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {	
				Intent intent = new Intent(activity,ProductListFragment.class);
				Bundle extras = new Bundle();								
				Categorie categorie = (Categorie) arg0.getItemAtPosition(arg2);
			    extras.putSerializable(EXTRA_KEY_CATEGROY, categorie); 
			    intent.putExtras(extras); 
			    startActivity(intent);
			}
		});
	}
	
}
