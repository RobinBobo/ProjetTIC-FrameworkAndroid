package com.ticfrontend.adapter;

import java.util.Collections;
import java.util.List;
import java.util.zip.Inflater;

import android.content.Context;
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
import com.ticfrontend.filter.CategoryFilter;
import com.ticfrontend.filter.ProductFilter;
import com.ticfrontend.magasin.Categorie;
import com.ticfrontend.thread.ThreadPreconditions;

public class CategorieListAdapter extends BaseAdapter implements Filterable {

	private final Context context;
	
	private List<Categorie> categories = Collections.emptyList();
	
	private LayoutInflater inflater;
	
	private CategoryFilter categorieFilter;
	
	public CategorieListAdapter(Context context, List<Categorie> categories) {
        this.context = context;
        this.categories = categories;
        this.inflater = LayoutInflater.from(context);
    }
	
	public void updateCategories(List<Categorie> categories) {
		ThreadPreconditions.checkOnMainThread();
        this.categories = categories;
        notifyDataSetChanged();
    }
	
	public void updateCategoriesWithoutNotify(List<Categorie> categories) {
		ThreadPreconditions.checkOnMainThread();
        this.categories = categories;
    }
	
	public List<Categorie> getCategories(){
		return categories;
	}
	
	@Override
	public int getCount() {
		return categories.size();
	}

	@Override
	public Object getItem(int position) {
		return categories.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		LinearLayout layoutItem;
		
	    if (convertView == null) {
	    	layoutItem = (LinearLayout) inflater.inflate(R.layout.single_item_category, parent, false);
	    } else {
	    	layoutItem = (LinearLayout) convertView;
	    }
	    
	    // On met une image aléatoire sur le ImageView de la catégorie
	    ImageView icon = (ImageView) layoutItem.findViewById(R.id.icon);
	    icon.setImageResource(categories.get(position).getIconRessource());
	    
	    TextView title = (TextView) layoutItem.findViewById(R.id.titleCategorie);
	    title.setText(categories.get(position).getNomCategorie());
	    
	    return layoutItem;
	}
	
	@Override
	public Filter getFilter() {
	    if(categorieFilter == null) 
	        categorieFilter = new CategoryFilter(this);
	    return categorieFilter;
	}
}
