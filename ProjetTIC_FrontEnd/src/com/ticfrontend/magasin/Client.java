package com.ticfrontend.magasin;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class Client implements Serializable {
	private int idClient;
	private String login;
	private String nomClient;
	private String prenomClient;
	private String adresseClient;
	private String adresseMail;
	private boolean sexeClient; // True : Homme, False : Femme
	private String mdpClient;
	
	private List<Commande> listeCommandesClient = null;
	
	public Client() {}
	
	public Client(String nom, String prenom){
		this.nomClient = nom;
		this.prenomClient = prenom;
	}
	
	public Client(String nom, String prenom, boolean sexe){
		this.nomClient = nom;
		this.prenomClient = prenom;
		this.sexeClient = sexe;
	}

	public Client(String login, String nom, String prenom,
			String adresse, String mail, boolean sexe) {
		this.setLogin(login);
		this.nomClient = nom;
		this.prenomClient = prenom;
		this.adresseClient = adresse;
		this.adresseMail = mail;
		this.sexeClient = sexe;
		this.setMdpClient("none");
	}

	public Client(String login, String nom, String prenom,
			String adresse, String mail, boolean sexe, String mdp) {
		this.setLogin(login);
		this.nomClient = nom;
		this.prenomClient = prenom;
		this.adresseClient = adresse;
		this.adresseMail = mail;
		this.sexeClient = sexe;
		this.mdpClient = mdp;
	}
	
	public void addCommande(Commande c){
		if(listeCommandesClient == null)
			listeCommandesClient = new ArrayList<Commande>();
		listeCommandesClient.add(c);
	}
	
	// GETTERS
	public int getIdClient() {return idClient;}
	public String getNomClient() {return nomClient;}
	public String getPrenomClient() {return prenomClient;}
	public String getAdresseClient() {return adresseClient;}
	public boolean getSexeClient() {return sexeClient;}
	
	public String getSexeClientToString(){
		if(this.sexeClient)
			return "Homme";
		else
			return "Femme";
	}
	public List<Commande> getListeCommandesClient() {return listeCommandesClient;}
	
	// SETTERS
	public void setIdClient(int idClient) {this.idClient = idClient;}
	public void setNomClient(String nomClient) {this.nomClient = nomClient;}	
	public void setPrenomClient(String prenomClient) {this.prenomClient = prenomClient;}
	public void setAdresseClient(String adresseClient) {this.adresseClient = adresseClient;}
	public void setSexeClient(boolean sexeClient) {this.sexeClient = sexeClient;}
	public void setListeCommandesClient(List<Commande> listeCommandesClient) {this.listeCommandesClient = listeCommandesClient;}

	public String getLogin() {return login;}

	public void setLogin(String login) {this.login = login;}

	public String getAdresseMail() {return adresseMail;}

	public void setAdresseMail(String adresseMail) {this.adresseMail = adresseMail;}

	public String getMdpClient() {return mdpClient;}

	public void setMdpClient(String mdpClient) {this.mdpClient = mdpClient;}
}
