package com.clouway.core;

import java.util.List;

/**
 * Created by clouway on 7/4/14.
 */
public class BookDetails {
  private final Book book;
  private final List<Post> posts;

  public BookDetails(Book book, List<Post> posts) {

    this.book = book;
    this.posts = posts;
  }

  public Book getBook() {
    return book;
  }

  public List<Post> getPosts() {
    return posts;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    BookDetails details = (BookDetails) o;

    if (book != null ? !book.equals(details.book) : details.book != null) return false;
    if (posts != null ? !posts.equals(details.posts) : details.posts != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = book != null ? book.hashCode() : 0;
    result = 31 * result + (posts != null ? posts.hashCode() : 0);
    return result;
  }
}
