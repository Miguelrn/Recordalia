package com.example.padfdi;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;


/**
 * A fragment representing a single Lugar detail screen.
 * This fragment is either contained in a {@link LugarListActivityMain}
 * in two-pane mode (on tablets) or a {@link LugarDetailActivity}
 * on handsets.
 */
public class LugarDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";


    private Lugar lugar;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public LugarDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            //mItem = DummyContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));
            //int id = getArguments().getInt(ARG_ITEM_ID);
            //String smartphone = getArguments().getString("smartphone");
            int id;
            //Para saber si estamos en tablet o smartphone
            if(getArguments().containsKey("smartphone")){
                String id_string = getArguments().getString(ARG_ITEM_ID);
                id=Integer.parseInt(id_string);
            }else{
                id = getArguments().getInt(ARG_ITEM_ID);
            }

            lugar = ListaLugares.getLugar(id);
            //System.out.print(id);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_muestra_lugar, container, false);

        // Show the dummy content as text in a TextView.
        /*if (lugar != null) {
            ((TextView) rootView.findViewById(R.id.lugar_detail)).setText(lugar.toString2());
        }*/
        /*String aux = "?";
        aux += "nombre="+lugar.getNombre()+"&";
        aux += "direccion="+lugar.getDireccion()+"&";
        aux += "telefono="+lugar.getTelefono()+"&";
        aux += "url="+lugar.getUrl()+"&";
        aux += "valoracion="+lugar.getValoracion()+"&";
        aux += "tipo="+lugar.getTipo()+"&";
        aux += "comentario="+lugar.getComentario();//+"&";*/

        WebView visorHTML = (WebView)rootView;
        WebSettings webSettings = visorHTML.getSettings();
        webSettings.setJavaScriptEnabled(true);
        visorHTML.setWebChromeClient(new WebChromeClient());
        visorHTML.addJavascriptInterface(lugar, "Android");
        visorHTML.loadUrl("file:///android_asset/lugar/detalle_lugar.html");

        return rootView;
    }
}
