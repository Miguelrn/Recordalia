package com.example.padfdi;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



/**
 * A fragment representing a single Lugar detail screen.
 * This fragment is either contained in a {@link LugarListActivityMain}
 * in two-pane mode (on tablets) or a {@link LugarDetailActivity}
 * on handsets.
 */
public class NuevoLugarFragment extends Fragment {


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public NuevoLugarFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_nuevo_lugar, container, false);


        return rootView;
    }


}


