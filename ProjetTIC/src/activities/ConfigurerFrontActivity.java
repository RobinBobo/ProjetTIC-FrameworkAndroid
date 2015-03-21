package activities;

import colorpicker.ColorPicker;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import fr.tic.R;

public class ConfigurerFrontActivity extends Activity implements  
ColorPicker.OnColorChangedListener {

	private String nomSite;

	public String getNomSite() {
		return nomSite;
	}

	public void setNomSite(String nomSite) {
		this.nomSite = nomSite;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_configurerfront);

		final EditText newNom = (EditText) findViewById(R.id.nomSite);
		final TextView msgErreur = (TextView) findViewById(R.id.msgErreur);

		// Ajout du nouveau nom
		final Button ajouterNom = (Button) findViewById(R.id.btnValider);
		ajouterNom.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {

				if (!newNom.getText().toString().matches("")) {
					nomSite = newNom.getText().toString();
					//MainActivity.getMaConfiguration().setWebsiteName(nomSite);
					MainActivity.getMaConfiguration().setWebsiteName(nomSite);

					CheckBox checkBoxIsShoppingCart = (CheckBox) findViewById(R.id.isShoppingCart);
					if (checkBoxIsShoppingCart.isChecked()) 
						MainActivity.getMaConfiguration().setShoppingCart(true);
					else
						MainActivity.getMaConfiguration().setShoppingCart(false);
					
					CheckBox checkBoxIsCustomerNotice = (CheckBox) findViewById(R.id.isCustomerNotice);
					if (checkBoxIsCustomerNotice.isChecked()) 
						MainActivity.getMaConfiguration().setCustomerNotice(true);
					else
						MainActivity.getMaConfiguration().setCustomerNotice(false);
					
					CheckBox checkBoxOrder = (CheckBox) findViewById(R.id.order);
					if (checkBoxOrder.isChecked()) 
						MainActivity.getMaConfiguration().setOrder(true);
					else
						MainActivity.getMaConfiguration().setOrder(false);
					
					CheckBox checkBoxProductSorting = (CheckBox) findViewById(R.id.productSorting);
					if (checkBoxProductSorting.isChecked()) 
						MainActivity.getMaConfiguration().setSortProduct(true);
					else
						MainActivity.getMaConfiguration().setSortProduct(false);
					
					CheckBox checkBoxCategorySorting = (CheckBox) findViewById(R.id.categorySorting);
					if (checkBoxCategorySorting.isChecked()) 
						MainActivity.getMaConfiguration().setSortCategory(true);
					else
						MainActivity.getMaConfiguration().setSortCategory(false);
					
					CheckBox checkBoxProductSearch = (CheckBox) findViewById(R.id.productSearch);
					if (checkBoxProductSearch.isChecked()) 
						MainActivity.getMaConfiguration().setProductSearch(true);
					else
						MainActivity.getMaConfiguration().setProductSearch(false);
					
					CheckBox checkBoxCategorySearch = (CheckBox) findViewById(R.id.categorySearch);
					if (checkBoxCategorySearch.isChecked()) 
						MainActivity.getMaConfiguration().setCategorySearch(true);
					else
						MainActivity.getMaConfiguration().setCategorySearch(false);

					msgErreur.setText("Configuration enregistré");
					msgErreur.setTextColor(Color.rgb(20, 148, 20));

				} else msgErreur.setText("Veuillez saisir un nom");
			}
		});
	}

	// ------------------------------  TEST
	@Override  
	public void colorChanged(String str,int color) {    
		/*ChoixNomSiteActivity.this.findViewById(android.R.id.content)  
		.setBackgroundColor(color);  */
		MainActivity.getMaConfiguration().setButtonsColor(color);
	}  	

	public void getColor(View v) {  
		new ColorPicker(this, this, "", Color.BLACK, Color.WHITE).show();   
	}  

	// ------------------------------  /TEST
}
