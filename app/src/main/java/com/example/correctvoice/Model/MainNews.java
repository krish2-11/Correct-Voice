package com.example.correctvoice.Model;

import java.util.ArrayList;

public class MainNews {
    String status;
    ArrayList<Model> results;

    public String getStatus() {
        return status;
    }

    public ArrayList<Model> getResults() {
        return results;
    }

    public MainNews(String status, ArrayList<Model> results) {
        this.status = status;
        this.results = results;
    }
}
