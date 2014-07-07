package com.clouway.http;

import com.clouway.core.Post;
import com.clouway.core.PostRepository;
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
public class PostControllerTest {

  private Post newPost;

  private PostController postController;

  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();

  @Mock
  private HttpServletResponse response = null;

  @Mock
  private HttpServletRequest request = null;

  @Mock
  private SiteMap siteMap = null;

  @Mock
  private PostRepository postRepository = null;

  @Before
  public void setUp() {

    postController = new PostController(postRepository, siteMap);
  }

  @Test
  public void addNewPost() throws Exception {

    pretendThatSameUserAddPost(user("Ivan"), postContent("Very good book"), bookId(1));

    context.checking(new Expectations() {{
      oneOf(siteMap).author();
      will(returnValue("author"));

      oneOf(request).getParameter("author");
      will(returnValue("Ivan"));

      oneOf(request).getParameter("postContent");
      will(returnValue("Very good book"));

      oneOf(siteMap).postContent();
      will(returnValue("postContent"));

      oneOf(siteMap).bookId();
      will(returnValue("bookId"));

      oneOf(request).getParameter("bookId");
      will(returnValue("1"));


      oneOf(postRepository).addPost(newPost);

      oneOf(siteMap).viewBookController();
      will(returnValue("/viewBookController"));

      oneOf(response).sendRedirect("/viewBookController");
    }
    });

    postController.doPost(request, response);

  }

  private int bookId(int bookId) {
    return bookId;
  }

  private void pretendThatSameUserAddPost(String author, String postContent, int bookId) {
    newPost = new Post(author, postContent, bookId);
  }

  private String user(String postAuthor) {
    return postAuthor;
  }

  private String postContent(String postContent) {
    return postContent;
  }


}
