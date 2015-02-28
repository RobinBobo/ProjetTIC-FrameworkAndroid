package com.ticfrontend.magasin;

import java.util.Date;

public class Avis {
	
	private Client client;
	private int note;
	private Date date;
	private String description;
	
	public Avis(Client c, int note, String descr){
		this.setClient(c);
		this.setNote(note);
		this.setDate(new Date());
		this.setDescription(descr);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getNote() {
		return note;
	}

	public void setNote(int note) {
		this.note = note;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
