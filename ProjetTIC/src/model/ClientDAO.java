package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.Client;
import beans.Panier;

public class ClientDAO extends DAO<Client> {

	//Méthode d'ajout de client dans la base
	@Override
	public Client create(Client obj) {
		
		try{
			//insertion

			PreparedStatement prepare = this.connect.prepareStatement(
											"INSERT INTO client (nomClient, prenomClient, adresseClient, sexeClient)"
											+ "VALUES(?, ?, ?, ?)");
			prepare.setString(1, obj.getNomClient());
			prepare.setString(2, obj.getPrenomClient());
			prepare.setString(3, obj.getAdresseClient());
			prepare.setBoolean(4, obj.getSexeClient());
			
			//Pour chaque panier de client, on l'ajoute dans la base avec create(Panier)
			for(Panier panier : obj.getMesPanier()){
				if(panier.getIdPanier() == 0){
					DAO<Panier> panierDAO = DAOFactory.getPanierDAO();
					panier = panierDAO.create(panier);
				}	
			}
				
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return obj;
	}

	 //Méthode de suppression dans la base
	@Override
	public boolean delete(Client obj) {
		
		try{
			
			//On supprime le client :
			this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE).executeUpdate("DELETE FROM client WHERE idClient =" + obj.getIdClient());
			
			//Pour chacun de ses panier : on appelle delete(Panier);
			for(Panier panier : obj.getMesPanier()){
				DAO<Panier> panierDAO = DAOFactory.getPanierDAO();
				panierDAO.delete(panier);
			}
		
			return true;
			
		}catch(SQLException e){
			e.printStackTrace();
			return false;
		}
		
	}

	//Méthode de mise à jour dans la base
	@Override
	public Client update(Client obj) {
		
		try{
			
			PreparedStatement prepare = this.connect.prepareStatement(
												"UPDATE client SET nomClient ='"+ obj.getNomClient() +"'" +
																  "prenomClient ='"+ obj.getPrenomClient() +"'" +
																  "adresseClient ='"+ obj.getAdresseClient() +"'" +
																  "sexeClient ='"+ obj.getSexeClient() +"'"
																+ "WHERE idClient ="+ obj.getIdClient());
			prepare.executeQuery();
			
			//Pour chaque panier : 
			// - il n'existe pas : je le crée
			// - il existe : je le mets à jour
			for(Panier panier : obj.getMesPanier()){
				DAO<Panier> panierDAO = DAOFactory.getPanierDAO();
				
				if(panier.getIdPanier() == 0){
					panier = panierDAO.create(panier);
					
					}else{
						panierDAO.update(panier);
					}
				}	
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return obj;
	}

	//Fonction de récupération de la base vers les objets :
	@Override
	public Client find(int id) {
		
		Client client = new Client();
		
		try{
			//je récupère le client
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM client WHERE idClient = " + id);
			
			//Pour chaque panier du client : find(Panier)
			if(result.first()){
				PanierDAO panierDAO = new PanierDAO();
				ArrayList<Panier> listePanier = new ArrayList<Panier>();
				
				result.beforeFirst();
				while(result.next() && result.getInt("idPanier") != 0){
					Panier pa = panierDAO.find(result.getInt("idPanier"));
					pa.setMonClient(client);
					listePanier.add(pa);			
				}
				
				result.first();
				
				//Création de l'objet Client :
				client = new Client(id,
						result.getString("nomClient"),
						result.getString("prenomClient"),
						result.getString("adresseClient"),
						result.getBoolean("sexeClient"),
						listePanier);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return client;
	}

}
