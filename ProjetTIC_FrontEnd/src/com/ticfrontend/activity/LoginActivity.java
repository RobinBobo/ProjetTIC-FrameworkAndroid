package com.ticfrontend.activity;

import android.app.Activity;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;

import com.example.projettic.R;

public class LoginActivity extends Activity {

	public static boolean ISCONNECTED = false;
	
	private EditText login;
	private EditText password;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		// Connexion de l'utilisateur
		// Puis changement des boutons de l'affichage principal
		login = (EditText) findViewById(R.id.editTextLoginUser);
		password = (EditText) findViewById(R.id.editTextPassUser);
		
		Button validerLogin = (Button) findViewById(R.id.boutonValiderLogin);
		validerLogin.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(login.getText().toString().length() > 0 && password.getText().toString().length() > 0){
					ISCONNECTED = true;	
					onBackPressed();				
				}
			}
		});
		
		CheckBox checkShowPass = (CheckBox) findViewById(R.id.showPass);
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.

		return super.onOptionsItemSelected(item);
	}
}
