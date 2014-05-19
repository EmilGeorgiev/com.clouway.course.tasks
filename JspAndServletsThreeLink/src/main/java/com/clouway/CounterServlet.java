package com.clouway;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by clouway on 5/19/14.
 */
public class CounterServlet extends HttpServlet{

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    resp.setContentType("text/html");
    HttpSession session = req.getSession();


    String link = req.getParameter("param");
    Integer count = (Integer) session.getAttribute(link);

    if(count == null) {
      count = 1;
    } else {
      count += 1;
    }

    session.setAttribute(link, count);
    resp.sendRedirect("/threelinks.jsp");
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    doGet(req, resp);
  }
}
