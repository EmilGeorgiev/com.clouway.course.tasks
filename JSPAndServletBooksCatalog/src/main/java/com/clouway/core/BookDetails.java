package com.clouway.core;

/**
 * Created by clouway on 7/4/14.
 */
public class BookDetails {
  private final Book book;
  private final Post post;

  public BookDetails(Book book, Post post) {

    this.book = book;
    this.post = post;
  }

  public Book getBook() {
    return book;
  }

  public Post getPost() {
    return post;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    BookDetails details = (BookDetails) o;

    if (book != null ? !book.equals(details.book) : details.book != null) return false;
    if (post != null ? !post.equals(details.post) : details.post != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = book != null ? book.hashCode() : 0;
    result = 31 * result + (post != null ? post.hashCode() : 0);
    return result;
  }
}
