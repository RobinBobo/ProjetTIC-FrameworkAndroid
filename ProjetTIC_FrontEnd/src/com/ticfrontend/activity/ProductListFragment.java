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
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class ProductListFragment extends Fragment {

	public static final String EXTRA_KEY_PRODUCT = "EXTRA_KEY_PRODUCT";

	private View rootView;
	private Activity activity;
	private List<Produit> listProduit;
	private ListView listViewProducts = null;
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
		
		Categorie verifCategorie = null;
				
		if(MainActivity.LISTPRODUIT != null)
			if(MainActivity.LISTPRODUIT.size() > 0)
				verifCategorie = MainActivity.LISTPRODUIT.get(0).getCategorieProduit();
		
		if(categorie.getNomCategorie().equalsIgnoreCase(verifCategorie.getNomCategorie()))
			this.listProduit = MainActivity.LISTPRODUIT;
		else {
			this.listProduit = Produit.getAListOfProductsBeta(categorie);
			for(int i = 0; i < listProduit.size(); i++)
				listProduit.get(i).setIconRessource(getRandomImage());		
		}		
		
		blanc = (TextView) rootView.findViewById(R.id.textViewBlanc);
		
		upName = (ImageView) rootView.findViewById(R.id.imgSortNameDesc);
		downName = (ImageView) rootView.findViewById(R.id.imgSortNameAsc);
		upPrice = (ImageView) rootView.findViewById(R.id.imgSortPriceDesc);
		downPrice = (ImageView) rootView.findViewById(R.id.imgSortPriceAsc);
		
		init();
		
		testAjoutItemsListProduct();
		
		listViewProducts = (ListView) rootView.findViewById(R.id.listviewProduit);
		listViewAdapter = new ProductListAdapter(getActivity(), listProduit);
		listProduit = listViewAdapter.getProducts();
		
		return rootView;
	}

	public void init(){
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
	
	private void testAjoutItemsListProduct(){
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
	
	private int getRandomImage() {
		int random = 1 + (int)(Math.random() * ((20 - 1) + 1));
		int res = R.drawable.prd1;
		switch (random) {
			case 1:
				res = R.drawable.prd1;
				break;
			case 2:
				res = R.drawable.prd2;				
				break;
			case 3:
				res = R.drawable.prd3;
				break;
			case 4:
				res = R.drawable.prd4;
				break;
			case 5:
				res = R.drawable.prd5;
				break;
			case 6:
				res = R.drawable.prd6;
				break;
			case 7:
				res = R.drawable.prd7;
				break;
			case 8:
				res = R.drawable.prd8;
				break;
			case 9:
				res = R.drawable.prd9;
				break;
			case 10:
				res = R.drawable.prd10;
				break;
			case 11:
				res = R.drawable.prd11;
				break;
			case 12:
				res = R.drawable.prd12;
				break;
			case 13:
				res = R.drawable.prd13;
				break;
			case 14:
				res = R.drawable.prd14;
				break;
			case 15:
				res = R.drawable.prd15;
				break;
			case 16:
				res = R.drawable.prd16;
				break;
			case 17:
				res = R.drawable.prd17;
				break;
			case 18:
				res = R.drawable.prd18;
				break;
			case 19:
				res = R.drawable.prd19;
				break;
			case 20:
				res = R.drawable.prd20;
				break;
			case 21:
				res = R.drawable.prd21;
				break;
			case 22:
				res = R.drawable.prd22;
				break;
			case 23:
				res = R.drawable.prd23;
				break;
			case 24:
				res = R.drawable.prd24;
				break;
			case 25:
				res = R.drawable.prd25;
				break;
			default:
				res = R.drawable.prd1;
				break;
		}
		return res;
	}
}
