package com.ticfrontend.comparator;

import java.util.Comparator;

import com.ticfrontend.magasin.Avis;

public class ReviewNoteComparator implements Comparator<Avis> {
	public static String DESC = "DESC";
	public static String ASC = "ASC";
	private static String SENS = null;
	
	public ReviewNoteComparator(String sens){
		if(sens.equals(DESC))
			SENS = DESC;
		else if(sens.equals(ASC))
			SENS = ASC;
		else 
			SENS = ASC;
	}
	
	@Override
	public int compare(Avis a1, Avis a2) {
		if(SENS == null)
			SENS = ASC;
		
		if(SENS.equals(ASC))
			return (int) (a1.getNote() - a2.getNote());
		else if(SENS.equals(DESC))
			return (int) (a2.getNote() - a1.getNote());
		else 
			return 0;
	}
}
