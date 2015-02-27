package com.ticfrontend.magasin;

import java.util.List;

public class Client {
	private int idClient;
	private String nomClient;
	private String prenomClient;
	private String adresseClient;
	private boolean sexeClient;
	private List<Commande> listeCommandesClient;
	
	public Client() {}
	
	public Client(String nom, String prenom, boolean sexe){
		
	}

	public int getIdClient() {
		return idClient;
	}


	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}


	public String getNomClient() {
		return nomClient;
	}


	public void setNomClient(String nomClient) {
		this.nomClient = nomClient;
	}


	public String getPrenomClient() {
		return prenomClient;
	}


	public void setPrenomClient(String prenomClient) {
		this.prenomClient = prenomClient;
	}


	public String getAdresseClient() {
		return adresseClient;
	}


	public void setAdresseClient(String adresseClient) {
		this.adresseClient = adresseClient;
	}


	public boolean isSexeClient() {
		return sexeClient;
	}

	public void setSexeClient(boolean sexeClient) {
		this.sexeClient = sexeClient;
	}

	public List<Commande> getListeCommandesClient() {
		return listeCommandesClient;
	}

	public void setListeCommandesClient(List<Commande> listeCommandesClient) {
		this.listeCommandesClient = listeCommandesClient;
	}
}
