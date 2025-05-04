package com.example.task51c;

public class RelatedArticle {
    private int id;
    private String title;
    private int imageResource;
    private String date;
    private String author;
    private String articleBody;

    // Constructor
    public RelatedArticle(int id, String title, int imageResource, String date, String author, String articleBody) {
        this.id = id;
        this.title = title;
        this.imageResource = imageResource;
        this.date = date;
        this.author = author;
        this.articleBody = articleBody;
    }

    public int getImageResId() {
        return imageResource;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return articleBody;
    }

    public String getAuthor() {
        return author;
    }
    public String getDate() {
        return date;
    }
}
