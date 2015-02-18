package com.ticfrontend.magasin;

import java.util.List;

public class Catalogue {

	private int idCatalogue;
	private List<Produit> listeProduitsCatalogue;
	
	public Catalogue(){
		
	}
	
	public String consulterCatalogue(){
		return null;
	}
	
	public int getIdCatalogue() {
		return idCatalogue;
	}
	
	public void setIdCatalogue(int idCatalogue) {
		this.idCatalogue = idCatalogue;
	}
	
	public List<Produit> getListeProduitsCatalogue() {
		return listeProduitsCatalogue;
	}
	
	public void setListeProduitsCatalogue(List<Produit> listeProduitsCatalogue) {
		this.listeProduitsCatalogue = listeProduitsCatalogue;
	}
	
}
