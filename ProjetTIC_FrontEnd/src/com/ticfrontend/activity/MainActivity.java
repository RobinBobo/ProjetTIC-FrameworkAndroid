package com.ticfrontend.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.projettic.R;
import com.example.projettic.RegisterActivity;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    public void init(){
    	// Bouton Mon Compte
    	Button account = (Button) findViewById(R.id.boutonCompte);
    	account.setOnClickListener(new OnClickListener() {
    		@Override
 			public void onClick(View arg0) {
    			startAnActivity(AccountActivity.class);
 			}
 		});
    	
    	Button cart = (Button) findViewById(R.id.boutonPanier);
    	cart.setOnClickListener(new OnClickListener() {
    		@Override
 			public void onClick(View arg0) {
    			startAnActivity(CartActivity.class);
 			}
 		});
    	
    	Button login = (Button) findViewById(R.id.boutonConnexion);
    	login.setOnClickListener(new OnClickListener() {
    		@Override
 			public void onClick(View arg0) {
    			startAnActivity(LoginActivity.class);
 			}
 		});
    	
    	Button register = (Button) findViewById(R.id.boutonEnregistrer);
    	register.setOnClickListener(new OnClickListener() {
    		@Override
 			public void onClick(View arg0) {
    			startAnActivity(RegisterActivity.class);
 			}
 		});
    }

    public <T> void startAnActivity(Class<T> activity){
    	Intent intent = new Intent(MainActivity.this, activity);
    	startActivity(intent);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        
        if (id == R.id.connexion) {
        	startAnActivity(LoginActivity.class);
            return true;
        }else if (id == R.id.enregister) {
        	startAnActivity(RegisterActivity.class);
            return true;
        }else if (id == R.id.mon_compte) {
        	startAnActivity(AccountActivity.class);
            return true;
        }else if (id == R.id.mon_panier) {
        	startAnActivity(CartActivity.class);
            return true; 
        }else if (id == R.id.action_settings) {	
            return true;
        }else if (id == R.id.a_propos) {
        	startAnActivity(AboutActivity.class);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
