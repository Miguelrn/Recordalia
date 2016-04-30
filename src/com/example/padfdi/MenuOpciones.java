package com.example.padfdi;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;

/**
 * Created by Pomer on 30/04/2015.
 */
/**
 * An activity representing a Settings detail screen. This
 * activity is only used on handset devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link LugarListActivityMain}.
 * <p/>
 * This activity is mostly just a 'shell' activity containing nothing
 * more than a {@link LugarDetailFragment}.
 */
public class MenuOpciones extends ActionBarActivity {

    private int mOrdenadoPor;
    private int mOrdenacion;

    public static final String EXTRA_MESSAGE = "com.example.pad.pad.Message";
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_menu_opciones);

        // Show the Up button in the action bar.
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        SharedPreferences sharedPref = this.getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        int defaultValue = 1;// = getResources().getInteger(R.string);
        mOrdenadoPor = sharedPref.getInt(getString(R.string.saved_ordenado_por),defaultValue);
        mOrdenacion = sharedPref.getInt(getString(R.string.saved_ordenacion),defaultValue);


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

           /* NuevoLugarFragment fragment = new NuevoLugarFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.lugar_detail_container, fragment)
                    .commit();*/
        }

        switch (mOrdenadoPor){
            case 1: ((RadioButton)this.findViewById(R.id.radio0)).setChecked(true); break;
            case 2: ((RadioButton)this.findViewById(R.id.radio1)).setChecked(true); break;
            case 3: ((RadioButton)this.findViewById(R.id.radio2)).setChecked(true); break;
        }
        switch (mOrdenacion){
            case 1: ((RadioButton)this.findViewById(R.id.radio3)).setChecked(true); break;
            case 2: ((RadioButton)this.findViewById(R.id.radio4)).setChecked(true); break;
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

    public void registrarOpciones(View view){//falta pornerlo en el otro fragmento...hasta que no funcione nada

        //----------------------ordenado por-------------------------------//
        if(((RadioButton)this.findViewById(R.id.radio0)).isChecked()){//fecha de publicacion
            mOrdenadoPor = 1;
        }
        else if(((RadioButton)this.findViewById(R.id.radio1)).isChecked()){//valoracion
            mOrdenadoPor = 2;
        }
        else if(((RadioButton)this.findViewById(R.id.radio2)).isChecked()){//nombre del lugar
            mOrdenadoPor = 3;
        }

        //--------------------------ordenacion----------------------------//
        if(((RadioButton)this.findViewById(R.id.radio3)).isChecked()){//mayor a menor
            mOrdenacion = 1;
        }
        else if(((RadioButton)this.findViewById(R.id.radio4)).isChecked()){//menor a mayor
            mOrdenacion = 2;
        }

        SharedPreferences sharedPref = this.getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(getString(R.string.saved_ordenado_por), mOrdenadoPor);
        editor.putInt(getString(R.string.saved_ordenacion), mOrdenacion);
        editor.commit();

    }

    public void valoresPorDefecto(View view){
        ((RadioButton)this.findViewById(R.id.radio0)).setChecked(true);
        ((RadioButton)this.findViewById(R.id.radio1)).setChecked(false);
        ((RadioButton)this.findViewById(R.id.radio2)).setChecked(false);

        ((RadioButton)this.findViewById(R.id.radio3)).setChecked(true);
        ((RadioButton)this.findViewById(R.id.radio4)).setChecked(false);

        //y guardarlo en las preferencias !!

        mOrdenadoPor = 1;
        mOrdenacion = 1;

        SharedPreferences sharedPref = this.getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(getString(R.string.saved_ordenado_por), mOrdenadoPor);
        editor.putInt(getString(R.string.saved_ordenacion), mOrdenacion);
        editor.commit();

    }



}


