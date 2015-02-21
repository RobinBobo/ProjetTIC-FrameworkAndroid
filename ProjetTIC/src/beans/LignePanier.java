package beans;


public class LignePanier {

	private Produit o_monProduit;
	private Panier o_monPanier;
	private int i_quantite;
	
	public LignePanier(){}
	
	public LignePanier(Produit theProduit, Panier thePanier, int theQuantite) {
		super();
		this.o_monProduit = theProduit;
		this.o_monPanier = thePanier;
		this.i_quantite = theQuantite;
	}

	public Produit getMonProduit() {
		return o_monProduit;
	}

	public void setMonProduit(Produit theProduit) {
		this.o_monProduit =theProduit;
	}

	public Panier getMonPanier() {
		return o_monPanier;
	}

	public void setMonPanier(Panier thePanier) {
		this.o_monPanier = thePanier;
	}

	public int getQuantite() {
		return i_quantite;
	}

	public void setQuantite(int theQuantite) {
		this.i_quantite = theQuantite;
	}
	
	
	
	
}
