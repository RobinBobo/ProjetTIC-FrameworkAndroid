package model;

import java.util.HashMap;
import java.util.Map;


//HERITAGE AVEC HASHMAP AVEC PANIER
public class Catalogue {


	private HashMap <Produit,Integer> catalogue;

	public Catalogue () {	
		catalogue = new HashMap <Produit,Integer> ();
	}		

	public void ajouterProduitDansCatalogue(Produit p, int quant) {

		if (!catalogue.containsValue(p)) {
			catalogue.put(p,quant);				
		}
	}

	public void modifierQuantiteProduitDansCatalogue(Produit p, int quant) {
		if (catalogue.containsKey(p)) {
			catalogue.put(p, quant);				
		}		
	}

	public void supprimerProduitDansCatalogue(Produit p) {
		if (catalogue.containsKey(p)) {
			catalogue.remove(p);
		}
	}

	public int getStock (Produit p) {
		for (Map.Entry<Produit,Integer> c : catalogue.entrySet()) {			
			if (p == c.getKey() ) {
				return c.getValue();				
			}
		}
		return -1;
	}

	public void modifierStock (Produit p, int quant) {
		if (catalogue.containsKey(p)) {
			catalogue.put(p,quant);
		}
	}

	public boolean outOfStock (Produit p)
	{
		for (Map.Entry<Produit,Integer> c : catalogue.entrySet()) {			
			if (p == c.getKey() ) {
				if ( c.getValue() == 0 )
					return true;
			}
		}		
		return false;		
	}
}
