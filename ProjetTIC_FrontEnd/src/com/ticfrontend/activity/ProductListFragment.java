package com.ticfrontend.activity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.projettic.R;
import com.ticfrontend.adapter.ProductListAdapter;
import com.ticfrontend.comparator.ProductNameComparator;
import com.ticfrontend.comparator.ProductPriceComparator;
import com.ticfrontend.magasin.Categorie;
import com.ticfrontend.magasin.Produit;

public class ProductListFragment extends Fragment {

	public static final String EXTRA_KEY_PRODUCT = "EXTRA_KEY_PRODUCT";

	private View rootView;
	private Activity activity;
	private List<Produit> listProduit;
	private ProductListAdapter listViewAdapter = null;

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
//	public ProductListFragment(/* Liste produit (ex: promos, nouveautes, offres speciales,*/ int title){
//		Categorie cat = new Categorie(1, "Categorie Beta");
//		// On simule une catégorie courante dans CategoryFragment pour pas que ça plante dans le onCreateView
//		CategoryFragment.currentCategorie = cat;
//		this.title = title;
//	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		this.rootView = inflater.inflate(R.layout.fragment_product_list, container, false);
		
		init();
		
		ajoutItemsListProduct();
		
		listViewAdapter = new ProductListAdapter(getActivity(), listProduit);
		listProduit = listViewAdapter.getProducts();
		
		return rootView;
	}

	public void init(){
		this.activity = this.getActivity();
		this.activity.setTitle(title);
		
		// On récupère la catégorie sur laquelle on a cliqué
		this.categorie = CategoryFragment.currentCategorie;
		
		this.listProduit = new ArrayList<Produit>();
		
		// On parcous la liste des produits et s'ils sont dans cette catégorie, on ajoute à la listProduit, le produit
		for(int i = 0; i < MainActivity.LISTPRODUIT.size(); i++)
			if(MainActivity.LISTPRODUIT.get(i).getCategorieProduit().getNomCategorie().equals(categorie.getNomCategorie()))
				listProduit.add(MainActivity.LISTPRODUIT.get(i));
		
		blanc = (TextView) rootView.findViewById(R.id.textViewBlanc);
		upName = (ImageView) rootView.findViewById(R.id.imgSortNameDesc);
		downName = (ImageView) rootView.findViewById(R.id.imgSortNameAsc);
		upPrice = (ImageView) rootView.findViewById(R.id.imgSortPriceDesc);
		downPrice = (ImageView) rootView.findViewById(R.id.imgSortPriceAsc);		
		
		Button sortPrice = (Button) rootView.findViewById(R.id.buttonSortPrice);
		
		// Init de la couleur sur les boutons
		GradientDrawable bgShape = (GradientDrawable) sortPrice.getBackground();		
		int r = Integer.parseInt(MainActivity.COLORBUTTONSTRING.substring(0, 2), 16);
		int g = Integer.parseInt(MainActivity.COLORBUTTONSTRING.substring(2, 4), 16);
		int b = Integer.parseInt(MainActivity.COLORBUTTONSTRING.substring(4, 6), 16);
		bgShape.setColor(Color.argb(255, r, g, b)); 

		
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
		
		// Init de la couleur sur les boutons
		bgShape = (GradientDrawable) sortName.getBackground();		
		r = Integer.parseInt(MainActivity.COLORBUTTONSTRING.substring(0, 2), 16);
		g = Integer.parseInt(MainActivity.COLORBUTTONSTRING.substring(2, 4), 16);
		b = Integer.parseInt(MainActivity.COLORBUTTONSTRING.substring(4, 6), 16);
		bgShape.setColor(Color.argb(255, r, g, b)); 

		
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

	// Permet d'ajouter un filtre sur le EditText
	private void addSearchFilter(final ProductListAdapter adapter) {
		EditText filter = (EditText) rootView.findViewById(R.id.editTextRechercheProduit);
		filter.addTextChangedListener(new TextWatcher() {
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
			public void afterTextChanged(Editable arg0) {}
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				adapter.updateProductWithoutNotify(listProduit);
				adapter.getFilter().filter(s.toString());
			}
		});
	}
	
	private void ajoutItemsListProduct(){
		ProductListAdapter pla = new ProductListAdapter(getActivity(), listProduit);
		ListView productsList = (ListView) rootView.findViewById(R.id.listviewProduit);
		productsList.setAdapter(pla);

		addSearchFilter(pla);
		
		productsList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {			   
				Produit product = (Produit) arg0.getItemAtPosition(arg2);
				Fragment fragment = new ProductDetailsFragment(product);
				FragmentManager fragmentManager = getFragmentManager();
				FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
				fragmentTransaction.replace(R.id.frame_container, fragment);
				fragmentTransaction.addToBackStack("tag");
				fragmentTransaction.commit();
			}
		});
	}
}
