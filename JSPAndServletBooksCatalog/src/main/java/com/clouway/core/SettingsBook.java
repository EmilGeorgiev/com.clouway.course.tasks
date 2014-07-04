package com.clouway.core;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import java.util.List;

/**
 * Created by clouway on 7/4/14.
 */
@Singleton
public class SettingsBook implements Configured<BookDetails> {

  private final BookRepository bookRepository;
  private final PostRepository postRepository;

  @Inject
  public SettingsBook(BookRepository bookRepository, PostRepository postRepository) {

    this.bookRepository = bookRepository;
    this.postRepository = postRepository;
  }

  @Override
  public BookDetails configure(String id) {
    int bookId = Integer.parseInt(id);
    Book book = bookRepository.findBookById(bookId);

    List<Post> postList = postRepository.findPostByBookId(bookId);

    return  new BookDetails(book, postList);
  }
}
