package com.ticfrontend.comparator;

import java.util.Comparator;

import com.ticfrontend.magasin.Produit;

public class ProductNameComparator implements Comparator<Produit>{
	@Override
	public int compare(Produit p1, Produit p2) {
		return p1.getNomProduit().compareTo(p2.getNomProduit());
	}
}
