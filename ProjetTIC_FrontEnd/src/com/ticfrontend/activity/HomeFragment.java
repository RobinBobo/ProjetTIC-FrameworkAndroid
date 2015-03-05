package com.ticfrontend.activity;

import com.example.projettic.R;
import com.ticfrontend.magasin.Produit;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class HomeFragment extends Fragment {
	private View rootView;
	private Activity activity;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        this.rootView = inflater.inflate(R.layout.fragment_home, container, false);
        this.activity = this.getActivity();
               
        return rootView;
    }

	@Override
	public void onActivityCreated(Bundle savedInstanceState)
	{
		super.onActivityCreated(savedInstanceState);
		this.activity.setTitle(R.string.title_fragment_home);
		
		Intent intent = activity.getIntent();
		//client = intent.getStringExtra(LoginFragment.EXTRA_KEY_USER);
		
		if(MainActivity.ISCONNECTED)
			((TextView)rootView.findViewById(R.id.textUser)).setText("Bienvenue " + MainActivity.CLIENT_ACTUEL.getNomClient());
		else 
			((TextView)rootView.findViewById(R.id.textUser)).setText("Connecte toi!");
	}
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
    }
}
