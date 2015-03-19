package activities;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import beans.Categorie;
import fr.tic.R;

public class AjouterCategorieActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ajoutercategorie);
		
		// R�cup�ration des donn�es
		final Button ajouterCategorie = (Button) findViewById(R.id.btnValider);
		final EditText nom = (EditText) findViewById(R.id.nom);
		final TextView msgErreur = (TextView) findViewById(R.id.msgErreur);
		
		ajouterCategorie.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				String msg = "";
				if (nom.getText().toString().matches("")) {
					msg += "Veuillez saisir le nom de la cat�gorie � ajouter.";
					msgErreur.setTextColor(Color.RED);
				} else {
					boolean alreadyExist = MainActivity.getListeCategories().isCategorie(nom.getText().toString());
					if (alreadyExist) {
						msg = "La cat�gorie existe d�j�.";
						msgErreur.setTextColor(Color.RED);
					} else {
						Categorie c = new Categorie(nom.getText().toString());
						MainActivity.getListeCategories().ajouterCategorie(c);
						msg = "La cat�gorie a bien �t� ajout�e.";
						msgErreur.setTextColor(Color.rgb(20, 148, 20));
						nom.setText("");
					}
				}
				msgErreur.setText(msg);
			}
		});
	}
}
