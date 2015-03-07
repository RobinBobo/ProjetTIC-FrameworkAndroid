package com.ticfrontend.activity;

import com.example.projettic.R;
import com.ticfrontend.magasin.Client;
import com.ticfrontend.magasin.Panier;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class RegisterFragment extends Fragment {
	private View rootView;
	private Activity activity;
	
	private EditText login;
	private EditText nomClient;
	private EditText prenomClient;
	private EditText adresseClient;
	private EditText sexeClient;
	private EditText adresseMail;
	private EditText mdp1;
	private EditText mdp2;
	private Button valider;
	
	private RadioButton radioButtonF = null;
	private RadioButton radioButtonH = null;
	
	//static final String EXTRA_KEY_REGISTER = "EXTRA_KEY_REGISTER";
	static final String EXTRA_KEY_USER = "EXTRA_KEY_USER";
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        this.rootView = inflater.inflate(R.layout.activity_register, container, false);
        this.activity = this.getActivity();
              
        this.radioButtonF = (RadioButton) rootView.findViewById(R.id.radioFemme);
		this.radioButtonH = (RadioButton) rootView.findViewById(R.id.radioHomme);
        
		init();
        
        
        return rootView;
    }

	private void init() {
		login = (EditText) rootView.findViewById(R.id.editTextRegId);
		nomClient = (EditText) rootView.findViewById(R.id.editTextRegLastname);
		prenomClient = (EditText) rootView.findViewById(R.id.editTextRegFirstname);
		adresseClient = (EditText) rootView.findViewById(R.id.editTextRegAdresse);
		adresseMail = (EditText) rootView.findViewById(R.id.editTextRegMail);
		mdp1 = (EditText) rootView.findViewById(R.id.editTextRegPass);
		mdp2 = (EditText) rootView.findViewById(R.id.editTextRegPassVerif);
		
		valider = (Button) rootView.findViewById(R.id.boutonValiderLogin);
		
		valider.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(login.getText().length() > 0 && nomClient.getText().length() > 0 
						&& prenomClient.getText().length() > 0 && adresseClient.getText().length() > 0 
						&& adresseMail.getText().length() > 0 && mdp1.getText().length() > 0 
						&& mdp2.getText().length() > 0){
					
					boolean sexeClient;
                	
                	if(radioButtonF.isActivated())
                		sexeClient = false;
                	else
                		sexeClient = true;
					
					Client c = new Client(login.getText().toString(), nomClient.getText().toString(), prenomClient.getText().toString(), 
							adresseClient.getText().toString(), adresseMail.getText().toString(), sexeClient);
					// Ajout dans la BDD
					// TODO
					
					// On r�cup�re l'instance dans l'activit� principale
					MainActivity.CLIENT_ACTUEL = c;
					
					// On dit que le client est connect�
					MainActivity.ISCONNECTED = true;
					
					CartFragment.PANIER_CLIENT = new Panier();
//					for(int i = 0; i < MainActivity.LISTPRODUITBETA.size(); i++)
//						CartFragment.PANIER_CLIENT.ajouterDansPanier(MainActivity.LISTPRODUITBETA.get(i), 2);
						
					Intent intent = activity.getIntent();
					//intent.putExtra(EXTRA_KEY_REGISTER, login.getText().toString());
					intent.putExtra(EXTRA_KEY_USER, login.getText().toString());
					activity.finish();
					startActivity(intent);
					
				} else {
					AlertDialog.Builder builder = new AlertDialog.Builder(activity);
					builder.setTitle("Cr�tion d'un compte");
		            builder.setMessage("Il manque des informations !");
		            builder.setCancelable(true);
		            builder.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
		                public void onClick(DialogInterface dialog, int id) {
		                    dialog.cancel();
		                }
		            });
		            AlertDialog alert = builder.create();
		            alert.show();
				}
			}
		});
	}
}
