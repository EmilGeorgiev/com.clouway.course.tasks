package com.clouway.http;

import com.clouway.core.Book;
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
import java.util.ArrayList;
import java.util.List;

/**
 * Created by clouway on 7/4/14.
 */
public class ViewBookControllerTest {

  private ViewBookController detailsController;
  private Book bookDetails;
  private List<Post> postList = new ArrayList<Post>();

  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();

  @Mock
  private SiteMap siteMap = null;

  @Mock
  private HttpServletResponse response = null;

  @Mock
  private HttpServletRequest request = null;

  @Mock
  private Configured<Book> configured = null;

  @Before
  public void setUp() {
    detailsController = new ViewBookController(siteMap, configured);
  }

  @Test
  public void showBookDetails() throws Exception {
    pretendThatUserWantsToSeeDetailsForBook(new Book(postList,
                                          title("JavaEE"),
                                          publisher("SoftPress"),
                                          publishersYear(2013),
                                          description("Book for JavaEE"),
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

  private String description(String description) {
    return description;
  }

  private void pretendThatUserWantsToSeeDetailsForBook(Book book) {

    bookDetails = book;

  }

  private int bookId(int bookId) {
    return bookId;
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
