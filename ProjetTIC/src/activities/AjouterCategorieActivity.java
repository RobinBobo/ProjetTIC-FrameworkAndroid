package activities;

import android.app.Activity;
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
		
		// Récupération des données
		final Button ajouterCategorie = (Button) findViewById(R.id.btnValider);
		final EditText id = (EditText) findViewById(R.id.id);
		final EditText nom = (EditText) findViewById(R.id.nom);
		final TextView msgErreur = (TextView) findViewById(R.id.msgErreur);
		
		ajouterCategorie.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				String msg = "";
				if (id.getText().toString().matches("") || nom.getText().toString().matches("")) {
					msg += "Veuillez remplir tous les champs.";
				} else {
					boolean alreadyExist = MainActivity.getListeCategories().isCategorie(Integer.parseInt(id.getText().toString()));
					if (alreadyExist) {
						msg = "L'identifiant existe déjà.";
					} else {
						Categorie c = new Categorie(Integer.parseInt(id.getText().toString()), nom.getText().toString());
						MainActivity.getListeCategories().ajouterCategorie(c);
						msg = "La catégorie a bien été ajoutée.";
					}
				}
				msgErreur.setText(msg);
			}
		});
	}
}
