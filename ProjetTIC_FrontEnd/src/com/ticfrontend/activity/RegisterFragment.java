package com.ticfrontend.activity;

import com.example.projettic.R;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class RegisterFragment extends Fragment {
	private View rootView;
	private Activity activity;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        this.rootView = inflater.inflate(R.layout.activity_register, container, false);
        this.activity = this.getActivity();
              
        return rootView;
    }
}
