package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import beans.Marque;

public class MarqueDAO extends DAO<Marque> {

	@Override
	public Marque create(Marque obj) {
		
		try{
			
			PreparedStatement prepare = this.connect.prepareStatement(
					"INSERT INTO marque(nomMarque)"
					+ "VALUES(?)");
			prepare.setString(1, obj.getNomMarque());
			
			prepare.executeUpdate();
				
		}catch(SQLException e){
			e.printStackTrace();
		}
			
		return obj;
	}

	@Override
	public boolean delete(Marque obj) {
		try{
			this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE).executeUpdate("DELETE FROM Marque WHERE id = " + obj.getIdMarque());
			return true;
		}catch(SQLException e){
			e.printStackTrace();
			return false;
		}
		
	}


	@Override
	public Marque update(Marque obj){
		try{
			this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE).executeQuery(
							"UPDATE produit SET"
									+"nomMarque = '" + obj.getNomMarque() + "'"
									+"WHERE idMarque =" + obj.getIdMarque());
			obj = this.find(obj.getIdMarque());
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return obj;
	}

	@Override
	public Marque find(int id) {
		Marque marque = new Marque();
		
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM marque WHERE idMarque =" + id);
			if(result.first()){
				marque = new Marque(
						id,
						result.getString("nomMarque"));
			}

		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return marque;
	}

}
