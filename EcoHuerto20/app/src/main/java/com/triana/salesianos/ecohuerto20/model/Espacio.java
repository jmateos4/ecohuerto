package com.triana.salesianos.ecohuerto20.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Espacio<T> {

    @SerializedName("plantaciones")
    @Expose
    private List<T> plantaciones = null;
    @SerializedName("dimensiones")
    @Expose
    private String dimensiones;

    /**
     * No args constructor for use in serialization
     *
     */
    public Espacio() {
    }

    /**
     *
     * @param plantaciones
     * @param dimensiones
     */
    public Espacio(List<T> plantaciones, String dimensiones) {
        super();
        this.plantaciones = plantaciones;
        this.dimensiones = dimensiones;
    }

    public List<T> getPlantaciones() {
        return plantaciones;
    }

    public void setPlantaciones(List<T> plantaciones) {
        this.plantaciones = plantaciones;
    }

    public String getDimensiones() {
        return dimensiones;
    }

    public void setDimensiones(String dimensiones) {
        this.dimensiones = dimensiones;
    }

}
