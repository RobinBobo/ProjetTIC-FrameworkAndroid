package com.ticfrontend.filter;
import java.util.ArrayList;
import java.util.List;

import android.widget.Filter;

import com.ticfrontend.adapter.ProductListAdapter;
import com.ticfrontend.magasin.Produit;


public class ProductFilter extends Filter {
	
	private List<Produit> productsList = null;

	private ProductListAdapter adapterList = null;
	
	public ProductFilter(List<Produit> produits) {
		this.productsList = produits;
	}
	
	public ProductFilter(List<Produit> produits, ProductListAdapter productListAdapter) {
		this.adapterList = productListAdapter;
		this.productsList = productListAdapter.getProducts();
	}

	@Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results = new FilterResults();
        // We implement here the filter logic
        if (constraint == null || constraint.length() == 0) {
            // No filter implemented we return all the list
            results.values = adapterList.getProducts();
            results.count = adapterList.getProducts().size();
        } else {
            // We perform filtering operation
            List<Produit> nProduitList = new ArrayList<Produit>();
            for (Produit p : productsList) {
                if (p.getNomProduit().toUpperCase().startsWith(constraint.toString().toUpperCase()))
                    nProduitList.add(p);
            }
            results.values = nProduitList;
            results.count = nProduitList.size();
        }
        return results;
    }

	@Override
	protected void publishResults(CharSequence constraint, FilterResults results) {
		// Now we have to inform the adapter about the new list filtered
		adapterList.updateProduct((List<Produit>) results.values);
		adapterList.notifyDataSetChanged();			
	}
}
