package activities;

import fr.tic.R;
import android.app.Activity;
import android.graphics.Color;
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
		
		final TextView msgErreur = (TextView) findViewById(R.id.msgErreur);
		
		final Button ajoutClient = (Button) findViewById(R.id.btnAjoutClient);
		ajoutClient.setOnClickListener(new OnClickListener() {
					
			public void onClick(View v) {
				boolean valide = false;
				RadioGroup radioSexe = (RadioGroup) findViewById(R.id.radioSexe);
				boolean sexe = (radioSexe.getCheckedRadioButtonId() == R.id.radioHomme) ? true : false;
				CharSequence nom = ((TextView) findViewById(R.id.nomClient)).getText();
				CharSequence prenom = ((TextView) findViewById(R.id.prenomClient)).getText();
				CharSequence adresse = ((TextView) findViewById(R.id.adresseClient)).getText();
				if (!nom.toString().matches("") && !prenom.toString().matches("") 
						&& !adresse.toString().matches("")) {
					valide = true;
				} else {
					msgErreur.setText("Veuillez saisir tous les champs !");
					msgErreur.setTextColor(Color.RED);
				}
				
				if (valide) {
					Client c = new Client(nom.toString(),prenom.toString(),
							adresse.toString(), sexe);
					MainActivity.getMesClients().ajouterClient(c);
					msgErreur.setText("Le client a bien été ajouté !");
					msgErreur.setTextColor(Color.rgb(20, 148, 20));
				}
			}
		});
	}

}
