package com.ticfrontend.activity;

import java.util.List;

import com.example.projettic.R;
import com.ticfrontend.adapter.CategorieListAdapter;
import com.ticfrontend.magasin.Categorie;
import com.ticfrontend.magasin.Produit;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class CategoryFragment extends Fragment {

	public static final String EXTRA_KEY_CATEGROY = "EXTRA_KEY_CATEGORY";
	private View rootView;
	private Activity activity;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		this.rootView = inflater.inflate(R.layout.fragment_category, container, false);
		this.activity = getActivity();
		init();
		
		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		this.activity.setTitle(R.string.title_fragment_category);
	}

	public void init(){
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
				// TODO
				// Ici à faire correspondre une categorie à une liste de produit
				Fragment fragment = new ProductListFragment();
				Bundle category = new Bundle();
				Categorie cat = (Categorie) arg0.getItemAtPosition(arg2);
				category.putSerializable(EXTRA_KEY_CATEGROY, cat); 
				fragment.setArguments(category);

				FragmentManager fragmentManager = getFragmentManager();
				FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
				fragmentTransaction.replace(R.id.frame_container, fragment);
				fragmentTransaction.addToBackStack("tag");
				fragmentTransaction.commit();
			}
		});
	}

}
