package fr.tic;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import beans.Client;

public class AjouterClientActivity extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ajouterclient);
		
		final Button ajoutClient = (Button) findViewById(R.id.btnAjoutClient);
		ajoutClient.setOnClickListener(new OnClickListener() {
					
			public void onClick(View v) {
				boolean valide = false;
				String id = (String) ((TextView) findViewById(R.id.idClient)).getText();
				RadioGroup radioSexe = (RadioGroup) findViewById(R.id.radioSexe);
				boolean sexe = (radioSexe.getCheckedRadioButtonId() == R.id.radioHomme) ? true : false;
				String nom = (String) ((TextView) findViewById(R.id.nomClient)).getText();
				String prenom = (String) ((TextView) findViewById(R.id.prenomClient)).getText();
				String adresse = (String) ((TextView) findViewById(R.id.adresseClient)).getText();
				if (id != null && nom != null && prenom != null && adresse != null) {
					valide = true;
				}
				
				if (valide) {
					Client c = new Client(Integer.parseInt(id) , nom, prenom,
							adresse, sexe);
					// TODO : Ajout dans la base ??? Accès à la liste de client ?
				}
			}
		});
	}

}
