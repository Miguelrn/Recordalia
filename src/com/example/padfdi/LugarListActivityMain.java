package com.example.padfdi;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RatingBar;

import java.util.ArrayList;


public class LugarListActivityMain extends ActionBarActivity implements LugarListFragment.Callbacks {


    private boolean mTwoPane;
    public static final String ORDENACION = "com.example.padfdi.ordenacion";
    public static final String ORDENACION2= "com.example.padfdi.ordenacion2";
    //public static final String STATE_SCORE = "com.example.padfdi.state_score";
    private int mOrdenadoPor;
    private int mOrdenacion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sharedPref = this.getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        int defaultValue = 1;// = getResources().getInteger(R.string);
        mOrdenadoPor = sharedPref.getInt(getString(R.string.saved_ordenado_por),defaultValue);
        mOrdenacion = sharedPref.getInt(getString(R.string.saved_ordenacion),defaultValue);

        if(savedInstanceState == null)
            ListaLugares.setContext(this,mOrdenadoPor,mOrdenacion);
        setContentView(R.layout.activity_lugar_list);

        if (findViewById(R.id.lugar_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-large and
            // res/values-sw600dp). If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;

            if (savedInstanceState != null) {
                return;
            }
            // In two-pane mode, list items should be given the
            // 'activated' state when touched.
            ((LugarListFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.lugar_list))
                    .setActivateOnItemClick(true);
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main, menu);
        //MenuItem mediaRouteMenuItem = menu.findItem(R.id.media_route_menu_item);
       // MediaRouteActionProvider mediaRouteActionProvider = (MediaRouteActionProvider) MenuItemCompat.getActionProvider(mediaRouteMenuItem);

       // mediaRouteActionProvider.setRouteSelector(mMediaRouteSelector);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.aniadir_lugar) {
            if(mTwoPane){
                //NuevoLugar fragment = new NuevoLugar();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.lugar_detail_container, new NuevoLugarFragment())
                        .commit();
            }else{

                Intent formularioIntent = new Intent(this, NuevoLugar.class);
                startActivity(formularioIntent);

            }


            return true;
        }
        else if (id == R.id.opciones) {
        	if(mTwoPane){
                //NuevoLugar fragment = new NuevoLugar();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.lugar_detail_container, new MenuOpcionesFragment())
                        .commit();
            }else{

                Intent formularioIntent = new Intent(this, MenuOpciones.class);
                startActivity(formularioIntent);

            }


            return true;
        	
        }
        return super.onOptionsItemSelected(item);
    }

   /* @Override
    protected void onResume() {
        super.onResume();
        ((BaseAdapter)getListAdapter()).notifyDataSetChanged();
    }*/



    /**
     * Callback method from {@link LugarListFragment.Callbacks}
     * indicating that the item with the given ID was selected.
     */
    @Override
    public void onItemSelected(int id) {
        if (mTwoPane) {
            Bundle arguments = new Bundle();
            arguments.putInt(LugarDetailFragment.ARG_ITEM_ID, id);
            LugarDetailFragment fragment = new LugarDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.lugar_detail_container, fragment)
                    .commit();

        } else {
            Intent detailIntent = new Intent(this, LugarDetailActivity.class);
            detailIntent.putExtra(LugarDetailFragment.ARG_ITEM_ID, Integer.toString(id));
            startActivity(detailIntent);
        }
    }

    /*
        @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the user's current game state
        savedInstanceState.putInt(STATE_SCORE, mCurrentScore);//solo sirve para guardar un estado y no carge de nuevo la BD

        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {
        // Always call the superclass so it can restore the view hierarchy
        super.onRestoreInstanceState(savedInstanceState);

        // Restore state members from saved instance
        mCurrentScore = savedInstanceState.getInt(STATE_SCORE);
    }

    @Override
    protected void onStop(){
        super.onStop();

        SharedPreferences sharedPref = this.getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(getString(R.string.saved_high_score), mCurrentScore);
        editor.commit();
        //guardar todos los datos que sean necesarios
    }
    @Override
    protected void onPause(){
        super.onPause();

        SharedPreferences sharedPref = this.getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(getString(R.string.saved_high_score), mCurrentScore);
        editor.commit();
        //guardar todos los datos que sean necesarios
    }
    */

    public void borrarFormulario(View v){//funciones llamadas desde tablet

        ((EditText) this.findViewById(R.id.NombreLugar)).setText("");
        ((EditText) this.findViewById(R.id.DireccionLugar)).setText("");
        ((EditText) this.findViewById(R.id.TelefonoContacto)).setText("");
        ((EditText) this.findViewById(R.id.UrlSitio)).setText("");
        ((EditText) this.findViewById(R.id.TipoEstablecimiento)).setText("");
        //((Spinner) this.findViewById(R.id.TipoEstablecimiento)).;//resetear el valor ¿?

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


        //llamar a la BD -> ojo que hacer con la id de la lista
        ListaLugares.addItem(new Lugar(NombreLugar, DireccionLocal, TelefonoContacto, UrlSitio, Comentarios, valoracion, TipoEstablecimiento));//spinner sube api lv y no funciona bien

        borrarFormulario(view);//limpiamos
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
