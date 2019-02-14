package com.triana.salesianos.ecohuerto20.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserResponse {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("picture")
    @Expose
    private String picture;
    @SerializedName("huertos")
    @Expose
    private List<String> huertos = null;

    public UserResponse() { }

    public UserResponse(String id, String name, String picture, List<String> huertos) {
        super();
        this.id = id;
        this.name = name;
        this.picture = picture;
        this.huertos = huertos;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public List<String> getHuertos() {
        return huertos;
    }

    public void setHuertos(List<String> huertos) {
        this.huertos = huertos;
    }

}