package beans;

import java.util.Observable;

import model.DAOFactory;


public class Produit extends Observable {

	private int i_idProduit = 0;
	private String s_nomProduit;
	private double d_prixProduit;
	private String s_descriptionProduit;
	private String s_categorieProduit;
	private String s_marqueProduit;
	private int i_stockProduit;
	
	//_____________________________________
   //Constructeurs :
  //_______________________________________________________________
	
	public Produit(){}
	
	public Produit(int theId, String theNom, double thePrix,
			String theDescription, String theCategorie,
			String theMarque, int theStock) {
		this.i_idProduit = theId;
		this.s_nomProduit = theNom;
		this.d_prixProduit = thePrix;
		this.s_descriptionProduit = theDescription;
		this.s_categorieProduit = theCategorie;
		this.s_marqueProduit = theMarque;
		this.i_stockProduit = theStock;
	}
	

	// Constructeur utilisé par l'application
	public Produit(String theNom, double thePrix,
			String theDescription, String theCategorie,
			String theMarque, int theStock) {
		this.s_nomProduit = theNom;
		this.d_prixProduit = thePrix;
		this.s_descriptionProduit = theDescription;
		this.s_categorieProduit = theCategorie;
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
		//DAOFactory.getProduitDAO().update(this);
	}
	
	public void achatProduit(int theQuantite){
		i_stockProduit = i_stockProduit - theQuantite;
		//DAOFactory.getProduitDAO().update(this);
	}
	
	//Notifie la liste des clients
	public void supprimerProduit(){
		//DAOFactory.getProduitDAO().delete(this);
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
		//DAOFactory.getProduitDAO().update(this);	
	}
	
	public int getIdProduit() {
		return i_idProduit;
	}

	public void setIdProduit(int theId) {
		this.i_idProduit = theId;
		//DAOFactory.getProduitDAO().update(this);	
	}

	public String getNomProduit() {
		return s_nomProduit;
	}

	public void setNomProduit(String theNom) {
		this.s_nomProduit = theNom;
		//DAOFactory.getProduitDAO().update(this);	
	}

	public double getPrixProduit() {
		return d_prixProduit;
	}

	public void setPrixProduit(double thePrix) {
		this.d_prixProduit = thePrix;
		//DAOFactory.getProduitDAO().update(this);	
	}

	public String getDescriptionProduit() {
		return s_descriptionProduit;
	}

	public void setDescriptionProduit(String theDescription) {
		this.s_descriptionProduit = theDescription;
		//DAOFactory.getProduitDAO().update(this);	
	}

	public String getCategorieProduit() {
		return s_categorieProduit;
	}

	public void setCategorieProduit(String theCategorie) {
		this.s_categorieProduit = theCategorie;
		//DAOFactory.getProduitDAO().update(this);	
	}

	public String getMarqueProduit() {
		return s_marqueProduit;
	}

	public void setMarqueProduit(String theMarque) {
		this.s_marqueProduit = theMarque;
		//DAOFactory.getProduitDAO().update(this);	
	}

}
