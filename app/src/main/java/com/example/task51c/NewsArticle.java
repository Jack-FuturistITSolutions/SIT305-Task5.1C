package com.example.task51c;
public class NewsArticle {
    private int id;
    private String title;
    private int imageResource;
    private String date;
    private String author;
    private String articleBody;

    public NewsArticle(int id, String title, int imageResource, String date, String author, String articleBody) {
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
    public int getImageResource() { return imageResource; }
    public String getDate() { return date; }
    public String getAuthor() { return author; }
    public String getArticleBody() { return articleBody; }
}

