package com.clouway.http;

import com.clouway.core.BuildPage;
import com.clouway.core.Page;
import com.clouway.core.PageBuilder;
import com.clouway.core.SiteMap;
import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class PageNavigationPageControllerTest {

  private NavigationPageController navigationPage;
  private Page page;

  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();

  @Mock
  private HttpServletRequest request = null;

  @Mock
  private HttpServletResponse response = null;

  @Mock
  private SiteMap siteMap = null;

  @Mock
  private BuildPage buildPage = null;

  @Before
  public void setUp() {
    navigationPage = new NavigationPageController(siteMap, buildPage);
  }

  @Test
  public void initialNavigateToFirstPage() throws Exception {

    pretendThatVisitStoreForFirstTime(previousPage(1),
            nextPage(2),
            lastPage(5),
            currentPage(1));

    context.checking(new Expectations() {{

      oneOf(siteMap).requestPage();
      will(returnValue("requestPage"));

      oneOf(request).getParameter("requestPage");
      will(returnValue(null));

      oneOf(buildPage).configure(null);
      will(returnValue(page));

      oneOf(siteMap).requestPage();
      will(returnValue("requestPage"));

      oneOf(request).setAttribute("requestPage", page);

      oneOf(siteMap).catalog();
      will(returnValue("catalog.jsp"));

      oneOf(request).getRequestDispatcher("catalog.jsp");
    }
    });

    navigationPage.doPost(request, response);
  }


  @Test
  public void navigateToAnyPage() throws Exception {

    context.checking(new Expectations() {{

      oneOf(siteMap).requestPage();
      will(returnValue("requestPage"));

      oneOf(request).getParameter("requestPage");
      will(returnValue("3"));

      oneOf(buildPage).configure("3");
      will(returnValue(page));

      oneOf(siteMap).requestPage();
      will(returnValue("requestPage"));

      oneOf(request).setAttribute("requestPage", page);

      oneOf(siteMap).catalog();
      will(returnValue("catalog.jsp"));

      oneOf(request).getRequestDispatcher("catalog.jsp");
    }
    });

    navigationPage.doPost(request, response);

  }

  private void pretendThatVisitStoreForFirstTime(int previousPage, int nextPage, int lastPage, int currentPage) {
    page = PageBuilder.newPageDetails()
            .previousPage(previousPage)
            .nextPage(nextPage)
            .pageNumber(currentPage)
            .build();
  }

  private int currentPage(int currentPage) {
    return currentPage;
  }

  private int lastPage(int lastPage) {
    return lastPage;
  }

  private int nextPage(int nextPage) {
    return nextPage;
  }

  private int previousPage(int previousPage) {
    return previousPage;
  }
}
