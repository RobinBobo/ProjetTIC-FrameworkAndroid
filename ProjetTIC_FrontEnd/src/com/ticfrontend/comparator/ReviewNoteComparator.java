package com.ticfrontend.comparator;

import java.util.Comparator;

import com.ticfrontend.magasin.Avis;

public class ReviewNoteComparator implements Comparator<Avis> {
	@Override
	public int compare(Avis a1, Avis a2) {
		return (int) (a1.getNote() - a2.getNote());
	}
}
