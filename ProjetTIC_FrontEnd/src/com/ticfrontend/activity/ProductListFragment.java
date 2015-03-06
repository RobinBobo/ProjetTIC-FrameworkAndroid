package com.ticfrontend.activity;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.example.projettic.R;
import com.ticfrontend.adapter.ProductListAdapter;
import com.ticfrontend.comparator.ProductPriceComparator;
import com.ticfrontend.magasin.Categorie;
import com.ticfrontend.magasin.Produit;

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
	
	public ProductListFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        this.rootView = inflater.inflate(R.layout.fragment_product_list, container, false);
        this.activity = this.getActivity();
        this.activity.setTitle(R.string.title_fragment_product_list);
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
	// TODO
	// private void ajoutListeProduit(Param)
	// Param : type de liste des produits � afficher 
	// exemple : liste des produits en promotion, des nouveaut�s, des offres sp�ciales,..
	private void testAjoutItemsListProduct(){
		List<Produit> listProduct = Produit.getAListOfProducts();
		ProductListAdapter pla = new ProductListAdapter(getActivity(), listProduct);
		ListView productsList = (ListView) rootView.findViewById(R.id.listviewProduit);
		productsList.setAdapter(pla);
		
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
