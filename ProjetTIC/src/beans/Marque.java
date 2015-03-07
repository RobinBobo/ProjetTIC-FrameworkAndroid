package beans;

import java.io.Serializable;
import java.util.ArrayList;
//TODO : Le systeme de Marque n'est pas encore implémenté... A voir si il le sera ?
public class Marque implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int i_idMarque = 0;
	private String s_nomMarque;
	private ArrayList<Produit> o_mesProduits = new ArrayList<Produit>();
	
	
	  //_____________________________________
     //Constructeurs :
    //_______________________________________________________________
	
	public Marque(){}
	
	public Marque(int theId, String theNom) {
		this.i_idMarque = theId;
		this.s_nomMarque = theNom;
	}
	
	public Marque(int theId, String theNom, ArrayList<Produit> theProduits) {
		this.i_idMarque = theId;
		this.s_nomMarque = theNom;
		this.o_mesProduits = theProduits;
	}
	
	
	
	  //_____________________________________
	 //Getters & Setters :
	//_______________________________________________________________
	
	public ArrayList<Produit> getMesProduits() {
		return o_mesProduits;
	}

	public void setMesProduits(ArrayList<Produit> theProduits) {
		this.o_mesProduits = theProduits;
	}

	public int getIdMarque() {
		return i_idMarque;
	}
	public void setIdMarque(int theId) {
		this.i_idMarque = theId;
	}
	public String getNomMarque() {
		return s_nomMarque;
	}
	public void setNomMarque(String theNom) {
		this.s_nomMarque = theNom;
	}
}
