package com.codecoy.caribbean.data_model;

public class ATM {

    private String id;
    private String atmName;
    private String atmImage;
    private Double lat;
    private Double lng;


    public ATM() {
    }


    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public String getAtmImage() {
        return atmImage;
    }

    public void setAtmImage(String atmImage) {
        this.atmImage = atmImage;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAtmName() {
        return atmName;
    }

    public void setAtmName(String atmName) {
        this.atmName = atmName;
    }
}
