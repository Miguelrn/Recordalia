package com.example.padfdi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;

/**
 * Created by Pomer on 30/04/2015.
 */
/**
 * An activity representing a single Lugar detail screen. This
 * activity is only used on handset devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link LugarListActivityMain}.
 * <p/>
 * This activity is mostly just a 'shell' activity containing nothing
 * more than a {@link LugarDetailFragment}.
 */
public class NuevoLugar extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_nuevo_lugar);

        // Show the Up button in the action bar.
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // savedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don't need to manually add it.
        // For more information, see the Fragments API guide at:
        //
        // http://developer.android.com/guide/components/fragments.html
        //
        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.

            NuevoLugarFragment fragment = new NuevoLugarFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.lugar_detail_container, fragment)
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. Use NavUtils to allow users
            // to navigate up one level in the application structure. For
            // more details, see the Navigation pattern on Android Design:
            //
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            //
            NavUtils.navigateUpTo(this, new Intent(this, LugarListActivityMain.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void borrarFormulario(View v){

        ((EditText) this.findViewById(R.id.NombreLugar)).setText("");
        ((EditText) this.findViewById(R.id.DireccionLugar)).setText("");
        ((EditText) this.findViewById(R.id.TelefonoContacto)).setText("");
        ((EditText) this.findViewById(R.id.UrlSitio)).setText("");
        ((EditText)this.findViewById(R.id.TipoEstablecimiento)).setText("");
        //((Spinner) this.findViewById(R.id.TipoEstablecimiento)).;//resetear el valor

        ((RatingBar) this.findViewById(R.id.Puntuacion)).setRating(0);
        ((EditText) this.findViewById(R.id.Comentarios)).setText("");

        //recargar el listado

    }


    public void mandarBD(View view) {


        String NombreLugar = ((EditText)this.findViewById(R.id.NombreLugar)).getText().toString();
        String DireccionLocal = ((EditText)this.findViewById(R.id.DireccionLugar)).getText().toString();
        //EditText text = (EditText) findViewById(R.id.TelefonoContacto);
        String TelefonoContacto =((EditText)this.findViewById(R.id.TelefonoContacto)).getText().toString();
        float  valoracion = ((RatingBar)this.findViewById(R.id.Puntuacion)).getRating();
        String Comentarios = ((EditText)this.findViewById(R.id.Comentarios)).getText().toString();
        String UrlSitio = ((EditText)this.findViewById(R.id.UrlSitio)).getText().toString();
        String TipoEstablecimiento = ((EditText)this.findViewById(R.id.TipoEstablecimiento)).getText().toString();
        //int tipoEstablecimiento = ((Spinner)this.findViewById(R.id.TipoEstablecimiento)).getGravity();


        ListaLugares.addItem(new Lugar(NombreLugar, DireccionLocal, TelefonoContacto, UrlSitio, Comentarios, valoracion, TipoEstablecimiento));

        borrarFormulario(view);
    }


}


