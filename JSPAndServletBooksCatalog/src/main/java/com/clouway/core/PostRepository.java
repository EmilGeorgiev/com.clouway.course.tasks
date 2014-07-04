package com.clouway.core;

import java.util.List;

/**
 * Created by clouway on 7/4/14.
 */
public interface PostRepository {

  List<Post> findPostByBookId(int bookId);

  void addPost(Post post);
}
