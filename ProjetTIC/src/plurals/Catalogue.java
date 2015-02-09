package plurals;

import java.util.ArrayList;

import model.DAOFactory;
import beans.Produit;

public class Catalogue{

	private ArrayList<Produit> o_mesProduits = new ArrayList<Produit>();
	
	public Catalogue(){}

	public ArrayList<Produit> getMesProduits() {
		return o_mesProduits;
	}

	
	public void ajouterProduitCatalogue(Produit theProduit){
		if(!this.getMesProduits().contains(theProduit)){
			this.o_mesProduits.add(theProduit);
			DAOFactory.getProduitDAO().create(theProduit);
		}else{
			//TODO : generate error "Already exists"
		}
		
	}
	
	public void supprimerProduitCatalogue(Produit theProduit){
		if(this.o_mesProduits.contains(theProduit)){
			this.o_mesProduits.remove(theProduit);
			theProduit.supprimerProduit();
		}else{
			//TODO : generate error "Not found"
		}
	}
	
	//On récupère les produits de la base et on les crée :
	public void findProduits(){
		boolean read = true;
		ArrayList<Produit> mesProduits = new ArrayList<Produit>();
		int id = 1;
		
		//Je lis la base. Si j'ai une réponse, j'incrément l'ID, sinon je quitte et je retourne la liste
		while(read){
			try{
				mesProduits.add(DAOFactory.getProduitDAO().find(id));
			}catch(Exception e){
				read = false;
				e.printStackTrace();
			}
			
			id++;
		}
		
			o_mesProduits = mesProduits;
	}
	
	public void ajoutObserver(ListeClients theClients){
		for(Produit p : o_mesProduits){
			p.addObserver(theClients);
		}
	}
	
	
	
	
	
	
}
