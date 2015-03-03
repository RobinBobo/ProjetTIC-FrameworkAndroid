package com.ticfrontend.activity;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.example.projettic.R;
import com.ticfrontend.adapter.ProductListAdapter;
import com.ticfrontend.magasin.Categorie;
import com.ticfrontend.magasin.Produit;
import com.ticfrontent.comparator.ProductPriceComparator;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ListFragment;
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

public class ProductListFragment extends Fragment {
	
	public static final String EXTRA_KEY_PRODUCT = "EXTRA_KEY_PRODUCT";
	
	private View rootView;
	private Activity activity;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        this.rootView = inflater.inflate(R.layout.fragment_product_list, container, false);
        this.activity = this.getActivity();
        init();
        testAjoutItemsListProduct();
        
        return rootView;
    }
	
	public void init(){
		Button search = (Button) rootView.findViewById(R.id.boutonValiderRechercheProduit);
    	search.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) { 
				// On change les textview
//				rootView.findViewById(R.id.layoutSort).setVisibility(View.VISIBLE);				
//				rootView.findViewById(R.id.textSearchResult).setVisibility(View.VISIBLE);
//						
				// Requete de recherche dans la BDD
				
				
				// Affichage de la liste
			}
		});	
    	
    	Button sortPrice = (Button) rootView.findViewById(R.id.buttonSortPrice);
    	sortPrice.setOnClickListener(new OnClickListener() {
    		@Override
 			public void onClick(View arg0) {
    			// Trier par prix
    			ListView list = (ListView) rootView.findViewById(R.id.listviewProduit);
    			ProductListAdapter adapter = (ProductListAdapter) list.getAdapter();
    			List<Produit> products = adapter.getProducts();
    			Collections.sort(products, new ProductPriceComparator());
    			adapter.updateProduct(products);
    		}
 		});
    	
    	Button sortName = (Button) rootView.findViewById(R.id.buttonSortName);
    	sortName.setOnClickListener(new OnClickListener() {
    		@Override
 			public void onClick(View arg0) {
    			// Trier par nom
    			ListView list = (ListView) rootView.findViewById(R.id.listviewProduit);
    			ProductListAdapter adapter = (ProductListAdapter) list.getAdapter();
    			List<Produit> products = adapter.getProducts();
    			Collections.sort(products, new Comparator<Produit>() {
    		        @Override
    		        public int compare(Produit product1, Produit product2) {
    		        	return product1.getNomProduit().compareTo(product2.getNomProduit());
    		        }
    		    });
    			
    			adapter.updateProduct(products);
 			}
 		});
	}
	
	private void testAjoutItemsListProduct(){
		List<Produit> listProduct = Produit.getAListOfProducts();
		
		//Création et initialisation de l'Adapter pour les catégories
		ProductListAdapter productsListAdapter = new ProductListAdapter(getActivity(), listProduct);
		
		//Récupération du composant ListView
		ListView productsList = (ListView) rootView.findViewById(R.id.listviewProduit);
		
		//Initialisation de la liste avec les données
		productsList.setAdapter(productsListAdapter);
		
		productsList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {			   
			    Fragment fragment = new ProductFragment();
			    Bundle extras = new Bundle();								
				Produit product = (Produit) arg0.getItemAtPosition(arg2);
			    extras.putSerializable(EXTRA_KEY_PRODUCT, product); 
				fragment.setArguments(extras);
				
				FragmentManager fragmentManager = getFragmentManager();
				FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
				fragmentTransaction.replace(R.id.frame_container, fragment);
				fragmentTransaction.addToBackStack("tag");
				fragmentTransaction.commit();
			}
		});
	}
}
