package model;

public class Produit {
	
	private String description;
	private double prix;
	
	public Produit(String description , double prix) {
		this.description = description;
		this.prix = prix;       
	}
	
	public double getPrix() {
		return this.prix;
	}
	
	public String getDescription() {
		return this.description;
	}
}