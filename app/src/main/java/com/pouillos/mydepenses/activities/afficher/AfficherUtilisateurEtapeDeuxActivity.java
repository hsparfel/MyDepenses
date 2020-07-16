package com.pouillos.mydepenses.activities.afficher;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.pouillos.mydepenses.R;
import com.pouillos.mydepenses.activities.NavDrawerActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import icepick.Icepick;

public class AfficherUtilisateurEtapeDeuxActivity extends NavDrawerActivity {

    @BindView(R.id.textAddress)
    TextInputEditText textAddress;
    @BindView(R.id.layoutAddress)
    TextInputLayout layoutAddress;

    @BindView(R.id.textZip)
    TextInputEditText textZip;
    @BindView(R.id.layoutZip)
    TextInputLayout layoutZip;

    @BindView(R.id.textTown)
    TextInputEditText textTown;
    @BindView(R.id.layoutTown)
    TextInputLayout layoutTown;

    @BindView(R.id.textPhone)
    TextInputEditText textPhone;
    @BindView(R.id.layoutPhone)
    TextInputLayout layoutPhone;

    @BindView(R.id.fabSave)
    FloatingActionButton fabSave;


    //Utilisateur currentUtilisateur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Icepick.restoreInstanceState(this, savedInstanceState);
        setContentView(R.layout.activity_afficher_utilisateur_etape_deux);

// 6 - Configure all views
     //   this.configureToolBar();
     //   this.configureDrawerLayout();
     //   this.configureNavigationView();
        ButterKnife.bind(this);
        activeUser = findActiveUser();
        if (activeUser != null && activeUser.getAdresse()!= null && activeUser.getCp()!= null && activeUser.getVille()!= null && activeUser.getTelephone()!= null) {
            textAddress.setText(activeUser.getAdresse());
            textZip.setText(activeUser.getCp());
            textTown.setText(activeUser.getVille());
            textPhone.setText(activeUser.getTelephone());

            // 6 - Configure all views
            this.configureToolBar();
            this.configureDrawerLayout();
            this.configureNavigationView();
        }

       // traiterIntent();
    }



  /*  public void traiterIntent() {
        Intent intent = getIntent();
        if (intent.hasExtra("utilisateurId")) {
            Long utilisateurId = intent.getLongExtra("utilisateurId", 0);
            currentUtilisateur = utilisateurDao.load(utilisateurId);
        }
    }*/

    private boolean isFullRempli() {
        boolean bool = true;
        if (!isFilled(textAddress)) {
            textAddress.setError("Obligatoire");
            bool = false;
        }

        if (!isFilled(textZip)) {
            textZip.setError("Obligatoire");
            bool = false;
        }
        if (!isFilled(textTown)) {
            textTown.setError("Obligatoire");
            bool = false;
        }
        if (!isFilled(textPhone)) {
            textPhone.setError("Obligatoire");
            bool = false;
        }
        if (!isValidZip(textZip)) {
            textZip.setError("Saisie Non Valide  (5 chiffres)");
            bool = false;
        }
        if (!isValidTel(textPhone)) {
            textPhone.setError("Saisie Non Valide  (10 chiffres)");
            bool = false;
        }
        return bool;
    }

    @OnClick(R.id.fabSave)
    public void setfabSaveClick() {



        if (isFullRempli()) {
            activeUser.setAdresse(textAddress.getText().toString());
            activeUser.setCp(textZip.getText().toString());
            activeUser.setVille(textTown.getText().toString());
            activeUser.setTelephone(textPhone.getText().toString());
            utilisateurDao.update(activeUser);
            ouvrirActiviteSuivante(AfficherUtilisateurEtapeDeuxActivity.this,AfficherUtilisateurEtapeTroisActivity.class,true);
        }
    }


}
