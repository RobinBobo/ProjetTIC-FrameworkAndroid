package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {
	
	
	
	private static String url = "jdbc:mysql://127.0.0.1:3333/ecomback";
	
	private static String user = "canglade";
	
	private static String passwd = "root";
	
	private static Connection connect;
	
	/**
	 * Méthode qui retourne l'instance
	 * et la crée si elle n'existe pas
	 * 
	 */
	public static Connection getInstance(){
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			System.out.println("Where is your MySQL JDBC Driver?");
			e.printStackTrace();
		}
		
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
	
	
