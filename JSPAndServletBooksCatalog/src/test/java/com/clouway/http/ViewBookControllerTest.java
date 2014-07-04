package com.clouway.http;

import com.clouway.core.Book;
import com.clouway.core.BookDetails;
import com.clouway.core.Configured;
import com.clouway.core.Post;
import com.clouway.core.SiteMap;
import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by clouway on 7/4/14.
 */
public class ViewBookControllerTest {

  private ViewBookController detailsController;
  private BookDetails bookDetails;
  private Post bookPost;
  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();

  @Mock
  private SiteMap siteMap = null;

  @Mock
  private HttpServletResponse response = null;

  @Mock
  private HttpServletRequest request = null;

  @Mock
  private Configured<BookDetails> configured;

  @Before
  public void setUp() {
    detailsController = new ViewBookController(siteMap, configured);
  }

  @Test
  public void showBookDetails() throws Exception {
    pretendThatUserWantsToSeeDetailsForBook(new Book(title("JavaEE"),
            publisher("SoftPress"),
            publishersYear(2013),
            belongsPage(4),
            bookId(45)));

    context.checking(new Expectations() {{

      oneOf(siteMap).bookId();
      will(returnValue("bookId"));

      oneOf(request).getParameter("bookId");
      will(returnValue("45"));

      oneOf(configured).configure("45");
      will(returnValue(bookDetails));

      oneOf(siteMap).details();
      will(returnValue("details"));

      oneOf(request).setAttribute("details", bookDetails);

      oneOf(siteMap).bookDetails();
      will(returnValue("bookDetails.jsp"));

      oneOf(request).getRequestDispatcher("bookDetails.jsp");
    }
    });

    detailsController.doPost(request, response);

  }

  private int bookId(int bookId) {
    return bookId;
  }

  private void pretendThatUserWantsToSeeDetailsForBook(Book book) {
    bookPost = new Post(author("Ivan"), post("Ivan post"), book.getId());
    bookDetails = new BookDetails(book, bookPost);
  }

  private String post(String post) {
    return post;
  }

  private String author(String author) {
    return author;
  }

  private int belongsPage(int page) {
    return page;
  }

  private int publishersYear(int publisherYear) {
    return publisherYear;
  }

  private String publisher(String publisher) {
    return publisher;
  }

  private String title(String title) {
    return title;
  }
}
