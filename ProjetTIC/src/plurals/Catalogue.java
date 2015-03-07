package plurals;

import java.io.Serializable;
import java.util.ArrayList;

import model.DAOFactory;
import beans.Produit;

public class Catalogue implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Produit> o_mesProduits = new ArrayList<Produit>();
	
	public Catalogue(){}

	public ArrayList<Produit> getMesProduits() {
		return o_mesProduits;
	}

	
	public void ajouterProduitCatalogue(Produit theProduit){
		if(!this.getMesProduits().contains(theProduit)){
			this.o_mesProduits.add(theProduit);
			//DAOFactory.getProduitDAO().create(theProduit);
		}else{
			//TODO : generate error "Already exists"
		}		
	}
	
	public Produit rechercherProduit(int id) {
		Produit pdt = null;
		for(int i=0; i<o_mesProduits.size(); i++) {
			if (o_mesProduits.get(i).getIdProduit() == id) {
				pdt = o_mesProduits.get(i);		
			}
		}
		return pdt;		
	}
	
	public void supprimerProduitCatalogue(Produit theProduit){
		if(this.o_mesProduits.contains(theProduit)){
			this.o_mesProduits.remove(theProduit);
			theProduit.supprimerProduit();
		}else{
			//TODO : generate error "Not found"
		}
	}
	
	public void afficherCatalogue() {
		for (int i=0; i < getMesProduits().size(); i++) {
			System.out.println(getMesProduits().get(i).getNomProduit()+"\n");
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
