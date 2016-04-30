//package com.cuatro.pad.padfdi;
package com.example.padfdi;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
//import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity implements MenuFragment.OnHeadlineSelectedListener{

    //private static final String EXTRA_MESSAGE = "com.example.padfdi.MainActivity";


	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lugares_recientes);
        if (findViewById(R.id.fragment_container) != null) {

            if (savedInstanceState != null) {
                return;
            }

            // Create a new Fragment to be placed in the activity layout
            MenuFragment firstFragment = new MenuFragment();

            // In case this activity was started with special instructions from an
            // Intent, pass the Intent's extras to the fragment as arguments
            firstFragment.setArguments(getIntent().getExtras());

            // Add the fragment to the 'fragment_container' FrameLayout
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, firstFragment).commit();
        }

        //------------------------------------------------------------------------//
        LugarBD bbdd = new LugarBD(this);//contexto

        SQLiteDatabase db = bbdd.getWritableDatabase();

        ContentValues values = new ContentValues();
        //values.put(LugarBD.COLUMN_NAME_ENTRY_ID, 1);
        values.put(LugarBD.COLUMN_NAME_NOMBRE, "prueba 1");
        values.put(LugarBD.COLUMN_NAME_DIRECCION, "calle falsa 123");
        values.put(LugarBD.COLUMN_NAME_LONGITUD, " 40°32'8''N");
        values.put(LugarBD.COLUMN_NAME_LATITUD, "3°38'45''W");
        values.put(LugarBD.COLUMN_NAME_TELEFONO, "+34913333333");
        values.put(LugarBD.COLUMN_NAME_URL, "restaurante.com");
        values.put(LugarBD.COLUMN_NAME_COMENTARIO, "No volver !! xD");
        values.put(LugarBD.COLUMN_NAME_VALORACION, "1");
        values.put(LugarBD.COLUMN_NAME_TIPOESTABLECIMIENTO, "restaurante");

        long newRowId;//la id asociada a la entrada que vamos a crear
        newRowId = db.insert(LugarBD.TABLE_NAME, null, values);
        db.close();

        //----------------------------------------------------------------------------//

        db = bbdd.getReadableDatabase();

        String[] projection = {//solo las columnas que vamos a necesitar
                //LugarBD.COLUMN_NAME_ENTRY_ID,
                LugarBD.COLUMN_NAME_NOMBRE,
                LugarBD.COLUMN_NAME_DIRECCION,
                LugarBD.COLUMN_NAME_LATITUD,
                LugarBD.COLUMN_NAME_LONGITUD,
                LugarBD.COLUMN_NAME_TELEFONO,
                LugarBD.COLUMN_NAME_URL,
                LugarBD.COLUMN_NAME_COMENTARIO,
                LugarBD.COLUMN_NAME_VALORACION,
                LugarBD.COLUMN_NAME_TIPOESTABLECIMIENTO
        };

        String sortOrder = LugarBD.COLUMN_NAME_NOMBRE + " DESC";

        Cursor c = db.query(
                LugarBD.TABLE_NAME,
                projection,
                null,                // The columns for the WHERE clause
                null,                // The values for the WHERE clause
                null,                // don't group the rows
                null,                // don't filter by row groups
                sortOrder            // The sort order
        );

        c.moveToFirst();
        //long itemId = c.getLong(c.getColumnIndexOrThrow(LugarBD.COLUMN_NAME_ENTRY_ID));
        db.close();

        //hacer bucle for de lo que mida el cursor Â¿? -> crear new LUGAR()
        String message = c.getString(0)+" - "+c.getString(1)+" - "+c.getString(2)+" - "+c.getString(3)+" -" +
                " "+c.getString(4)+" - "+c.getString(5)+" - "+c.getString(6)+" - "+c.getString(7)+" - "+c.getString(8);//tipo devuelve nulo Â¿?

        //TextView text = (TextView) this.findViewById(R.id.article_fragment);
        //text.setText(message);

        System.out.println(message);

        c.close();
        
        
        
        
        /*Button nueva = new Button(this);
        nueva.on*/
    }
    
    private void nuevoFormulario(){
    	Intent intent = new Intent(this, FormularioNuevo.class);
        startActivity(intent);
    	
    }
    

    private void insertToBD(){


    }

    private void updateBD(){

        /*
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // New value for one column
        ContentValues values = new ContentValues();
        values.put(FeedEntry.COLUMN_NAME_TITLE, title);

        // Which row to update, based on the ID
        String selection = FeedEntry.COLUMN_NAME_ENTRY_ID + " LIKE ?";
        String[] selectionArgs = { String.valueOf(rowId) };

        int count = db.update(
            FeedReaderDbHelper.FeedEntry.TABLE_NAME,
            values,
            selection,
            selectionArgs);
        */
    }

    private void deleteFromBD(){
/*
        // Define 'where' part of query.
        String selection = LugarBD.COLUMN_NAME_ENTRY_ID + " LIKE ?";
        // Specify arguments in placeholder order.
        String[] selectionArgs = { String.valueOf(rowId) };
        // Issue SQL statement.
        db.delete(table_name, selection, selectionArgs);
*/
    }


    //@Override
   /* public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }*/

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
    public void onArticleSelected(int position) {
        // The user selected the headline of an article from the HeadlinesFragment

        // Capture the article fragment from the activity layout
        LugarFragment articleFrag = (LugarFragment)
                getSupportFragmentManager().findFragmentById(R.id.article_fragment);

        if (articleFrag != null) {
            // If article frag is available, we're in two-pane layout...

            // Call a method in the ArticleFragment to update its content
            articleFrag.updateArticleView(position);


        } else {
            // If the frag is not available, we're in the one-pane layout and must swap frags...

            // Create fragment and give it an argument for the selected article
            LugarFragment newFragment = new LugarFragment();
            Bundle args = new Bundle();
            args.putInt(LugarFragment.ARG_POSITION, position);
            newFragment.setArguments(args);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            // Replace whatever is in the fragment_container view with this fragment,
            // and add the transaction to the back stack so the user can navigate back
            transaction.replace(R.id.fragment_container, newFragment);
            transaction.addToBackStack(null);//para navegar hacia atras en las selecciones

            // Commit the transaction
            transaction.commit();
        }
    }
}
