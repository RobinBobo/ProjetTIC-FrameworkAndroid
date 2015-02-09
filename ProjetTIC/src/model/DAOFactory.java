package model;

import beans.Categorie;
import beans.Client;
import beans.Marque;
import beans.Panier;
import beans.Produit;

public class DAOFactory {

	public static DAO<Client> getClientDAO(){
		return new ClientDAO();
	}
	
	public static DAO<Categorie> getCategorieDAO(){
		return new CategorieDAO();
	}
	
	public static DAO<Produit> getProduitDAO(){
		return new ProduitDAO();
	}
	
	public static DAO<Panier> getPanierDAO(){
		return new PanierDAO();
	}
	
	public static DAO<Marque> getMarqueDAO(){
		return new MarqueDAO();
	}
}



