package com.ticfrontend.comparator;
import java.util.Comparator;

import com.ticfrontend.magasin.Avis;

public class ReviewDateComparator implements Comparator<Avis> {

	@Override
	public int compare(Avis a1, Avis a2) {
		int res = (int) ((int) a2.getNote() - a2.getNote());
		return res;
	}

}