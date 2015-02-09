package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//CTRL + SHIFT + O pour générer les imports
public class Connect {
    
	
	private static String url = "jdbc:mysql://localhost/MABDD";
	
	private static String user = "root";
	
	private static String passwd = "root";
	
	private static Connection connect;
	
	/**
	 * Méthode qui retourne l'instance
	 * et la crée si elle n'existe pas
	 * 
	 */
	public static Connection getInstance(){
		if(connect == null){
			try {
				connect = DriverManager.getConnection(url, user, passwd);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		return connect;	
	}	
	
	
	
	
}