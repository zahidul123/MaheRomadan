package com.example.maheromadan.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

 @Generated("jsonschema2pojo")
 public class Datum {

     @SerializedName("distCode")
     @Expose
     private String distCode;
     @SerializedName("distName")
     @Expose
     private String distName;
     @SerializedName("distRamadanDetails")
     @Expose
     private List<DistRamadanDetail> distRamadanDetails = null;

     public String getDistCode() {
         return distCode;
     }

     public void setDistCode(String distCode) {
         this.distCode = distCode;
     }

     public String getDistName() {
         return distName;
     }

     public void setDistName(String distName) {
         this.distName = distName;
     }

     public List<DistRamadanDetail> getDistRamadanDetails() {
         return distRamadanDetails;
     }

     public void setDistRamadanDetails(List<DistRamadanDetail> distRamadanDetails) {
         this.distRamadanDetails = distRamadanDetails;
     }

 }
