package com.ticfrontend.magasin;

import java.util.ArrayList;
import java.util.List;


public class Produit {
	private int idProduit;
	private String nomProduit;
	private double prixProduit;
	private Categorie categorieProduit;
	private String descriptionProduit;
	private String marqueProduit;
	
	public Produit(int id, String nom, double prix, Categorie categorie, String desc, String marque) {
		this.idProduit = id;
		this.nomProduit = nom;
		this.prixProduit = prix;
		this.categorieProduit = categorie;
		this.descriptionProduit = desc;
		this.marqueProduit = marque;
	}
	
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
	
	public int getIdProduit() {
		return idProduit;
	}

	public String getNomProduit() {
		return nomProduit;
	}

	public double getPrixProduit() {
		return prixProduit;
	}

	public Categorie getCategorieProduit() {
		return categorieProduit;
	}

	public String getDescriptionProduit() {
		return descriptionProduit;
	}

	public String getMarqueProduit() {
		return marqueProduit;
	}

	public static List<Produit> getAListOfProducts() {

		List<Produit> listProduct = new ArrayList<Produit>();
		
		Categorie multimedia = new Categorie(1, "Multimédia");
		
		listProduct.add(new Produit(1, "A Produit 1", 19.99, multimedia, "Multimédia (audio, vidéo, jeux vidéo)", "Marque NoName"));
		listProduct.add(new Produit(2, "B Produit 2", 12.99, multimedia, "Multimédia (audio, vidéo, jeux vidéo)", "Marque NoName"));;
		listProduct.add(new Produit(3, "C Produit 3", 15.99, multimedia, "Multimédia (audio, vidéo, jeux vidéo)", "Marque NoName"));
		listProduct.add(new Produit(4, "E Produit 4", 12.99, multimedia, "Multimédia (audio, vidéo, jeux vidéo)", "Marque NoName"));
		listProduct.add(new Produit(5, "G Produit 5", 29.99, multimedia, "Multimédia (audio, vidéo, jeux vidéo)", "Marque NoName"));
		listProduct.add(new Produit(6, "V Produit 6", 69.99, multimedia, "Multimédia (audio, vidéo, jeux vidéo)", "Marque NoName"));
		listProduct.add(new Produit(7, "Z Produit 7", 119.99, multimedia, "Multimédia (audio, vidéo, jeux vidéo)", "Marque NoName"));
		listProduct.add(new Produit(8, "T Produit 8", 19.99, multimedia, "Multimédia (audio, vidéo, jeux vidéo)", "Marque NoName"));
		listProduct.add(new Produit(9, "D Produit 9", 619.99, multimedia, "Multimédia (audio, vidéo, jeux vidéo)", "Marque NoName"));
		listProduct.add(new Produit(10, "F Produit 10", 2419.99, multimedia, "Multimédia (audio, vidéo, jeux vidéo)", "Marque NoName"));
		listProduct.add(new Produit(11, "Q Produit 11", 20.99, multimedia, "Multimédia (audio, vidéo, jeux vidéo)", "Marque NoName"));
		listProduct.add(new Produit(12, "K Produit 12", 9.99, multimedia, "Multimédia (audio, vidéo, jeux vidéo)", "Marque NoName"));
		listProduct.add(new Produit(13, "G Produit 13", 1.99, multimedia, "Multimédia (audio, vidéo, jeux vidéo)", "Marque NoName"));
		listProduct.add(new Produit(14, "H Produit 14", 2.00, multimedia, "Multimédia (audio, vidéo, jeux vidéo)", "Marque NoName"));
		
		return listProduct;
	}
	
}
