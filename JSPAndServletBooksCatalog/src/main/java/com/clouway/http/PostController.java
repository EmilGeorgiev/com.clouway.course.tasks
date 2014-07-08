package com.clouway.http;

import com.clouway.core.Post;
import com.clouway.core.PostRepository;
import com.clouway.core.SiteMap;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by clouway on 7/4/14.
 */
@Singleton
public class PostController extends HttpServlet {

  private final PostRepository postRepository;
  private final SiteMap siteMap;

  @Inject
  public PostController(PostRepository postRepository, SiteMap siteMap) {

    this.postRepository = postRepository;
    this.siteMap = siteMap;
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    doPost(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    String author = req.getParameter(siteMap.author());

    String postContent = req.getParameter(siteMap.postContent());

    String id = siteMap.bookId();

    int bookId = Integer.parseInt(req.getParameter(id));

    postRepository.addPost(new Post(author, postContent, bookId));

    resp.addCookie(new Cookie(id, req.getParameter(id)));

    resp.sendRedirect(siteMap.viewBookController());

  }
}
