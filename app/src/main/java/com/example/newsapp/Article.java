package com.example.newsapp;

public class Article {
    private String mArticleTitle;
    private String mArticleSection;
    private String mArticleDate;

   //constructor
    public Article(String mArticleTitle, String mArticleSection, String mArticleDate) {
        this.mArticleTitle = mArticleTitle;
        this.mArticleSection = mArticleSection;
        this.mArticleDate = mArticleDate;
    }


   //getter and setter
    public String getmArticleTitle() {
        return mArticleTitle;
    }

    public void setmArticleTitle(String mArticleTitle) {
        this.mArticleTitle = mArticleTitle;
    }

    public String getmArticleSection() {
        return mArticleSection;
    }

    public void setmArticleSection(String mArticleSection) {
        this.mArticleSection = mArticleSection;
    }

    public String getmArticleDate() {
        return mArticleDate;
    }

    public void setmArticleDate(String mArticleDate) {
        this.mArticleDate = mArticleDate;
    }
}
