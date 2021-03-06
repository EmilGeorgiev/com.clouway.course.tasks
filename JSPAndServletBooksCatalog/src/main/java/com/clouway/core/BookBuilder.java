package com.clouway.core;

import java.util.List;

/**
 * Created by clouway on 7/3/14.
 */
public class BookBuilder {



  public static BookBuilder newBook() {
    return new BookBuilder();
  }

  private List<Post> postList;
  private String title;
  private String publishers;
  private String description;
  private int publisherYear;
  private int belongsPage;
  private int id;

  public BookBuilder postList(List<Post> postList) {
    this.postList =postList;
    return this;
  }

  public BookBuilder title(String title) {
    this.title = title;
    return this;
  }

  public BookBuilder publishers(String publishers) {
    this.publishers = publishers;
    return this;
  }

  public BookBuilder description(String description) {
    this.description = description;
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

  public BookBuilder id(int id) {
    this.id = id;
    return this;
  }

  public Book build() {
    return new Book(postList,
                    title,
                    publishers,
                    publisherYear,
                    description,
                    belongsPage,
                    id);
  }
}
