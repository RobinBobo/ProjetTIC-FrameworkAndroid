package com.ticfrontend.comparator;
import java.util.Comparator;

import com.ticfrontend.magasin.Produit;

public class ProductPriceComparator implements Comparator<Produit> {
	
	public static String DESC = "DESC";
	public static String ASC = "ASC";
	private static String SENS = null;
	
	public ProductPriceComparator(String sens){
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
			return (int) ((int) p1.getPrixProduit() - p2.getPrixProduit());
		else if(SENS.equals(DESC))
			return (int) ((int) p2.getPrixProduit() - p1.getPrixProduit());
		return 0;
	}   
}