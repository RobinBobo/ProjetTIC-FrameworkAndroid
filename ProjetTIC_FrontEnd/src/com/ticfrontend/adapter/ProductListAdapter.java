package com.ticfrontend.adapter;

import java.util.Collections;
import java.util.List;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.projettic.R;
import com.ticfrontend.filter.ProductFilter;
import com.ticfrontend.magasin.Produit;
import com.ticfrontend.thread.ThreadPreconditions;

public class ProductListAdapter extends BaseAdapter implements Filterable {

	private final Activity activity;
	
	private List<Produit> products = Collections.emptyList();
	
	private LayoutInflater inflater;
	
	private ProductFilter produitFilter;
	
	public ProductListAdapter(Activity activity, List<Produit> products) {
        this.activity = activity;
        this.products = products;
        this.inflater = LayoutInflater.from(activity);
    }
	
	public void updateProduct(List<Produit> products) {
		ThreadPreconditions.checkOnMainThread();
        this.products = products;
        notifyDataSetChanged();
    }
	
	public void updateProductWithoutNotify(List<Produit> products) {
		ThreadPreconditions.checkOnMainThread();
        this.products = products;
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
	    	layoutItem = (LinearLayout) inflater.inflate(R.layout.single_item_product, parent, false);
	    } else {
	    	layoutItem = (LinearLayout) convertView;
	    }
	    
	    TextView title = (TextView) layoutItem.findViewById(R.id.titleProduct);
	    TextView desc = (TextView) layoutItem.findViewById(R.id.descProduct);
	    TextView price = (TextView) layoutItem.findViewById(R.id.priceProduct);
	    
	    title.setText(products.get(position).getNomProduit());
	    desc.setText(products.get(position).getDescriptionProduit());
	    price.setText(String.valueOf(products.get(position).getPrixProduit()) + " €");
	    
	    // On met une image aléatoire sur le ImageView du produit
	    ImageView icon = (ImageView) layoutItem.findViewById(R.id.icon);
	    icon.setImageResource(products.get(position).getIconRessource());
	    
	    return layoutItem;
	}

	@Override
	public Filter getFilter() {
	    if(produitFilter == null) 
	        produitFilter = new ProductFilter(products, this);
	    return produitFilter;
	}
}
