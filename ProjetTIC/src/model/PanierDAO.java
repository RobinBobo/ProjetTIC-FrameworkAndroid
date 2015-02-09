package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.LignePanier;
import beans.Panier;

public class PanierDAO extends DAO<Panier> {

	//Méthode d'ajout de Panier dans la base
	@Override
	public Panier create(Panier obj) {
		
		try{
			//Insertion dans la table
				PreparedStatement prepare = this.connect.prepareStatement(
											"INSERT INTO panier(commandePanier, typeReglementPanier, idClient)"
										  + "VALUES(?, ?, ?)");
				
				prepare.setBoolean(1, obj.isCommande());
				prepare.setString(2, obj.getTypeReglement());
				prepare.setInt(3, obj.getMonClient().getIdClient());
				prepare.executeUpdate();
				
				ResultSet result = this.connect.createStatement(
							ResultSet.TYPE_SCROLL_INSENSITIVE,
							ResultSet.CONCUR_UPDATABLE).executeQuery(
									"select last_insert_id() as last_id from schedule");
				int id = result.getInt("last_id");
				
				//Pour chaque ligne de l'arraylist, on ajoute dans la table contenuPanier
				for(LignePanier ligne : obj.getMesLignes()){
					PreparedStatement prepare2 = this.connect.prepareStatement(
							"INSERT INTO contenuPanier(idProduit, idPanier, quantite)"
						  + "VALUES(?, ?, ?)");

					prepare2.setInt(1, ligne.getMonProduit().getIdProduit());
					prepare2.setInt(2, id);
					prepare2.setInt(3, ligne.getQuantite());
					prepare2.executeUpdate();
				
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return obj;
	}

	//Méthode de suppression dans la base
	@Override
	public boolean delete(Panier obj) {
		
		try{
			
			//On supprime les lignes correspondantes dans la table contenuPanier
			this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE).executeUpdate(
							"DELETE FROM contenuPanier WHERE idPanier =" + obj.getIdPanier());
			
			//on supprime dans la table Panier
			this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE).executeUpdate(
							"DELETE FROM panier WHERE idPanier =" + obj.getIdPanier());
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		
		
		
		return false;
	}

	//Méthode de mise à jour dans la base
	@Override
	public Panier update(Panier obj) {
		
		try{
			//on update la table panier.
			this.connect.createStatement(
						ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_UPDATABLE).executeUpdate(
								"UPDATE panier SET commandePanier ='"+ obj.isCommande() +"',"+
										          "typeReglementPanier ='"+ obj.getTypeReglement()+"',"+
												  "idClient ='"+ obj.getMonClient().getIdClient()  +"'");
			
			//Supprime toutes les lignes de contenuPanier pour ce Produit (Si jamais on a moins de lignes dans le Panier)
			this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE).executeUpdate(
							"DELETE FROM contenuPanier WHERE idPanier =" + obj.getIdPanier());
			
			//Pour chaque ligne du panier, on la récupère dans la table contenuPanier
			for(LignePanier ligne : obj.getMesLignes()){
				
				PreparedStatement prepare = this.connect.prepareStatement(
						"INSERT INTO contenuPanier(idProduit, idPanier, quantite)"
					  + "VALUES(?, ?, ?)");
	
				prepare.setInt(1, ligne.getMonProduit().getIdProduit());
				prepare.setInt(2, obj.getIdPanier());
				prepare.setInt(3, ligne.getQuantite());
				prepare.executeUpdate();
				
			}
			
			obj = this.find(obj.getIdPanier());

			
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return obj;
	}

	//Méthode de récupération des objets depuis la base
	@Override
	public Panier find(int id) {
		Panier panier = new Panier();
		
		try{
			ResultSet result = this.connect.createStatement(
									ResultSet.TYPE_SCROLL_INSENSITIVE,
									ResultSet.CONCUR_UPDATABLE).executeQuery(
											"SELECT * FROM panier WHERE idPanier ="+id);
			
			panier = new Panier(id,
					result.getBoolean("commandePanier"),
					result.getString("typeReglementPanier"));
			
			if(result.first()){
				ResultSet result2 = this.connect.createStatement(
						ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM contenuPanier WHERE idPanier ="+ id);
				
				
				
				ArrayList<LignePanier> listeLignes = new ArrayList<LignePanier>();
				
				if(result2.first()){
					result2.beforeFirst();
					
					ProduitDAO prodDAO = new ProduitDAO();
					
					while(result2.next() && result2.getInt("idPanier") !=0){
						listeLignes.add(new LignePanier(prodDAO.find(result2.getInt("idProduit")), panier, result2.getInt("quantite")));
						
					}
				}
	
		}
		
		
		}catch(SQLException e){
			e.printStackTrace();
		}
	
		return panier;
	}

}
