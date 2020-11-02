package com.codecoy.caribbean.dataModel;

import android.os.Parcel;
import android.os.Parcelable;

public class Country implements Parcelable {
    private CountryInformation information;
    private CountrySlider countrySlider;
    private String history;
    private Delicacies delicacies;
    private ReligionAndCulture religionAndCulture;
    private String countryId;
    private String flagImageUrl;
    private String armFlagUrl;

    public Country() {
        information=new CountryInformation();
        countrySlider =new CountrySlider();
        delicacies=new Delicacies();
        religionAndCulture=new ReligionAndCulture();
    }


    protected Country(Parcel in) {
        information = in.readParcelable(CountryInformation.class.getClassLoader());
        countrySlider = in.readParcelable(CountrySlider.class.getClassLoader());
        history = in.readString();
        delicacies = in.readParcelable(Delicacies.class.getClassLoader());
        religionAndCulture = in.readParcelable(ReligionAndCulture.class.getClassLoader());
        countryId = in.readString();
        flagImageUrl = in.readString();
        armFlagUrl = in.readString();
    }

    public static final Creator<Country> CREATOR = new Creator<Country>() {
        @Override
        public Country createFromParcel(Parcel in) {
            return new Country(in);
        }

        @Override
        public Country[] newArray(int size) {
            return new Country[size];
        }
    };

    public ReligionAndCulture getReligionAndCulture() {
        return religionAndCulture;
    }

    public void setReligionAndCulture(ReligionAndCulture religionAndCulture) {
        this.religionAndCulture = religionAndCulture;
    }

    public void setInformation(CountryInformation information) {
        this.information = information;
    }

    public void setCountrySlider(CountrySlider countrySlider) {
        this.countrySlider = countrySlider;
    }

    public void setDelicacies(Delicacies delicacies) {
        this.delicacies = delicacies;
    }




    public String getArmFlagUrl() {
        return armFlagUrl;
    }

    public void setArmFlagUrl(String armFlagUrl) {
        this.armFlagUrl = armFlagUrl;
    }

    public String getFlagImageUrl() {
        return flagImageUrl;
    }

    public void setFlagImageUrl(String flagImageUrl) {
        this.flagImageUrl = flagImageUrl;
    }

    public CountryInformation getInformation() {
        return information;
    }

    public CountrySlider getCountrySlider() {
        return countrySlider;
    }

    public Delicacies getDelicacies() {
        return delicacies;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(information, flags);
        dest.writeParcelable(countrySlider, flags);
        dest.writeString(history);
        dest.writeParcelable(delicacies, flags);
        dest.writeParcelable(religionAndCulture, flags);
        dest.writeString(countryId);
        dest.writeString(flagImageUrl);
        dest.writeString(armFlagUrl);
    }
}
