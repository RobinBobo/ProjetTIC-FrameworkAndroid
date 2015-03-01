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
	private String description;
	
	public Avis(){}
	
	public Avis(Client c, double note,Date date, String desc){
		this.client = c;
		this.note = note;
		this.date = date;
		this.description = desc;
	}
	
	public Avis(Client c, double note, String descr){
		this.setClient(c);
		this.setNote(note);
		//this.setDate(new Date());
		this.setDescription(descr);
	}

	public void setDescription(String description) {this.description = description;}
	public void setNote(double note) {	this.note = note;}
	public void setClient(Client client) {this.client = client;}
	public void setDate(Date date) {this.date = date;}
	
	public String getDescription() {return description;	}
	public double getNote() {return note;}
	public Client getClient() {	return client;}
	public Date getDate() {	return date;}
	
	public static List<Avis> getAListOfReviews1(){
		List<Avis> listeAvis = new ArrayList<Avis>();
		
		listeAvis.add(new Avis(new Client(),1, "Bon produit"));
		listeAvis.add(new Avis(new Client(),1, "Mauvais Produit"));
		listeAvis.add(new Avis(new Client(),1, "Satisfaisant"));
		listeAvis.add(new Avis(new Client(),1, "Excellent, fortement conseillé!"));
		
		return listeAvis;
	}
	
	public static List<Avis> getAListOfReviews2(){
		List<Avis> listeAvis = new ArrayList<Avis>();
		
		listeAvis.add(new Avis(new Client(),3, "Bon produit"));
		listeAvis.add(new Avis(new Client(),1, "Mauvais Produit"));
		listeAvis.add(new Avis(new Client(),2, "Satisfaisant"));
		listeAvis.add(new Avis(new Client(),4.5, "Excellent, fortement conseillé!"));
		
		return listeAvis;
	}
}
