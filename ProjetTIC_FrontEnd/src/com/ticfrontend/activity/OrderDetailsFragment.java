package com.ticfrontend.activity;

import java.text.DecimalFormat;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.projettic.R;
import com.ticfrontend.adapter.PanierHashMapAdapter;
import com.ticfrontend.magasin.Commande;
import com.ticfrontend.magasin.Panier;

public class OrderDetailsFragment extends Fragment {
	private View rootView;
	private Activity activity;

	private Commande order = null;
	
	public OrderDetailsFragment(Commande c){
		this.order = c;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		this.rootView = inflater.inflate(R.layout.fragment_detail_order, container, false);
		this.activity = getActivity();
		
		init();

		return rootView;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		this.activity.setTitle(R.string.title_fragment_cart);
	}

	public View getRootView(){
		return rootView;
	}
	
	private void init() {
		
		TextView title = (TextView) rootView.findViewById(R.id.titleDetailOrder);
		title.setText("Détail de la commande n°" + order.getIdCommande());
		
		if(MainActivity.ISCONNECTED && MainActivity.CLIENT_ACTUEL != null) {
			ajoutItemsListOrder();
		}
		
		Button supprimer = (Button) rootView.findViewById(R.id.boutonSupprimer);
		supprimer.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
				builder.setTitle("Suppression d'une commande");
				builder.setMessage("Etes-vous sûr de vouloir supprimer cette commande de votre compte ?");
				builder.setCancelable(false);
				builder.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						MainActivity.CLIENT_ACTUEL.removeCommande(order);
						Fragment fragment = new OrderFragment();
						FragmentManager fragmentManager = getFragmentManager();
						fragmentManager.beginTransaction().replace(R.id.frame_container, fragment).addToBackStack("tag").commit();
						dialog.cancel();
					}
				});
				builder.setNegativeButton("Non", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();
					}
				});
				
				AlertDialog alert = builder.create();
				alert.show();
			}
		});
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);    
	}
	
	private void ajoutItemsListOrder(){
		Panier panier = order.getPanierCommande();
		PanierHashMapAdapter phma = new PanierHashMapAdapter(this.getActivity(),panier.getMapProduitQuantite(), rootView, true);

		ListView produitList = (ListView) rootView.findViewById(R.id.listviewOrder);
		produitList.setAdapter(phma);

		double prixTotal = panier.calculTotalPanier();

		TextView prixT = (TextView) rootView.findViewById(R.id.prixTotal);

		DecimalFormat df = new DecimalFormat() ; 
		df.setMaximumFractionDigits(2) ; //arrondi à 2 chiffres apres la virgules 
		df.setMinimumFractionDigits(2) ; 
		df.setDecimalSeparatorAlwaysShown(true); 

		prixT.setText("Total : "+ df.format(prixTotal) + " €");
	}	
}
