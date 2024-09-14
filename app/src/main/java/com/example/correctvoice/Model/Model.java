package com.example.correctvoice.Model;

public class Model {
    String author , title , urlimage;

    public String getAuther() {
        return author;
    }

    public String getTitle() {
        return title;
    }


    public String getUrlimage() {
        return urlimage;
    }

    public Model(String auther, String title, String urlimage) {
        this.author = auther;
        this.title = title;
        this.urlimage = urlimage;
    }
}
