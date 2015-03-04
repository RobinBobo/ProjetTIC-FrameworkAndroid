package com.ticfrontend.adapter;

import java.util.Collections;
import java.util.List;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.projettic.R;
import com.ticfrontend.magasin.Commande;
import com.ticfrontend.thread.ThreadPreconditions;

public class CommandeListAdapter extends BaseAdapter{

	private List<Commande> commandes = Collections.emptyList();
	
	private LayoutInflater inflater;
	
	public CommandeListAdapter(Activity activity, List<Commande> commandes) {
        this.commandes = commandes;
        this.inflater = LayoutInflater.from(activity);
    }
	
	public void updateProduct(List<Commande> commandes) {
		ThreadPreconditions.checkOnMainThread();
        this.commandes = commandes;
        notifyDataSetChanged();
    }
	
	public List<Commande> getCommandes() {
		return commandes;
	}

	@Override
	public int getCount() {
		return commandes.size();
	}

	@Override
	public Object getItem(int position) {
		return commandes.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		LinearLayout layoutItem;
		
	    if (convertView == null) {
	    	layoutItem = (LinearLayout) inflater.inflate(R.layout.single_item_command, parent, false);
	    } else {
	    	layoutItem = (LinearLayout) convertView;
	    }
	    
	    
	    TextView title = (TextView) layoutItem.findViewById(R.id.titleCommande);
	    TextView price = (TextView) layoutItem.findViewById(R.id.priceCommande);
	    
	    title.setText("Commande n°" + String.valueOf(commandes.get(position).getIdCommande()));
	    price.setText(commandes.get(position).getPrixTotalCommandeToString() + " €");
	    
	    return layoutItem;
	}
	
	@Override
	public void notifyDataSetChanged() {
	    //do your sorting here
	    super.notifyDataSetChanged();
	}
}
