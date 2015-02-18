package com.ticfrontend.magasin;

import java.util.List;

public class Panier {

	private int idPanier;
	private List<Produit> listeProduitsPanier;
	
	public Panier () {		
	}

	public int getIdPanier() {
		return idPanier;
	}

	public void setIdPanier(int idPanier) {
		this.idPanier = idPanier;
	}

	public List<Produit> getListeProduitsPanier() {
		return listeProduitsPanier;
	}

	public void setListeProduitsPanier(List<Produit> listeProduitsPanier) {
		this.listeProduitsPanier = listeProduitsPanier;
	}

}
