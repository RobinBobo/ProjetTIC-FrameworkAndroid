package com.ticfrontend.adapter;

import java.text.DecimalFormat;
import java.util.Collections;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.projettic.R;
import com.ticfrontend.magasin.Produit;
import com.ticfrontend.thread.ThreadPreconditions;

public class CartProductListAdapter extends BaseAdapter {
	
	private List<Produit> products = Collections.emptyList();
	
	private LayoutInflater inflater;
	
	public CartProductListAdapter(Context context, List<Produit> products) {
        this.products = products;
        this.inflater = LayoutInflater.from(context);
    }
	
	public void updateProduct(List<Produit> products) {
		ThreadPreconditions.checkOnMainThread();
        this.products = products;
        notifyDataSetChanged();
    }
	
	public List<Produit> getProducts() {
		return products;
	}

	@Override
	public int getCount() {
		return products.size();
	}

	@Override
	public Object getItem(int position) {
		return products.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		LinearLayout layoutItem;
		
	    if (convertView == null) {
	    	layoutItem = (LinearLayout) inflater.inflate(R.layout.single_item_cart_product, parent, false);
	    } else {
	    	layoutItem = (LinearLayout) convertView;
	    }
	    
	    TextView title = (TextView) layoutItem.findViewById(R.id.titleProduct);
	    TextView desc = (TextView) layoutItem.findViewById(R.id.descProduct);
	    TextView price = (TextView) layoutItem.findViewById(R.id.priceProduct);
	    TextView qte = (TextView) layoutItem.findViewById(R.id.qteProduct);
	    
	    title.setText(products.get(position).getNomProduit());
	    desc.setText(products.get(position).getDescriptionProduit());
	    price.setText(String.valueOf(products.get(position).getPrixProduit()) + " €");
	    qte.setText("Quantité : " + String.valueOf(products.get(position).getQuantite()));
	    
	    //prixTotal += products.get(position).getPrixProduit();
	    
	    return layoutItem;
	}
	
	@Override
	public void notifyDataSetChanged() {
	    //do your sorting here
	    super.notifyDataSetChanged();
	}
	
	public double getPrixTotal() {
		double total = 0;
		
		for(int i = 0; i < products.size(); i++) 
           total += products.get(i).getPrixProduit();
		
		return total;
	}
	
	public String getPrixTotalToString() {
		DecimalFormat df = new DecimalFormat() ; 
		df.setMaximumFractionDigits(2) ; //arrondi à 2 chiffres apres la virgules 
		df.setMinimumFractionDigits(2) ; 
		df.setDecimalSeparatorAlwaysShown(true); 
		double total = 0;
		
		for(int i = 0; i < products.size(); i++) 
           total += products.get(i).getPrixProduit();
		
		String prix = df.format(total);
		
		return prix;		
	}
}
