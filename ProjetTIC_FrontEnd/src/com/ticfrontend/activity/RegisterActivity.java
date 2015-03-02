package com.ticfrontend.activity;

import com.example.projettic.R;
import com.ticfrontend.magasin.Client;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends Activity {
	
	private Context context = null;
	
	private EditText login;
	private EditText nomClient;
	private EditText prenomClient;
	private EditText adresseClient;
	private EditText sexeClient;
	private EditText adresseMail;
	private EditText mdp1;
	private EditText mdp2;
	private Button valider;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		
		context = this;
		
		login = (EditText) findViewById(R.id.editTextRegId);
		nomClient = (EditText) findViewById(R.id.editTextRegLastname);
		prenomClient = (EditText) findViewById(R.id.editTextRegFirstname);
		adresseClient = (EditText) findViewById(R.id.editTextRegAdresse);
		adresseMail = (EditText) findViewById(R.id.editTextRegMail);
		mdp1 = (EditText) findViewById(R.id.editTextRegPass);
		mdp2 = (EditText) findViewById(R.id.editTextRegPassVerif);
		
		valider = (Button) findViewById(R.id.boutonValiderLogin);
		
		valider.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(login.getText().length() > 0 && nomClient.getText().length() > 0 
						&& prenomClient.getText().length() > 0 && adresseClient.getText().length() > 0 
						&& adresseMail.getText().length() > 0 && mdp1.getText().length() > 0 
						&& mdp2.getText().length() > 0){
					
					Client c = new Client(login.toString(), nomClient.toString(), prenomClient.toString(), adresseClient.toString(), adresseMail.toString(), true);
					// Ajout dans la BDD
					// TODO
					
					// On récupère l'instance dans l'activité principale
					MainActivity.CLIENT_ACTUEL = c;
					
					// On dit que le client est connecté
					LoginActivity.ISCONNECTED = true;
					
					onBackPressed();
					
				} else {
					AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
		            builder1.setMessage("Il manque des informations !");
		            builder1.setCancelable(true);
		            builder1.setNeutralButton("No", new DialogInterface.OnClickListener() {
		                public void onClick(DialogInterface dialog, int id) {
		                    dialog.cancel();
		                }
		            });

		            AlertDialog alert11 = builder1.create();
		            alert11.show();
				}
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.register, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
//		int id = item.getItemId();
//		if (id == R.id.action_settings) {
//			return true;
//		}
		return super.onOptionsItemSelected(item);
	}
}
