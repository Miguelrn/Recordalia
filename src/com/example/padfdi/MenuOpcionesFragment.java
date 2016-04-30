
package com.example.padfdi;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;


/**
 * A fragment representing a single Lugar detail screen.
 * This fragment is either contained in a {@link LugarListActivityMain}
 * in two-pane mode (on tablets) or a {@link LugarDetailActivity}
 * on handsets.
 */
public class MenuOpcionesFragment extends Fragment {

    private int mOrdenadoPor;
    private int mOrdenacion;
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public MenuOpcionesFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_menu_opciones, container, false);

        SharedPreferences sharedPref = this.getActivity().getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        int defaultValue = 1;// = getResources().getInteger(R.string);
        mOrdenadoPor = sharedPref.getInt(getString(R.string.saved_ordenado_por),defaultValue);
        mOrdenacion = sharedPref.getInt(getString(R.string.saved_ordenacion),defaultValue);
        switch (mOrdenadoPor){
            case 1: ((RadioButton)rootView.findViewById(R.id.radio0)).setChecked(true); break;
            case 2: ((RadioButton)rootView.findViewById(R.id.radio1)).setChecked(true); break;
            case 3: ((RadioButton)rootView.findViewById(R.id.radio2)).setChecked(true); break;
        }
        switch (mOrdenacion){
            case 1: ((RadioButton)rootView.findViewById(R.id.radio3)).setChecked(true); break;
            case 2: ((RadioButton)rootView.findViewById(R.id.radio4)).setChecked(true); break;
        }


        return rootView;
    }


}


