package beans;

import java.io.Serializable;
import java.util.ArrayList;

import model.DAOFactory;

public class Panier implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int i_idPanier = 0;
	private boolean b_commande;
	private String s_typeReglement;
	private Client o_monClient;
	private ArrayList<LignePanier> o_mesLignes = new ArrayList<LignePanier>();
	
	//_____________________________________
   //Constructeurs :
  //_______________________________________________________________
	
	public Panier() {}
	
	public Panier(int thePanier, boolean theCommande, String theType,
			Client theClient, ArrayList<LignePanier> theLignes) {
		this.i_idPanier = thePanier;
		this.b_commande = theCommande;
		this.s_typeReglement = theType;
		this.o_monClient = theClient;
		this.o_mesLignes = theLignes;
	}
	
	public Panier(int thePanier, boolean theCommande, String theType,
			Client theClient) {
		this.i_idPanier = thePanier;
		this.b_commande = theCommande;
		this.s_typeReglement = theType;
		this.o_monClient = theClient;
	}
	
	public Panier(int thePanier, boolean theCommande, String theType) {
		this.i_idPanier = thePanier;
		this.b_commande = theCommande;
		this.s_typeReglement = theType;
	}
	
	  //_____________________________________
	 //Méthodes :
	//_______________________________________________________________
	
	public void commander(String theReglement){
		this.b_commande = true;
		this.s_typeReglement = theReglement;
		for(LignePanier ligne : o_mesLignes){
			ligne.getMonProduit().achatProduit(ligne.getQuantite());
		}	
		//DAOFactory.getPanierDAO().update(this);
	}

	//On ajoute une ligne : on met à jour la base
	public void ajouterProduitPanier(Produit theProduit, int theQuantite){
		boolean good = true;
		
		for(LignePanier ligne : o_mesLignes){
			if(ligne.getMonProduit() == theProduit){
				good = false;
			}
		}
		
		if(good){
			if(theProduit.getStockProduit() >= theQuantite){
				this.o_mesLignes.add(new LignePanier(theProduit, this, theQuantite));
			}else{
				//TODO : generate error "Wrong value"
			}
			//DAOFactory.getPanierDAO().update(this);
		}else{
			//TODO : generate error "Already exists"
		}
		
	}
	
	//On modifie une ligne : on met à jour la base
	public void modifierQuantiteProduit(Produit theProduit, int theQuantite){
		boolean good = false;
		for(LignePanier ligne : o_mesLignes){
			if(ligne.getMonProduit() == theProduit){
				if(theProduit.getStockProduit() >= theQuantite){
					ligne.setQuantite(theQuantite);
					good = true;
				}else{
					//TODO : generate error "Wrong value"
				}
			}
		}
		
		if(good){
			//DAOFactory.getPanierDAO().update(this);
		}else{
			//TODO : generate error "Not Found"
		}
		
		
	}
	
	//On supprime une ligne : on met la base à jour :
	public void supprimerProduitPanier(Produit theProduit){
		boolean good = false;
		for(LignePanier ligne : o_mesLignes){
			if(ligne.getMonProduit() == theProduit){
				o_mesLignes.remove(ligne);
				good = true;
			}
		}
		
		if(good){
			//DAOFactory.getPanierDAO().update(this);
		}else{
			//TODO : generate error "Not found"
		}
	}
	
	
	
	

      //_____________________________________
	 //Getters & Setters :
	//_______________________________________________________________

	public Client getMonClient() {
		return o_monClient;
	}

	public void setMonClient(Client theClient) {
		this.o_monClient = theClient;
		//DAOFactory.getPanierDAO().update(this);
	}

	public int getIdPanier() {
		return i_idPanier;
	}
	
	public void setIdPanier(int theId) {
		this.i_idPanier = theId;
		//DAOFactory.getPanierDAO().update(this);
	}
	
	public boolean isCommande() {
		return b_commande;
	}
	
	public String getTypeReglement() {
		return s_typeReglement;
	}

	public void setTypeReglement(String theType) {
		this.s_typeReglement = theType;
		//DAOFactory.getPanierDAO().update(this);
	}
	
	public ArrayList<LignePanier> getMesLignes() {
		return o_mesLignes;
	}
	
}
