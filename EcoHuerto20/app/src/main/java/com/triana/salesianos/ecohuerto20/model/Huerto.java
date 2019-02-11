
package com.triana.salesianos.ecohuerto20.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Huerto {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("nombre")
    @Expose
    private String nombre;
    @SerializedName("direccion")
    @Expose
    private String direccion;
    @SerializedName("foto")
    @Expose
    private String foto;
    @SerializedName("espacio")
    @Expose
    private Espacio espacio;
    @SerializedName("user")
    @Expose
    private String user;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("updatedAt")
    @Expose
    private String updatedAt;

    /**
     * No args constructor for use in serialization
     *
     */
    public Huerto() {
    }

    /**
     *
     * @param updatedAt
     * @param nombre
     * @param id
     * @param direccion
     * @param createdAt
     * @param espacio
     * @param foto
     * @param user
     */
    public Huerto(String id, String nombre, String direccion, String foto, Espacio espacio, String user, String createdAt, String updatedAt) {
        super();
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.foto = foto;
        this.espacio = espacio;
        this.user = user;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Espacio getEspacio() {
        return espacio;
    }

    public void setEspacio(Espacio espacio) {
        this.espacio = espacio;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

}
