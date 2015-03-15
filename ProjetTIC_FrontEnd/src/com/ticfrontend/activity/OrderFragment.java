package com.ticfrontend.activity;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.example.projettic.R;
import com.ticfrontend.adapter.CommandeListAdapter;
import com.ticfrontend.magasin.Commande;

public class OrderFragment extends Fragment {
	private View rootView;
	private Activity activity;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		this.rootView = inflater.inflate(R.layout.fragment_command, container, false);
		this.activity = this.getActivity();
		
		init();

		return rootView;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		this.activity.setTitle(R.string.title_fragment_order);
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}
	
	public void init(){
		testAjoutItemsListCommande();	
	}
	
	private void testAjoutItemsListCommande(){
		final List<Commande> listCommande = MainActivity.CLIENT_ACTUEL.getListeCommandesClient();
		
		if(listCommande != null){
			if(!listCommande.isEmpty()){
				//Création et initialisation de l'Adapter pour les catégories
				CommandeListAdapter commandeListAdapter = new CommandeListAdapter(this.getActivity(), listCommande);
				
				//Récupération du composant ListView
				ListView commandeList = (ListView) rootView.findViewById(R.id.listviewCommande);
				
				//Initialisation de la liste avec les données
				commandeList.setAdapter(commandeListAdapter);
				
				commandeList.setOnItemClickListener(new OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {	
						// Affiche la commande
						Commande c = (Commande) arg0.getItemAtPosition(arg2);
						//Commande c2 = listCommande.get(arg2); 
						Fragment fragment = new OrderDetailsFragment(c);
						
						if (fragment != null) {
							FragmentManager fragmentManager = getFragmentManager();
							fragmentManager.beginTransaction().replace(R.id.frame_container, fragment).addToBackStack("tag").commit();
						}
					}
				});							
			} else {
				returnHome();
			}
		} else {
			returnHome();
		}
	}
	
	public void returnHome(){
		Fragment fragment = new HomeFragment();
		FragmentManager fragmentManager = getFragmentManager();
		fragmentManager.beginTransaction().replace(R.id.frame_container, fragment).addToBackStack("tag").commit();
		
		AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setMessage("Vous n'avez pas de commande enregistrée.");
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
