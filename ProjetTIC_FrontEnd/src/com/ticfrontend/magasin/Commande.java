package com.ticfrontend.magasin;

import java.text.DecimalFormat;
import java.util.List;

public class Commande {
	private static int idStatic = 0;
	private int idCommande = 0;
	private double prixTotalCommande;
	private String typeReglementCommande;

	private List<Produit> produits;
	
	public Commande(double prix, List<Produit> produits){
		this.idStatic++;
		this.idCommande = idStatic;
		this.prixTotalCommande = prix;
		this.produits = produits;
	}
 
	public int getIdCommande() {
		return idCommande;
	}

	public void setIdCommande(int idCommande) {
		this.idCommande = idCommande;
	}

	public String getTypeReglementCommande() {
		return typeReglementCommande;
	}

	public void setTypeReglementCommande(String typeReglementCommande) {
		this.typeReglementCommande = typeReglementCommande;
	}

	public List<Produit> getProduits() {
		return produits;
	}

	public void setProduits(List<Produit> produits) {
		this.produits = produits;
	}

	public double getPrixTotalCommande() {
		return prixTotalCommande;
	}
	
	public String getPrixTotalCommandeToString() {		
		DecimalFormat df = new DecimalFormat() ; 
		df.setMaximumFractionDigits(2) ; //arrondi à 2 chiffres apres la virgules 
		df.setMinimumFractionDigits(2) ; 
		df.setDecimalSeparatorAlwaysShown(true); 
		
		double total = 0;
		
		for(int i = 0; i < produits.size(); i++) 
	           total += produits.get(i).getPrixProduit();
		
		String prix = df.format(total);
		
		return prix;
	}
}
