package com.twu.biblioteca.domain;

public class Book extends Item {

    private static int NEXT_BOOK_ID = 1;

    private String title;
    private String author;
    private Integer publicationYear;

    public Book(String title, String author, Integer publicationYear) {
        super(NEXT_BOOK_ID);
        NEXT_BOOK_ID++;

        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(Integer publicationYear) {
        this.publicationYear = publicationYear;
    }

}
