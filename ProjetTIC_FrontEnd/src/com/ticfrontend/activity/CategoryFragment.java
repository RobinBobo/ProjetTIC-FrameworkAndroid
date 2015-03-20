package com.ticfrontend.activity;

import java.util.List;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;

import com.example.projettic.R;
import com.ticfrontend.adapter.CategorieListAdapter;
import com.ticfrontend.magasin.Categorie;

public class CategoryFragment extends Fragment {

	public static final String EXTRA_KEY_CATEGROY = "EXTRA_KEY_CATEGORY";
	private View rootView;
	private Activity activity;
	
	public static Categorie currentCategorie = null;
	
	private List<Categorie> listCategories;
	
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
		ajoutItemsListCategorie();
	}

	private void ajoutItemsListCategorie() {
		listCategories = MainActivity.LISTCATEGORIE;
				
		//Création et initialisation de l'Adapter pour les catégories
		CategorieListAdapter categorieListAdapter = new CategorieListAdapter(this.getActivity(), listCategories);

		//Récupération du composant ListView
		ListView categorieList = (ListView) rootView.findViewById(R.id.listviewCat);

		//Initialisation de la liste avec les données
		categorieList.setAdapter(categorieListAdapter);
		
		addSearchFilter(categorieListAdapter);

		categorieList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {	
				Fragment fragment = new ProductListFragment();
				currentCategorie = (Categorie) arg0.getItemAtPosition(arg2);

				FragmentManager fragmentManager = getFragmentManager();
				FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
				fragmentTransaction.replace(R.id.frame_container, fragment);
				fragmentTransaction.addToBackStack("tag");
				fragmentTransaction.commit();
			}
		});
	}
	
	// Permet d'ajouter un filtre sur le EditText
	private void addSearchFilter(final CategorieListAdapter adapter) {
		EditText filter = (EditText) rootView.findViewById(R.id.editTextRecherche);
		filter.addTextChangedListener(new TextWatcher() {
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
			public void afterTextChanged(Editable arg0) {}
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				adapter.updateCategoriesWithoutNotify(listCategories);
				adapter.getFilter().filter(s.toString());
			}
		});
	}
}
