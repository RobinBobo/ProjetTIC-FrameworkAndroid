package plurals;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import model.DAOFactory;
import android.os.Parcel;
import android.os.Parcelable;
import beans.Client;
import beans.Panier;
import beans.Produit;

public class ListeClients implements Observer, Parcelable{

	ArrayList<Client> o_mesClients = new ArrayList<Client>();
	
	public ListeClients(){}
	
	public ArrayList<Client> getListeClients(){
		return o_mesClients;
	}
	
	//Crée un nouveau client. L'ajoute dans la base.
	public void ajouterClient(Client theClient){
		if(!this.o_mesClients.contains(theClient)){
			this.o_mesClients.add(theClient);
			//DAOFactory.getClientDAO().create(theClient);
		}else{
			//TODO : generate error "Already exists"
		}
	}
	
	//Supprime un client. Le supprime de la base
	public void supprimClient(Client theClient){
		if(this.o_mesClients.contains(theClient)){
			this.o_mesClients.remove(theClient);
			//DAOFactory.getClientDAO().delete(theClient);
		}else{
			//TODO : generate error "Not found"
		}
	}
	
	//On récupère les clients de la base et on les crée :
	public void findClients(){
		boolean read = true;
		ArrayList<Client> mesClients = new ArrayList<Client>();
		int id = 1;
		
		//Je lis la base. Si j'ai une réponse, j'incrément l'ID, sinon je quitte et je retourne la liste
		while(read){
			try{
				mesClients.add(DAOFactory.getClientDAO().find(id));
			}catch(Exception e){
				read = false;
				e.printStackTrace();
			}
			
			id++;
		}
		
			o_mesClients = mesClients;
	
	}

	//Lorsqu'un produit est supprimé, cherche dans chaque panier de chaque 
	//client et appelle supprimerProduitPanier(Produit) :
	@Override
	public void update(Observable arg0, Object arg1) {
		Produit p = (Produit) arg0;
		for(Client c : o_mesClients){
			for(Panier pa : c.getMesPanier()){
				pa.supprimerProduitPanier(p);
			}
		}
		
			
	}

	  //
	 // Création d'objets pour le passage entre activités
	//
	
	public ListeClients(Parcel in)
    {
        this.getFromParcel(in);
    }
 
    @SuppressWarnings("rawtypes")
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator()
    {
        public ListeClients createFromParcel(Parcel in)
        {
            return new ListeClients(in);
        }
 
        @Override
        public Object[] newArray(int size) {
            return null;
        }
    };
 
    @Override
    public int describeContents() {
        return 0;
    }
 
    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        //Taille de la liste
        int size = o_mesClients.size();
        dest.writeInt(size);
        for(int i=0; i < size; i++)
        {
            Client cli = o_mesClients.get(i); //On vient lire chaque objet personne
            dest.writeLong(cli.getIdClient());
            dest.writeString(cli.getNomClient());
            dest.writeString(cli.getPrenomClient());
            dest.writeString(cli.getAdresseClient());
        }
    }
 
    public void getFromParcel(Parcel in)
    { 
        //Récupération du nombre d'objet
        int size = in.readInt();
 
        //On repeuple la liste avec de nouveau objet
        for(int i = 0; i < size; i++)
        {
            Client cli = new Client();
            cli.setIdClient((int) in.readLong());
            cli.setNomClient(in.readString());
            cli.setPrenomClient(in.readString());
            cli.setAdresseClient(in.readString());
            o_mesClients.add(cli);
        }
 
    }
	
}
