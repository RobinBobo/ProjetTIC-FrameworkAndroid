package activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import fr.tic.R;

public class ChoixNomSiteActivity extends Activity {
	
	private String nomSite;

	public String getNomSite() {
		return nomSite;
	}

	public void setNomSite(String nomSite) {
		this.nomSite = nomSite;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_choixnomsite);
		
		final EditText newNom = (EditText) findViewById(R.id.nomSite);
		final TextView msgErreur = (TextView) findViewById(R.id.msgErreur);
		
		// Ajout du nouveau nom
		final Button ajouterNom = (Button) findViewById(R.id.btnValider);
		ajouterNom.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				
				if (!newNom.getText().toString().matches("")) {
					nomSite = newNom.getText().toString();
					MainActivity.setNomSite(nomSite);
					Intent intent = new Intent(ChoixNomSiteActivity.this, MainActivity.class);
			        startActivity(intent);
				} else msgErreur.setText("Veuillez saisir un nom");
			}
		});
	}
}