package com.clouway.core;

/**
 * Created by clouway on 7/4/14.
 */
public interface PostRepository {

  Post findPostByBookId(int bookId);

  void addPost(Post post);
}
