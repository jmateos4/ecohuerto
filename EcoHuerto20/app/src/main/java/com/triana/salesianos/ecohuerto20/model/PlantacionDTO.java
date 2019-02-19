package com.triana.salesianos.ecohuerto20.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PlantacionDTO {

    @SerializedName("huerto")
    @Expose
    private String huerto;
    @SerializedName("nombre")
    @Expose
    private String nombre;
    @SerializedName("tipo")
    @Expose
    private String tipo;
    @SerializedName("riegoAut")
    @Expose
    private boolean riegoAut;

    public PlantacionDTO() {
    }

    public PlantacionDTO(String huerto, String nombre, String tipo) {
        this.huerto = huerto;
        this.nombre = nombre;
        this.tipo = tipo;
        this.riegoAut = false;
    }

    public String getHuerto() {
        return huerto;
    }

    public PlantacionDTO setHuerto(String huerto) {
        this.huerto = huerto;
        return this;
    }

    public String getNombre() {
        return nombre;
    }

    public PlantacionDTO setNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public String getTipo() {
        return tipo;
    }

    public PlantacionDTO setTipo(String tipo) {
        this.tipo = tipo;
        return this;
    }

    public boolean isRiegoAut() {
        return riegoAut;
    }

    public PlantacionDTO setRiegoAut(boolean riegoAut) {
        this.riegoAut = riegoAut;
        return this;
    }
}
