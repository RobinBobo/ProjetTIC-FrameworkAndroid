package activities;

import java.util.ArrayList;

import beans.Client;
import fr.tic.R;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SupprimerClientActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_supprimerclient);
		
		// Récupération des données
		final EditText rechClient = (EditText) findViewById(R.id.rechClient);
		final EditText rechPrenom = (EditText) findViewById(R.id.prenomClient);
		final TextView msgErreur = (TextView) findViewById(R.id.msgErreur);
		Button btnSupprimer = (Button) findViewById(R.id.btnSupprimer);
		rechPrenom.setVisibility(View.INVISIBLE);
		
		btnSupprimer.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				if (rechPrenom.getText().toString().matches("")) {
					if (!rechClient.getText().toString().matches("")) {
						ArrayList<Client> resRech = MainActivity.getMesClients().rechClient(rechClient.getText().toString());
						if (resRech.size()==1) {
							MainActivity.getMesClients().supprimClient(resRech.get(0));
							msgErreur.setText("Le client '" + resRech.get(0).getNomClient() + " "
									+ resRech.get(0).getPrenomClient() +"' a bien été supprimé de la base");
							msgErreur.setTextColor(Color.rgb(20, 148, 20));
							rechClient.setText("");
						} else if (resRech.size()>1) {
							String liste = "Plusieurs clients correspondent à votre recherche, voici la liste : \n";
							for(int i=0; i<resRech.size(); i++) {
								liste += resRech.get(i).getNomClient()+  " " + resRech.get(i).getPrenomClient() + "\n";
							}
							liste += "Veuillez saisir le prénom du client à supprimer parmis cette liste : ";
							msgErreur.setText(liste);
							msgErreur.setTextColor(Color.rgb(20, 148, 20));
							rechPrenom.setVisibility(View.VISIBLE);
						} else {
							msgErreur.setText("Le client n'existe pas dans la base...");
							msgErreur.setTextColor(Color.RED);
						}
					} else {
						msgErreur.setText("Veuillez saisir un nom de client");
							msgErreur.setTextColor(Color.RED);
						}
					} else {
						if (MainActivity.getMesClients().supprimerClient(rechClient.getText().toString(), rechPrenom.getText().toString())) {
							msgErreur.setText("Le client '" + rechClient.getText().toString() + " " + rechPrenom.getText().toString() + "' a bien été supprimé.");
							msgErreur.setTextColor(Color.rgb(20, 148, 20));
							rechPrenom.setVisibility(View.INVISIBLE);
							rechPrenom.setText("");
							rechClient.setText("");
							
						} else {
							
						}
					}
					
				}
			});
	}

}
