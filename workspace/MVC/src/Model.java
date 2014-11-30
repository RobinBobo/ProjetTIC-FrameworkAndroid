import java.util.Observable;

public class Model extends Observable{
	
	String valeur;

	public String getValeur() {
		return valeur;
	}

	public void setValeur(String valeur) {
		this.valeur = valeur;
		System.out.println("Nouvelle valeur:"+valeur);
		this.setChanged();
		this.notifyObservers();
	}

}
