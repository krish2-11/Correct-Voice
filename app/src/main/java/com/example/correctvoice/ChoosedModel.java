package com.example.correctvoice;

import com.example.correctvoice.Model.Model;

public class ChoosedModel {
    Model article;
    private ChoosedModel(){}
    static class Singleton{
        public static ChoosedModel choosedModel = new ChoosedModel();
    }

    public static ChoosedModel getInstance(){
        return Singleton.choosedModel;
    }

    public Model getArticle() {
        return article;
    }

    public void setArticle(Model article) {
        this.article = article;
    }
}
