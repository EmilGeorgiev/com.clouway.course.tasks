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

    pretendThatSameUserAddPost(user("Ivan"), postContent("Very gook book"), bookId(1));

    context.checking(new Expectations() {{
      oneOf(siteMap).author();
      will(returnValue("author"));

      oneOf(siteMap).postContent();
      will(returnValue("postContent"));

      oneOf(siteMap).bookId();
      will(returnValue("bookId"));

      oneOf(postRepository).addPost(newPost);

      oneOf(siteMap).bookInfoPage();
      will(returnValue("bookInfoPage.jsp"));

      oneOf(response).sendRedirect("bookInfoPage.jsp");
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
