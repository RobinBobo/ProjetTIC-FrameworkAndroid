package com.ticfrontend.activity;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.example.projettic.R;
import com.ticfrontend.adapter.ProductListAdapter;
import com.ticfrontend.comparator.ProductNameComparator;
import com.ticfrontend.comparator.ProductPriceComparator;
import com.ticfrontend.magasin.Categorie;
import com.ticfrontend.magasin.Produit;

import android.animation.ArgbEvaluator;
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
	private List<Produit> listProduit;
	private int title = R.string.title_fragment_product_list;
	
	private Categorie categorie = null;

	// TODO
	// private void ajoutListeProduit(Param)
	// Param : type de liste des produits � afficher 
	// exemple : liste des produits en promotion, des nouveaut�s, des offres sp�ciales,..
	public ProductListFragment(/* Liste produit (ex: promos, nouveautes, offres speciales,*/ int title){
		Categorie cat = new Categorie(1, "Categorie Beta");
		// On simule une cat�gorie courante dans CategoryFragment pour pas que �a plante dans le onCreateView
		CategoryFragment.currentCategorie = cat;
		this.listProduit = Produit.getAListOfProductsBeta(cat);
		this.title = title;
	}
	public ProductListFragment(/* Liste produit (ex: promos, nouveautes, offres speciales,*/){
//		this.listProduit = Produit.getAListOfProducts();
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		this.rootView = inflater.inflate(R.layout.fragment_product_list, container, false);
		this.activity = this.getActivity();
		this.activity.setTitle(title);
		
		// On r�cup�re la cat�gorie sur laquelle on a cliqu�
		this.categorie = CategoryFragment.currentCategorie;
		
		Categorie verifCategorieBeta = null;
				
		if(MainActivity.LISTPRODUITBETA != null)
			if(MainActivity.LISTPRODUITBETA.size() > 0)
				verifCategorieBeta = MainActivity.LISTPRODUITBETA.get(0).getCategorieProduit();
		
		if(categorie.getNomCategorie().equalsIgnoreCase(verifCategorieBeta.getNomCategorie()))
			this.listProduit = MainActivity.LISTPRODUITBETA;
		else
			this.listProduit = Produit.getAListOfProductsBeta(categorie);
		
		init();
		
		testAjoutItemsListProduct();

		return rootView;
	}

	public void init(){
		Button search = (Button) rootView.findViewById(R.id.boutonValiderRechercheProduit);
		search.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) { 
				
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
				
				Collections.sort(products, new ProductPriceComparator(ProductPriceComparator.ASC));
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
				Collections.sort(products, new ProductNameComparator(ProductNameComparator.ASC));
				adapter.updateProduct(products);
			}
		});
	}

	private void testAjoutItemsListProduct(){
		ProductListAdapter pla = new ProductListAdapter(getActivity(), listProduit);
		ListView productsList = (ListView) rootView.findViewById(R.id.listviewProduit);
		productsList.setAdapter(pla);

		productsList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {			   
				Produit product = (Produit) arg0.getItemAtPosition(arg2);
				Fragment fragment = new ProductDetailsFragment(product);
//				Bundle extras = new Bundle();								
//				
//				extras.putSerializable(EXTRA_KEY_PRODUCT, product); 
//				fragment.setArguments(extras);

				FragmentManager fragmentManager = getFragmentManager();
				FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
				fragmentTransaction.replace(R.id.frame_container, fragment);
				fragmentTransaction.addToBackStack("tag");
				fragmentTransaction.commit();
			}
		});
	}
}
