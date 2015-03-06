package beans;

import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;

import model.DAOFactory;

public class Categorie implements Parcelable{
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
	// M�thodes :
	// _______________________________________________________________

	// Ajout d'un produit dans la categorie, avec update de la cat�gorie et du
	// produit (via setCategorieProduit())
	public void ajouterProduitCategorie(Produit theProduit) {
		if (!this.o_produitsCategorie.contains(theProduit)) {
			this.o_produitsCategorie.add(theProduit);
			theProduit.setCategorieProduit(this);
			//DAOFactory.getCategorieDAO().update(this);
		} else {
			// TODO : generate error "Already exists"
		}
	}

	// Delete d'un produit dans la cat�gorie
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

	//
	// Cr�ation d'objets pour le passage entre activit�s
	//

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeLong(i_idCategorie);
		dest.writeString(s_nomCategorie);
	}

	public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
		@Override
		public Categorie createFromParcel(Parcel source) {
			return new Categorie(source);
		}

		@Override
		public Object[] newArray(int size) {
			return null;
		}
	};

	public Categorie(Parcel in) {
		this.i_idCategorie = (int) in.readLong();
		this.s_nomCategorie = in.readString();
	}

	public void getFromParcel(Parcel in) {
		this.setIdCategorie(in.readInt());
		this.setNomCategorie(in.readString());
	}

}
