package com.ticfrontend.activity;

import java.text.DateFormat;
import java.util.List;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.projettic.R;
import com.ticfrontend.magasin.Avis;
import com.ticfrontend.magasin.Produit;

public class ProductDetailsFragment extends Fragment {
	private View rootView;
	private Activity activity;

	private static Button boutonAjouterPanier;
	private Produit product;
	
	private final int nbAvis = 3;
	List<Avis> listeAvis = null;

	public ProductDetailsFragment(Produit prd){
		this.product = prd;
		listeAvis = product.getListeAvisProduit();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		this.rootView = inflater.inflate(R.layout.fragment_product, container, false);
		this.activity = this.getActivity();
		
		init();
		
		return rootView;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		this.activity.setTitle(R.string.title_fragment_product);
	}
	public void init(){
		boutonAjouterPanier = (Button) rootView.findViewById(R.id.boutonAjouter); 
		boutonAjouterPanier.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Passer commande
				AlertDialog.Builder builder = new AlertDialog.Builder(activity);
				AlertDialog alert;
				
				if(MainActivity.ISCONNECTED){
					DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
					    @Override
					    public void onClick(DialogInterface dialog, int which) {
					        switch (which){
					        case DialogInterface.BUTTON_POSITIVE:
					        	CartFragment.PANIER_CLIENT.ajouterDansPanier(product, 1);
					            break;

					        case DialogInterface.BUTTON_NEGATIVE:
					            //No button clicked
					            break;
					        }
					    }
					};
					builder.setMessage("Confirmez-vous l'ajout ?").setPositiveButton("Oui", dialogClickListener)
					    .setNegativeButton("Non", dialogClickListener).show();
				} else {
					builder.setMessage("Vous devez vous connecter.");
					builder.setCancelable(true);
					builder.setNeutralButton("Retour", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {
							dialog.cancel();
						}
					});
					alert = builder.create();
					alert.show();
				}
			}
		});
		
		populateScreen();
	}

	private void populateScreen() {
		// Image produit
		ImageView icon = (ImageView) rootView.findViewById(R.id.imgProduct);
		icon.setImageResource(product.getIconRessource());
		
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
		if(MainActivity.CUSTOMERNOTICE) {
			int tailleListe = listeAvis.size();

			for(Avis a : listeAvis)
				noteGenerale += a.getNote();
	
			noteGenerale = noteGenerale/tailleListe;
	
			((RatingBar) rootView.findViewById(R.id.ratingBarReview)).setRating((float)noteGenerale);
			((TextView) rootView.findViewById(R.id.NombreVotes)).setText(String.valueOf(tailleListe) + ((tailleListe==1) ? " vote": " votes"));
	
			// Test liste d'avis
			testAjoutAvis();
			
			// Quand on click sur Voir tous les avis, on lance un nouveau fragment qui comporte une listview avec tous les avis
			// Permet de trier aussi
			Button voirPlusAvis = (Button) rootView.findViewById(R.id.boutonVoirPlusAvis);
			
			// Init de la couleur sur les boutons
			GradientDrawable bgShape = (GradientDrawable) voirPlusAvis.getBackground();		
			int r = Integer.parseInt(MainActivity.COLORBUTTONSTRING.substring(0, 2), 16);
			int g = Integer.parseInt(MainActivity.COLORBUTTONSTRING.substring(2, 4), 16);
			int b = Integer.parseInt(MainActivity.COLORBUTTONSTRING.substring(4, 6), 16);
			bgShape.setColor(Color.argb(255, r, g, b)); 

			
			voirPlusAvis.setText("Voir tous les avis (" + listeAvis.size() + ")");
			voirPlusAvis.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					Fragment fragmentAvis = null;
					fragmentAvis = new ReviewsFragment(product);
					FragmentManager fragmentManager = getFragmentManager();
					fragmentManager.beginTransaction().replace(R.id.frame_container, fragmentAvis).addToBackStack("tag").commit();
				}
			});	
		} else {
			rootView.findViewById(R.id.listeAvis).setVisibility(View.GONE);
			rootView.findViewById(R.id.ratingBarReview).setVisibility(View.GONE);
			rootView.findViewById(R.id.NombreVotes).setVisibility(View.GONE);
			rootView.findViewById(R.id.textReviewProduct).setVisibility(View.GONE);
			rootView.findViewById(R.id.progresseBarReviews).setVisibility(View.GONE);
			rootView.findViewById(R.id.boutonVoirPlusAvis).setVisibility(View.GONE);
		}
	}

	// Permet de simuler une listView d'avis
	// Car une listView dans un scrollview --> A bannir, utilisation pas terrible sinon
	private void testAjoutAvis() {
		LinearLayout lv = (LinearLayout) rootView.findViewById(R.id.listeAvis);
		
		int nbEnd = nbAvis;
		
		TextView nomClient = null;
		RatingBar note = null;
		TextView dateAvis = null;
		TextView titreAvis = null;
		TextView descAvis = null;
		
		// Si la liste de comporte pas assez d'avis, on change le nombre à afficher avec la taille de la liste
		if(nbAvis > listeAvis.size())
			nbEnd = listeAvis.size();
		
		
		// On ajoute chaque avis séparemment, sans utiliser d'adapter car on peuple un linearlayout et non une listview
		for(int i = 0; i < nbEnd; i++){
			View v = new View(activity);
			LayoutInflater inflater = LayoutInflater.from(activity);
			v = inflater.inflate(R.layout.single_item_review, null, false);
			
			nomClient = (TextView) v.findViewById(R.id.nomClient);
			note = (RatingBar) v.findViewById(R.id.ratingBarAvisClient);
			dateAvis = (TextView) v.findViewById(R.id.dateAvisClient);
			titreAvis = (TextView) v.findViewById(R.id.titreAvisClient);
			descAvis = (TextView) v.findViewById(R.id.descriptionAvisClient);
			
			Avis a = listeAvis.get(i);
			
			DateFormat shortDateFormat = DateFormat.getDateTimeInstance(DateFormat.SHORT,DateFormat.SHORT);
			
			nomClient.setText(a.getClient().getNomClient()  + " " + a.getClient().getPrenomClient());
			note.setRating((float) a.getNote());
			dateAvis.setText(shortDateFormat.format(a.getDate()));
			titreAvis.setText(a.getTitre());
			descAvis.setText(a.getDescription());
			
			// Pour séparer les avis avec un espace
			// TODO Changer la couleur pour un gris ou une fine bare noire peut etre... 
			// Sinon on peut laisser comme ça, ça rend pas mal quand même
			LinearLayout separator = new LinearLayout(activity);
			LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, 30);
			separator.setLayoutParams(params);
			
			// On ajoute l'avis en cours
			lv.addView(v);
			// On ajoute un séparateur pour que tous les avis ne soient pas collés
			lv.addView(separator);
		}
		
	}
}
