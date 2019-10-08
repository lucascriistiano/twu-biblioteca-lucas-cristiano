package com.twu.biblioteca.domain;

public class Book {

    public static int NEXT_BOOK_ID = 1;

    private Integer id;
    private String title;
    private String author;
    private Integer publicationYear;
    private BookStatus status;

    public Book(String title, String author, Integer publicationYear) {
        this.id = NEXT_BOOK_ID;
        NEXT_BOOK_ID++;
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.status = BookStatus.AVAILABLE;
    }

    public Book(String title, String author, Integer publicationYear, BookStatus status) {
        this(title, author, publicationYear);
        this.status = status;
    }

    public Integer getId() {
        return id;
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

    public BookStatus getStatus() {
        return status;
    }

    public void setStatus(BookStatus status) {
        this.status = status;
    }

}
