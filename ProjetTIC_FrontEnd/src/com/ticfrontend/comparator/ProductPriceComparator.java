package com.ticfrontend.comparator;
import java.util.Comparator;

import com.ticfrontend.magasin.Produit;

public class ProductPriceComparator implements Comparator<Produit> {
	@Override
	public int compare(Produit p1, Produit p2) {
		int res = (int) ((int) p1.getPrixProduit() - p2.getPrixProduit());
		return res;
	}   
}