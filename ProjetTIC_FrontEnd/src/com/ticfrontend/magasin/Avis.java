package com.ticfrontend.magasin;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Avis implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Client client;
	private double note;
	private Date date;
	private String titre;
	private String description;
	
	public Avis(){}
	
	public Avis(Client c, String titre, double note,Date date, String desc){
		this.client = c;
		this.setTitre(titre);
		this.note = note;
		this.date = date;
		this.description = desc;
	}
	
	public Avis(Client c, String titre, double note, String descr){
		this.setClient(c);
		this.setTitre(titre);
		this.setNote(note);
		this.setDate(new Date());
		this.setDescription(descr);
	}

	public Avis(Avis a){
		this.client = a.getClient();
		this.titre = a.getTitre();
		this.note = a.getNote();
		this.date = a.getDate();
		this.description = a.getDescription();
	}
	
	public void setDescription(String description) {this.description = description;}
	public void setNote(double note) {	this.note = note;}
	public void setClient(Client client) {this.client = client;}
	public void setDate(Date date) {this.date = date;}
	public void setTitre(String titre) {this.titre = titre;}
	
	public String getTitre() {return titre;}
	public String getDescription() {return description;	}
	public double getNote() {return note;}
	public Client getClient() {	return client;}
	public Date getDate() {	return date;}
	
//	public static List<Avis> getAListOfReviews1(){
//		List<Avis> listeAvis = new ArrayList<Avis>();
//		
//		listeAvis.add(new Avis(new Client("DOE","John",false), "Bon Produit", 4.5,new Date(), "content du produit, rien a rajouter"));
//		listeAvis.add(new Avis(new Client("RICHARD", "Thomas",true), "Mauvais" ,1.5,new Date(), "pas stable, pas ergonomique"));
//		listeAvis.add(new Avis(new Client("MICHEL","Jean",false),"Satisfaisant" ,3,new Date(), "passable, efficace"));
//		listeAvis.add(new Avis(new Client("OBAMA","Barrack",false),"Excellent produit! conseillé",5,new Date(), "haute gamme, super achat"));
//		listeAvis.add(new Avis(new Client("DOE","John",false), "Bon Produit", 4.5,new Date(), "content du produit, rien a rajouter"));
//		listeAvis.add(new Avis(new Client("RICHARD", "Thomas",true), "Mauvais" ,1.5,new Date(), "pas stable, pas ergonomique"));
//		listeAvis.add(new Avis(new Client("MICHEL","Jean",false),"Satisfaisant" ,3,new Date(), "passable, efficace"));
//		listeAvis.add(new Avis(new Client("OBAMA","Barrack",false),"Excellent produit! conseillé",5,new Date(), "haute gamme, super achat"));
//		listeAvis.add(new Avis(new Client("DOE","John",false), "Bon Produit", 4.5,new Date(), "content du produit, rien a rajouter"));
//		listeAvis.add(new Avis(new Client("RICHARD", "Thomas",true), "Mauvais" ,1.5,new Date(), "pas stable, pas ergonomique"));
//		listeAvis.add(new Avis(new Client("MICHEL","Jean",false),"Satisfaisant" ,3,new Date(), "passable, efficace"));
//		listeAvis.add(new Avis(new Client("OBAMA","Barrack",false),"Excellent produit! conseillé",5,new Date(), "haute gamme, super achat"));
//		listeAvis.add(new Avis(new Client("DOE","John",false), "Bon Produit", 4.5,new Date(), "content du produit, rien a rajouter"));
//		listeAvis.add(new Avis(new Client("RICHARD", "Thomas",true), "Mauvais" ,1.5,new Date(), "pas stable, pas ergonomique"));
//		listeAvis.add(new Avis(new Client("MICHEL","Jean",false),"Satisfaisant" ,3,new Date(), "passable, efficace"));
//		listeAvis.add(new Avis(new Client("OBAMA","Barrack",false),"Excellent produit! conseillé",5,new Date(), "haute gamme, super achat"));
//		listeAvis.add(new Avis(new Client("DOE","John",false), "Bon Produit", 4.5,new Date(), "content du produit, rien a rajouter"));
//		listeAvis.add(new Avis(new Client("RICHARD", "Thomas",true), "Mauvais" ,1.5,new Date(), "pas stable, pas ergonomique"));
//		listeAvis.add(new Avis(new Client("MICHEL","Jean",false),"Satisfaisant" ,3,new Date(), "passable, efficace"));
//		listeAvis.add(new Avis(new Client("OBAMA","Barrack",false),"Excellent produit! conseillé",5,new Date(), "haute gamme, super achat"));
//		listeAvis.add(new Avis(new Client("DOE","John",false), "Bon Produit", 4.5,new Date(), "content du produit, rien a rajouter"));
//		listeAvis.add(new Avis(new Client("RICHARD", "Thomas",true), "Mauvais" ,1.5,new Date(), "pas stable, pas ergonomique"));
//		listeAvis.add(new Avis(new Client("MICHEL","Jean",false),"Satisfaisant" ,3,new Date(), "passable, efficace"));
//		listeAvis.add(new Avis(new Client("OBAMA","Barrack",false),"Excellent produit! conseillé",5,new Date(), "haute gamme, super achat"));
//		listeAvis.add(new Avis(new Client("DOE","John",false), "Bon Produit", 4.5,new Date(), "content du produit, rien a rajouter"));
//		listeAvis.add(new Avis(new Client("RICHARD", "Thomas",true), "Mauvais" ,1.5,new Date(), "pas stable, pas ergonomique"));
//		listeAvis.add(new Avis(new Client("MICHEL","Jean",false),"Satisfaisant" ,3,new Date(), "passable, efficace"));
//		listeAvis.add(new Avis(new Client("OBAMA","Barrack",false),"Excellent produit! conseillé",5,new Date(), "haute gamme, super achat"));
//		return listeAvis;
//	}
	
	public static List<Avis> getAListOfReviewsLong(){
		List<Avis> listeAvis = new ArrayList<Avis>();
		
		listeAvis.add(new Avis(new Client("DOE","John",false), "Bon Produit", 4.5,new Date(), "content du produit, rien a rajouter"));
		listeAvis.add(new Avis(new Client("RICHARD", "Thomas",true), "Mauvais" ,1.5,new Date(), "pas stable, pas ergonomique"));
		listeAvis.add(new Avis(new Client("MICHEL","Jean",false),"Satisfaisant" ,3,new Date(), "passable, efficace"));
		listeAvis.add(new Avis(new Client("OBAMA","Barrack",false),"Excellent produit! conseillé",5,new Date(), "haute gamme, super achat"));
		listeAvis.add(new Avis(new Client("DOE","John",false), "Bon Produit", 4.5,new Date(), "content du produit, rien a rajouter"));
		listeAvis.add(new Avis(new Client("RICHARD", "Thomas",true), "Mauvais" ,1.5,new Date(), "pas stable, pas ergonomique"));
		listeAvis.add(new Avis(new Client("MICHEL","Jean",false),"Satisfaisant" ,3,new Date(), "passable, efficace"));
		listeAvis.add(new Avis(new Client("OBAMA","Barrack",false),"Excellent produit! conseillé",5,new Date(), "haute gamme, super achat"));
		listeAvis.add(new Avis(new Client("DOE","John",false), "Bon Produit", 4.5,new Date(), "content du produit, rien a rajouter"));
		listeAvis.add(new Avis(new Client("RICHARD", "Thomas",true), "Mauvais" ,1.5,new Date(), "pas stable, pas ergonomique"));
		listeAvis.add(new Avis(new Client("MICHEL","Jean",false),"Satisfaisant" ,3,new Date(), "passable, efficace"));
		listeAvis.add(new Avis(new Client("OBAMA","Barrack",false),"Excellent produit! conseillé",5,new Date(), "haute gamme, super achat"));
		listeAvis.add(new Avis(new Client("DOE","John",false), "Bon Produit", 4.5,new Date(), "content du produit, rien a rajouter"));
		listeAvis.add(new Avis(new Client("RICHARD", "Thomas",true), "Mauvais" ,1.5,new Date(), "pas stable, pas ergonomique"));
		listeAvis.add(new Avis(new Client("MICHEL","Jean",false),"Satisfaisant" ,3,new Date(), "passable, efficace"));
		listeAvis.add(new Avis(new Client("OBAMA","Barrack",false),"Excellent produit! conseillé",5,new Date(), "haute gamme, super achat"));
		listeAvis.add(new Avis(new Client("DOE","John",false), "Bon Produit", 4.5,new Date(), "content du produit, rien a rajouter"));
		listeAvis.add(new Avis(new Client("RICHARD", "Thomas",true), "Mauvais" ,1.5,new Date(), "pas stable, pas ergonomique"));
		listeAvis.add(new Avis(new Client("MICHEL","Jean",false),"Satisfaisant" ,3,new Date(), "passable, efficace"));
		listeAvis.add(new Avis(new Client("OBAMA","Barrack",false),"Excellent produit! conseillé",5,new Date(), "haute gamme, super achat"));
		
		return listeAvis;
	}
	
//	public static List<Avis> getAListOfReviews2(){
//		List<Avis> listeAvis = new ArrayList<Avis>();
//		
//		listeAvis.add(new Avis(new Client("DOE","John",false), "Bon Produit", 3,new Date(), "content du produit, rien a rajouter"));
//		listeAvis.add(new Avis(new Client("RICHARD", "Thomas",true), "Mauvais" , 4,new Date(), "pas stable, pas ergonomique"));
//		listeAvis.add(new Avis(new Client("MICHEL","Jean",false),"Satisfaisant" , 2,new Date(), "passable, efficace"));
//		listeAvis.add(new Avis(new Client("OBAMA","Barrack",false),"Excellent produit! conseillé",1,new Date(), "haute gamme, super achat"));
//		
//		return listeAvis;
//	}
//	
	public static List<Avis> getAListOfReviewsSmall(){
		List<Avis> listeAvis = new ArrayList<Avis>();
		
		listeAvis.add(new Avis(new Client("DOE","John",false), "Bon Produit", 3,new Date(), "content du produit, rien a rajouter"));
		listeAvis.add(new Avis(new Client("RICHARD", "Thomas",true), "Mauvais" , 4,new Date(), "pas stable, pas ergonomique"));
		listeAvis.add(new Avis(new Client("MICHEL","Jean",false),"Satisfaisant" , 2,new Date(), "passable, efficace"));
		listeAvis.add(new Avis(new Client("OBAMA","Barrack",false),"Excellent produit! conseillé",1,new Date(), "haute gamme, super achat"));
		
		return listeAvis;
	}
}
