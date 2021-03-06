package com.ticfrontend.activity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParserException;

import com.example.projettic.R;
import com.ticfrontend.configuratormanagement.Configurator;
import com.ticfrontend.configuratormanagement.XmlLoader;
import com.ticfrontend.magasin.Categorie;
import com.ticfrontend.magasin.Client;
import com.ticfrontend.magasin.Produit;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
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
	private Activity activity;

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
	
	public AccountFragment() {}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		this.rootView = inflater.inflate(R.layout.fragment_account, container, false);
		this.clientActuel = MainActivity.CLIENT_ACTUEL;
		this.activity = getActivity();
		
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
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState)
	{
		super.onActivityCreated(savedInstanceState);
		this.activity.setTitle(R.string.title_fragment_account);
	}
	
	private void init() {
		login.setText("Login : " + clientActuel.getLogin());
		mdp1.setText("Mot de passe : " + clientActuel.getMdpClient());
		nomClient.setText("Nom : " + clientActuel.getNomClient());
		prenomClient.setText("Pr�nom : " + clientActuel.getPrenomClient());
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
					radioButtonH.setChecked(true);
					radioButtonF.setChecked(false);					
				} else {
					radioButtonF.setChecked(true);
					radioButtonH.setChecked(false);
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
				
				// Remettre les textes par d�faut sur les TextView
				login.setText("Login : ");
				mdp1.setText("Mot de passe : ");
				nomClient.setText("Nom : ");
				prenomClient.setText("Pr�nom : ");
				adresseClient.setText("Adresse postale : ");
				adresseMail.setText("Adresse mail : ");
				sexe.setText("Sexe : ");
				
				// On change le bouton de nom et on change le onclick sur ce bouton
				Button valider = (Button) rootView.findViewById(R.id.boutonValider);
				valider.setText("Valider les modifications");
				valider.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						// TODO Demander validation
						// Modifier les informations du client actuel, sur la BDD et dans l'appli
						// Revenir sur l'�cran d'acceuil
						
						if(editTextLogin.getText().length() > 0 && editTextNomClient.getText().length() > 0 
								&& editTextPrenomClient.getText().length() > 0 && editTextAdresseClient.getText().length() > 0 
								&& editTextAdresseMail.getText().length() > 0 && editTextMdp1.getText().length() > 0 
								&& editTextMdp2.getText().length() > 0){
							
							if(editTextMdp1.getText().length() > 5){
								if(editTextMdp1.getText().toString().equals(editTextMdp2.getText().toString())){

									AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
									builder.setTitle("Modification du compte");
									builder.setMessage("Etes-vous s�r de vouloir enregister ces nouvelles informations ?");
									builder.setCancelable(true);
									builder.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
										public void onClick(DialogInterface dialog, int id) {
											dialog.cancel();
											
											boolean sexeClient;
											if(radioButtonF.isChecked())
												sexeClient = false;
											else
												sexeClient = true;
											
											clientActuel.setLogin(editTextLogin.getText().toString());
											clientActuel.setNomClient(editTextNomClient.getText().toString());
											clientActuel.setPrenomClient(editTextPrenomClient.getText().toString());
											clientActuel.setAdresseMail(editTextAdresseMail.getText().toString());
											clientActuel.setAdresseClient(editTextAdresseClient.getText().toString());
											clientActuel.setMdpClient(editTextMdp1.getText().toString());
											clientActuel.setSexeClient(sexeClient);
											
											
											File xmlToLoad = new File(Environment.getExternalStorageDirectory(), "client.xml");
											XmlLoader x = new XmlLoader ();										
																		
											try {
												x.createCustomer(xmlToLoad,MainActivity.LISTCLIENT);												
											} catch (FileNotFoundException e) {
												e.printStackTrace();
											} catch (IllegalArgumentException e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											} catch (IllegalStateException e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											} catch (XmlPullParserException e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											} catch (IOException e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}

											
											// Modification du client dans la BDD
											// TODO
											
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
									AlertDialog.Builder builder = new AlertDialog.Builder(activity);
									builder.setTitle("Modification du compte");
									builder.setMessage("Les mots de passe sont diff�rents !");
									builder.setCancelable(true);
									builder.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
										public void onClick(DialogInterface dialog, int id) {
											dialog.cancel();
										}
									});
									AlertDialog alert = builder.create();
									alert.show();
								}						
							} else {
								AlertDialog.Builder builder = new AlertDialog.Builder(activity);
								builder.setTitle("Modification du compte");
								builder.setMessage("Le mot de passe doit contenir au moins 6 caract�res !");
								builder.setCancelable(true);
								builder.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog, int id) {
										dialog.cancel();
									}
								});
								AlertDialog alert = builder.create();
								alert.show();
							}							
						} else {
							AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
							builder.setTitle("Modification du compte");
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
