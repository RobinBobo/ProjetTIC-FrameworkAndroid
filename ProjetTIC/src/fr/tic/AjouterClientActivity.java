package fr.tic;

import plurals.ListeClients;
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
		
		Bundle b    = getIntent().getExtras();
		final ListeClients mesClients    = b.getParcelable("mesClients");
	    System.out.println(mesClients);
		
		final Button ajoutClient = (Button) findViewById(R.id.btnAjoutClient);
		ajoutClient.setOnClickListener(new OnClickListener() {
					
			public void onClick(View v) {
				boolean valide = false;
				CharSequence id = ((TextView) findViewById(R.id.idClient)).getText();
				RadioGroup radioSexe = (RadioGroup) findViewById(R.id.radioSexe);
				boolean sexe = (radioSexe.getCheckedRadioButtonId() == R.id.radioHomme) ? true : false;
				CharSequence nom = ((TextView) findViewById(R.id.nomClient)).getText();
				CharSequence prenom = ((TextView) findViewById(R.id.prenomClient)).getText();
				CharSequence adresse = ((TextView) findViewById(R.id.adresseClient)).getText();
				if (id != null && nom != null && prenom != null && adresse != null) {
					valide = true;
				}
				
				if (valide) {
					Client c = new Client(Integer.parseInt(id.toString()) , nom.toString(),prenom.toString(),
							adresse.toString(), sexe);
					mesClients.ajouterClient(c);
				}
			}
		});
	}

}
