package com.clouway.core;

/**
 * Created by clouway on 7/4/14.
 */
public class Post {

  private final String author;
  private final String postContent;
  private final int bookId;

  public Post(String author, String postContent, int bookId) {

    this.author = author;
    this.postContent = postContent;
    this.bookId = bookId;
  }

  public String getAuthor() {
    return author;
  }

  public String getPostContent() {
    return postContent;
  }
}
