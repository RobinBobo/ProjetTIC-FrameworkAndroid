package com.ticfrontend.magasin;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
public class Client implements Serializable {
	private int idClient;
	private String nomClient;
	private String prenomClient;
	private String adresseClient;
	private boolean sexeClient;
	private List<Commande> listeCommandesClient;
	
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

	// GETTERS
	public int getIdClient() {return idClient;}
	public String getNomClient() {return nomClient;}
	public String getPrenomClient() {return prenomClient;}
	public String getAdresseClient() {return adresseClient;}
	public boolean isSexeClient() {return sexeClient;}
	public List<Commande> getListeCommandesClient() {return listeCommandesClient;}
	
	// SETTERS
	public void setIdClient(int idClient) {this.idClient = idClient;}
	public void setNomClient(String nomClient) {this.nomClient = nomClient;}	
	public void setPrenomClient(String prenomClient) {this.prenomClient = prenomClient;}
	public void setAdresseClient(String adresseClient) {this.adresseClient = adresseClient;}
	public void setSexeClient(boolean sexeClient) {this.sexeClient = sexeClient;}
	public void setListeCommandesClient(List<Commande> listeCommandesClient) {this.listeCommandesClient = listeCommandesClient;}
}
