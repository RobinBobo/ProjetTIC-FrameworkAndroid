package com.ticfrontend.activity;

import java.util.List;

import com.example.projettic.R;
import com.ticfrontend.adapter.AvisListAdapter;
import com.ticfrontend.magasin.Avis;
import com.ticfrontend.magasin.Produit;

import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

public class ProductFragment extends Fragment {
	private View rootView;
	private Activity activity;

	private static TextView tv;
	private static Button b;
	private Produit product;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		this.rootView = inflater.inflate(R.layout.activity_product, container, false);
		this.activity = this.getActivity();
		
		return rootView;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState)
	{
		super.onActivityCreated(savedInstanceState);

		Bundle bundle = getArguments();
		if(bundle != null)
		{
			product = new Produit((Produit) bundle.getSerializable(ProductListFragment.EXTRA_KEY_PRODUCT)); 
			populateScreen();
		}
	}
	public void init(){
		//		tv = (TextView) rootView.findViewById(R.id.availableQuantity);	
		//		b = (Button) rootView.findViewById(R.id.boutonAjouter);  
	}

	private void populateScreen() {
		// Nom produit
		TextView name = (TextView) rootView.findViewById(R.id.textProductName);
		name.setText(product.getNomProduit());

		// Prix produit
		TextView price = ((TextView) rootView.findViewById(R.id.textPriceProduct));
		price.setText(String.valueOf(product.getPrixProduit()) + " €");

		// Description produit
		TextView desc = (TextView) rootView.findViewById(R.id.textInfoProduct);
		desc.setText(product.getDescriptionProduit());

		// Marque produit
		TextView brand = (TextView) rootView.findViewById(R.id.textProductMarque);
		brand.setText("Marque : " + product.getMarqueProduit());

		// Catégorie produit
		TextView cat = (TextView) rootView.findViewById(R.id.textProductCategorie);
		cat.setText("Catégorie : " + product.getCategorieProduit().getNomCategorie());

		//Note produit
		double noteGenerale = 0;
		int tailleListe = product.getListeAvisProduit().size();

		for(Avis a : product.getListeAvisProduit())
			noteGenerale += a.getNote();

		noteGenerale = noteGenerale/tailleListe;

		((RatingBar) rootView.findViewById(R.id.ratingBarReview)).setRating((float)noteGenerale);
		((TextView) rootView.findViewById(R.id.NombreVotes)).setText(String.valueOf(tailleListe) + ((tailleListe==1) ? " vote": " votes"));

		// Test liste d'avis
		testAjoutAvis();

	}

	private void testAjoutAvis(){
		List<Avis> listeAvis = product.getListeAvisProduit();
		//Creation et initialisation de l'Adapter pour les catégories
		AvisListAdapter ala = new AvisListAdapter(activity, listeAvis);
		//Récupération du composant ListView
		ListView listviewAvis = (ListView) rootView.findViewById(R.id.listviewAvis);	
		//Initialisation de la liste avec les données
		listviewAvis.setAdapter(ala);
	}
}
