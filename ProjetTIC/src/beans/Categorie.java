package beans;

import java.io.Serializable;
import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;

import model.DAOFactory;

public class Categorie implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int i_idCategorie = 0;
	private String s_nomCategorie;
	private ArrayList<Produit> o_produitsCategorie = new ArrayList<Produit>();

	// _____________________________________
	// Constructeurs :
	// _______________________________________________________________

	public Categorie() {
	}

	public Categorie(int theId, String theNom) {
		this.i_idCategorie = theId;
		this.s_nomCategorie = theNom;
	}

	public Categorie(int theId, String theNom, ArrayList<Produit> theProduits) {
		this.i_idCategorie = theId;
		this.s_nomCategorie = theNom;
		this.o_produitsCategorie = theProduits;
	}

	// _____________________________________
	// Méthodes :
	// _______________________________________________________________

	// Ajout d'un produit dans la categorie, avec update de la catégorie et du
	// produit (via setCategorieProduit())
	public void ajouterProduitCategorie(Produit theProduit) {
		if (!this.o_produitsCategorie.contains(theProduit)) {
			this.o_produitsCategorie.add(theProduit);
			theProduit.setCategorieProduit(this.getNomCategorie());
			//DAOFactory.getCategorieDAO().update(this);
		} else {
			// TODO : generate error "Already exists"
		}
	}

	// Delete d'un produit dans la catégorie
	public void supprimerProduitCategorie(Produit theProduit) {
		if (this.o_produitsCategorie.contains(theProduit)) {
			this.o_produitsCategorie.remove(theProduit);
			//DAOFactory.getCategorieDAO().update(this);
		} else {
			// TODO : generate error "Not found"
		}
	}

	// _____________________________________
	// Getters & Setters :
	// _______________________________________________________________

	public int getIdCategorie() {
		return i_idCategorie;
	}

	public void setIdCategorie(int theId) {
		this.i_idCategorie = theId;
		//DAOFactory.getCategorieDAO().update(this);
	}

	public String getNomCategorie() {
		return s_nomCategorie;
	}

	public void setNomCategorie(String theNom) {
		this.s_nomCategorie = theNom;
		//DAOFactory.getCategorieDAO().update(this);
	}

	public ArrayList<Produit> getMesProduits() {
		return this.o_produitsCategorie;
	}

}
