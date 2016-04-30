//package com.cuatro.pad.padfdi;
package com.example.padfdi;



public class Lugar{
    private String nombre;
    private String direccion;
    private String foto;
    private String telefono;
    private String url;
    private String comentario;
    private long fecha;
    private float valoracion;
    private String tipo;
    private int _id;//esteid es solo para el callback del listview



    public Lugar(String nombre, String direccion, /*String longitud,
                 String latitud, */String telefono, String url, String comentario,
                 float valoracion, String tipo, int id) {
        fecha = System.currentTimeMillis();

        this.nombre = nombre;
        this.direccion = direccion;
        //this.latitud = latitud;
        //this.longitud = longitud;
        this.telefono = telefono;
        this.url = url;
        this.comentario = comentario;
        this.valoracion = valoracion;
        this.tipo = tipo;
        this._id = id;

    }

    public Lugar(String nombre, String direccion, /*String longitud,
                 String latitud, */String telefono, String url, String comentario,
                 float valoracion, String tipo) {
        fecha = System.currentTimeMillis();

        this.nombre = nombre;
        this.direccion = direccion;
        //this.latitud = latitud;
        //this.longitud = longitud;
        this.telefono = telefono;
        this.url = url;
        this.comentario = comentario;
        this.valoracion = valoracion;
        this.tipo = tipo;
        this._id = -1;//para que falle si no lo cambia correctamente
    }


    @Override
    public String toString() {
        return this.nombre + " \n" + this.direccion;
    }

    public String getNombre() {
        return this.nombre;
    }

    public String toString2(){
        return "Sitio: " + this.nombre +
                "\nDireccion: " +this.direccion +
                "\nTelefono: " +this.telefono +
                "\nLink: " +this.url +
                "\nValoracion: " +this.valoracion +
                "\nComentarios: " +this.comentario;
    }

    public int get_id() {
        return _id;
    }

    public String getDireccion() {
        return direccion;
    }

    /*public String getLatitud() {
        return latitud;
    }

    public String getLongitud() {
        return longitud;
    }*/

    @android.webkit.JavascriptInterface
    public String getFoto() {
        return foto;
    }

    @android.webkit.JavascriptInterface
    public String getTelefono() {
        return telefono;
    }

    @android.webkit.JavascriptInterface
    public String getUrl() {
        return url;
    }

    @android.webkit.JavascriptInterface
    public String getComentario() {
        return comentario;
    }

    @android.webkit.JavascriptInterface
    public long getFecha() {
        return fecha;
    }

    @android.webkit.JavascriptInterface
    public float getValoracion() {
        return valoracion;
    }

    @android.webkit.JavascriptInterface
    public String getTipo() {
        return tipo;
    }

    @android.webkit.JavascriptInterface
    public void set_id(int id){//no recomendable....
        this._id = id;
    }
}

