package com.ticfrontend.activity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.projettic.R;

public class HomeFragment extends Fragment {
	private View rootView;
	private Activity activity;
	private Fragment fragment;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		this.rootView = inflater.inflate(R.layout.fragment_home, container, false);
		this.activity = this.getActivity();
		TextView title = (TextView) rootView.findViewById(R.id.textWelcome);
		
		title.setText("Bienvenue sur " + MainActivity.WEBSITENAME);
		
		init();
		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		this.activity.setTitle(R.string.title_fragment_home);

		if(MainActivity.ISCONNECTED)
			((TextView)rootView.findViewById(R.id.textUser)).setText("Bonjour " + MainActivity.CLIENT_ACTUEL.getPrenomClient());
		else 
			((TextView)rootView.findViewById(R.id.textUser)).setText("Connectez-vous !");
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	private void initProductListFrag(/*List<Produit> promos,*/ int title ){
		fragment = new ProductListFragment(/*promos*/title);

		FragmentManager fragmentManager = getFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		fragmentTransaction.replace(R.id.frame_container, fragment);
		fragmentTransaction.addToBackStack("tag");
		fragmentTransaction.commit();
	}
	
	public void init(){
		Button promos = (Button) rootView.findViewById(R.id.buttonPromo);
		Button offresSpec = (Button) rootView.findViewById(R.id.buttonOffreSpec);
		Button aNePasManquer = (Button) rootView.findViewById(R.id.buttonANePasManquer);
		Button nouveaute = (Button) rootView.findViewById(R.id.buttonNouveautes);

		promos.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				initProductListFrag(R.string.title_productList_sale);
			}
		});
		offresSpec.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				initProductListFrag(R.string.title_productList_specialOffer);
			}
		});
		aNePasManquer.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				initProductListFrag(R.string.title_productList_DoNotMiss);
			}
		});
		nouveaute.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				initProductListFrag(R.string.title_productList_news);
			}
		});
	}
}
