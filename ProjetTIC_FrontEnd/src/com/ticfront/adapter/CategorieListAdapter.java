package com.ticfront.adapter;

import java.util.Collections;
import java.util.List;
import java.util.zip.Inflater;

import thread.ThreadPreconditions;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.projettic.R;
import com.ticfrontend.magasin.Categorie;

public class CategorieListAdapter extends BaseAdapter{

	private final Context context;
	
	private List<Categorie> categories = Collections.emptyList();
	
	private LayoutInflater inflater;
	
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
	    
	    TextView title = (TextView) layoutItem.findViewById(R.id.titleCategorie);
	    title.setText(categories.get(position).getNomCategorie());
	    
	    return layoutItem;
	}
}
