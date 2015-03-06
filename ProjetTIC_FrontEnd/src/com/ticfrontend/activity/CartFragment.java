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
	public static Panier PANIER_CLIENT = null;
	private List<Produit> listProduit;

	public static final String EXTRA_KEY_PRODUCT = "EXTRA_KEY_PRODUCT";

	public CartFragment(){}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		this.rootView = inflater.inflate(R.layout.activity_cart, container, false);
		this.activity = getActivity();
		this.prixTotal = 0;

		testAjoutItemsListCartProduct();

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

	private void init() {
		Button commander = (Button) rootView.findViewById(R.id.boutonCommande);

		commander.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Passer commande
				if(!PANIER_CLIENT.estVide()){
					//Commande commande = new Commande(prixTotal, listProduit);
					Commande commande = new Commande(PANIER_CLIENT);
					Client client = MainActivity.CLIENT_ACTUEL;
					client.addCommande(commande);

					Fragment fragment = new OrderFragment();
					FragmentManager fragmentManager = getFragmentManager();
					fragmentManager.beginTransaction().replace(R.id.frame_container, fragment).addToBackStack("tag").commit();
					initCart();
				} else {
					AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
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
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);    
	}

	private void testAjoutItemsListCartProduct(){
		listProduit = Produit.getAListOfProducts();

		PanierHashMapAdapter phma = new PanierHashMapAdapter(this.getActivity(),PANIER_CLIENT.getMapProduitQuantite());
		//CartProductListAdapter produitsListAdapter = new CartProductListAdapter(this.getActivity(), listProduit);

		ListView produitList = (ListView) rootView.findViewById(R.id.listviewCard);
		//produitList.setAdapter(produitsListAdapter);
		produitList.setAdapter(phma);
		//prixTotal = produitsListAdapter.getPrixTotal();
		this.prixTotal = PANIER_CLIENT.getTotalPanier();

		TextView prixT = (TextView) rootView.findViewById(R.id.prixTotal);

		DecimalFormat df = new DecimalFormat() ; 
		df.setMaximumFractionDigits(2) ; //arrondi à 2 chiffres apres la virgules 
		df.setMinimumFractionDigits(2) ; 
		df.setDecimalSeparatorAlwaysShown(true); 

		//prixT.setText("Total : " + produitsListAdapter.getPrixTotalToString() + " €");
		prixT.setText("Total : "+ df.format(prixTotal) + " €");

		produitList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {	
				// TODO modifier quantité ou supprimer de la liste

				Fragment fragment = new ProductDetailsFragment();
				Bundle extras = new Bundle();								
				Map.Entry<Produit, Integer> item = (Map.Entry<Produit, Integer>) arg0.getItemAtPosition(arg2);
				Produit product = item.getKey();
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
