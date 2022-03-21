package com.example.maheromadan.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

public



@Generated("jsonschema2pojo")
class Params {

    @SerializedName("Fajr")
    @Expose
    private Integer fajr;
    @SerializedName("Isha")
    @Expose
    private Integer isha;

    public Integer getFajr() {
        return fajr;
    }

    public void setFajr(Integer fajr) {
        this.fajr = fajr;
    }

    public Integer getIsha() {
        return isha;
    }

    public void setIsha(Integer isha) {
        this.isha = isha;
    }

}
