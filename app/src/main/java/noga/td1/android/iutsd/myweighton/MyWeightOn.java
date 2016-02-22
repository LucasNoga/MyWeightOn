package noga.td1.android.iutsd.myweighton;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.text.DecimalFormat;

public class MyWeightOn extends AppCompatActivity {

    private final static String TAG = "MyWeightOn";

    //données
    private int visibility = 1;
    private double poids;
    private double gravite = 9.8;
    private double unite = 1;
    private String celeste = "Terre";
    private String uniteChaine = "";
    private double resultat = 0.;
    private String edit = "";

    //Composant de l'interface
    private Button calcul, raz;
    private EditText et1;
    private TextView tw, label;
    private RadioButton rb1, rb2, rb3, rb4;
    private Switch sw;
    RadioGroup rg;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_weight_on);

        //Récupération des éléments de l'application
        calcul = (Button) findViewById(R.id.button1);
        raz = (Button) findViewById(R.id.button2);
        et1 = (EditText) findViewById(R.id.editText);
        rb1 = (RadioButton) findViewById(R.id.radioButton);
        rb2 = (RadioButton) findViewById(R.id.radioButton2);
        rb3 = (RadioButton) findViewById(R.id.radioButton3);
        rb4 = (RadioButton) findViewById(R.id.radioButton4);
        rg = (RadioGroup) findViewById(R.id.groupeButton);
        sw = (Switch) findViewById(R.id.switch2);
        tw = (TextView) findViewById(R.id.textView3);
        rb1.setChecked(true);

        calcul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                poids = Integer.parseInt(String.valueOf(et1.getText()));
                gravite = 9.8;
                unite = 1;
                celeste = "Terre";
                uniteChaine = "";
                resultat = 0.;


                if (sw.isChecked()) {
                    uniteChaine = "kg";
                    unite = 1;
                    Log.i(TAG, (String) sw.getTextOn());
                } else {
                    unite = 2.20462;
                    uniteChaine = "lbs";
                }

                if (rb1.isChecked()) {
                    Log.i(TAG, "Lune");
                    celeste = "Lune";
                    gravite = 1.6;
                } else if (rb2.isChecked()) {
                    Log.i(TAG, "Mercure");
                    celeste = "Mercure";
                    gravite = 3.7;
                } else if (rb3.isChecked()) {
                    Log.i(TAG, "Mars");
                    celeste = "Mars";
                    gravite = 3.6;
                } else if (rb4.isChecked()) {
                    Log.i(TAG, "Jupiter");
                    celeste = "Jupiter";
                    gravite = 25;
                }

                resultat = poids * gravite * unite;
                DecimalFormat f = new DecimalFormat();
                f.setMaximumFractionDigits(2);
                String re = f.format(resultat);
                edit = "Votre poids sur " + celeste + ": " + re + " " + uniteChaine;
                tw.setText(edit);

            }
        });

        raz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rb1.isChecked()) {
                    Log.i(TAG, "Lune");
                } else if (rb2.isChecked()) {
                    rb1.setChecked(true);
                } else if (rb3.isChecked()) {
                    rb1.setChecked(true);
                } else if (rb4.isChecked()) {
                    rb1.setChecked(true);
                }
                et1.setText("");
                tw.setText("");
                sw.setChecked(true);

            }
        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    //permet de stocker les données a sauvegareder
   protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putDouble("POIDS", poids); //element a sauvegarder
        outState.putDouble("GRAVITE", gravite); //element a sauvegarder
        outState.putDouble("UNITE", unite); //element a sauvegarder
        outState.putString("CELESTE", celeste); //element a sauvegarder
        outState.putString("UNITECHAINE", uniteChaine); //element a sauvegarder
        outState.putDouble("RESULTAT", resultat); //element a sauvegarder
        outState.putString("EDIT", edit); //element a sauvegarder
    }

    //recupere l'instance sauvegardé
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        poids = savedInstanceState.getDouble("POIDS"); //element a recuperer
        gravite = savedInstanceState.getDouble("GRAVITE");; //element a recuperer
        unite = savedInstanceState.getDouble("UNITE");; //element a recuperer
        celeste = savedInstanceState.getString("CELESTE");; //element a recuperer
        uniteChaine = savedInstanceState.getString("UNITECHAINE");; //element a recuperer
        resultat = savedInstanceState.getDouble("RESULTAT");; //element a recuperer
        edit = savedInstanceState.getString("EDIT");; //element a recuperer
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my_weight_on, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart() {
        super.onStart();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "MyWeightOn Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://noga.td1.android.iutsd.myweighton/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "MyWeightOn Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://noga.td1.android.iutsd.myweighton/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.disconnect();
    }
}
