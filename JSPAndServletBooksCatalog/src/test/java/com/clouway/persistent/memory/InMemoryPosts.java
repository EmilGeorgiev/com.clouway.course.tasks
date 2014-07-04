package com.clouway.persistent.memory;

import com.clouway.core.Post;
import com.clouway.core.PostRepository;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by clouway on 7/4/14.
 */
public class InMemoryPosts implements PostRepository {

  private Map<Integer, Post> postMap = new HashMap<Integer, Post>();

  public InMemoryPosts() {
    fillInMap();
  }

  private void fillInMap() {
    postMap.put(1, new Post("Ivan", "Ivan Post", 1));
    postMap.put(2, new Post("Petar", "Peter Post", 2));
    postMap.put(3, new Post("Stoqn", "Stoqn Post", 3));
    postMap.put(4, new Post("Georgi", "Georgi Post", 4));
  }

  @Override
  public Post findPostByBookId(int bookId) {
    return postMap.get(bookId);
  }

  @Override
  public void addPost(Post post) {

  }
}
