package plurals;

import java.io.Serializable;
import java.util.ArrayList;

import model.DAOFactory;
import beans.Produit;

@SuppressWarnings("serial")
public class Catalogue implements Serializable{

	private ArrayList<Produit> o_mesProduits = new ArrayList<Produit>();
	
	public Catalogue(){}

	public ArrayList<Produit> getMesProduits() {
		return o_mesProduits;
	}

	
	public void ajouterProduitCatalogue(Produit theProduit){
		if(!this.getMesProduits().contains(theProduit)){
			this.o_mesProduits.add(theProduit);
			//DAOFactory.getProduitDAO().create(theProduit);
		}else{
			//TODO : generate error "Already exists"
		}		
	}
	
	public Produit rechercherProduit(int id) {
		Produit pdt = null;
		for(int i=0; i<o_mesProduits.size(); i++) {
			if (o_mesProduits.get(i).getIdProduit() == id) {
				pdt = o_mesProduits.get(i);		
			}
		}
		return pdt;		
	}
	
	public void supprimerProduitCatalogue(Produit theProduit){
		if(this.o_mesProduits.contains(theProduit)){
			this.o_mesProduits.remove(theProduit);
			theProduit.supprimerProduit();
		}else{
			//TODO : generate error "Not found"
		}
	}
	
	//On récupère les produits de la base et on les crée :
	public void findProduits(){
		boolean read = true;
		ArrayList<Produit> mesProduits = new ArrayList<Produit>();
		int id = 1;
		
		//Je lis la base. Si j'ai une réponse, j'incrément l'ID, sinon je quitte et je retourne la liste
		while(read){
			try{
				mesProduits.add(DAOFactory.getProduitDAO().find(id));
			}catch(Exception e){
				read = false;
				e.printStackTrace();
			}
			
			id++;
		}
		
			o_mesProduits = mesProduits;
	}
	
	public void ajoutObserver(ListeClients theClients){
		for(Produit p : o_mesProduits){
			p.addObserver(theClients);
		}
	}

	  //
	 // Création d'objets pour le passage entre activités
	//
//	
//	public Catalogue(Parcel in)
//  {
//      this.getFromParcel(in);
//  }
//
//  @SuppressWarnings("rawtypes")
//  public static final Parcelable.Creator CREATOR = new Parcelable.Creator()
//  {
//      public Catalogue createFromParcel(Parcel in)
//      {
//          return new Catalogue(in);
//      }
//
//      @Override
//      public Object[] newArray(int size) {
//          return null;
//      }
//  };
//
//  @Override
//  public int describeContents() {
//      return 0;
//  }
//
//  @Override
//  public void writeToParcel(Parcel dest, int flags)
//  {
//      //Taille de la liste
//      int size = o_mesProduits.size();
//      dest.writeInt(size);
//      for(int i=0; i < size; i++)
//      {
//          Produit pdt = o_mesProduits.get(i); //On vient lire chaque objet personne
//          dest.writeLong(pdt.getIdProduit());
//          dest.writeString(pdt.getNomProduit());
//          dest.writeDouble(pdt.getPrixProduit());
//          dest.writeString(pdt.getDescriptionProduit());
//          dest.writeValue(pdt.getCategorieProduit());
//          dest.writeString(pdt.getMarqueProduit());
//          dest.writeInt(pdt.getStockProduit());
//      }
//  }
//
//  public void getFromParcel(Parcel in)
//  { 
//      //Récupération du nombre d'objet
//      int size = in.readInt();
//
//      //On repeuple la liste avec de nouveau objet
//      for(int i = 0; i < size; i++)
//      {
//          Produit pdt = new Produit();
//          pdt.setIdProduit((int) in.readInt());
//          pdt.setNomProduit(in.readString());
//          pdt.setPrixProduit(in.readDouble());
//          pdt.setCategorieProduit(in.readString());
//          pdt.setMarqueProduit(in.readString());
//          pdt.setStockProduit(in.readInt());
//          o_mesProduits.add(pdt);
//      }
//
//  }	
//	
}
