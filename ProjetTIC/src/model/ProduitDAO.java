package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import beans.Produit;

public class ProduitDAO extends DAO<Produit> {

	//Méthode d'ajout dans la base
	@Override
	public Produit create(Produit obj) {
		
		try{
			
			//insertion des valeurs

			PreparedStatement prepare = this.connect.prepareStatement(
					"INSERT INTO Produit(nomProduit, prixProduit, descriptionProduit, categorieProduit, marqueProduit, stockProduit)"
					+"VALUES(?, ?, ?, ?, ?, ?)"
					);
		
			prepare.setString(1, obj.getNomProduit());
			prepare.setDouble(2, obj.getPrixProduit());
			prepare.setString(3, obj.getDescriptionProduit());
			//prepare.setInt(4, obj.getCategorieProduit().getIdCategorie());
			prepare.setString(5, obj.getMarqueProduit());
			prepare.setInt(6, obj.getStockProduit());
			
			prepare.executeUpdate();	
			
		}catch(SQLException e){
			e.printStackTrace();
		}	
		
		return obj;
	}

	//Méthode de suppression dans la base
	@Override
	public boolean delete(Produit obj) {
	
		try {
			this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE).executeUpdate("DELETE FROM Produit where idProduit ="+ obj.getIdProduit());
			
			return true;
		}catch(SQLException e){
			e.printStackTrace();
			return false;
		}
	}

	//Méthode de mise à jour dans la base
	@Override
	public Produit update(Produit obj) {
		
		try{
			//Mise à jour dans la table
			this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE).executeQuery(
					"UPDATE produit SET "
					+ "nomProduit = '" + obj.getNomProduit() + "',"
					+ "prixProduit = '" + obj.getPrixProduit() + "',"
					+ "descriptionProduit = '" + obj.getDescriptionProduit() + "',"
					//+ "categorieProduit = '" + obj.getCategorieProduit().getIdCategorie() + "',"
					+ "marqueProduit = '" + obj.getMarqueProduit() + "',"
					+ "stockProduit = '" + obj.getStockProduit() + "'"
					+ "WHERE idProduit =" + obj.getIdProduit()
					);
			obj = this.find(obj.getIdProduit());

		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return obj;
	}

	//Méthode de récupération des objets depuis la base
	@Override
	public Produit find(int id) {
		Produit produit = new Produit();
		
		try{
			//récupération de l'objet
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Produit WHERE idProduit =" + id
					);
			
			//création
			if(result.first()){
				produit = new Produit(
						id,
						result.getString("nomProduit"),
						result.getInt("prixProduit"),
						result.getString("descriptionProduit"),
						result.getString("marqueProduit"),
						result.getInt("stockProduit"));
				
				result.beforeFirst();
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return produit;
	}

}
