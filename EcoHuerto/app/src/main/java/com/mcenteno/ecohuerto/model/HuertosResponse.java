
package com.mcenteno.ecohuerto.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class HuertosResponse {

    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("rows")
    @Expose
    private List<Huerto> huertos = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public HuertosResponse() {
    }

    /**
     * 
     * @param count
     * @param rows
     */
    public HuertosResponse(Integer count, List<Huerto> rows) {
        super();
        this.count = count;
        this.huertos = rows;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<Huerto> getRows() {
        return huertos;
    }

    public void setRows(List<Huerto> rows) {
        this.huertos = rows;
    }


}
