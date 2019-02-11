package com.triana.salesianos.ecohuerto20.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Espacio {

    @SerializedName("plantaciones")
    @Expose
    private List<Object> plantaciones = null;
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
    public Espacio(List<Object> plantaciones, String dimensiones) {
        super();
        this.plantaciones = plantaciones;
        this.dimensiones = dimensiones;
    }

    public List<Object> getPlantaciones() {
        return plantaciones;
    }

    public void setPlantaciones(List<Object> plantaciones) {
        this.plantaciones = plantaciones;
    }

    public String getDimensiones() {
        return dimensiones;
    }

    public void setDimensiones(String dimensiones) {
        this.dimensiones = dimensiones;
    }

}
