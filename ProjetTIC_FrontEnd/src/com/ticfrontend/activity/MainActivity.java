package com.ticfrontend.activity;

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
import android.widget.Toast;

import com.example.projettic.R;
import com.ticfrontend.adapter.NavDrawerListAdapter;
import com.ticfrontend.configuratormanagement.Configurator;
import com.ticfrontend.configuratormanagement.XmlLoader;
import com.ticfrontend.magasin.Avis;
import com.ticfrontend.magasin.Categorie;
import com.ticfrontend.magasin.Client;
import com.ticfrontend.magasin.Produit;
import com.ticfrontend.model.NavDrawerItem;

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
//	public static ArrayList<Produit> LISTDIVERS = null;
	public static String WEBSITENAME = null;
	public static int COLORBUTTON;
	public static String COLORBUTTONSTRING = null;
	public static boolean CUSTOMERNOTICE = false;
	public static boolean ORDER = false;
	public static boolean SHOPPINGCART = false;
//	public static boolean DIVERS = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);
		
		setConfiguration();

		checkConfiguration();
		
		initSlideMenu();
				
		if (savedInstanceState == null) {
			displayView(0);
		}
		
	}

	private void setConfiguration() {
		// Loading XML //	
		Configurator c = new Configurator ();
		File xmlToLoad = new File(Environment.getExternalStorageDirectory(), "configuration.xml");
		XmlLoader x = new XmlLoader ();
		
		LISTPRODUIT = new ArrayList<Produit>();
		LISTCLIENT = new ArrayList<Client>();
		LISTCATEGORIE = new ArrayList<Categorie>();
//		LISTDIVERS = new ArrayList<Produit>();
		
		try {
			x.load(new FileInputStream(xmlToLoad), c, LISTPRODUIT, LISTCATEGORIE);
			System.out.println("Suspens : " + c.getWebsiteName() + c.getOrder() + c.getCustomerNotice());
			Log.v("XML",c.getWebsiteName() + c.getOrder() + c.getCustomerNotice());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		if(c.getWebsiteName() != null)
			WEBSITENAME = c.getWebsiteName();
		
		COLORBUTTON = c.getButtonsColor();
		
		// Si COLORBUTTON == 0 alors on met #3A467A comme couleur par d�faut
		if(COLORBUTTON == 0) {
			COLORBUTTONSTRING = "3A467A";
		}
		else {
			String hexa = Integer.toHexString(COLORBUTTON);
			COLORBUTTONSTRING = hexa.substring(2, hexa.length());
		}
		
		CUSTOMERNOTICE = c.getCustomerNotice();
		ORDER = c.getOrder();
		SHOPPINGCART = c.getShoppingCart();

		// Pour test
//		CUSTOMERNOTICE = false;
//		SHOPPINGCART = false;
		
		if(!SHOPPINGCART)
			ORDER = false;
		
		// Si l'admin veut des avis
		if(CUSTOMERNOTICE){
			// On ajoute des avis fictif pour les produits que l'on vient de r�cup�rer
			List<Avis> avisBeta = Avis.getAListOfReviewsLong();
			List<Avis> avisBeta2 = Avis.getAListOfReviewsSmall();
			for(int i = 0; i < LISTPRODUIT.size(); i++){
				if(i%2 == 0)
					LISTPRODUIT.get(i).setListeAvisProduit(avisBeta);
				else 
					LISTPRODUIT.get(i).setListeAvisProduit(avisBeta2);	
			}
		}
			
		// Ajout de clients par d�faut
		Client client1 = new Client("florian2412", "Pussacq", "Florian", "332 Cours de la Lib�ration, 33400 TALENCE", "florian.pussacq@gmail.com", true, "pupuce");
		Client client2 = new Client("tavash", "Sang", "Tavahiura", "TAHITI, Polyn�sie Fran�aise", "tava.sang@gmail.com", true, "tavabien");
		Client client3 = new Client("cecileB", "Bourrat", "C�cile", "18 Rue de l'inconnu, 33000 BORDEAUX, ", "cecile.bourrat@gmail.com", false, "boubou");
		Client client4 = new Client("robinB", "Bobo", "Robin", "5 Impasse des Mimosas, 33700 MERIGNAC", "robin.bobo@gmail.com", true, "bobo");
		Client client5 = new Client("chrisA", "Anglade", "Christophe", "20 Rue de l'inconnu, 33000 BORDEAUX", "christophe.anglade@gmail.com", true, "angla2");
		
		LISTCLIENT.add(client1);
		LISTCLIENT.add(client2);
		LISTCLIENT.add(client3);
		LISTCLIENT.add(client4);
		LISTCLIENT.add(client5);
	}

	private void checkConfiguration() {
		if(WEBSITENAME == null) {
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("Probl�me de configuration");
            builder.setMessage("Impossible d'initialiser l'application, le fichier de configuration est incorrect (le nom du commerce n'est pas renseign�). Veuillez pr�venir l'administrateur de l'application. Des probl�mes peuvent subvenir si vous continuer. Continuer quand m�me ?");
            builder.setCancelable(true);
            builder.setPositiveButton("Continuer � mes risques et p�rils", new DialogInterface.OnClickListener() {
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
			if(SHOPPINGCART)
				navDrawerItems.add(new NavDrawerItem(navMenuTitles[4], navMenuIcons.getResourceId(4, -1)));				
			
			// Mon compte
			navDrawerItems.add(new NavDrawerItem(navMenuTitles[6], navMenuIcons.getResourceId(6, -1)));
			
			// Mes commandes
			if(ORDER)
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
				adapter.notifyDataSetChanged();
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

		return super.onOptionsItemSelected(item);
	}

	/* *
	 * Called when invalidateOptionsMenu() is triggered
	 */
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// if nav drawer is opened, hide the action items
		//boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
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
				if(SHOPPINGCART) {
					fragment = new CartFragment();	
					pos_title = 4;					
				} else {
					fragment = new AccountFragment();
					pos_title = 6;
				}
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
				if(SHOPPINGCART) {
					fragment = new AccountFragment();
					pos_title = 6;		
				} else {
					if(ORDER) {
						fragment = new OrderFragment();
						pos_title = 8;
					} else {
						ISCONNECTED = false;
						Intent intent = getIntent();
						finish();
						startActivity(intent);
						pos_title = 0;
					}
				}
			}
			break;
		}
		case 4:
			if (ISCONNECTED){
				if(SHOPPINGCART) {
					if(ORDER) {
						fragment = new OrderFragment();
						pos_title = 8;
					} else {
						ISCONNECTED = false;
						Intent intent = getIntent();
						finish();
						startActivity(intent);
						pos_title = 0;
					}
				} else {
					fragment = new AboutFragment();
					pos_title = 7;
				}
			} else {
				fragment = new AboutFragment();
				pos_title = 7;
			}
			break;
		case 5:
			if (ISCONNECTED){
				if(ORDER) {
					ISCONNECTED = false;
					Intent intent = getIntent();
					finish();
					startActivity(intent);
					pos_title = 0;					
				} else {
					fragment = new AboutFragment();
					pos_title = 7;
				}
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
}
