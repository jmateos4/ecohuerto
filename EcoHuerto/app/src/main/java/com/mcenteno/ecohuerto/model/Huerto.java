package com.mcenteno.ecohuerto.model;

public class Huerto {

    private String nombre;
    private Espacio espacio;
    private String localizacion;

    public Huerto(String nombre, Espacio espacio, String localizacion) {
        this.nombre = nombre;
        this.espacio = espacio;
        this.localizacion = localizacion;
    }

    public Huerto(String nombre, String localizacion) {
        this.nombre = nombre;
        this.localizacion = localizacion;
    }

    public String getNombre() {
        return nombre;
    }

    public Huerto setNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public Espacio getEspacio() {
        return espacio;
    }

    public Huerto setEspacio(Espacio espacio) {
        this.espacio = espacio;
        return this;
    }

    public String getLocalizacion() {
        return localizacion;
    }

    public Huerto setLocalizacion(String localizacion) {
        this.localizacion = localizacion;
        return this;
    }

    @Override
    public String toString() {
        return "Huerto{" +
                "nombre='" + nombre + '\'' +
                ", espacio=" + espacio +
                ", localizacion='" + localizacion + '\'' +
                '}';
    }
}
