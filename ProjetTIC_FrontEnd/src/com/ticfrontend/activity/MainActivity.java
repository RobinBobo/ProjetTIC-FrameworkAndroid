package com.ticfrontend.activity;

import com.example.projettic.R;
import com.ticfrontend.magasin.Avis;
import com.ticfrontend.magasin.Categorie;
import com.ticfrontend.magasin.Client;
import com.ticfrontend.magasin.Produit;
import com.ticfrontend.model.*;
import com.ticfrontend.adapter.*;
import com.ticfrontend.configuratormanagement.Configurator;
import com.ticfrontend.configuratormanagement.XmlLoader;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

@SuppressWarnings("deprecation")
public class MainActivity extends Activity {

	public static boolean ISCONNECTED = false;
	public static Client CLIENT_ACTUEL = null;

	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;

	// nav drawer title
	private CharSequence mDrawerTitle;
	// used to store app title
	private CharSequence mTitle;
	// slide menu items
	private String[] navMenuTitles;
	private TypedArray navMenuIcons;

	private ArrayList<NavDrawerItem> navDrawerItems;
	private NavDrawerListAdapter adapter;
		
	// Configuration venant du Back-End
	public static ArrayList<Produit> LISTPRODUIT = null;
	public static ArrayList<Client> LISTCLIENT = null;
	public static ArrayList<Categorie> LISTCATEGORIE = null;
	public static String WEBSITENAME = null;
	public static ColorDrawable COLORBUTTON = null;
	public static boolean AVIS = false;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		setConfiguration();
		
		initSlideMenu();
				
		//////////////////////////////////////////////////////
				
		if (savedInstanceState == null) {
			displayView(0);
		}
		
		checkConfiguration();
	}

	private void setConfiguration() {
		// Loading XML //	
		Configurator c = new Configurator ();
		File xmlToLoad = new File(Environment.getExternalStorageDirectory(), "configuration.xml");
		XmlLoader x = new XmlLoader ();
		LISTPRODUIT = new ArrayList<Produit>();
		LISTCLIENT = new ArrayList<Client>();
		LISTCATEGORIE = new ArrayList<Categorie>();
		
		try {
			x.load(new FileInputStream(xmlToLoad), c, LISTPRODUIT, LISTCATEGORIE);
			System.out.println("Suspens : " + c.getWebsiteName() + c.getOrder() + c.getCustomerNotice());
			Log.v("XML",c.getWebsiteName() + c.getOrder() + c.getCustomerNotice());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		// Si l'admin veut des avis
		if(AVIS){
			// On ajoute des avis fictif pour les produits que l'on vient de récupérer
			List<Avis> avisBeta = Avis.getAListOfReviewsBeta();
			List<Avis> avisBeta2 = Avis.getAListOfReviewsBeta2();
			for(int i = 0; i < LISTPRODUIT.size(); i++){
				if(i%2 == 0)
					LISTPRODUIT.get(i).setListeAvisProduit(avisBeta);
				else 
					LISTPRODUIT.get(i).setListeAvisProduit(avisBeta2);
				LISTPRODUIT.get(i).setIconRessource(getRandomImage());	
			}
		}
		
		if(c.getWebsiteName() != null)
			WEBSITENAME = c.getWebsiteName();
		//if(c.getButtonsColor() != null)
			COLORBUTTON = new ColorDrawable(c.getButtonsColor());
			
		Client client1 = new Client("florian2412", "Pussacq", "Florian", "La Fond Du Cros 24700 MENESPLET", "florian2412@gmail.com", true, "pupuce");
		Client client2 = new Client("tavash", "Sang", "Tavahiura", "TAHITI, Polynésie Française", "tava.sang@gmail.com", true, "tavabien");
//		Client client3 = new Client("florian2412", "Pussacq", "Florian", "La Fond Du Cros 24700 MENESPLET", "florian2412@gmail.com", false, "pupuce");
//		Client client4 = new Client("florian2412", "Pussacq", "Florian", "La Fond Du Cros 24700 MENESPLET", "florian2412@gmail.com", true, "pupuce");
//		Client client5 = new Client("florian2412", "Pussacq", "Florian", "La Fond Du Cros 24700 MENESPLET", "florian2412@gmail.com", true, "pupuce");
		
		LISTCLIENT.add(client1);
		LISTCLIENT.add(client2);
		//LISTCLIENT.add(client1);
	}

	private void checkConfiguration() {
		if(WEBSITENAME == null) {
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("Problème de configuration");
            builder.setMessage("Le fichier de configuration est incorrect (le nom du commerce n'est pas renseigné). Des problèmes peuvent subvenir si vous continuer. Continuer quand même ?");
            builder.setCancelable(true);
            builder.setPositiveButton("Continuer à mes risques et périls", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.cancel();
                    initSlideMenu();
                }
            });
            builder.setNegativeButton("Quitter", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.cancel();
                    finish();
                }
            });
            AlertDialog alert = builder.create();
            alert.show();
		}
		
	}

	public void initSlideMenu(){
		// On initialise le titre si site/app
		mTitle = mDrawerTitle = WEBSITENAME;
		// load slide menu items
		navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);
		// nav drawer icons from resources
		navMenuIcons = getResources().obtainTypedArray(R.array.nav_drawer_icons);
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.list_slidermenu);
		navDrawerItems = new ArrayList<NavDrawerItem>();

		// adding nav drawer items to array
		// Accueil
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[0], navMenuIcons.getResourceId(0, -1)));
		// Categories
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[1], navMenuIcons.getResourceId(1, -1)));
		if(!ISCONNECTED){
			// Creer un Compte
			navDrawerItems.add(new NavDrawerItem(navMenuTitles[2], navMenuIcons.getResourceId(2, -1)));
			// Connexion
			navDrawerItems.add(new NavDrawerItem(navMenuTitles[3], navMenuIcons.getResourceId(3, -1)));
			// A propos
			navDrawerItems.add(new NavDrawerItem(navMenuTitles[7], navMenuIcons.getResourceId(7, -1)));
		}else if(ISCONNECTED){
			// Mon panier
			navDrawerItems.add(new NavDrawerItem(navMenuTitles[4], navMenuIcons.getResourceId(4, -1)));
			// Mon compte
			navDrawerItems.add(new NavDrawerItem(navMenuTitles[6], navMenuIcons.getResourceId(6, -1)));
			// Mes commandes
			navDrawerItems.add(new NavDrawerItem(navMenuTitles[8], navMenuIcons.getResourceId(8, -1)));
			// Deconnexion
			navDrawerItems.add(new NavDrawerItem(navMenuTitles[5], navMenuIcons.getResourceId(5, -1)));
			// A propos
			navDrawerItems.add(new NavDrawerItem(navMenuTitles[7], navMenuIcons.getResourceId(7, -1)));
		}

		// Recycle the typed array
		navMenuIcons.recycle();
		mDrawerList.setOnItemClickListener(new SlideMenuClickListener());

		// setting the nav drawer list adapter
		adapter = new NavDrawerListAdapter(getApplicationContext(),navDrawerItems);
		mDrawerList.setAdapter(adapter);

		// enabling action bar app icon and behaving it as toggle button
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);

		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_drawer, //nav menu toggle icon
				R.string.app_name, // nav drawer open - description for accessibility
				R.string.app_name // nav drawer close - description for accessibility
				) {
			public void onDrawerClosed(View view) {
				super.onDrawerClosed(view);
				getActionBar().setTitle(mTitle);
				// calling onPrepareOptionsMenu() to show action bar icons
				invalidateOptionsMenu();
			}

			public void onDrawerOpened(View drawerView) {
				super.onDrawerOpened(drawerView);
				adapter.updateCounter();
				getActionBar().setTitle(mDrawerTitle);
				// calling onPrepareOptionsMenu() to hide action bar icons
				invalidateOptionsMenu();
			}
		};
		mDrawerLayout.setDrawerListener(mDrawerToggle);

	}
	
	// Slide menu item click listener
	private class SlideMenuClickListener implements
	ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,	long id) {
			displayView(position);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// toggle nav drawer on selecting action bar app icon/title
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		// Handle action bar actions click
//		switch (item.getItemId()) {
//		case R.id.action_settings:
//			return true;
//		default:
//		}
		return super.onOptionsItemSelected(item);
	}

	/* *
	 * Called when invalidateOptionsMenu() is triggered
	 */
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// if nav drawer is opened, hide the action items
		boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
		//menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
		return super.onPrepareOptionsMenu(menu);
	}

	/**
	 * Diplaying fragment view for selected nav drawer list item
	 * */
	private void displayView(int position) {
		// update the main content by replacing fragments
		Fragment fragment = null;
		int pos_title=0;
		switch (position) {
		case 0:
			fragment = new HomeFragment();
			pos_title = 0;
			break;
		case 1:
			fragment = new CategoryFragment();
			pos_title = 1;
			break;
		case 2:
		{
			if(!ISCONNECTED){
				fragment = new RegisterFragment();
				pos_title = 2;
			}
			else if (ISCONNECTED){
				fragment = new CartFragment();	
				pos_title = 4;
			}
			break;
		}
		case 3:
		{
			if(!ISCONNECTED){
				fragment = new LoginFragment();
				pos_title = 3;
			}
			else if (ISCONNECTED){
				fragment = new AccountFragment();
				pos_title = 6;
			}
			break;
		}
		case 4:
			if (ISCONNECTED){
				fragment = new OrderFragment();
				pos_title = 8;
			} else {
				fragment = new AboutFragment();
				pos_title = 7;
			}
			break;
		case 5:
			if (ISCONNECTED){
				ISCONNECTED = false;
				Intent intent = getIntent();
				finish();
				startActivity(intent);
				pos_title = 0;
			} else {
				fragment = new AboutFragment();
				pos_title = 7;
			}
			break;
		case 6:
			if (ISCONNECTED){
				fragment = new AboutFragment();
				pos_title = 7;
			}
			break;
		default:
			break;
		}

		if (fragment != null) {
			FragmentManager fragmentManager = getFragmentManager();
			
			// update selected item and title, then close the drawer
			mDrawerList.setItemChecked(position, true);
			mDrawerList.setSelection(position);
			setTitle(navMenuTitles[pos_title]);
			mDrawerLayout.closeDrawer(mDrawerList);
			fragmentManager.beginTransaction().replace(R.id.frame_container, fragment).addToBackStack("tag").commit();

		} else {
			// error in creating fragment
			Log.e("MainActivity", "Error in creating fragment");
		}
	}

	@Override
	public void onBackPressed() {
		if (getFragmentManager().getBackStackEntryCount() == 0) {
			this.finish();
		} else {
			getFragmentManager().popBackStack();
		}
	}

	@Override
	public void setTitle(CharSequence title) {
		mTitle = title;
		getActionBar().setTitle(mTitle);
	}


	/**
	 * When using the ActionBarDrawerToggle, you must call it during
	 * onPostCreate() and onConfigurationChanged()...
	 */
	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggls
		mDrawerToggle.onConfigurationChanged(newConfig);
	}
	
	private int getRandomImage() {
		int random = 1 + (int)(Math.random() * ((20 - 1) + 1));
		int res = R.drawable.prd1;
		switch (random) {
			case 1:
				res = R.drawable.prd1;
				break;
			case 2:
				res = R.drawable.prd2;				
				break;
			case 3:
				res = R.drawable.prd3;
				break;
			case 4:
				res = R.drawable.prd4;
				break;
			case 5:
				res = R.drawable.prd5;
				break;
			case 6:
				res = R.drawable.prd6;
				break;
			case 7:
				res = R.drawable.prd7;
				break;
			case 8:
				res = R.drawable.prd8;
				break;
			case 9:
				res = R.drawable.prd9;
				break;
			case 10:
				res = R.drawable.prd10;
				break;
			case 11:
				res = R.drawable.prd11;
				break;
			case 12:
				res = R.drawable.prd12;
				break;
			case 13:
				res = R.drawable.prd13;
				break;
			case 14:
				res = R.drawable.prd14;
				break;
			case 15:
				res = R.drawable.prd15;
				break;
			case 16:
				res = R.drawable.prd16;
				break;
			case 17:
				res = R.drawable.prd17;
				break;
			case 18:
				res = R.drawable.prd18;
				break;
			case 19:
				res = R.drawable.prd19;
				break;
			case 20:
				res = R.drawable.prd20;
				break;
			case 21:
				res = R.drawable.prd21;
				break;
			case 22:
				res = R.drawable.prd22;
				break;
			case 23:
				res = R.drawable.prd23;
				break;
			case 24:
				res = R.drawable.prd24;
				break;
			case 25:
				res = R.drawable.prd25;
				break;
			default:
				res = R.drawable.prd1;
				break;
		}
		return res;
	}
}
