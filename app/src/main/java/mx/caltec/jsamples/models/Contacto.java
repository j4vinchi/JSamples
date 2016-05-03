package mx.caltec.jsamples.models;

import android.net.Uri;

/**
 * Created by jcruz on 03/05/16.
 */
public class Contacto {
    private Uri photo;
    private String nombre;
    private String email;
    private String telefono;

    public Contacto(Uri photo, String nombre, String email, String telefono) {
        this.photo = photo;
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
    }

    public Uri getPhoto() {
        return photo;
    }

    public void setPhoto(Uri photo) {
        this.photo = photo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
