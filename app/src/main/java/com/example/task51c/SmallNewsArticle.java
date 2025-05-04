package com.example.task51c;

public class SmallNewsArticle {
    private int id;
    private String title;
    private String articleBody;
    private String author;
    private int imageResource;
    private String date;

    // Constructor
    public SmallNewsArticle(int id, String title, int imageResource, String date, String author, String articleBody) {
        this.id = id;
        this.title = title;
        this.imageResource = imageResource;
        this.date = date;
        this.author = author;
        this.articleBody = articleBody;
    }

    // Getters
    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getArticleBody() { return articleBody; }
    public int getImageResource() { return imageResource; }
    public String getDate() { return date; }
}

