package com.ticfrontend.magasin;


public class Produit {
	private int idProduit;
	private String nomProduit;
	private double prixProduit;
	private Categorie categorieProduit;
	private String descriptionProduit;
	private String marqueProduit;
	
	public Produit() {}
	
	public void setCategorieProduit(Categorie categorieProduit) {
		this.categorieProduit = categorieProduit;
	}

	public void setIdProduit(int idProduit) {
		this.idProduit = idProduit;
	}

	public void setNomProduit(String nomProduit) {
		this.nomProduit = nomProduit;
	}

	public void setPrixProduit(double prixProduit) {
		this.prixProduit = prixProduit;
	}

	public void setDescriptionProduit(String descriptionProduit) {
		this.descriptionProduit = descriptionProduit;
	}

	public void setMarqueProduit(String marqueProduit) {
		this.marqueProduit = marqueProduit;
	}
}
