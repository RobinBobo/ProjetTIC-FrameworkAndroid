package activities;

import fr.tic.R;
import plurals.Catalogue;
import plurals.ListeClients;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import beans.Client;

public class AjouterClientActivity extends Activity{
	
	private ListeClients mesClients;
	
	public ListeClients getMesClients() {
		return mesClients;
	}

	public void setMesClients(ListeClients mesClients) {
		this.mesClients = mesClients;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ajouterclient);
		
		Intent intent = this.getIntent();
		
		setMesClients((ListeClients) intent.getSerializableExtra("mesClients"));
		
		final TextView msgErreur = (TextView) findViewById(R.id.msgErreur);
		
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
				if (!id.toString().matches("") && !nom.toString().matches("") && !prenom.toString().matches("") 
						&& !adresse.toString().matches("")) {
					valide = true;
				} else msgErreur.setText("Veuillez saisir tous les champs !");
				
				if (valide) {
					Client c = new Client(Integer.parseInt(id.toString()) , nom.toString(),prenom.toString(),
							adresse.toString(), sexe);
					mesClients.ajouterClient(c);
					MainActivity.getMesClients().ajouterClient(c);
					msgErreur.setText("Le client a bien été ajouté !");
				}
			}
		});
	}

}
