package com.example.correctvoice.Model;

import java.util.ArrayList;

public class MainNews {
    String status;
    String totalResult;
    ArrayList<Model> articles;

    public String getStatus() {
        return status;
    }

    public String getTotalResult() {
        return totalResult;
    }

    public ArrayList<Model> getArticles() {
        return articles;
    }

    public MainNews(String status, String totalResult, ArrayList<Model> articles) {
        this.status = status;
        this.totalResult = totalResult;
        this.articles = articles;
    }
}
