package com.ticfrontend.filter;
import java.util.ArrayList;
import java.util.List;

import android.widget.Filter;

import com.ticfrontend.adapter.CategorieListAdapter;
import com.ticfrontend.magasin.Categorie;

public class CategoryFilter extends Filter {
	
	private CategorieListAdapter adapterList = null;
	
	public CategoryFilter(CategorieListAdapter productListAdapter) {
		this.adapterList = productListAdapter;
	}

	@Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results = new FilterResults();
        // We implement here the filter logic
        if (constraint == null || constraint.length() == 0) {
            // No filter implemented we return all the list
            results.values = adapterList.getCategories();
            results.count = adapterList.getCategories().size();
        } else {
            // We perform filtering operation
            List<Categorie> newCategorieList = new ArrayList<Categorie>();
            for (Categorie p : adapterList.getCategories()) {
                if (p.getNomCategorie().toUpperCase().startsWith(constraint.toString().toUpperCase()))
                    newCategorieList.add(p);
            }
            results.values = newCategorieList;
            results.count = newCategorieList.size();
        }
        return results;
    }

	@Override
	protected void publishResults(CharSequence constraint, FilterResults results) {
		// Now we have to inform the adapter about the new list filtered
    	adapterList.updateCategories((List<Categorie>) results.values);
    	adapterList.notifyDataSetChanged();
	}
}
