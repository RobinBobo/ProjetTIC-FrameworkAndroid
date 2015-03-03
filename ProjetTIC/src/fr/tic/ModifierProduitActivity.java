package fr.tic;

import plurals.Catalogue;
import android.app.Activity;
import android.os.Bundle;

public class ModifierProduitActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_modifierproduit);
		
		// Récupération des données
		Bundle b     = getIntent().getExtras();
		final Catalogue monCatalogue    = b.getParcelable("monCatalogue");
		
	}

}
