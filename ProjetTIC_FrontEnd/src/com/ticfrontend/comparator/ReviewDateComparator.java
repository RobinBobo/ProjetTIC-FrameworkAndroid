package com.ticfrontend.comparator;
import java.util.Comparator;

import com.ticfrontend.magasin.Avis;

public class ReviewDateComparator implements Comparator<Avis> {

	@Override
	public int compare(Avis a1, Avis a2) {
		int res = a1.getDate().compareTo(a2.getDate());
		return res;
	}

}