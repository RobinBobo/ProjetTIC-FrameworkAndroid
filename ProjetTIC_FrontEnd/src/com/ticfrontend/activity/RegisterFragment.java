package com.ticfrontend.activity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.xmlpull.v1.XmlPullParserException;

import com.example.projettic.R;
import com.ticfrontend.configuratormanagement.XmlLoader;
import com.ticfrontend.magasin.Client;
import com.ticfrontend.magasin.Panier;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Environment;
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
        this.rootView = inflater.inflate(R.layout.fragment_register, container, false);
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
		
		// Init de la couleur sur les boutons
		GradientDrawable bgShape = (GradientDrawable) valider.getBackground();		
		int r = Integer.parseInt(MainActivity.COLORBUTTONSTRING.substring(0, 2), 16);
		int g = Integer.parseInt(MainActivity.COLORBUTTONSTRING.substring(2, 4), 16);
		int b = Integer.parseInt(MainActivity.COLORBUTTONSTRING.substring(4, 6), 16);
		bgShape.setColor(Color.argb(255, r, g, b)); 

		
		valider.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(login.getText().length() > 0 && nomClient.getText().length() > 0 
						&& prenomClient.getText().length() > 0 && adresseClient.getText().length() > 0 
						&& adresseMail.getText().length() > 0 && mdp1.getText().length() > 0 
						&& mdp2.getText().length() > 0){
					if(mdp1.getText().length() > 5){
						if(mdp1.getText().toString().equals(mdp2.getText().toString())){
							boolean sexeClient;
							
							if(radioButtonF.isChecked())
								sexeClient = false;
							else
								sexeClient = true;
							
							Client c = new Client(login.getText().toString(), nomClient.getText().toString(), prenomClient.getText().toString(), 
									adresseClient.getText().toString(), adresseMail.getText().toString(), sexeClient, mdp1.getText().toString());
							// Ajout dans la BDD
							// TODO
							
							// On récupère l'instance dans l'activité principale et on dit que le client est connecté
							MainActivity.CLIENT_ACTUEL = c;
							MainActivity.ISCONNECTED = true;
							CartFragment.PANIER_CLIENT = new Panier();
							
							// Ajout du nouveau client dans la LISTCLIENT
							if(c != null)
								MainActivity.LISTCLIENT.add(c);
							
							// ICI ROBINHO
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

							
							
							Intent intent = activity.getIntent();
							
							//						intent.putExtra(EXTRA_KEY_USER, login.getText().toString());
							activity.finish();
							startActivity(intent);					
						} else {
							AlertDialog.Builder builder = new AlertDialog.Builder(activity);
							builder.setTitle("Création d'un compte");
							builder.setMessage("Les mots de passe sont différents !");
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
						builder.setTitle("Création d'un compte");
						builder.setMessage("Le mot de passe doit contenir au moins 6 caractères !");
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
					builder.setTitle("Création d'un compte");
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
