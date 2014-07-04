package com.clouway.http;

import com.clouway.core.Configured;
import com.clouway.core.PageDetails;
import com.clouway.core.PageDetailsBuilder;
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
  private PageDetails pageDetails;

  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();

  @Mock
  private HttpServletRequest request = null;

  @Mock
  private HttpServletResponse response = null;

  @Mock
  private SiteMap siteMap = null;

  @Mock
  private Configured configured = null;

  @Before
  public void setUp() {
    navigationPage = new NavigationPageController(siteMap, configured);
  }

  @Test
  public void initialNavigateToFirstPage() throws Exception {

    pretendThatVisitStoreForFirstTime(previousPage(1),
            nextPage(2),
            lastPage(10),
            currentPage(1));

    context.checking(new Expectations() {{

      oneOf(siteMap).requestPage();
      will(returnValue("requestPage"));

      oneOf(request).getParameter("requestPage");
      will(returnValue(null));

      oneOf(configured).configure(null);
      will(returnValue(pageDetails));

      oneOf(siteMap).requestPage();
      will(returnValue("requestPage"));

      oneOf(request).setAttribute("requestPage", pageDetails);

      oneOf(siteMap).catalog();
      will(returnValue("catalog.jsp"));

      oneOf(request).getRequestDispatcher("catalog.jsp");
    }
    });

    navigationPage.doPost(request, response);
  }

  private void pretendThatVisitStoreForFirstTime(int previousPage, int nextPage, int lastPage, int currentPage) {
    pageDetails = PageDetailsBuilder.newPageDetails()
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

  @Test
  public void navigateToAnyPage() throws Exception {

    context.checking(new Expectations() {{

      oneOf(siteMap).requestPage();
      will(returnValue("requestPage"));

      oneOf(request).getParameter("requestPage");
      will(returnValue("3"));

      oneOf(configured).configure("3");
      will(returnValue(pageDetails));

      oneOf(siteMap).requestPage();
      will(returnValue("requestPage"));

      oneOf(request).setAttribute("requestPage", pageDetails);

      oneOf(siteMap).catalog();
      will(returnValue("catalog.jsp"));

      oneOf(request).getRequestDispatcher("catalog.jsp");
    }
    });

    navigationPage.doPost(request, response);

  }
}
