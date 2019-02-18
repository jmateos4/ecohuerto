package com.triana.salesianos.ecohuerto20.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PlantacionResponse {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("nombre")
        @Expose
        private String nombre;
        @SerializedName("tipo")
        @Expose
        private String tipo;
        @SerializedName("huerto")
        @Expose
        private String huerto;
        @SerializedName("riegoAut")
        @Expose
        private Boolean riegoAut;
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
        public PlantacionResponse() {
        }

        public PlantacionResponse(String nombre, String tipo, String huerto, Boolean riegoAut){
            this.nombre=nombre;
            this.tipo=tipo;
            this.huerto=huerto;
            this.riegoAut=riegoAut;
        }


        /**
         *
         * @param updatedAt
         * @param nombre
         * @param id
         * @param riegoAut
         * @param huerto
         * @param createdAt
         * @param tipo
         */
        public PlantacionResponse(String id, String nombre, String tipo, String huerto, Boolean riegoAut, String createdAt, String updatedAt) {
            super();
            this.id = id;
            this.nombre = nombre;
            this.tipo = tipo;
            this.huerto = huerto;
            this.riegoAut = riegoAut;
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

        public String getTipo() {
            return tipo;
        }

        public void setTipo(String tipo) {
            this.tipo = tipo;
        }

        public String getHuerto() {
            return huerto;
        }

        public void setHuerto(String huerto) {
            this.huerto = huerto;
        }

        public Boolean getRiegoAut() {
            return riegoAut;
        }

        public void setRiegoAut(Boolean riegoAut) {
            this.riegoAut = riegoAut;
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
