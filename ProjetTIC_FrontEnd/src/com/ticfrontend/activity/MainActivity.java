package com.ticfrontend.activity;

import com.example.projettic.R;
import com.ticfrontend.magasin.Client;
import com.ticfrontend.model.*;
import com.ticfrontend.adapter.*;
import com.ticfrontend.configuratormanagement.Configurator;
import com.ticfrontend.configuratormanagement.XmlLoader;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main1);
		initSlideMenu();

		// TEST Loading XML //
		/*
		Configurator c = new Configurator ();
		File xmlToLoad = new File(Environment.getExternalStorageDirectory(), "configuration.xml");
		XmlLoader x = new XmlLoader ();

		try {
			x.load(new FileInputStream(xmlToLoad), c);
			System.out.println("Suspens : " + c.getWebsiteName() + c.getOrder() + c.getCustomerNotice());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		 */
		if (savedInstanceState == null) {
			// on first time display view for first nav item
			displayView(0);
		}
	}

	public void updateSlideMenu(){
		if(ISCONNECTED){
			navDrawerItems.remove(3);
			navDrawerItems.remove(2);
			navDrawerItems.remove(4);
			navDrawerItems.add(2, new NavDrawerItem(navMenuTitles[4], navMenuIcons.getResourceId(4, -1)));
			navDrawerItems.add(3, new NavDrawerItem(navMenuTitles[5], navMenuIcons.getResourceId(5, -1)));
			navDrawerItems.add(4, new NavDrawerItem(navMenuTitles[8], navMenuIcons.getResourceId(8, -1)));
			navDrawerItems.add(5, new NavDrawerItem(navMenuTitles[7], navMenuIcons.getResourceId(7, -1)));
		}else if(!ISCONNECTED){
			navDrawerItems.remove(3);
			navDrawerItems.remove(2);
			navDrawerItems.add(2, new NavDrawerItem(navMenuTitles[2], navMenuIcons.getResourceId(2, -1)));
			navDrawerItems.add(3, new NavDrawerItem(navMenuTitles[3], navMenuIcons.getResourceId(3, -1)));
		}
		navMenuIcons.recycle();		
	}

	public void initSlideMenu(){
		mTitle = mDrawerTitle = getTitle();
		// load slide menu items
		navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);
		// nav drawer icons from resources
		navMenuIcons = getResources().obtainTypedArray(R.array.nav_drawer_icons);
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.list_slidermenu);
		navDrawerItems = new ArrayList<NavDrawerItem>();

		// adding nav drawer items to array
		// Home
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
			navDrawerItems.add(new NavDrawerItem(navMenuTitles[4], navMenuIcons.getResourceId(4, -1)));
			// What's hot, We  will add a counter here
			navDrawerItems.add(new NavDrawerItem(navMenuTitles[6], navMenuIcons.getResourceId(6, -1)));
			// Mes commandes
			navDrawerItems.add(new NavDrawerItem(navMenuTitles[8], navMenuIcons.getResourceId(8, -1)));
			navDrawerItems.add(new NavDrawerItem(navMenuTitles[5], navMenuIcons.getResourceId(5, -1)));
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
				getActionBar().setTitle(mTitle);
				// calling onPrepareOptionsMenu() to show action bar icons
				invalidateOptionsMenu();
			}

			public void onDrawerOpened(View drawerView) {
				getActionBar().setTitle(mDrawerTitle);
				// calling onPrepareOptionsMenu() to hide action bar icons
				invalidateOptionsMenu();
			}
		};
		mDrawerLayout.setDrawerListener(mDrawerToggle);

	}
	/**
	 * Slide menu item click listener
	 * */
	private class SlideMenuClickListener implements
	ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,	long id) {
			// display view for selected nav drawer item
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
		switch (item.getItemId()) {
		case R.id.action_settings:
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
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
		switch (position) {
		case 0:
			fragment = new HomeFragment();
			break;
		case 1:
			fragment = new CategoryFragment();
			break;
		case 2:
		{
			if(!ISCONNECTED)
				fragment = new RegisterFragment();
			else if (ISCONNECTED)
				fragment = new CartFragment();	
			break;
		}
		case 3:
		{
			if(!ISCONNECTED)
				fragment = new LoginFragment();
			else if (ISCONNECTED){
				fragment = new AccountFragment();
			}
			break;
		}
		case 4:
			if (ISCONNECTED){
				//				ISCONNECTED = false;
				//				Intent intent = getIntent();
				//				finish();
				//				startActivity(intent);
				fragment = new CommandFragment();
			} else {
				fragment = new AboutFragment();
			}
			break;
		case 5:
			if (ISCONNECTED){
				ISCONNECTED = false;
				Intent intent = getIntent();
				finish();
				startActivity(intent);
			} else {
				fragment = new AboutFragment();
			}
			break;
		case 6:
			if (ISCONNECTED)
				fragment = new AboutFragment();
			break;
		default:
			break;
		}

		if (fragment != null) {
			FragmentManager fragmentManager = getFragmentManager();
			fragmentManager.beginTransaction().replace(R.id.frame_container, fragment).addToBackStack("tag").commit();

			// update selected item and title, then close the drawer
			mDrawerList.setItemChecked(position, true);
			mDrawerList.setSelection(position);
			setTitle(navMenuTitles[position]);
			mDrawerLayout.closeDrawer(mDrawerList);
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
