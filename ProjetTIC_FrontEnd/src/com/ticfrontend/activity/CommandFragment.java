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

public class CommandFragment extends Fragment {
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
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}


	public void init(){
		testAjoutItemsListCommande();	
	}
	
	private void testAjoutItemsListCommande(){
		List<Commande> listCommande = MainActivity.CLIENT_ACTUEL.getListeCommandesClient();
		
		if(listCommande != null){
			//Création et initialisation de l'Adapter pour les catégories
			CommandeListAdapter commandeListAdapter = new CommandeListAdapter(this.getActivity(), listCommande);
			
			//Récupération du composant ListView
			ListView commandeList = (ListView) rootView.findViewById(R.id.listviewCommande);
			
			//Initialisation de la liste avec les données
			commandeList.setAdapter(commandeListAdapter);
			
			commandeList.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {	
					// TODO Afficher la commande, dans une nouvelle vue ou 
				}
			});			
		} else {
			Fragment fragment = new HomeFragment();
			FragmentManager fragmentManager = getFragmentManager();
			fragmentManager.beginTransaction().replace(R.id.frame_container, fragment).addToBackStack("tag").commit();
			
			AlertDialog.Builder builder = new AlertDialog.Builder(activity);
            builder.setMessage("Nous n'avez pas de commande enregistrée.");
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
}
