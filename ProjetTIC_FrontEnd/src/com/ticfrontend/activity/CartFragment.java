package com.ticfrontend.activity;

import java.text.DecimalFormat;
import java.util.List;

import com.example.projettic.R;
import com.ticfrontend.adapter.CartProductListAdapter;
import com.ticfrontend.adapter.CategorieListAdapter;
import com.ticfrontend.adapter.ProductListAdapter;
import com.ticfrontend.magasin.Categorie;
import com.ticfrontend.magasin.Client;
import com.ticfrontend.magasin.Commande;
import com.ticfrontend.magasin.Produit;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

public class CartFragment extends Fragment {
	private View rootView;
	
	private double prixTotal;
	
	private List<Produit> listProduit;
	
	public static final String EXTRA_KEY_PRODUCT = "EXTRA_KEY_PRODUCT";
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        this.rootView = inflater.inflate(R.layout.activity_cart, container, false);
        this.prixTotal = 0;
         
        testAjoutItemsListCartProduct();
        
        init();
        
        return rootView;
    }
	
	private void init() {
		Button commander = (Button) rootView.findViewById(R.id.boutonCommande);
		
		commander.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Passer commande
				Commande commande = new Commande(prixTotal, listProduit);
				Client client = MainActivity.CLIENT_ACTUEL;
				client.addCommande(commande);
				Fragment fragment = new HomeFragment();
				FragmentManager fragmentManager = getFragmentManager();
				fragmentManager.beginTransaction().replace(R.id.frame_container, fragment).addToBackStack("tag").commit();
			}
		});
	}

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);    
    }
	
	private void testAjoutItemsListCartProduct(){
		listProduit = Produit.getAListOfProducts();
		//Création et initialisation de l'Adapter pour les catégories
		
		CartProductListAdapter produitsListAdapter = new CartProductListAdapter(this.getActivity(), listProduit);

		//Récupération du composant ListView
		ListView produitList = (ListView) rootView.findViewById(R.id.listviewCard);

		//Initialisation de la liste avec les données
		produitList.setAdapter(produitsListAdapter);

		prixTotal = produitsListAdapter.getPrixTotal();
		
		TextView prixT = (TextView) rootView.findViewById(R.id.prixTotal);
			
		DecimalFormat df = new DecimalFormat() ; 
		df.setMaximumFractionDigits(2) ; //arrondi à 2 chiffres apres la virgules 
		df.setMinimumFractionDigits(2) ; 
		df.setDecimalSeparatorAlwaysShown(true); 
		
		prixT.setText("Total : " + produitsListAdapter.getPrixTotalToString() + " €");
		
		produitList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {	
				// TODO modifier quantité ou supprimer de la liste
				
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
