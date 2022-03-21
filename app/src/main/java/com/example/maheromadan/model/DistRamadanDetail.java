package com.example.maheromadan.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

 @Generated("jsonschema2pojo")
 public class DistRamadanDetail {

     @SerializedName("ramadanPeriod")
     @Expose
     private String ramadanPeriod;
     @SerializedName("ramadanDate")
     @Expose
     private String ramadanDate;
     @SerializedName("banglaDate")
     @Expose
     private String banglaDate;
     @SerializedName("engDate")
     @Expose
     private String engDate;
     @SerializedName("weekDay")
     @Expose
     private String weekDay;
     @SerializedName("sehri")
     @Expose
     private String sehri;
     @SerializedName("iftar")
     @Expose
     private String iftar;
     @SerializedName("date")
     @Expose
     private String date;
     @SerializedName("sehriFormatted")
     @Expose
     private String sehriFormatted;
     @SerializedName("iftarFormatted")
     @Expose
     private String iftarFormatted;

     public String getRamadanPeriod() {
         return ramadanPeriod;
     }

     public void setRamadanPeriod(String ramadanPeriod) {
         this.ramadanPeriod = ramadanPeriod;
     }

     public String getRamadanDate() {
         return ramadanDate;
     }

     public void setRamadanDate(String ramadanDate) {
         this.ramadanDate = ramadanDate;
     }

     public String getBanglaDate() {
         return banglaDate;
     }

     public void setBanglaDate(String banglaDate) {
         this.banglaDate = banglaDate;
     }

     public String getEngDate() {
         return engDate;
     }

     public void setEngDate(String engDate) {
         this.engDate = engDate;
     }

     public String getWeekDay() {
         return weekDay;
     }

     public void setWeekDay(String weekDay) {
         this.weekDay = weekDay;
     }

     public String getSehri() {
         return sehri;
     }

     public void setSehri(String sehri) {
         this.sehri = sehri;
     }

     public String getIftar() {
         return iftar;
     }

     public void setIftar(String iftar) {
         this.iftar = iftar;
     }

     public String getDate() {
         return date;
     }

     public void setDate(String date) {
         this.date = date;
     }

     public String getSehriFormatted() {
         return sehriFormatted;
     }

     public void setSehriFormatted(String sehriFormatted) {
         this.sehriFormatted = sehriFormatted;
     }

     public String getIftarFormatted() {
         return iftarFormatted;
     }

     public void setIftarFormatted(String iftarFormatted) {
         this.iftarFormatted = iftarFormatted;
     }

 }
