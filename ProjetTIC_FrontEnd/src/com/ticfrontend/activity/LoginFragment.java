package com.ticfrontend.activity;

import com.example.projettic.R;
import com.ticfrontend.magasin.Client;
import com.ticfrontend.magasin.Panier;
import com.ticfrontend.magasin.Produit;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class LoginFragment extends Fragment {

	private View rootView;
	private Activity activity;

	private EditText login;
	private EditText password;
	
	static final String EXTRA_KEY_USER = "EXTRA_KEY_USER";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		this.rootView = inflater.inflate(R.layout.activity_login, container, false);
		this.activity = this.getActivity();
		init();

		return rootView;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}


	public void init(){
		// Connexion de l'utilisateur
		// Puis changement des boutons de l'affichage principal
		login = (EditText) rootView.findViewById(R.id.editTextLoginUser);
		password = (EditText) rootView.findViewById(R.id.editTextPassUser);

		Button validerLogin = (Button) rootView.findViewById(R.id.boutonValiderLogin);
		validerLogin.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(login.getText().toString().length() > 0 && password.getText().toString().length() > 0){
					MainActivity.ISCONNECTED = true;	
					Intent intent = activity.getIntent();
					Client c = new Client(login.getText().toString(), "John");
					MainActivity.CLIENT_ACTUEL = c;
					
					CartFragment.PANIER_CLIENT = new Panier();

//					for(int i = 0; i < MainActivity.LISTPRODUITBETA.size(); i++)
//						CartFragment.PANIER_CLIENT.ajouterDansPanier(MainActivity.LISTPRODUITBETA.get(i), 1);
					
					intent.putExtra(EXTRA_KEY_USER, c.getNomClient() + c.getPrenomClient());
					activity.finish();
					startActivity(intent);
				}
			}
		});

		CheckBox checkShowPass = (CheckBox) rootView.findViewById(R.id.showPass);
		checkShowPass.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean isCheck) {
				if(!isCheck)
					password.setTransformationMethod(PasswordTransformationMethod.getInstance());
				else
					password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());					
			}
		});
	}
}
