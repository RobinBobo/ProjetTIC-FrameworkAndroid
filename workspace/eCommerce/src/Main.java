
import model.Catalogue;
import model.Client;
import model.Magasin;
import model.Produit;

public class Main {
	public static void main(String[] args) {
	
		
		
		//Construction des produits
		Produit livre = new Produit("The Livre", 15);
		Produit cd = new Produit("The CD", 15);
		
		Catalogue cat = new Catalogue();
		cat.ajouterProduitDansCatalogue(livre,10);
		cat.ajouterProduitDansCatalogue(cd,0);
		
		Magasin mag = new Magasin();
		mag.setCatalogue(cat);
		//Construction du magasin
		Client c = new Client ("Robin","BOBO","Mr",21,"Secret");
		
		mag.addCustomer(c);
		
		c.displayCustomerInfo();
		c.addShoppingCart();
		c.addShoppingCart();
		
		c.getCurrentShoppingCart().ajouterProduitDansPanier(livre, 10,cat);
		c.getCurrentShoppingCart().ajouterProduitDansPanier(cd, 25,cat);
		
		c.calculateShoppingCart();
		c.displayCustomerInfo();
		
		c.getCurrentShoppingCart().setPanierValid(true);
		c.calculateShoppingCart();
		c.displayCustomerInfo();
	
		
	
		System.out.println("\nLe prix du panier est de : " +
			c.getCurrentShoppingCart().calculerPrixPanier());
	}
}