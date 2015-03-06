package beans;

import java.util.Observable;

import android.os.Parcel;
import android.os.Parcelable;

import model.DAOFactory;

public class Produit extends Observable implements Parcelable{

	private int i_idProduit = 0;
	private String s_nomProduit;
	private double d_prixProduit;
	private String s_descriptionProduit;
	private Categorie o_categorieProduit;
	private String s_marqueProduit;
	private int i_stockProduit;
	
	//_____________________________________
   //Constructeurs :
  //_______________________________________________________________
	
	public Produit(){}
	
	public Produit(int theId, String theNom, double thePrix,
			String theDescription, Categorie theCategorie,
			String theMarque, int theStock) {
		this.i_idProduit = theId;
		this.s_nomProduit = theNom;
		this.d_prixProduit = thePrix;
		this.s_descriptionProduit = theDescription;
		this.o_categorieProduit = theCategorie;
		this.s_marqueProduit = theMarque;
		this.i_stockProduit = theStock;
	}
	
	public Produit(int theId, String theNom, double thePrix, 
			String theDescription, String theMarque, int theStock){
		this.i_idProduit = theId;
		this.s_nomProduit = theNom;
		this.d_prixProduit = thePrix;
		this.s_descriptionProduit = theDescription;
		this.s_marqueProduit = theMarque;
		this.i_stockProduit = theStock;
	}
	
	
	  //_____________________________________
	 //Méthodes :
	//_______________________________________________________________
	
	public void retirerCategorie(){
		this.setCategorieProduit(null);
		DAOFactory.getProduitDAO().update(this);
	}
	
	public void achatProduit(int theQuantite){
		i_stockProduit = i_stockProduit - theQuantite;
		DAOFactory.getProduitDAO().update(this);
	}
	
	//Notifie la liste des clients
	public void supprimerProduit(){
		DAOFactory.getProduitDAO().delete(this);
		setChanged();
		notifyObservers();
	}
	
	
	  //_____________________________________
	 //Getters & Setters :
	//_______________________________________________________________
	
	public int getStockProduit() {
		return i_stockProduit;
	}
	
	public void setStockProduit(int theStock) {
		this.i_idProduit = theStock;
		DAOFactory.getProduitDAO().update(this);	
	}
	
	public int getIdProduit() {
		return i_idProduit;
	}

	public void setIdProduit(int theId) {
		this.i_idProduit = theId;
		DAOFactory.getProduitDAO().update(this);	
	}

	public String getNomProduit() {
		return s_nomProduit;
	}

	public void setNomProduit(String theNom) {
		this.s_nomProduit = theNom;
		DAOFactory.getProduitDAO().update(this);	
	}

	public double getPrixProduit() {
		return d_prixProduit;
	}

	public void setPrixProduit(double thePrix) {
		this.d_prixProduit = thePrix;
		DAOFactory.getProduitDAO().update(this);	
	}

	public String getDescriptionProduit() {
		return s_descriptionProduit;
	}

	public void setDescriptionProduit(String theDescription) {
		this.s_descriptionProduit = theDescription;
		DAOFactory.getProduitDAO().update(this);	
	}

	public Categorie getCategorieProduit() {
		return o_categorieProduit;
	}

	public void setCategorieProduit(Categorie theCategorie) {
		this.o_categorieProduit = theCategorie;
		DAOFactory.getProduitDAO().update(this);	
	}

	public String getMarqueProduit() {
		return s_marqueProduit;
	}

	public void setMarqueProduit(String theMarque) {
		this.s_marqueProduit = theMarque;
		DAOFactory.getProduitDAO().update(this);	
	}

	//
	// Création d'objets pour le passage entre activités
	//

	@Override
	public int describeContents() {
		return 0;
	}

	@Override	
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(i_idProduit);
		dest.writeString(s_nomProduit);
		dest.writeDouble(d_prixProduit);
		dest.writeString(s_descriptionProduit);
		dest.writeValue(o_categorieProduit);
		dest.writeString(s_marqueProduit);
		dest.writeInt(i_stockProduit);
		
	}

	public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
		@Override
		public Produit createFromParcel(Parcel source) {
			return new Produit(source);
		}

		@Override
		public Object[] newArray(int size) {
			return null;
		}
	};

	public Produit(Parcel in) {
		this.i_idProduit = (int) in.readLong();
		this.s_nomProduit = in.readString();
		this.d_prixProduit = in.readDouble();
		this.s_descriptionProduit = in.readString();
		this.o_categorieProduit = (Categorie) in.readValue(null);
		this.s_marqueProduit = in.readString();
		this.i_stockProduit = in.readInt();
	}

	public void getFromParcel(Parcel in) {
		this.setIdProduit(in.readInt());
		this.setNomProduit(in.readString());
		this.setPrixProduit(in.readDouble());
		this.setDescriptionProduit(in.readString());
		this.setCategorieProduit((Categorie) in.readValue(null));
		this.setMarqueProduit(in.readString());
		this.setStockProduit(in.readInt());
	}
	
}
