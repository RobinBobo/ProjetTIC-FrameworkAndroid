package com.ticfrontend.activity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import android.app.Activity;
import android.app.Fragment;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.webkit.WebView.FindListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.projettic.R;
import com.ticfrontend.adapter.AvisListAdapter;
import com.ticfrontend.adapter.ProductListAdapter;
import com.ticfrontend.comparator.ProductPriceComparator;
import com.ticfrontend.comparator.ReviewDateComparator;
import com.ticfrontend.comparator.ReviewNoteComparator;
import com.ticfrontend.magasin.Avis;
import com.ticfrontend.magasin.Produit;

public class ReviewsFragment  extends Fragment {
	private View rootView;
	private Activity activity;
	private List<Avis> listeAvis;
	
	// Produit courant
	private Produit product = null;
	
	// Pour savoir si on trie en ASC ou DESC
	private boolean sortDirectionDate = false;
	private boolean sortDirectionNote = false;
	private ImageView upDate = null;
	private ImageView downDate = null;
	private ImageView upNote = null;
	private ImageView downNote = null;
	private TextView blanc = null;
	
	
	public ReviewsFragment(Produit prd){
		this.product = prd;
	}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        this.rootView = inflater.inflate(R.layout.fragment_reviews, container, false);
        this.activity = this.getActivity();
       // this.product = ProductDetailsFragment.product;
        this.listeAvis = product.getListeAvisProduit();
		
        blanc = (TextView) rootView.findViewById(R.id.textViewBlanc);
		
		upDate = (ImageView) rootView.findViewById(R.id.imgSortDateDesc);
		downDate = (ImageView) rootView.findViewById(R.id.imgSortDateAsc);
		upNote = (ImageView) rootView.findViewById(R.id.imgSortNoteDesc);
		downNote = (ImageView) rootView.findViewById(R.id.imgSortNoteAsc);
		
        init();
        
        return rootView;
    }

	private void init() {
		TextView title = (TextView) rootView.findViewById(R.id.titleReviews);
		title.setText("Tous les avis (" + listeAvis.size() + ")");
		
		Button sortDate = (Button) rootView.findViewById(R.id.buttonSortDate);
		Button sortNote = (Button) rootView.findViewById(R.id.buttonSortNote);

		if(MainActivity.TRIAVIS) {
			sortDate.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					ListView listviewAvis = (ListView) rootView.findViewById(R.id.listviewReviews);	
					AvisListAdapter adapter = (AvisListAdapter) listviewAvis.getAdapter();
					List<Avis> listeAllAvis = adapter.getAvis();
					
					blanc.setVisibility(View.VISIBLE);
					
					if(!sortDirectionDate) {
						downDate.setVisibility(View.VISIBLE);
						upDate.setVisibility(View.GONE);
						downNote.setVisibility(View.GONE);
						upNote.setVisibility(View.GONE);
						Collections.sort(listeAllAvis, new ReviewDateComparator(ReviewDateComparator.ASC));
						sortDirectionDate = true;
					} else {
						upDate.setVisibility(View.VISIBLE);
						downDate.setVisibility(View.GONE);
						downNote.setVisibility(View.GONE);
						upNote.setVisibility(View.GONE);
						Collections.sort(listeAllAvis, new ReviewDateComparator(ReviewDateComparator.DESC));
						sortDirectionDate = false;
					}
					
					adapter.updateAvis(listeAllAvis);
				}
			});
			
			try {
				listeAvis.get(0).setDate(new SimpleDateFormat("dd/MM/yyyy").parse("31/02/2010"));
				listeAvis.get(1).setDate(new SimpleDateFormat("dd/MM/yyyy").parse("31/02/2008"));
				listeAvis.get(2).setDate(new SimpleDateFormat("dd/MM/yyyy").parse("31/02/2006"));
				listeAvis.get(3).setDate(new SimpleDateFormat("dd/MM/yyyy").parse("31/02/2000"));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			sortNote.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					// Trier par note
					ListView list = (ListView) rootView.findViewById(R.id.listviewReviews);
					AvisListAdapter adapter = (AvisListAdapter) list.getAdapter();
					List<Avis> listeAllAvis = adapter.getAvis();
					
					blanc.setVisibility(View.GONE);
					
					if(!sortDirectionNote) {
						downNote.setVisibility(View.VISIBLE);
						upNote.setVisibility(View.GONE);
						downDate.setVisibility(View.GONE);
						upDate.setVisibility(View.GONE);
						Collections.sort(listeAllAvis, new ReviewNoteComparator(ReviewNoteComparator.ASC));
						sortDirectionNote = true;
					} else {
						upNote.setVisibility(View.VISIBLE);
						downNote.setVisibility(View.GONE);
						downDate.setVisibility(View.GONE);
						upDate.setVisibility(View.GONE);
						Collections.sort(listeAllAvis, new ReviewNoteComparator(ReviewNoteComparator.DESC));
						sortDirectionNote = false;
					}
					adapter.updateAvis(listeAllAvis);
				}
			});	
		} else 
			rootView.findViewById(R.id.layoutSort).setVisibility(View.GONE);
			
    	testAjoutAvis();	
	}
	
	private void testAjoutAvis(){
		//Creation et initialisation de l'Adapter pour les catégories
		AvisListAdapter ala = new AvisListAdapter(activity, listeAvis);
		//Récupération du composant ListView
		ListView listviewAvis = (ListView) rootView.findViewById(R.id.listviewReviews);	
		//Initialisation de la liste avec les données
		listviewAvis.setAdapter(ala);
	}
}

