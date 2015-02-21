package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.Categorie;
import beans.Produit;

public class CategorieDAO extends DAO<Categorie> {

	//Methode de sauvegarde de l'objet dans la base (retourne l'objet Categorie) :
	@Override
	public Categorie create(Categorie obj) {
		try{
			
			//si on a un résultat, on insère les valeurs :
		
	
				PreparedStatement prepare = this.connect.prepareStatement(
						"INSERT INTO categorie(nomCategorie)"
						+ "VALUES(?)");
				prepare.setString(1, obj.getNomCategorie());
				prepare.executeUpdate();
				
				//pour chaque produit, s'il n'est pas dans la base, on appelle create(Produit) :
				for(Produit prod : obj.getMesProduits()){
					if(prod.getIdProduit() == 0){
						DAO<Produit> produitDAO = DAOFactory.getProduitDAO();
						prod = produitDAO.create(prod);
					}

			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return obj;
	}

	//Méthode de suppression dans la base (retourne vrai si réussie) :
	@Override
	public boolean delete(Categorie obj) {
		
		try{
			
			//Pour chacun de ses produits : on appelle delete(Produit);
			for(Produit produit : obj.getMesProduits()){
				DAO<Produit> produitDAO = DAOFactory.getProduitDAO();
				produitDAO.delete(produit);
			}
			
			//supprime la catégorie
			this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE).executeUpdate("DELETE FROM categorie WHERE idCategorie ="
												+ obj.getIdCategorie());
			
			return true;
		}catch(SQLException e)
		{
			e.printStackTrace();
			return false;
		}
	}

	//Méthode de mise à jour dans la base :
	@Override
	public Categorie update(Categorie obj) {
		
		try{
			
			//On met à jour la table Categorie
			PreparedStatement prepare = this.connect.prepareStatement(
					"UPDATE categorie SET nomCategorie ='"+obj.getNomCategorie()+"'"+"WHERE idCategorie="+obj.getIdCategorie());
			prepare.executeUpdate();
			
			//Pour chaque produit de l'objet, on appelle create(Produit) ou update(Produit) :
			for(Produit prod : obj.getMesProduits()){
				DAO<Produit> produitDAO = DAOFactory.getProduitDAO();
				
				if(prod.getIdProduit() == 0){
					prod = produitDAO.create(prod);
					}else{
						produitDAO.update(prod);
					}
				}
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return obj;
	}

	//Méthode de récupération de la base vers les objets :
	@Override
	public Categorie find(int id) {
		Categorie categorie = new Categorie();
		
		try{
			
			//On Select la table Categorie :
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Categorie "
							+ "WHERE idCategorie =" + id
					);
			
			
			if(result.first()){
				
				//On Select la table produit :
				ResultSet result2 = this.connect.createStatement(
						ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Produit "
								+ "WHERE idCategorie =" + id
						);
				
				ArrayList<Produit> listeProduits = new ArrayList<Produit>();
				
				if(result2.first()){
					ProduitDAO prodDao = new ProduitDAO();
				
					result2.beforeFirst();
					
					//On remplie la liste des Produits avec find(Produit) :
					while(result2.next() && result2.getInt("idProduit") != 0){
						Produit p = prodDao.find(result2.getInt("idProduit"));
						p.setCategorieProduit(categorie);
						listeProduits.add(p);
					}
				}
				
				result.first();
				
				
				//Creation de l'objet récupéré :
				categorie = new Categorie(
						id,
						result.getString("nomCategorie"),
						listeProduits);
				
			}
				
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return categorie;
	}

}
