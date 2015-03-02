//package com.ticfront.ui;
//
//import com.example.projettic.R;
//import com.ticfrontend.magasin.Categorie;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//
//public class CategorieView extends LinearLayout{
//
//	LinearLayout view;
//    String[] countries = new String[] {"India", "USA", "Canada"};
//
//    public CategorieView(Context context, Categorie categorie) {
//        super(context);
//        LayoutInflater  mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        mInflater.inflate(R.layout.single_item_category, this, true);
//    	
//        TextView title = (TextView) this.findViewById(R.id.titleCategorie);
//    	title.setText(categorie.getNomCategorie());
//    }
//}
