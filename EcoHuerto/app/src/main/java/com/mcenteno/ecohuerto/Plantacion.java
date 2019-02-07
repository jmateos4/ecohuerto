package com.mcenteno.ecohuerto;

public class Plantacion {

    private String nombre;
    private String tipo;
    private String riegoAutomatico;

    public Plantacion(String nombre, String tipo, String riegoAutomatico) {
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

    public String getRiegoAutomatico() {
        return riegoAutomatico;
    }

    public Plantacion setRiegoAutomatico(String riegoAutomatico) {
        this.riegoAutomatico = riegoAutomatico;
        return this;
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
