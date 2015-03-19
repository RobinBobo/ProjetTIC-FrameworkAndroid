package activities;

import fr.tic.R;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SupprimerCategorieActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_supprimercategorie);
		
		// Récupération des données
		final EditText rechCatego = (EditText) findViewById(R.id.rechCatego);
		final TextView msgErreur = (TextView) findViewById(R.id.msgErreur);
		Button btnSupprimer = (Button) findViewById(R.id.btnSupprimer);
		
		btnSupprimer.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				if (!rechCatego.getText().toString().matches("")) {
					if (MainActivity.getListeCategories().supprimCategorie(rechCatego.getText().toString())) {
						msgErreur.setText("La catégorie a bien été supprimée");
						msgErreur.setTextColor(Color.rgb(20, 148, 20));
					} else {
						msgErreur.setText("La catégorie n'existe pas dans la base...");
						msgErreur.setTextColor(Color.RED);
					}
				} else {
					msgErreur.setText("Veuillez saisir une catégorie");
					msgErreur.setTextColor(Color.RED);
				}
			}
		});
		
	}

}
