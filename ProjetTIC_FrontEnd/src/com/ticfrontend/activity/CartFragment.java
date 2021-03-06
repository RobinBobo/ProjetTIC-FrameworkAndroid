package com.ticfrontend.activity;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.projettic.R;
import com.ticfrontend.adapter.PanierHashMapAdapter;
import com.ticfrontend.magasin.Client;
import com.ticfrontend.magasin.Commande;
import com.ticfrontend.magasin.Panier;
import com.ticfrontend.magasin.Produit;

public class CartFragment extends Fragment {
	private View rootView;
	private Activity activity;

	private double prixTotal;
	public static Panier PANIER_CLIENT;;
//	private List<Produit> listProduit;

	public static final String EXTRA_KEY_PRODUCT = "EXTRA_KEY_PRODUCT";

	public CartFragment(){}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		this.rootView = inflater.inflate(R.layout.fragment_cart, container, false);
		this.activity = getActivity();
		this.prixTotal = 0;

		init();

		return rootView;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState)
	{
		super.onActivityCreated(savedInstanceState);
		this.activity.setTitle(R.string.title_fragment_cart);
	}
	
	public void initCart(){
		PANIER_CLIENT.viderPanier();
		this.prixTotal=0;
	}

	public View getRootView(){
		return rootView;
	}
	
	private void init() {
		if(MainActivity.ISCONNECTED && MainActivity.CLIENT_ACTUEL != null && PANIER_CLIENT != null)
			ajoutItemsListCartProduct();		
		
		Button commander = (Button) rootView.findViewById(R.id.boutonCommande);

		if(MainActivity.ORDER) {
			commander.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					// TODO Passer commande
					if(!PANIER_CLIENT.estVide()){
						//Commande commande = new Commande(prixTotal, listProduit);
						Panier p = new Panier(PANIER_CLIENT);
						Commande commande = new Commande(p);
						
						MainActivity.CLIENT_ACTUEL.addCommande(commande);
						
						Fragment fragment = new OrderFragment();
						FragmentManager fragmentManager = getFragmentManager();
						fragmentManager.beginTransaction().replace(R.id.frame_container, fragment).addToBackStack("tag").commit();
						initCart();
					} else {
						AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
						builder.setTitle("Mon panier");
						builder.setMessage("Vore panier est vide.");
						builder.setCancelable(false);
						builder.setNeutralButton("Retour", new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								dialog.cancel();
							}
						});
						AlertDialog alert = builder.create();
						alert.show();
					}
				}
			});			
		} else {
			commander.setVisibility(View.GONE);
		}
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);    
	}
//
//	private void testAjoutItemsListCartProduct(){
//		listProduit = Produit.getAListOfProducts();
//
//		TextView prixT = (TextView) rootView.findViewById(R.id.prixTotal);
//
//		PanierHashMapAdapter phma = new PanierHashMapAdapter(this.getActivity(),PANIER_CLIENT.getMapProduitQuantite(), rootView);
//		//CartProductListAdapter produitsListAdapter = new CartProductListAdapter(this.getActivity(), listProduit);
//
//		ListView produitList = (ListView) rootView.findViewById(R.id.listviewCard);
//		//produitList.setAdapter(produitsListAdapter);
//		produitList.setAdapter(phma);
//		//prixTotal = produitsListAdapter.getPrixTotal();
//		this.prixTotal = PANIER_CLIENT.calculTotalPanier();
//
//
//		DecimalFormat df = new DecimalFormat() ; 
//		df.setMaximumFractionDigits(2) ; //arrondi � 2 chiffres apres la virgules 
//		df.setMinimumFractionDigits(2) ; 
//		df.setDecimalSeparatorAlwaysShown(true); 
//
//		//prixT.setText("Total : " + produitsListAdapter.getPrixTotalToString() + " �");
//		prixT.setText("Total : "+ df.format(prixTotal) + " �");
//
//		produitList.setOnItemClickListener(new OnItemClickListener() {
//			@Override
//			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {	
//				// TODO modifier quantit� ou supprimer de la liste
//				Map.Entry<Produit, Integer> item = (Map.Entry<Produit, Integer>) arg0.getItemAtPosition(arg2);
//				Produit product = item.getKey();
//				Fragment fragment = new ProductDetailsFragment(product);
////				Bundle extras = new Bundle();								
////				
////				extras.putSerializable(EXTRA_KEY_PRODUCT, product); 
////				fragment.setArguments(extras);
//
//				FragmentManager fragmentManager = getFragmentManager();
//				FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//				fragmentTransaction.replace(R.id.frame_container, fragment);
//				fragmentTransaction.addToBackStack("tag");
//				fragmentTransaction.commit();
//
//			}
//		});
//	}
	
	private void ajoutItemsListCartProduct(){
		PanierHashMapAdapter phma = new PanierHashMapAdapter(this.getActivity(),PANIER_CLIENT.getMapProduitQuantite(), rootView);

		ListView produitList = (ListView) rootView.findViewById(R.id.listviewCard);
		produitList.setAdapter(phma);
		this.prixTotal = PANIER_CLIENT.calculTotalPanier();

		TextView prixT = (TextView) rootView.findViewById(R.id.prixTotal);

		DecimalFormat df = new DecimalFormat() ; 
		df.setMaximumFractionDigits(2) ; //arrondi � 2 chiffres apres la virgules 
		df.setMinimumFractionDigits(2) ; 
		df.setDecimalSeparatorAlwaysShown(true); 

		//prixT.setText("Total : " + produitsListAdapter.getPrixTotalToString() + " �");
		prixT.setText("Total : "+ df.format(prixTotal) + " �");

		produitList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {	
				// TODO modifier quantit� ou supprimer de la liste
				Map.Entry<Produit, Integer> item = (Map.Entry<Produit, Integer>) arg0.getItemAtPosition(arg2);
				Produit product = item.getKey();
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
