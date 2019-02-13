package com.triana.salesianos.ecohuerto20.model;

public class Plantacion {

    private String nombre;
    private String tipo;
    private Boolean riegoAutomatico;

    public Plantacion(String nombre, String tipo, Boolean riegoAutomatico) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.riegoAutomatico = riegoAutomatico;
    }

    public String getNombre() {
        return nombre;
    }

    public Plantacion setNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public String getTipo() {
        return tipo;
    }

    public Plantacion setTipo(String tipo) {
        this.tipo = tipo;
        return this;
    }

    public Boolean getRiegoAutomatico() {
        return riegoAutomatico;
    }

    public void setRiegoAutomatico(Boolean riegoAutomatico) {
        this.riegoAutomatico = riegoAutomatico;
    }

    @Override
    public String toString() {
        return "Plantacion{" +
                "nombre='" + nombre + '\'' +
                ", tipo='" + tipo + '\'' +
                ", riegoAutomatico='" + riegoAutomatico + '\'' +
                '}';
    }
}
