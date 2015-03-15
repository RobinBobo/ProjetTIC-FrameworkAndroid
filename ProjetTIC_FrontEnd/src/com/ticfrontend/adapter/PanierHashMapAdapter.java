package com.ticfrontend.adapter;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.projettic.R;
import com.ticfrontend.activity.CartFragment;
import com.ticfrontend.activity.MainActivity;
import com.ticfrontend.magasin.Produit;
import com.ticfrontend.thread.ThreadPreconditions;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PanierHashMapAdapter extends BaseAdapter {
	private Context context = null;
	private View rootViewCart = null;
	
	private LayoutInflater inflater;
	private ArrayList<Object> mData;
	
	private boolean isInOrder = false;

	public PanierHashMapAdapter(Context c, HashMap<Produit, Integer> map, View view){
		this.context = c;
		this.rootViewCart = view;
		this.inflater = LayoutInflater.from(c);
		this.mData = new ArrayList<Object>();
		this.mData.addAll(map.entrySet());
	}
	
	public PanierHashMapAdapter(Context c, HashMap<Produit, Integer> map, View view, boolean isInOrder){
		this.context = c;
		this.rootViewCart = view;
		this.inflater = LayoutInflater.from(c);
		this.mData = new ArrayList<Object>();
		this.mData.addAll(map.entrySet());
		this.isInOrder = isInOrder;
	}

	@Override
	public int getCount() {	return mData.size();}

	@Override
	public Map.Entry<Produit, Integer> getItem(int pos) {return (Map.Entry) mData.get(pos);}

	@Override
	public long getItemId(int pos) {
		return pos;
	}

	@Override
	public View getView(final int position, View convertView, final ViewGroup parent) {

		LinearLayout layoutItem;

		if (convertView == null) {
			layoutItem = (LinearLayout) inflater.inflate(R.layout.single_item_cart_product, parent, false);
		} else {
			layoutItem = (LinearLayout) convertView;
		}		
		
		final Map.Entry<Produit, Integer> item = getItem(position);

		// On met une image aléatoire sur le ImageView de la catégorie
	    ImageView icon = (ImageView) layoutItem.findViewById(R.id.icon);
	    icon.setImageResource(item.getKey().getIconRessource());
		
		final TextView title = (TextView) layoutItem.findViewById(R.id.titleProduct);
		TextView desc = (TextView) layoutItem.findViewById(R.id.descProduct);
		TextView price = (TextView) layoutItem.findViewById(R.id.priceProduct);
		final TextView qte = (TextView) layoutItem.findViewById(R.id.qteProduct);

		title.setText(item.getKey().getNomProduit());
		desc.setText(item.getKey().getDescriptionProduit());
		price.setText(String.valueOf(item.getKey().getPrixProduit()) + " €");
		qte.setText("Quantité : " + String.valueOf(item.getValue()));
		
		final PanierHashMapAdapter adapter = this;
		
		Button enlever = (Button) layoutItem.findViewById(R.id.boutonEnlever);
		
		if(isInOrder) {
			enlever.setVisibility(View.GONE);
		} else {
			enlever.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					AlertDialog.Builder builder = new AlertDialog.Builder(context);
					builder.setTitle("Suppression d'un produit du panier");
					builder.setMessage("Vous êtes sur le point de supprimer 1 " + title.getText().toString() + " du panier, en êtes-vous sûr ?");
					builder.setCancelable(true);
					builder.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {
							// Suppression d'un produit du panier
							if(item.getValue() -1 < 1) {
								// On supprime du panier le produit
								mData.remove(position);
								CartFragment.PANIER_CLIENT.getMapProduitQuantite().remove(item.getKey());
								adapter.updateProduct(mData);
							} else {
								// On change le text de la vue et la donnée du panier
								item.setValue(item.getValue()-1);
								qte.setText("Quantité : " + String.valueOf(item.getValue()));
							}
							double prixTotal = CartFragment.PANIER_CLIENT.calculTotalPanier();
							TextView prixT = (TextView) rootViewCart.findViewById(R.id.prixTotal);
							
							DecimalFormat df = new DecimalFormat() ; 
							df.setMaximumFractionDigits(2) ; //arrondi à 2 chiffres apres la virgules 
							df.setMinimumFractionDigits(2) ; 
							df.setDecimalSeparatorAlwaysShown(true); 
							
							prixT.setText("Total : "+ df.format(prixTotal) + " €");
							
							dialog.cancel();
						}
					});
					builder.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {
							dialog.cancel();
						}
					});
					AlertDialog alert = builder.create();
					alert.show();
				}
			});			
		}

		return layoutItem;
	}
	
	@Override
	public void notifyDataSetChanged() {
	    //do your sorting here
	    super.notifyDataSetChanged();
	}
	
	public void updateProduct(List<Object> data) {
		ThreadPreconditions.checkOnMainThread();
        this.mData = (ArrayList<Object>) data;
        notifyDataSetChanged();
    }
	
	public void updateProductWithoutNotify(List<Object> data) {
		ThreadPreconditions.checkOnMainThread();
        this.mData = (ArrayList<Object>) data;
    }
	
}
