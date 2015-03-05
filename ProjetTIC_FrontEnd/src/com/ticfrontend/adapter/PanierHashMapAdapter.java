package com.ticfrontend.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.example.projettic.R;
import com.ticfrontend.magasin.Produit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PanierHashMapAdapter extends BaseAdapter {
	private LayoutInflater inflater;
	private final ArrayList<Object> mData;

	public PanierHashMapAdapter(Context c, HashMap<Produit, Integer> map){
		this.inflater = LayoutInflater.from(c);
		this.mData = new ArrayList<Object>();
		this.mData.addAll(map.entrySet());
	}

	@Override
	public int getCount() {	return mData.size();}

	@Override
	public Map.Entry<Produit, Integer> getItem(int pos) {return (Map.Entry) mData.get(pos);}

	@Override
	public long getItemId(int pos) {
		// TODO Auto-generated method stub
		return pos;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		LinearLayout layoutItem;

		if (convertView == null) {
			layoutItem = (LinearLayout) inflater.inflate(R.layout.single_item_cart_product, parent, false);
		} else {
			layoutItem = (LinearLayout) convertView;
		}

		Map.Entry<Produit, Integer> item = getItem(position);

		TextView title = (TextView) layoutItem.findViewById(R.id.titleProduct);
		TextView desc = (TextView) layoutItem.findViewById(R.id.descProduct);
		TextView price = (TextView) layoutItem.findViewById(R.id.priceProduct);
		TextView qte = (TextView) layoutItem.findViewById(R.id.qteProduct);

		title.setText(item.getKey().getNomProduit());
		desc.setText(item.getKey().getDescriptionProduit());
		price.setText(String.valueOf(item.getKey().getPrixProduit()) + " €");
		qte.setText("Quantité : " + String.valueOf(item.getValue()));

		return layoutItem;
	}
	
	@Override
	public void notifyDataSetChanged() {
	    //do your sorting here
	    super.notifyDataSetChanged();
	}
	
}
