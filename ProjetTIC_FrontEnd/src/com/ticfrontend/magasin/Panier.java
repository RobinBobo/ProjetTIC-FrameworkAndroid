package com.ticfrontend.magasin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import android.util.Log;

public class Panier {

	private int idPanier;
	private List<Produit> listeProduitsPanier;
	private HashMap<Produit, Integer> mapProduitQuantite;
	private double totalPanier;

	public Panier () {
		this.mapProduitQuantite = new HashMap<Produit, Integer>();
		this.totalPanier = 0;
		this.listeProduitsPanier= new ArrayList<Produit>();
	}
	
	public Panier(HashMap<Produit, Integer> map){
		this.mapProduitQuantite = map;
		this.totalPanier = this.calculTotalPanier();
		this.listeProduitsPanier= new ArrayList<Produit>();
	}
	
	public Panier(Panier panierCommande) {
		this.idPanier = panierCommande.idPanier;
		this.mapProduitQuantite = new HashMap<Produit, Integer>();
		
		for(Entry<Produit, Integer> entry : panierCommande.mapProduitQuantite.entrySet()){
			Produit prd = entry.getKey();
			Integer i = entry.getValue();
			this.mapProduitQuantite.put(prd, i);
		}
		
		this.totalPanier = panierCommande.totalPanier;
		this.listeProduitsPanier= panierCommande.listeProduitsPanier;
	}

	//public double getTotalPanier() {return totalPanier;}
	public int getIdPanier() {return idPanier;}
	public List<Produit> getListeProduitsPanier() {	return listeProduitsPanier;}
	public HashMap<Produit, Integer> getMapProduitQuantite() {
		return mapProduitQuantite;
	}
	
	public void setTotalPanier(double totalPanier) {this.totalPanier = totalPanier;}
	public void setMapProduitQuantite(HashMap<Produit, Integer> mapProduitQuantite) {
		this.mapProduitQuantite = mapProduitQuantite;
	}
	public void setIdPanier(int idPanier) {this.idPanier = idPanier;}
	public void setListeProduitsPanier(List<Produit> listeProduitsPanier) {this.listeProduitsPanier = listeProduitsPanier;}

	public void viderPanier(){this.mapProduitQuantite.clear(); this.totalPanier = 0;}
	public boolean estVide() {	
		return (this.mapProduitQuantite.isEmpty());
	}
	public int nombreProduit(){
		int nbTotalProduits = 0;
		for(Entry<Produit, Integer> entry : this.mapProduitQuantite.entrySet()){
			nbTotalProduits += entry.getValue();
		}
		return nbTotalProduits;
	}
	
	public double calculTotalPanier(){
		double total = 0;
		for(Entry<Produit, Integer> entry : this.mapProduitQuantite.entrySet()){
			Produit prd = entry.getKey();
			int quantite = entry.getValue();
			total += prd.getPrixProduit()*quantite;
		}
		return total;
	}
	
	public void ajouterDansPanier(Produit p, int qte){
		// On v�rifie que la produit ne se trouve pas d�j� dans le panier, si oui, on augmente la quantit�
		boolean trouve = false;
		for(Entry<Produit, Integer> entry : this.mapProduitQuantite.entrySet()){
			Produit prd = entry.getKey();
			if(prd.equals(p)){
				trouve = true;
				entry.setValue(entry.getValue()+1);
			}
		}
		
		if(!trouve){
			if(p!=null && qte>0){
				this.listeProduitsPanier.add(p);
				this.mapProduitQuantite.put(p, qte);
				this.setTotalPanier(calculTotalPanier());
			}
			else
				Log.e("PANIER","Erreur ajout dans panier");			
		}
	}
	
	public void supprimerDansPanier(Produit p){
		if(p!=null){
			this.mapProduitQuantite.remove(p);
			this.setTotalPanier(calculTotalPanier());
		}
		
	}	
	
}
