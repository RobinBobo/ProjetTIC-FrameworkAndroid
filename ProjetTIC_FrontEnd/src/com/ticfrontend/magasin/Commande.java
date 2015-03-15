package com.ticfrontend.magasin;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

public class Commande implements Serializable{
	private static int idStatic = 0;
	private int idCommande = 0;
	private double prixTotalCommande;
	private String typeReglementCommande;
	
	// TS modif : on commande un panier et pas une liste de produits (conception)
	private Date dateCommande;
	private Panier panierCommande;
	
	public Commande(Panier p){
		this.idStatic++;
		this.idCommande = idStatic;
		this.setPanierCommande(p);
		this.prixTotalCommande = p.calculTotalPanier();
		this.setDateCommande(new Date());
	}
 
	public int getIdCommande() {return idCommande;}
	public void setIdCommande(int idCommande) {this.idCommande = idCommande;}
	
	public String getTypeReglementCommande() {return typeReglementCommande;}
	public void setTypeReglementCommande(String typeReglementCommande) {this.typeReglementCommande = typeReglementCommande;}

	public Date getDateCommande() {return dateCommande;}
	public void setDateCommande(Date dateCommande) {this.dateCommande = dateCommande;}

	public Panier getPanierCommande() {return panierCommande;}
	public void setPanierCommande(Panier panierCommande) {
		Panier p = new Panier(panierCommande);
		this.panierCommande = p;
		
	}
	
	public double getPrixTotalCommande() {return prixTotalCommande;}
	
	public String getPrixTotalCommandeToString() {		
		DecimalFormat df = new DecimalFormat() ; 
		df.setMaximumFractionDigits(2) ; //arrondi à 2 chiffres apres la virgules 
		df.setMinimumFractionDigits(2) ; 
		df.setDecimalSeparatorAlwaysShown(true); 
		
//		double total = 0;
//		
//		for(int i = 0; i < produits.size(); i++) 
//	           total += produits.get(i).getPrixProduit();
//		
		String prix = df.format(this.prixTotalCommande);
		
		return prix;
	}

	
}
