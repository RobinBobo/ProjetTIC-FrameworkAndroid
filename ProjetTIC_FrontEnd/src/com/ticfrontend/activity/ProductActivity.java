package com.ticfrontend.activity;

import com.example.projettic.R;
import com.example.projettic.R.id;
import com.example.projettic.R.layout;
import com.example.projettic.R.menu;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.NumberPicker.OnValueChangeListener;
import android.widget.TextView;


public class ProductActivity extends Activity implements NumberPicker.OnValueChangeListener {

	private static TextView tv;
	static Dialog d ;
	private static Button b;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_product);
		init();
	}

	public void init(){
		tv = (TextView) findViewById(R.id.availableQuantity);	
		b = (Button) findViewById(R.id.boutonAjouter);  
		b.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				show();
			}
		});
	}
	public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

		Log.i("value is",""+newVal);
	}

	public void show()
	{

		final Dialog d = new Dialog(ProductActivity.this);
		d.setTitle("Choisir la quantité");
		d.setContentView(R.layout.dialog_quantity);
		Button b1 = (Button) d.findViewById(R.id.button1);
		Button b2 = (Button) d.findViewById(R.id.button2);
		final NumberPicker np = (NumberPicker) d.findViewById(R.id.numberPicker1);
		np.setMaxValue(30);
		np.setMinValue(0);
		np.setWrapSelectorWheel(false);
		np.setOnValueChangedListener((OnValueChangeListener) this);
		b1.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v) {
				tv.setText("Qte: "+String.valueOf(np.getValue()));
				d.dismiss();
			}    
		});
		b2.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v) {
				d.dismiss();
			}    
		});
		d.show();


	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.product, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
