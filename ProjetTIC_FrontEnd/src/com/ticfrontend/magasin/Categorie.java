package com.ticfrontend.magasin;

import java.io.Serializable;

import com.example.projettic.R;

public class Categorie implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idCategorie;
	private String nomCategorie;
	
	private int iconRessource;
	
	private static int cptImage = 0;
	
	public Categorie () {
		
	}

	public Categorie(int id, String nom) {
		this.idCategorie = id;
		this.nomCategorie = nom;
	}
	
	public Categorie(int id, String nom, int icon) {
		this.idCategorie = id;
		this.nomCategorie = nom;
		this.setIconRessource(icon);
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

	public int getIconRessource() {
		return iconRessource;
	}

	public void setIconRessource(int iconRessource) {
		this.iconRessource = iconRessource;
	}
	
	public static int getNoRandomImage() {
		int res = R.drawable.cat1;
		
		if(cptImage > 8)
			cptImage = 1;
		else
			cptImage++;
			
		switch (cptImage) {
			case 1:
				res = R.drawable.cat1;
				break;
			case 2:
				res = R.drawable.cat2;				
				break;
			case 3:
				res = R.drawable.cat3;
				break;
			case 4:
				res = R.drawable.cat4;
				break;
			case 5:
				res = R.drawable.cat5;
				break;
			case 6:
				res = R.drawable.cat6;
				break;
			case 7:
				res = R.drawable.cat7;
				break;
			case 8:
				res = R.drawable.cat8;
				break;
			default:
				res = R.drawable.cat1;
				break;
		}
		return res;
	}
}
