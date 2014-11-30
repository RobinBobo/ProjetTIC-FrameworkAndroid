package JavaBeans;

public class Client {
	int idClient;
	String nomClient;
	String prenomClient;
	String adresseClient;
	boolean sexeClient;
	Panier panierClient;
	
	public Client() {}


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


	public void setPanierClient(Panier panierClient) {
		this.panierClient = panierClient;
	}
	
	
}
