package com.example.padfdi;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Pomer on 08/05/2015.
 */
public class ListaLugaresAdapter extends ArrayAdapter<Lugar> {

   /* protected Activity activity;


    public ListaLugaresAdapter(Activity activity) {
        this.activity = activity;
    }
*/
    public ListaLugaresAdapter(Context context, ArrayList<Lugar> users){
        super(context,0,users);
    }

    @Override
    public int getCount() {
        return ListaLugares.listado.size();
    }

    @Override
    public Lugar getItem(int position) {
        return ListaLugares.listado.get(position);
    }

    @Override
    public long getItemId(int position) {
        return ListaLugares.listado.get(position).get_id();
    }

    /*@Override
    public View getView(int position, View contentView, ViewGroup parent) {
        View vi=contentView;

        if(contentView == null) {
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            vi = inflater.inflate(R.layout.activity_lugar_list, null);

        }
        Log.d("ADAPTER", "Textview updated");
        Lugar item = ListaLugares.listado.get(position);

        /*ImageView image = (ImageView) vi.findViewById(R.id.icono);
        int imageResource = activity.getResources().getIdentifier("icono1.png", null, activity.getPackageName());
        image.setImageDrawable(activity.getResources().getDrawable(imageResource));

        TextView nombre = (TextView) vi.findViewById(R.id.nombre_lugar);
        nombre.setText(item.getNombre());

        TextView tipo = (TextView) vi.findViewById(R.id.Fecha_publicacion);
        tipo.setText(item.toString());//poner la fecha casteada? long to String

        return vi;
    }*/

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Lugar sitio = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_view_format, parent, false);
        }
        // Lookup view for data population
        TextView tvName = (TextView) convertView.findViewById(R.id.tvName);
        TextView tvHome = (TextView) convertView.findViewById(R.id.tvHome);
        // Populate the data into the template view using the data object
        tvName.setText(sitio.getNombre());
        tvHome.setText(sitio.getDireccion());
        // Return the completed view to render on screen
        return convertView;
    }


    //overrride de notifi changed ?
}
