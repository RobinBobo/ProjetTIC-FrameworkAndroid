package JavaBeans;

public class Produit {
	int idProduit;
	String nomProduit;
	double prixProduit;
	Categorie categorieProduit;
	String descriptionProduit;
	String marqueProduit;
	
	public Produit() {}
	
	public void setCategorieProduit(Categorie categorieProduit) {
		this.categorieProduit = categorieProduit;
	}

	public void setIdProduit(int idProduit) {
		this.idProduit = idProduit;
	}

	public void setNomProduit(String nomProduit) {
		this.nomProduit = nomProduit;
	}

	public void setPrixProduit(double prixProduit) {
		this.prixProduit = prixProduit;
	}

	public void setDescriptionProduit(String descriptionProduit) {
		this.descriptionProduit = descriptionProduit;
	}

	public void setMarqueProduit(String marqueProduit) {
		this.marqueProduit = marqueProduit;
	}
	
	
}
