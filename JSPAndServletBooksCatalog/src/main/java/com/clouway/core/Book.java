package com.clouway.core;

import java.util.List;

/**
 * Created by clouway on 7/3/14.
 */
public class Book {

  private List<Post> postList;
  private String title;
  private String publishers;
  private String description;
  private int publisherYear;
  private int id;
  private int belongsPage;

  public Book() {
  }

  public Book(List<Post> postList, String title, String publishers, int publisherYear, String description, int belongsPage, int id) {

    this.postList = postList;
    this.title = title;
    this.publishers = publishers;
    this.publisherYear = publisherYear;
    this.description = description;
    this.belongsPage = belongsPage;
    this.id = id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Book book = (Book) o;

    if (belongsPage != book.belongsPage) return false;
    if (id != book.id) return false;
    if (publisherYear != book.publisherYear) return false;
    if (publishers != null ? !publishers.equals(book.publishers) : book.publishers != null) return false;
    if (title != null ? !title.equals(book.title) : book.title != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = title != null ? title.hashCode() : 0;
    result = 31 * result + (publishers != null ? publishers.hashCode() : 0);
    result = 31 * result + publisherYear;
    result = 31 * result + id;
    result = 31 * result + belongsPage;
    return result;
  }

  public List<Post> getPostList() {
    return postList;
  }

  public void setPostList(List<Post> postList) {
    this.postList = postList;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public int getPublisherYear() {
    return publisherYear;
  }

  public void setPublisherYear(int publisherYear) {
    this.publisherYear = publisherYear;
  }

  public int getBelongsPage() {
    return belongsPage;
  }

  public void setBelongsPage(int belongsPage) {
    this.belongsPage = belongsPage;
  }

  public String getTitle() {
    return title;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getPublishers() {
    return publishers;
  }

  public void setPublishers(String publishers) {
    this.publishers = publishers;
  }

  public int getYearPublisher() {
    return publisherYear;
  }

  public void setYearPublisher(int publisherYear) {
    this.publisherYear = publisherYear;
  }

  public String getDescription() {
    return description;
  }
}
