package JavaBeans;

public class Commande {

	private int idCommande;
	private float prixTotalCommande;
	private String typeReglementCommande;
	
	public Commande () {
		
	}

	public int getIdCommande() {
		return idCommande;
	}

	public void setIdCommande(int idCommande) {
		this.idCommande = idCommande;
	}

	public float getPrixTotalCommande() {
		return prixTotalCommande;
	}

	public void setPrixTotalCommande(float prixTotalCommande) {
		this.prixTotalCommande = prixTotalCommande;
	}

	public String getTypeReglementCommande() {
		return typeReglementCommande;
	}

	public void setTypeReglementCommande(String typeReglementCommande) {
		this.typeReglementCommande = typeReglementCommande;
	}
}
