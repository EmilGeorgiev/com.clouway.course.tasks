package com.clouway.core;

/**
 * Created by clouway on 7/4/14.
 */
public class  Post {

  private final String author;
  private final String postContent;
  private final int bookId;

  public Post(String author, String postContent, int bookId) {

    this.author = author;
    this.postContent = postContent;
    this.bookId = bookId;
  }

  public int getBookId() {
    return bookId;
  }

  public String getAuthor() {
    return author;
  }

  public String getPostContent() {
    return postContent;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Post post = (Post) o;

    if (bookId != post.bookId) return false;
    if (author != null ? !author.equals(post.author) : post.author != null) return false;
    if (postContent != null ? !postContent.equals(post.postContent) : post.postContent != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = author != null ? author.hashCode() : 0;
    result = 31 * result + (postContent != null ? postContent.hashCode() : 0);
    result = 31 * result + bookId;
    return result;
  }
}
