package com.example.maheromadan.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

public @Generated("jsonschema2pojo")
class Month__1 {

    @SerializedName("number")
    @Expose
    private Integer number;
    @SerializedName("en")
    @Expose
    private String en;

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getEn() {
        return en;
    }

    public void setEn(String en) {
        this.en = en;
    }

}
