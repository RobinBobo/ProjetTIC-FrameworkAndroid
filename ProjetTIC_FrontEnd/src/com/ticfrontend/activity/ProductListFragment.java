package com.ticfrontend.activity;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.example.projettic.R;
import com.ticfrontend.adapter.ProductListAdapter;
import com.ticfrontend.comparator.ProductNameComparator;
import com.ticfrontend.comparator.ProductPriceComparator;
import com.ticfrontend.comparator.ReviewNoteComparator;
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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class ProductListFragment extends Fragment {

	public static final String EXTRA_KEY_PRODUCT = "EXTRA_KEY_PRODUCT";

	private View rootView;
	private Activity activity;
	private List<Produit> listProduit;
	private int title = R.string.title_fragment_product_list;
	
	private Categorie categorie = null;

	// Pour savoir si on trie en ASC ou DESC
	private boolean sortDirectionName = false;
	private boolean sortDirectionPrice = false;
	private ImageView upName = null;
	private ImageView downName = null;
	private ImageView upPrice = null;
	private ImageView downPrice = null;
	private TextView blanc = null;
	
	
	// TODO
	// private void ajoutListeProduit(Param)
	// Param : type de liste des produits à afficher 
	// exemple : liste des produits en promotion, des nouveautés, des offres spéciales,..
	public ProductListFragment(/* Liste produit (ex: promos, nouveautes, offres speciales,*/ int title){
		Categorie cat = new Categorie(1, "Categorie Beta");
		// On simule une catégorie courante dans CategoryFragment pour pas que ça plante dans le onCreateView
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
		
		// On récupère la catégorie sur laquelle on a cliqué
		this.categorie = CategoryFragment.currentCategorie;
		
		Categorie verifCategorieBeta = null;
				
		if(MainActivity.LISTPRODUITBETA != null)
			if(MainActivity.LISTPRODUITBETA.size() > 0)
				verifCategorieBeta = MainActivity.LISTPRODUITBETA.get(0).getCategorieProduit();
		
		if(categorie.getNomCategorie().equalsIgnoreCase(verifCategorieBeta.getNomCategorie()))
			this.listProduit = MainActivity.LISTPRODUITBETA;
		else
			this.listProduit = Produit.getAListOfProductsBeta(categorie);
		
		
		blanc = (TextView) rootView.findViewById(R.id.textViewBlanc);
		
		upName = (ImageView) rootView.findViewById(R.id.imgSortNameDesc);
		downName = (ImageView) rootView.findViewById(R.id.imgSortNameAsc);
		upPrice = (ImageView) rootView.findViewById(R.id.imgSortPriceDesc);
		downPrice = (ImageView) rootView.findViewById(R.id.imgSortPriceAsc);
		
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
				
				blanc.setVisibility(View.GONE);
    			
    			if(!sortDirectionPrice) {
    				downPrice.setVisibility(View.VISIBLE);
    				upPrice.setVisibility(View.GONE);
    				downName.setVisibility(View.GONE);
    				upName.setVisibility(View.GONE);
    				Collections.sort(products, new ProductPriceComparator(ProductPriceComparator.ASC));
    				sortDirectionPrice = true;
    			} else {
    				upPrice.setVisibility(View.VISIBLE);
    				downPrice.setVisibility(View.GONE);
    				downName.setVisibility(View.GONE);
    				upName.setVisibility(View.GONE);
    				Collections.sort(products, new ProductPriceComparator(ProductPriceComparator.DESC));
    				sortDirectionPrice = false;
    			}				
				
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
				
				blanc.setVisibility(View.VISIBLE);
    			
    			if(!sortDirectionName) {
    				downName.setVisibility(View.VISIBLE);
    				upName.setVisibility(View.GONE);
    				downPrice.setVisibility(View.GONE);
    				upPrice.setVisibility(View.GONE);
    				Collections.sort(products, new ProductNameComparator(ProductNameComparator.ASC));
    				sortDirectionName = true;
    			} else {
    				upName.setVisibility(View.VISIBLE);
    				downName.setVisibility(View.GONE);
    				downPrice.setVisibility(View.GONE);
    				upPrice.setVisibility(View.GONE);
    				Collections.sort(products, new ProductNameComparator(ProductNameComparator.DESC));
    				sortDirectionName = false;
    			}					
				
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
