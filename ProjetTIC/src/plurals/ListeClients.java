package plurals;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import model.DAOFactory;
import beans.Client;
import beans.Panier;
import beans.Produit;

public class ListeClients implements Observer{

	ArrayList<Client> o_mesClients = new ArrayList<Client>();
	
	public ListeClients(){}
	
	public ArrayList<Client> getListeClients(){
		return o_mesClients;
	}
	
	//Cr�e un nouveau client. L'ajoute dans la base.
	public void ajouterClient(Client theClient){
		if(!this.o_mesClients.contains(theClient)){
			this.o_mesClients.add(theClient);
			DAOFactory.getClientDAO().create(theClient);
		}else{
			//TODO : generate error "Already exists"
		}
	}
	
	//Supprime un client. Le supprime de la base
	public void supprimClient(Client theClient){
		if(this.o_mesClients.contains(theClient)){
			this.o_mesClients.remove(theClient);
			DAOFactory.getClientDAO().delete(theClient);
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
