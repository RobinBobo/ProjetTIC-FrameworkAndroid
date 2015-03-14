package com.ticfrontend.magasin;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Produit implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int idProduit;
	private String nomProduit;
	private double prixProduit;
	private Categorie categorieProduit;
	private String descriptionProduit;
	private String marqueProduit;
	private List<Avis> listeAvisProduit;
	
	private int quantite = 1;
	
	public Produit() {
	}

	public Produit(int id, String nom, double prix, Categorie categorie, String desc, String marque, List<Avis> listeAvis) {
		this.idProduit = id;
		this.nomProduit = nom;
		this.prixProduit = prix;
		this.categorieProduit = categorie;
		this.descriptionProduit = desc;
		this.marqueProduit = marque;
		this.listeAvisProduit = listeAvis;
	}
	
	public Produit(int id, String nom, double prix, Categorie categorie, String desc, String marque, List<Avis> listeAvis, int qte) {
		this.idProduit = id;
		this.nomProduit = nom;
		this.prixProduit = prix;
		this.categorieProduit = categorie;
		this.descriptionProduit = desc;
		this.marqueProduit = marque;
		this.listeAvisProduit = listeAvis;
		this.quantite = qte;
	}
	
	public Produit (Produit p){
		this.idProduit = p.getIdProduit();
		this.nomProduit = p.getNomProduit();
		this.prixProduit = p.getPrixProduit();
		this.categorieProduit = p.getCategorieProduit();
		this.descriptionProduit = p.getDescriptionProduit();
		this.marqueProduit = p.getMarqueProduit();
		this.listeAvisProduit = p.getListeAvisProduit();
		this.quantite = p.getQuantite();
	}
	

	public void setCategorieProduit(Categorie categorieProduit) {this.categorieProduit = categorieProduit;	}
	public void setIdProduit(int idProduit) {this.idProduit = idProduit;}
	public void setNomProduit(String nomProduit) {this.nomProduit = nomProduit;	}
	public void setPrixProduit(double prixProduit) {this.prixProduit = prixProduit;	}
	public void setDescriptionProduit(String descriptionProduit) {	this.descriptionProduit = descriptionProduit;}
	public void setMarqueProduit(String marqueProduit) {this.marqueProduit = marqueProduit;	}
	public void setListeAvisProduit(List<Avis> listeAvisProduit) {this.listeAvisProduit = listeAvisProduit;}
	
	public int getIdProduit() {	return idProduit; }
	public String getNomProduit() {	return nomProduit; }
	public double getPrixProduit() {return prixProduit; }
	public Categorie getCategorieProduit() {return categorieProduit;}
	public String getDescriptionProduit() {	return descriptionProduit;}
	public String getMarqueProduit() {return marqueProduit;	}
	public List<Avis> getListeAvisProduit() {return listeAvisProduit;}
	
	public static List<Produit> getAListOfProducts() {

		List<Produit> listProduct = new ArrayList<Produit>();
		List<Avis> la = new ArrayList<Avis>();
		List<Avis> la2 = new ArrayList<Avis>();
		la = Avis.getAListOfReviews1();
		la2 = Avis.getAListOfReviews2();
		
		Categorie multimedia = new Categorie(1, "Multimédia");
		
		listProduct.add(new Produit(1, "A Produit 1", 19.99, multimedia, "Multimédia (audio, vidéo, jeux vidéo)", "Marque NoName",la));
		listProduct.add(new Produit(2, "B Produit 2", 12.99, multimedia, "Multimédia (audio, vidéo, jeux vidéo)", "Marque NoName",la2));;
		listProduct.add(new Produit(3, "C Produit 3", 15.99, multimedia, "Multimédia (audio, vidéo, jeux vidéo)", "Marque NoName",la));
		listProduct.add(new Produit(4, "E Produit 4", 12.99, multimedia, "Multimédia (audio, vidéo, jeux vidéo)", "Marque NoName",la2));
		listProduct.add(new Produit(5, "G Produit 5", 29.99, multimedia, "Multimédia (audio, vidéo, jeux vidéo)", "Marque NoName",la));
		listProduct.add(new Produit(6, "V Produit 6", 69.99, multimedia, "Multimédia (audio, vidéo, jeux vidéo)", "Marque NoName",la2));
		listProduct.add(new Produit(7, "Z Produit 7", 119.99, multimedia, "Multimédia (audio, vidéo, jeux vidéo)", "Marque NoName",la));
		listProduct.add(new Produit(8, "T Produit 8", 19.99, multimedia, "Multimédia (audio, vidéo, jeux vidéo)", "Marque NoName",la2));
		listProduct.add(new Produit(9, "D Produit 9", 619.99, multimedia, "Multimédia (audio, vidéo, jeux vidéo)", "Marque NoName",la));
		listProduct.add(new Produit(10, "F Produit 10", 2419.99, multimedia, "Multimédia (audio, vidéo, jeux vidéo)", "Marque NoName",la2));
		listProduct.add(new Produit(11, "Q Produit 11", 20.99, multimedia, "Multimédia (audio, vidéo, jeux vidéo)", "Marque NoName",la));
		listProduct.add(new Produit(12, "K Produit 12", 9.99, multimedia, "Multimédia (audio, vidéo, jeux vidéo)", "Marque NoName",la2));
		listProduct.add(new Produit(13, "G Produit 13", 1.99, multimedia, "Multimédia (audio, vidéo, jeux vidéo)", "Marque NoName",la));
		listProduct.add(new Produit(14, "H Produit 14", 2.00, multimedia, "Multimédia (audio, vidéo, jeux vidéo)", "Marque NoName",la));
		
		return listProduct;
	}
	
	
	public static List<Produit> getAListOfProductsBeta(Categorie cat) {

		List<Produit> listProduct = new ArrayList<Produit>();
		List<Avis> la = new ArrayList<Avis>();
		List<Avis> la2 = new ArrayList<Avis>();
		la = Avis.getAListOfReviewsBeta();
		la2 = Avis.getAListOfReviewsBeta2();
		
		//Categorie multimedia = new Categorie(1, "Multimédia");
		
		listProduct.add(new Produit(1, "A Produit 1", 19.99, cat, "Multimédia (audio, vidéo, jeux vidéo)", "Marque NoName",la));
		listProduct.add(new Produit(2, "B Produit 2", 12.99, cat, "Multimédia (audio, vidéo, jeux vidéo)", "Marque NoName",la2));;
		listProduct.add(new Produit(3, "C Produit 3", 15.99, cat, "Multimédia (audio, vidéo, jeux vidéo)", "Marque NoName",la));
		listProduct.add(new Produit(4, "E Produit 4", 12.99, cat, "Multimédia (audio, vidéo, jeux vidéo)", "Marque NoName",la2));
		listProduct.add(new Produit(5, "G Produit 5", 29.99, cat, "Multimédia (audio, vidéo, jeux vidéo)", "Marque NoName",la));
		listProduct.add(new Produit(6, "V Produit 6", 69.99, cat, "Multimédia (audio, vidéo, jeux vidéo)", "Marque NoName",la2));
		listProduct.add(new Produit(7, "Z Produit 7", 119.99, cat, "Multimédia (audio, vidéo, jeux vidéo)", "Marque NoName",la));
		listProduct.add(new Produit(8, "T Produit 8", 19.99, cat, "Multimédia (audio, vidéo, jeux vidéo)", "Marque NoName",la2));
		listProduct.add(new Produit(9, "D Produit 9", 619.99, cat, "Multimédia (audio, vidéo, jeux vidéo)", "Marque NoName",la));
		listProduct.add(new Produit(10, "F Produit 10", 2419.99, cat, "Multimédia (audio, vidéo, jeux vidéo)", "Marque NoName",la2));
		listProduct.add(new Produit(11, "Q Produit 11", 20.99, cat, "Multimédia (audio, vidéo, jeux vidéo)", "Marque NoName",la));
		listProduct.add(new Produit(12, "K Produit 12", 9.99, cat, "Multimédia (audio, vidéo, jeux vidéo)", "Marque NoName",la2));
		listProduct.add(new Produit(13, "G Produit 13", 1.99, cat, "Multimédia (audio, vidéo, jeux vidéo)", "Marque NoName",la));
		listProduct.add(new Produit(14, "H Produit 14", 2.00, cat, "Multimédia (audio, vidéo, jeux vidéo)", "Marque NoName",la));
		
		return listProduct;
	}

	public static HashMap<Produit,Integer> getAHashMapOfProductsNQuantity(){
		HashMap<Produit,Integer> listProduct = new HashMap<Produit,Integer>();
		List<Avis> la = new ArrayList<Avis>();
		List<Avis> la2 = new ArrayList<Avis>();
		la = Avis.getAListOfReviews1();
		la2 = Avis.getAListOfReviews2();
		
		Categorie multimedia = new Categorie(1, "Multimédia");
		
		listProduct.put(new Produit(1, "A Produit 1", 19.99, multimedia, "Multimédia (audio, vidéo, jeux vidéo)", "Marque NoName",la),3);
		listProduct.put(new Produit(2, "B Produit 2", 12.99, multimedia, "Multimédia (audio, vidéo, jeux vidéo)", "Marque NoName",la2),1);;
		listProduct.put(new Produit(3, "C Produit 3", 15.99, multimedia, "Multimédia (audio, vidéo, jeux vidéo)", "Marque NoName",la),2);
		listProduct.put(new Produit(4, "E Produit 4", 12.99, multimedia, "Multimédia (audio, vidéo, jeux vidéo)", "Marque NoName",la2),9);
		listProduct.put(new Produit(5, "G Produit 5", 29.99, multimedia, "Multimédia (audio, vidéo, jeux vidéo)", "Marque NoName",la),1);
		listProduct.put(new Produit(6, "V Produit 6", 69.99, multimedia, "Multimédia (audio, vidéo, jeux vidéo)", "Marque NoName",la2),4);
		listProduct.put(new Produit(7, "Z Produit 7", 119.99, multimedia, "Multimédia (audio, vidéo, jeux vidéo)", "Marque NoName",la),7);
		listProduct.put(new Produit(8, "T Produit 8", 19.99, multimedia, "Multimédia (audio, vidéo, jeux vidéo)", "Marque NoName",la2),1);
		listProduct.put(new Produit(9, "D Produit 9", 619.99, multimedia, "Multimédia (audio, vidéo, jeux vidéo)", "Marque NoName",la),1);
		listProduct.put(new Produit(10, "F Produit 10", 2419.99, multimedia, "Multimédia (audio, vidéo, jeux vidéo)", "Marque NoName",la2),8);
		listProduct.put(new Produit(11, "Q Produit 11", 20.99, multimedia, "Multimédia (audio, vidéo, jeux vidéo)", "Marque NoName",la),1);
		listProduct.put(new Produit(12, "K Produit 12", 9.99, multimedia, "Multimédia (audio, vidéo, jeux vidéo)", "Marque NoName",la2),2);
		listProduct.put(new Produit(13, "G Produit 13", 1.99, multimedia, "Multimédia (audio, vidéo, jeux vidéo)", "Marque NoName",la),3);
		listProduct.put(new Produit(14, "H Produit 14", 2.00, multimedia, "Multimédia (audio, vidéo, jeux vidéo)", "Marque NoName",la),4);
		
		return listProduct;
	}
	
	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}	
}
