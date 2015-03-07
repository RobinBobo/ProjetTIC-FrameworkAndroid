package beans;

import java.io.Serializable;
import java.util.ArrayList;

import model.DAOFactory;

public class Client implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int i_idClient = 0;
	private String s_nomClient;
	private String s_prenomClient;
	private String s_adresseClient;
	private boolean b_sexeClient; // true : homme / false : femme
	private ArrayList<Panier> o_mesPaniers = new ArrayList<Panier>();

	// _____________________________________
	// Constructeurs :
	// _______________________________________________________________

	public Client() {
	}

	public Client(int theId, String theNom, String thePrenom,
			String theAdresse, boolean theSexe) {

		this.i_idClient = theId;
		this.s_nomClient = theNom;
		this.s_prenomClient = thePrenom;
		this.s_adresseClient = theAdresse;
		this.b_sexeClient = theSexe;
	}

	public Client(int theId, String theNom, String thePrenom,
			String theAdresse, boolean theSexe, ArrayList<Panier> thePaniers) {

		this.i_idClient = theId;
		this.s_nomClient = theNom;
		this.s_prenomClient = thePrenom;
		this.s_adresseClient = theAdresse;
		this.b_sexeClient = theSexe;
		this.o_mesPaniers = thePaniers;
	}

	// _____________________________________
	// Méthodes :
	// _______________________________________________________________

	// ajoute un Panier pour ce Client. Update la base.
	public void ajouterPanierClient(Panier thePanier) {
		if (!this.getMesPanier().contains(thePanier)) {
			o_mesPaniers.add(thePanier);
			//DAOFactory.getClientDAO().update(this);
		} else {
			// TODO : generate error "Already exists"
		}

	}

	public void supprimerPanierClient(Panier thePanier) {
		if (this.getMesPanier().contains(thePanier)) {
			this.getMesPanier().remove(thePanier);
			//DAOFactory.getPanierDAO().delete(thePanier);
		} else {
			// TODO : generate error "Not found"
		}
	}

	// _____________________________________
	// Getters & Setters :
	// _______________________________________________________________

	public int getIdClient() {
		return i_idClient;
	}

	public void setIdClient(int theId) {
		this.i_idClient = theId;
		//DAOFactory.getClientDAO().update(this);
	}

	public String getNomClient() {
		return s_nomClient;
	}

	public void setNomClient(String theNom) {
		this.s_nomClient = theNom;
		//DAOFactory.getClientDAO().update(this);
	}

	public String getPrenomClient() {
		return s_prenomClient;
	}

	public void setPrenomClient(String thePrenom) {
		this.s_prenomClient = thePrenom;
		//DAOFactory.getClientDAO().update(this);
	}

	public String getAdresseClient() {
		return s_adresseClient;
	}

	public void setAdresseClient(String theAdresse) {
		this.s_adresseClient = theAdresse;
		//DAOFactory.getClientDAO().update(this);
	}

	public boolean getSexeClient() {
		return b_sexeClient;
	}

	public void setSexeClient(boolean theSexe) {
		this.b_sexeClient = theSexe;
		//DAOFactory.getClientDAO().update(this);
	}

	public ArrayList<Panier> getMesPanier() {
		return o_mesPaniers;
	}

}
