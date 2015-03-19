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
		
		// R�cup�ration des donn�es
		final EditText rechCatego = (EditText) findViewById(R.id.rechCatego);
		final TextView msgErreur = (TextView) findViewById(R.id.msgErreur);
		Button btnSupprimer = (Button) findViewById(R.id.btnSupprimer);
		
		btnSupprimer.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				if (!rechCatego.getText().toString().matches("")) {
					if (MainActivity.getListeCategories().supprimCategorie(rechCatego.getText().toString())) {
						msgErreur.setText("La cat�gorie a bien �t� supprim�e");
						msgErreur.setTextColor(Color.rgb(20, 148, 20));
					} else {
						msgErreur.setText("La cat�gorie n'existe pas dans la base...");
						msgErreur.setTextColor(Color.RED);
					}
				} else {
					msgErreur.setText("Veuillez saisir une cat�gorie");
					msgErreur.setTextColor(Color.RED);
				}
			}
		});
		
	}

}
