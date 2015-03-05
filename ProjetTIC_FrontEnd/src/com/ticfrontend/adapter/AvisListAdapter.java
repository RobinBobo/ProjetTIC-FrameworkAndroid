package com.ticfrontend.adapter;

import java.text.DateFormat;
import java.util.Collections;
import java.util.List;

import com.example.projettic.R;
import com.ticfrontend.magasin.Avis;
import com.ticfrontend.magasin.Produit;
import com.ticfrontend.thread.ThreadPreconditions;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

public class AvisListAdapter extends BaseAdapter {

	private final Context context;	
	private List<Avis> avis = Collections.emptyList();
	private LayoutInflater inflater;

	public AvisListAdapter(Context context, List<Avis> avis) {
		this.context = context;
		this.avis = avis;
		this.inflater = LayoutInflater.from(context);
	}

	public void updateAvis(List<Avis> avis) {
		ThreadPreconditions.checkOnMainThread();
		this.avis = avis;
		notifyDataSetChanged();
	}

	public List<Avis> getAvis() {return avis;}
	@Override
	public int getCount() {	return avis.size();	}
	@Override
	public Object getItem(int arg0) {return avis.get(arg0);	}
	@Override
	public long getItemId(int arg0) {return arg0;}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LinearLayout layoutItem;

		if (convertView == null) {
			layoutItem = (LinearLayout) inflater.inflate(R.layout.single_item_review, parent, false);
		} else {
			layoutItem = (LinearLayout) convertView;
		}
		
		DateFormat shortDateFormat = DateFormat.getDateTimeInstance(DateFormat.SHORT,DateFormat.SHORT);
		Avis a = avis.get(position);
		
		TextView nomClient = (TextView) layoutItem.findViewById(R.id.nomClient);
		RatingBar note = (RatingBar) layoutItem.findViewById(R.id.ratingBarAvisClient);
		TextView dateAvis = (TextView) layoutItem.findViewById(R.id.dateAvisClient);
		TextView titreAvis = (TextView) layoutItem.findViewById(R.id.titreAvisClient);
		TextView descAvis = (TextView) layoutItem.findViewById(R.id.descriptionAvisClient);
		
		nomClient.setText(a.getClient().getPrenomClient() + " " + a.getClient().getNomClient());
		note.setRating((float)a.getNote());
		dateAvis.setText(shortDateFormat.format(avis.get(position).getDate()));
		titreAvis.setText(a.getTitre());
		descAvis.setText(a.getDescription());
		
		return layoutItem;
	}

	@Override
	public void notifyDataSetChanged() {
		//do your sorting here
		super.notifyDataSetChanged();
	}

}
