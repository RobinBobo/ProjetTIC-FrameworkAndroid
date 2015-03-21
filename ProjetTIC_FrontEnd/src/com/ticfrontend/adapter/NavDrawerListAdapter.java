package com.ticfrontend.adapter;

import java.util.ArrayList;

import com.example.projettic.R;
import com.ticfrontend.activity.CartFragment;
import com.ticfrontend.activity.MainActivity;
import com.ticfrontend.model.NavDrawerItem;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class NavDrawerListAdapter extends BaseAdapter {

	private Context context;
	private ArrayList<NavDrawerItem> navDrawerItems;
	private int count;

	public NavDrawerListAdapter(Context context, ArrayList<NavDrawerItem> navDrawerItems){
		this.context = context;
		this.navDrawerItems = navDrawerItems;
	}

	@Override
	public int getCount() {
		return navDrawerItems.size();
	}

	@Override
	public Object getItem(int position) {		
		return navDrawerItems.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			LayoutInflater mInflater = (LayoutInflater)
					context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
			convertView = mInflater.inflate(R.layout.drawer_list_item, null);
		}

		ImageView imgIcon = (ImageView) convertView.findViewById(R.id.icon);
		TextView txtTitle = (TextView) convertView.findViewById(R.id.title);
		TextView txtCount = (TextView) convertView.findViewById(R.id.counter);

		imgIcon.setImageResource(navDrawerItems.get(position).getIcon());        
		txtTitle.setText(navDrawerItems.get(position).getTitle());

		// displaying count
		// check whether it set visible or not
		if(!MainActivity.ISCONNECTED)
			txtCount.setVisibility(View.GONE);
		else {
			if(CartFragment.PANIER_CLIENT != null)
				this.count = ((!CartFragment.PANIER_CLIENT.estVide()) ? CartFragment.PANIER_CLIENT.nombreProduit() : 0);
			if(this.count != 0){
				navDrawerItems.get(2).setCount(String.valueOf(this.count));
				navDrawerItems.get(2).setCounterVisibility(true);
			}
			else
				navDrawerItems.get(2).setCounterVisibility(false);
		}

		if(navDrawerItems.get(position).getCounterVisibility()){
			txtCount.setVisibility(View.VISIBLE);
			txtCount.setText(navDrawerItems.get(position).getCount());
		}else{
			txtCount.setVisibility(View.GONE);
		}
		return convertView;
	}
	
	@Override
	public void notifyDataSetChanged() {
		//do your sorting here
		super.notifyDataSetChanged();
	}
}
