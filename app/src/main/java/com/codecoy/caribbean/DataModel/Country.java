package com.codecoy.caribbean.DataModel;

import java.util.ArrayList;
import java.util.List;

public class Country {

    private Information information;
    private Slider slider;
    private String history;
    private Delicacies delicacies;

    public Country() {
        information=new Information();
        slider=new Slider();
    }

    private  class Information{
        private String name;
        private String flagImageUrl;
        private String motto;
        private String language;
        private int population;
        private String capital;
        private double temperature;
        private String currencyName;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getFlagImageUrl() {
            return flagImageUrl;
        }

        public void setFlagImageUrl(String flagImageUrl) {
            this.flagImageUrl = flagImageUrl;
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

        public int getPopulation() {
            return population;
        }

        public void setPopulation(int population) {
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
    private class Slider{

        private List<String> images;
        private List<String> videos;

        public Slider() {

            images=new ArrayList<>();
            videos=new ArrayList<>();

        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }

        public List<String> getVideos() {
            return videos;
        }

        public void setVideos(List<String> videos) {
            this.videos = videos;
        }
    }
    private class Delicacies{

        private List<String> images;
        private List<String> videos;
        private String description;

        public Delicacies() {

            images=new ArrayList<>();
            videos=new ArrayList<>();

        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }

        public List<String> getVideos() {
            return videos;
        }

        public void setVideos(List<String> videos) {
            this.videos = videos;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }

}


