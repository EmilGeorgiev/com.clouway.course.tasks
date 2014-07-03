package com.clouway.core;

/**
 * Created by clouway on 7/3/14.
 */
public class BookBuilder {

  public static BookBuilder newBook() {
    return new BookBuilder();
  }

  private String title;
  private String publishers;
  private int publisherYear;
  private int belongsPage;

  public BookBuilder title(String title) {
    this.title = title;
    return this;
  }

  public BookBuilder publishers(String publishers) {
    this.publishers = publishers;
    return this;
  }

  public BookBuilder publisherYear(int publisherYear) {
    this.publisherYear = publisherYear;
    return this;
  }

  public BookBuilder belongsPage(int belongspage) {
    this.belongsPage = belongspage;
    return this;
  }

  public Book build() {
    return new Book(title, publishers, publisherYear, belongsPage);
  }
}
