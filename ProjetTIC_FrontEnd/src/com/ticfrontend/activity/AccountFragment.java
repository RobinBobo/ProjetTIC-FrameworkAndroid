package com.ticfrontend.activity;

import com.example.projettic.R;
import com.ticfrontend.magasin.Client;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class AccountFragment extends Fragment {

	private View rootView;

	private Client clientActuel = null;
	
	private TextView login = null;
	private TextView nomClient = null;
	private TextView prenomClient = null;
	private TextView adresseClient = null;
	private TextView sexe = null;
	private TextView adresseMail = null;
	private TextView mdp1 = null;
	private TextView mdp2 = null;
	
	private EditText editTextLogin = null;
	private EditText editTextNomClient = null;
	private EditText editTextPrenomClient = null;
	private EditText editTextAdresseClient = null;
	
	private EditText editTextAdresseMail = null;
	private EditText editTextMdp1 = null;
	private EditText editTextMdp2 = null;
	
	TextView text = null;
	
	private RadioGroup radioGroup = null;
	private RadioButton radioButtonF = null;
	private RadioButton radioButtonH = null;
	
	public AccountFragment() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		this.rootView = inflater.inflate(R.layout.fragment_account, container, false);
		this.clientActuel = MainActivity.CLIENT_ACTUEL;
		
		login = (TextView) rootView.findViewById(R.id.accountLoginUser);
		nomClient = (TextView) rootView.findViewById(R.id.accountLastName);
		prenomClient = (TextView) rootView.findViewById(R.id.accountFirstName);
		adresseClient = (TextView) rootView.findViewById(R.id.accountAdresse);
		adresseMail = (TextView) rootView.findViewById(R.id.accountMail);
		mdp1 = (TextView) rootView.findViewById(R.id.accountMDP1);
		mdp2 = (TextView) rootView.findViewById(R.id.accountMDP2);
		sexe = (TextView) rootView.findViewById(R.id.accountSexe);
		
		init();
		
		return rootView;
	}

	private void init() {
		login.setText("Login : " + clientActuel.getLogin());
		mdp1.setText("Mot de passe : " + clientActuel.getMdpClient());
		nomClient.setText("Nom : " + clientActuel.getNomClient());
		prenomClient.setText("Prénom : " + clientActuel.getPrenomClient());
		adresseClient.setText("Adresse postale : " + clientActuel.getAdresseClient());
		adresseMail.setText("Adresse mail : " + clientActuel.getAdresseMail());
		sexe.setText("Sexe : " + clientActuel.getSexeClientToString());
		
		Button valider = (Button) rootView.findViewById(R.id.boutonValider);
		valider.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) { 
				// Afficher tous les EditText et TextView (mdpVerif) et RadioButton (sexe)
				
				radioGroup = (RadioGroup) rootView.findViewById(R.id.radioAccountSexe);
				radioGroup.setVisibility(View.VISIBLE);
				
				radioButtonF = (RadioButton) rootView.findViewById(R.id.radioAccountFemme);
				radioButtonF.setVisibility(View.VISIBLE);
				
				radioButtonH = (RadioButton) rootView.findViewById(R.id.radioAccountHomme);
				radioButtonH.setVisibility(View.VISIBLE);
				
				if(clientActuel.getSexeClient()){
					radioButtonH.setSelected(true);
					radioButtonF.setSelected(false);					
				} else {
					radioButtonF.setSelected(true);
					radioButtonH.setSelected(false);
				}
				
				text = (TextView) rootView.findViewById(R.id.accountMDP2);
				text.setVisibility(View.VISIBLE);
				
				editTextLogin = (EditText) rootView.findViewById(R.id.editTextAccountId);
				editTextLogin.setVisibility(View.VISIBLE);
				
				editTextMdp1 = (EditText) rootView.findViewById(R.id.editTextAccountPass);
				editTextMdp1.setVisibility(View.VISIBLE);
				
				editTextMdp2 = (EditText) rootView.findViewById(R.id.editTextAccountPassVerif);
				editTextMdp2.setVisibility(View.VISIBLE);
				
				editTextNomClient = (EditText) rootView.findViewById(R.id.editTextAccountLastname);
				editTextNomClient.setVisibility(View.VISIBLE);
				
				editTextPrenomClient = (EditText) rootView.findViewById(R.id.editTextAccountFirstname);
				editTextPrenomClient.setVisibility(View.VISIBLE);
				
				editTextAdresseClient = (EditText) rootView.findViewById(R.id.editTextAccountAdresse);
				editTextAdresseClient.setVisibility(View.VISIBLE);
				
				editTextAdresseMail = (EditText) rootView.findViewById(R.id.editTextAccountMail);
				editTextAdresseMail.setVisibility(View.VISIBLE);
				
				// Remettre les textes par défaut sur les TextView

				//login = (TextView) rootView.findViewById(R.id.accountLoginUser);
				login.setText("Login : ");
				
				//text = (TextView) rootView.findViewById(R.id.accountMDP1);
				mdp1.setText("Mot de passe : ");
				
				//text = (TextView) rootView.findViewById(R.id.accountLastName);
				nomClient.setText("Nom : ");
				
				//text = (TextView) rootView.findViewById(R.id.accountFirstName);
				prenomClient.setText("Prénom : ");
				
				//text = (TextView) rootView.findViewById(R.id.accountAdresse);
				adresseClient.setText("Adresse postale : ");
				
				//text = (TextView) rootView.findViewById(R.id.accountMail);
				adresseMail.setText("Adresse mail : ");
				
				//text = (TextView) rootView.findViewById(R.id.accountSexe);
				sexe.setText("Sexe : ");
				
				// On change le bouton de nom et on change le onclick sur ce bouton
				Button valider = (Button) rootView.findViewById(R.id.boutonValider);
				valider.setText("Valider les modifications");
				valider.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						// TODO Demander validation
						// Modifier les informations du client actuel, sur la BDD et dans l'appli
						// Revenir sur l'écran d'acceuil
						
						if(editTextLogin.getText().length() > 0 && editTextNomClient.getText().length() > 0 
								&& editTextPrenomClient.getText().length() > 0 && editTextAdresseClient.getText().length() > 0 
								&& editTextAdresseMail.getText().length() > 0 && editTextMdp1.getText().length() > 0 
								&& editTextMdp2.getText().length() > 0){
							
							AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
							builder.setTitle("Modification du compte");
				            builder.setMessage("Etes-vous sûr de vouloir enregister ces nouvelles informations ?");
				            builder.setCancelable(true);
				            builder.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
				                public void onClick(DialogInterface dialog, int id) {
				                	dialog.cancel();
				                	
				                	boolean sexeClient;
				                	
				                	if(radioButtonF.isActivated())
				                		sexeClient = false;
				                	else
				                		sexeClient = true;
				                	
				                	Client c = new Client(editTextLogin.getText().toString(), editTextNomClient.getText().toString(), editTextPrenomClient.getText().toString(), 
				                			editTextAdresseClient.getText().toString(), editTextAdresseMail.getText().toString(), sexeClient, editTextMdp1.getText().toString());
				                	// Modification du client dans la BDD
				                	// TODO
				                	
				                	// On récupère l'instance dans l'activité principale
				                	MainActivity.CLIENT_ACTUEL = c;
				                	
				                	// On dit que le client est connecté
				                	MainActivity.ISCONNECTED = true;
				                	
				                	Intent intent = getActivity().getIntent();
				                	getActivity().finish();
				                	startActivity(intent);				                    
				                }
				            });
				            builder.setNegativeButton("Non", new DialogInterface.OnClickListener() {
				                public void onClick(DialogInterface dialog, int id) {
				                    dialog.cancel();
				                }
				            });
				            
				            AlertDialog alert = builder.create();
				            alert.show();
							
						} else {
							AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
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
		});	
		
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);    
	}

}
