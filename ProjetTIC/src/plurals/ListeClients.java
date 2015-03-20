package plurals;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import model.DAOFactory;
import beans.Categorie;
import beans.Client;
import beans.Panier;
import beans.Produit;

public class ListeClients implements Observer, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ArrayList<Client> o_mesClients = new ArrayList<Client>();
	
	public ListeClients(){}
	
	public ArrayList<Client> getListeClients(){
		return o_mesClients;
	}
	
	//Cr�e un nouveau client. L'ajoute dans la base.
	public void ajouterClient(Client theClient){
		if(!this.o_mesClients.contains(theClient)){
			this.o_mesClients.add(theClient);
			//DAOFactory.getClientDAO().create(theClient);
		}else{
			//TODO : generate error "Already exists"
		}
	}
	

	// Recherche de clients � partir du nom
	public ArrayList<Client> rechClient(String theClient){
		ArrayList<Client> o_clientsTrouves = new ArrayList<Client>();
		for(int i=0; i<o_mesClients.size(); i++) {
			if (o_mesClients.get(i).getNomClient().equals(theClient)) {
				o_clientsTrouves.add(o_mesClients.get(i));
			}
		}
		return o_clientsTrouves;
	}
	

	// Suppression d'un client � partir du nom (utilis� par l'application)
	public boolean supprimerClient(String nom, String prenom){
		boolean supp = false;
		for(int i=0; i<o_mesClients.size(); i++) {
			if (o_mesClients.get(i).getNomClient().equals(nom) && o_mesClients.get(i).getPrenomClient().equals(prenom)) {
				o_mesClients.remove(i);
				supp = true;
			}
		}
		return supp;
	}
	
	//Supprime un client. Le supprime de la base (utilis� par l'application)
	public void supprimClient(Client theClient){
		if(this.o_mesClients.contains(theClient)){
			this.o_mesClients.remove(theClient);
			//DAOFactory.getClientDAO().delete(theClient);
		}else{
			//TODO : generate error "Not found"
		}
	}
	
	//On r�cup�re les clients de la base et on les cr�e :
	public void findClients(){
		boolean read = true;
		ArrayList<Client> mesClients = new ArrayList<Client>();
		int id = 1;
		
		//Je lis la base. Si j'ai une r�ponse, j'incr�ment l'ID, sinon je quitte et je retourne la liste
		while(read){
			try{
				mesClients.add(DAOFactory.getClientDAO().find(id));
			}catch(Exception e){
				read = false;
				e.printStackTrace();
			}
			
			id++;
		}
		
			o_mesClients = mesClients;
	
	}

	//Lorsqu'un produit est supprim�, cherche dans chaque panier de chaque 
	//client et appelle supprimerProduitPanier(Produit) :
	@Override
	public void update(Observable arg0, Object arg1) {
		Produit p = (Produit) arg0;
		for(Client c : o_mesClients){
			for(Panier pa : c.getMesPanier()){
				pa.supprimerProduitPanier(p);
			}
		}
		
			
	}
	
}
