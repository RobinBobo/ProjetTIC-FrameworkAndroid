package com.ticfrontend.magasin;

import java.io.Serializable;

public class Commande implements Serializable {
	private int idCommande;
	private float prixTotalCommande;
	private String typeReglementCommande;

	public Commande(){
		
	}
	
	public float calculPrixTotal(){
		return 0;
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
	
	
}
