package com.ticfrontend.comparator;

import java.util.Comparator;

import com.ticfrontend.magasin.Produit;

public class ProductNameComparator implements Comparator<Produit>{
	
	public static String DESC = "DESC";
	public static String ASC = "ASC";
	private static String SENS = null;
	
	public ProductNameComparator(String sens){
		if(sens.equals(DESC))
			SENS = DESC;
		else if(sens.equals(ASC))
			SENS = ASC;
		else 
			SENS = ASC;
	}
	
	@Override
	public int compare(Produit p1, Produit p2) {
		if(SENS == null)
			SENS = ASC;
		
		if(SENS.equals(ASC))
			return p1.getNomProduit().compareTo(p2.getNomProduit());
		else if(SENS.equals(DESC))
			return p2.getNomProduit().compareTo(p1.getNomProduit());
		return 0;
	}
}
