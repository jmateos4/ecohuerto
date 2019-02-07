
package com.mcenteno.ecohuerto.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Huerto {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("nombre")
    @Expose
    private String nombre;
    @SerializedName("localizacion")
    @Expose
    private String localizacion;
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
     * @param createdAt
     * @param localizacion
     * @param user
     */
    public Huerto(String id, String nombre, String localizacion, String user, String createdAt, String updatedAt) {
        super();
        this.id = id;
        this.nombre = nombre;
        this.localizacion = localizacion;
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

    public String getLocalizacion() {
        return localizacion;
    }

    public void setLocalizacion(String localizacion) {
        this.localizacion = localizacion;
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
