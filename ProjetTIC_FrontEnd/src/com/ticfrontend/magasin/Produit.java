package com.ticfrontend.magasin;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.projettic.R;

public class Produit implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int idProduit;
	private String nomProduit;
	private double prixProduit;
	private Categorie categorieProduit;
	private String descriptionProduit;
	private String marqueProduit;
	private List<Avis> listeAvisProduit;
	
	private int iconRessource;
	
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
	
	public Produit(int id, String nom, double prix, Categorie categorie, String desc, String marque, List<Avis> listeAvis, int qte, int icon) {
		this.idProduit = id;
		this.nomProduit = nom;
		this.prixProduit = prix;
		this.categorieProduit = categorie;
		this.descriptionProduit = desc;
		this.marqueProduit = marque;
		this.listeAvisProduit = listeAvis;
		this.quantite = qte;
		this.iconRessource = icon;
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
		this.iconRessource = p.getIconRessource();
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
	
	public static List<Produit> getAListOfProducts(Categorie cat) {

		List<Produit> listProduct = new ArrayList<Produit>();
		List<Avis> la = new ArrayList<Avis>();
		List<Avis> la2 = new ArrayList<Avis>();
		la = Avis.getAListOfReviewsSmall();
		la2 = Avis.getAListOfReviewsLong();
		
		//Categorie multimedia = new Categorie(1, "Multimédia");
		
		listProduct.add(new Produit(1, "A Produit 1", 19.99, cat, "Multimédia (audio, vidéo, jeux vidéo)", "Marque NoName",la,10));
		listProduct.add(new Produit(2, "B Produit 2", 12.99, cat, "Multimédia (audio, vidéo, jeux vidéo)", "Marque NoName",la2, 10));;
		listProduct.add(new Produit(3, "C Produit 3", 15.99, cat, "Multimédia (audio, vidéo, jeux vidéo)", "Marque NoName",la, 10));
		listProduct.add(new Produit(4, "E Produit 4", 12.99, cat, "Multimédia (audio, vidéo, jeux vidéo)", "Marque NoName",la2, 10));
		listProduct.add(new Produit(5, "G Produit 5", 29.99, cat, "Multimédia (audio, vidéo, jeux vidéo)", "Marque NoName",la, 10));
		listProduct.add(new Produit(6, "V Produit 6", 69.99, cat, "Multimédia (audio, vidéo, jeux vidéo)", "Marque NoName",la2, 10));
		listProduct.add(new Produit(7, "Z Produit 7", 119.99, cat, "Multimédia (audio, vidéo, jeux vidéo)", "Marque NoName",la, 10));
		listProduct.add(new Produit(8, "T Produit 8", 19.99, cat, "Multimédia (audio, vidéo, jeux vidéo)", "Marque NoName",la2, 10));
		listProduct.add(new Produit(9, "D Produit 9", 619.99, cat, "Multimédia (audio, vidéo, jeux vidéo)", "Marque NoName",la, 10));
		listProduct.add(new Produit(10, "F Produit 10", 2419.99, cat, "Multimédia (audio, vidéo, jeux vidéo)", "Marque NoName",la2, 10));
		listProduct.add(new Produit(11, "Q Produit 11", 20.99, cat, "Multimédia (audio, vidéo, jeux vidéo)", "Marque NoName",la, 10));
		listProduct.add(new Produit(12, "K Produit 12", 9.99, cat, "Multimédia (audio, vidéo, jeux vidéo)", "Marque NoName",la2, 10));
		listProduct.add(new Produit(13, "G Produit 13", 1.99, cat, "Multimédia (audio, vidéo, jeux vidéo)", "Marque NoName",la, 10));
		listProduct.add(new Produit(14, "H Produit 14", 2.00, cat, "Multimédia (audio, vidéo, jeux vidéo)", "Marque NoName",la, 10));
		
		return listProduct;
	}

	public static HashMap<Produit,Integer> getAHashMapOfProductsNQuantity(){
		HashMap<Produit,Integer> listProduct = new HashMap<Produit,Integer>();
		List<Avis> la = new ArrayList<Avis>();
		List<Avis> la2 = new ArrayList<Avis>();
		la = Avis.getAListOfReviewsLong();
		la2 = Avis.getAListOfReviewsSmall();
		
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

	public int getIconRessource() {
		return iconRessource;
	}

	public void setIconRessource(int ressouceIcon) {
		this.iconRessource = ressouceIcon;
	}	
	
	public static int getRandomImage() {
		int random = 1 + (int)(Math.random() * ((20 - 1) + 1));
		int res = R.drawable.prd1;
		switch (random) {
			case 1:
				res = R.drawable.prd1;
				break;
			case 2:
				res = R.drawable.prd2;				
				break;
			case 3:
				res = R.drawable.prd3;
				break;
			case 4:
				res = R.drawable.prd4;
				break;
			case 5:
				res = R.drawable.prd5;
				break;
			case 6:
				res = R.drawable.prd6;
				break;
			case 7:
				res = R.drawable.prd7;
				break;
			case 8:
				res = R.drawable.prd8;
				break;
			case 9:
				res = R.drawable.prd9;
				break;
			case 10:
				res = R.drawable.prd10;
				break;
			case 11:
				res = R.drawable.prd11;
				break;
			case 12:
				res = R.drawable.prd12;
				break;
			case 13:
				res = R.drawable.prd13;
				break;
			case 14:
				res = R.drawable.prd14;
				break;
			case 15:
				res = R.drawable.prd15;
				break;
			case 16:
				res = R.drawable.prd16;
				break;
			case 17:
				res = R.drawable.prd17;
				break;
			case 18:
				res = R.drawable.prd18;
				break;
			case 19:
				res = R.drawable.prd19;
				break;
			case 20:
				res = R.drawable.prd20;
				break;
			case 21:
				res = R.drawable.prd21;
				break;
			case 22:
				res = R.drawable.prd22;
				break;
			case 23:
				res = R.drawable.prd23;
				break;
			case 24:
				res = R.drawable.prd24;
				break;
			case 25:
				res = R.drawable.prd25;
				break;
			default:
				res = R.drawable.prd1;
				break;
		}
		return res;
	}
}
