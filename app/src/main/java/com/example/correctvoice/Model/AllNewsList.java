package com.example.correctvoice.Model;

import java.util.ArrayList;

public class AllNewsList {
    public ArrayList<Model> generalNews = new ArrayList<Model>();
    public ArrayList<Model> entertaimentNews = new ArrayList<Model>();
    public ArrayList<Model> sportsNews = new ArrayList<Model>();
    public ArrayList<Model> healthNews = new ArrayList<Model>();
    public ArrayList<Model> scienceNews = new ArrayList<Model>();
    public ArrayList<Model> technologyNews = new ArrayList<Model>();

    public void setHealthNews(ArrayList<Model> healthNews) {
        this.healthNews = healthNews;
    }

    public void setScienceNews(ArrayList<Model> scienceNews) {
        this.scienceNews = scienceNews;
    }

    public void setTechnologyNews(ArrayList<Model> technologyNews) {
        this.technologyNews = technologyNews;
    }

    public void setBusinessNews(ArrayList<Model> businessNews) {
        this.businessNews = businessNews;
    }

    public ArrayList<Model> getBusinessNews() {
        return businessNews;
    }

    public ArrayList<Model> getTechnologyNews() {
        return technologyNews;
    }

    public ArrayList<Model> getScienceNews() {
        return scienceNews;
    }

    public ArrayList<Model> getHealthNews() {
        return healthNews;
    }

    public ArrayList<Model> businessNews = new ArrayList<Model>();

    private AllNewsList(){}

    static class InitList{
        public static AllNewsList allNewsList = new AllNewsList();
    }

    public static AllNewsList getInstance(){
        return InitList.allNewsList;
    }

    public ArrayList<Model> getGeneralNews() {
        return generalNews;
    }

    public ArrayList<Model> getEntertaimentNews() {
        return entertaimentNews;
    }

    public void setGeneralNews(ArrayList<Model> generalNews) {
        this.generalNews = generalNews;
    }

    public void setEntertaimentNews(ArrayList<Model> entertaimentNews) {
        this.entertaimentNews = entertaimentNews;
    }

    public void setSportsNews(ArrayList<Model> sportsNews) {
        this.sportsNews = sportsNews;
    }

    public ArrayList<Model> getSportsNews() {
        return sportsNews;
    }
}
