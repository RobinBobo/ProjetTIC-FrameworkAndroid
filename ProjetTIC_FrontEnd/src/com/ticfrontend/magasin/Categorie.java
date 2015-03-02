package com.ticfrontend.magasin;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Categorie implements Serializable {
	private int idCategorie;
	private String nomCategorie;
	
	public Categorie () {
		
	}

	public Categorie(int id, String nom) {
		this.idCategorie = id;
		this.nomCategorie = nom;
	}

	public int getIdCategorie() {
		return idCategorie;
	}

	public void setIdCategorie(int idCategorie) {
		this.idCategorie = idCategorie;
	}

	public String getNomCategorie() {
		return nomCategorie;
	}

	public void setNomCategorie(String nomCategorie) {
		this.nomCategorie = nomCategorie;
	}

	public static List<Categorie> getAListOfCategorie() {

		List<Categorie> listCategorie = new ArrayList<Categorie>();
		
		listCategorie.add(new Categorie(1, "Catégorie 1"));
		listCategorie.add(new Categorie(2, "Catégorie 2"));
		listCategorie.add(new Categorie(3, "Catégorie 3"));
		listCategorie.add(new Categorie(4, "Catégorie 4"));
		listCategorie.add(new Categorie(5, "Catégorie 5"));
		listCategorie.add(new Categorie(6, "Catégorie 6"));
		listCategorie.add(new Categorie(7, "Catégorie 7"));
		listCategorie.add(new Categorie(8, "Catégorie 8"));
		listCategorie.add(new Categorie(9, "Catégorie 9"));
		listCategorie.add(new Categorie(10, "Catégorie 10"));
		listCategorie.add(new Categorie(11, "Catégorie 11"));
		listCategorie.add(new Categorie(12, "Catégorie 12"));
		listCategorie.add(new Categorie(13, "Catégorie 13"));
		listCategorie.add(new Categorie(14, "Catégorie 14"));
		
		return listCategorie;
	}
}
