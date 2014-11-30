package model;

import java.util.HashMap;
import java.util.Map;

public class Panier {	

	HashMap <Produit,Integer> panier;
	
	private boolean panierValid;
	
	public Panier() {
		panier = new HashMap <Produit,Integer> ();
		panierValid = false;
		if (!panierValid) {			
			panierValid = false;
		}
	}
	
	public boolean getPanierValid () {
		return this.panierValid;
	}
	
	public void setPanierValid (boolean valid) {
		this.panierValid =valid;
	}

	public void ajouterProduitDansPanier(Produit p, int quant, Catalogue c) {
		if (!panierValid) {
			if (!panier.containsValue(p)) {
				if (!c.outOfStock(p))
					panier.put(p,quant);
				else
					System.out.println("\nProduit : " +  p.getDescription() + " en rupture de stock !");
			}
		}
	}

	public void modifierQuantiteProduitDansPanier(Produit p, int quant) {
		if (!panierValid) {
			if (panier.containsKey(p)) {
				panier.put(p, quant);				
			}
		}
	}

	public void supprimerProduitDansPanier(Produit p) {
		if (!panierValid) {
			if (panier.containsKey(p)) {
				panier.remove(p);
			}
		}
	}

	public double calculerPrixPanier() {
		double res = 0;
		for (Map.Entry<Produit,Integer> p : panier.entrySet()) {			
			res += p.getValue()*p.getKey().getPrix();
		}
		return res;
	}

	public void validerPanier() {
		panierValid = true;
	}
}
