package com.ticfrontend.comparator;
import java.util.Comparator;

import com.ticfrontend.magasin.Avis;

public class ReviewDateComparator implements Comparator<Avis> {
	public static String DESC = "DESC";
	public static String ASC = "ASC";
	private static String SENS = null;
	
	public ReviewDateComparator(String sens){
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
			return a1.getDate().compareTo(a2.getDate());
		else if(SENS.equals(DESC))
			return a2.getDate().compareTo(a1.getDate());
		else 
			return 0;
	}
}