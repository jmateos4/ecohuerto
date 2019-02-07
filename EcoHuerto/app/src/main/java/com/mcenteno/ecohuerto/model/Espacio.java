package com.mcenteno.ecohuerto.model;

public class Espacio {

    private String nombre;
    private String dimension;
    private Plantacion plantacion;

    public Espacio(String nombre, String dimension, Plantacion plantacion) {
        this.nombre = nombre;
        this.dimension = dimension;
        this.plantacion = plantacion;
    }

    public String getNombre() {
        return nombre;
    }

    public Espacio setNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public String getDimension() {
        return dimension;
    }

    public Espacio setDimension(String dimension) {
        this.dimension = dimension;
        return this;
    }

    public Plantacion getPlantacion() {
        return plantacion;
    }

    public Espacio setPlantacion(Plantacion plantacion) {
        this.plantacion = plantacion;
        return this;
    }

    @Override
    public String toString() {
        return "Espacio{" +
                "nombre='" + nombre + '\'' +
                ", dimension='" + dimension + '\'' +
                ", plantacion=" + plantacion +
                '}';
    }
}
