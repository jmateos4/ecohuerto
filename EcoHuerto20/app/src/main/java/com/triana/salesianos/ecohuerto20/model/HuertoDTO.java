package com.triana.salesianos.ecohuerto20.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HuertoDTO {

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


  /**
   * No args constructor for use in serialization
   *
   */
  public HuertoDTO() {
  }

  public HuertoDTO(String nombre, String direccion, Espacio espacio) {
    this.nombre = nombre;
    this.direccion = direccion;
    this.espacio = espacio;
  }

  public HuertoDTO(String nombre, String direccion, String foto, Espacio espacio) {
    this.nombre = nombre;
    this.direccion = direccion;
    this.foto = foto;
    this.espacio = espacio;
  }

  public HuertoDTO(String id, String nombre, String direccion, String foto, Espacio espacio) {
    this.id = id;
    this.nombre = nombre;
    this.direccion = direccion;
    this.foto = foto;
    this.espacio = espacio;
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


}
