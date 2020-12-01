package com.codecoy.caribbean.data_model;

import android.os.Parcel;
import android.os.Parcelable;

public class CountryInformation implements Parcelable {
    private String name;

    private String motto;
    private String language;
    private String population;
    private String capital;
    private double temperature;
    private String currencyName;
   private String extraInformation;

    public CountryInformation() {
    }


    protected CountryInformation(Parcel in) {
        name = in.readString();
        motto = in.readString();
        language = in.readString();
        population = in.readString();
        capital = in.readString();
        temperature = in.readDouble();
        currencyName = in.readString();
        extraInformation = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(motto);
        dest.writeString(language);
        dest.writeString(population);
        dest.writeString(capital);
        dest.writeDouble(temperature);
        dest.writeString(currencyName);
        dest.writeString(extraInformation);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<CountryInformation> CREATOR = new Creator<CountryInformation>() {
        @Override
        public CountryInformation createFromParcel(Parcel in) {
            return new CountryInformation(in);
        }

        @Override
        public CountryInformation[] newArray(int size) {
            return new CountryInformation[size];
        }
    };

    public String getExtraInformation() {
        return extraInformation;
    }

    public void setExtraInformation(String extraInformation) {
        this.extraInformation = extraInformation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getMotto() {
        return motto;
    }

    public void setMotto(String motto) {
        this.motto = motto;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }


}
