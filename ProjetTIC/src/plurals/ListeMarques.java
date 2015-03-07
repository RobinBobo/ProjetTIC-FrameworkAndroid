package plurals;

import java.io.Serializable;
import java.util.ArrayList;

import beans.Marque;
import beans.Produit;

public class ListeMarques implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ArrayList<Marque> o_mesMarques = new ArrayList<Marque>();
	
	public ListeMarques(){}
	
	public void ajouterMarque(Marque theMarque){
		if(!this.o_mesMarques.contains(theMarque)){
			o_mesMarques.add(theMarque);
		}else{
			//TODO : generate error "Already exists"
		}
	}

	//Supprime tous les produits associés à la marque
	public void supprimerMarque(Marque theMarque){
		if(this.o_mesMarques.contains(theMarque)){
			o_mesMarques.remove(theMarque);
			for(Marque m : o_mesMarques){
				for(Produit p : m.getMesProduits()){
					p.supprimerProduit();
				}
			}
		}else{
			//TODO : generate error "Not found"
		}
	}
	
}
