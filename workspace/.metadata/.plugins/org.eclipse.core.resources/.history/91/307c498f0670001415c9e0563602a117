package model;

import java.util.ArrayList;
import java.util.List;

public class Client {
	private String name;
	private String surname;
	private String gender;
	private Integer age;
	private String adress;
	private Integer nbCreatedShoppingCart;
	private Integer nbValidateShoppingCart;
	
	private List<Panier> myShoppingCart;
	
	public Client (String name, String surname, String gender, Integer age, String adress) {
		this.name = name;
		this.surname = surname;
		this.gender  = gender;
		this.age = age;
		this.adress = adress;
		myShoppingCart = new ArrayList<Panier> ();	
		nbCreatedShoppingCart = 0;
		nbValidateShoppingCart = 0;		
	}
	
	public void addShoppingCart () {
		myShoppingCart.add(new Panier ());
	}
	
	public Panier getCurrentShoppingCart () {
		return myShoppingCart.get(myShoppingCart.size()-1);
	}
	
	public void calculateShoppingCart () {
		nbCreatedShoppingCart = 0;
		nbValidateShoppingCart = 0;
		for (Panier p : myShoppingCart) {
			if (p.getPanierValid())
				nbValidateShoppingCart++;
			else
				nbCreatedShoppingCart++;		
		}
	}
	
	public void displayCustomerInfo () {
		System.out.println("\nNom : " + this.name + "\nSurname : " + this.surname + "\nGender : " + this.gender + "\nAge : " 
		+ this.age + "\nAdress : " + this.adress + "\nNbCreatedShoppingCart : "+ this.nbCreatedShoppingCart + "\nNbValidateShoppingCart : " 
		+ this.nbValidateShoppingCart );
	}
	

}
