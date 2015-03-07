package com.ticfrontend.magasin;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.ticfrontend.activity.MainActivity;

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
		
		listCategorie.add(new Categorie(1, "Cat�gorie 1"));
		listCategorie.add(new Categorie(2, "Cat�gorie 2"));
		listCategorie.add(new Categorie(3, "Cat�gorie 3"));
		listCategorie.add(new Categorie(4, "Cat�gorie 4"));
		listCategorie.add(new Categorie(5, "Cat�gorie 5"));
		listCategorie.add(new Categorie(6, "Cat�gorie 6"));
		listCategorie.add(new Categorie(7, "Cat�gorie 7"));
		listCategorie.add(new Categorie(8, "Cat�gorie 8"));
		listCategorie.add(new Categorie(9, "Cat�gorie 9"));
		listCategorie.add(new Categorie(10, "Cat�gorie 10"));
		listCategorie.add(new Categorie(11, "Cat�gorie 11"));
		listCategorie.add(new Categorie(12, "Cat�gorie 12"));
		listCategorie.add(new Categorie(13, "Cat�gorie 13"));
		listCategorie.add(new Categorie(14, "Cat�gorie 14"));
		
		return listCategorie;
	}
	
	public static List<Categorie> getAListOfCategorieBeta() {

		List<Categorie> listCategorie = new ArrayList<Categorie>();
		
		if(MainActivity.LISTPRODUITBETA.size() > 0)
			listCategorie.add(MainActivity.LISTPRODUITBETA.get(0).getCategorieProduit());
		
		for(int i = 0; i < MainActivity.LISTPRODUITBETA.size(); i++){
			for(int j = 0; j < listCategorie.size(); j++)
				if(!listCategorie.get(j).getNomCategorie().equalsIgnoreCase(MainActivity.LISTPRODUITBETA.get(j).getCategorieProduit().getNomCategorie()))
					listCategorie.add(MainActivity.LISTPRODUITBETA.get(i).getCategorieProduit());
		}
		
		listCategorie.add(new Categorie(1, "Livres"));
		listCategorie.add(new Categorie(2, "Videos"));
		listCategorie.add(new Categorie(3, "Musique"));
		listCategorie.add(new Categorie(4, "Jeux vid�o"));
		listCategorie.add(new Categorie(5, "Multim�dia"));
		listCategorie.add(new Categorie(6, "Divers"));
		
		return listCategorie;
	}
}
