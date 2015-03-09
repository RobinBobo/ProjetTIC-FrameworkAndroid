package plurals;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import model.DAOFactory;
import beans.Categorie;
import beans.Produit;

public class ListeCategories implements Observer, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ArrayList<Categorie> o_mesCategories = new ArrayList<Categorie>();
	
	public ListeCategories() {}
		
	public ArrayList<Categorie> getListeCategories(){
		return o_mesCategories;
	}
	
	public void ajouterCategorie(Categorie theCategorie){
		if(!this.o_mesCategories.contains(theCategorie)){
			this.o_mesCategories.add(theCategorie);
			//DAOFactory.getCategorieDAO().create(theCategorie);
		}else{
			//TODO : generate error "Already exists"
		}
	}
	
	public void supprimCategorie(Categorie theCategorie){
		if(this.o_mesCategories.contains(theCategorie)){
			this.o_mesCategories.remove(theCategorie);
			//DAOFactory.getCategorieDAO().delete(theCategorie);
		}else{
			//TODO : generate error "Not found"
		}
	}
	
	public boolean isCategorie(int id){
		boolean isPresent = false;
		for(int i=0; i<o_mesCategories.size(); i++) {
			if (o_mesCategories.get(i).getIdCategorie() == id) {
				isPresent = true;
			}
		}
		return isPresent;
	}
	
	//récupération dans la base :
	public void findCategories(){
		boolean read = true;
		ArrayList<Categorie> mesCategories = new ArrayList<Categorie>();
		int id = 1;
		
		//Je lis la base. Si j'ai une réponse, j'incrément l'ID, sinon je quitte et je retourne la liste
		while(read){
			try{
				mesCategories.add(DAOFactory.getCategorieDAO().find(id));
			}catch(Exception e){
				read = false;
				e.printStackTrace();
			}
			
			id++;
		}
		
			o_mesCategories = mesCategories; 
	}
	
	//Lorsqu'un produit est supprimé, cherche dans chaque panier de chaque 
		//client et appelle supprimerProduitPanier(Produit) :
		@Override
		public void update(Observable arg0, Object arg1) {
			Produit p = (Produit) arg0;
			for(Categorie c : o_mesCategories){
				c.supprimerProduitCategorie(p);
			}
			
				
		}
	
	
	
	
	
}
