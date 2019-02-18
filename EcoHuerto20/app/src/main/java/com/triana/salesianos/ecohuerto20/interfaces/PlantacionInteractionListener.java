package com.triana.salesianos.ecohuerto20.interfaces;

public interface PlantacionInteractionListener {
    public void borrarPlantacion(String idPlantacion);
    public void setRiegoAutOnOff(String idPlantacion, String nombre, String tipo, String huerto, Boolean riegoAut);
}
