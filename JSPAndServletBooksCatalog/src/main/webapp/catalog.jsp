<%@ page import="com.clouway.core.Book" %>
<%@ page import="com.clouway.core.Page" %>
<%--
  Created by IntelliJ IDEA.
  User: clouway
  Date: 6/30/14
  Time: 9:32 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="book" uri="/WEB-INF/SubstringDescriptor.tld"%>
<%--<jsp:useBean id="currentPage" class="com.clouway.core.PageDetails"/>--%>
<%--<jsp:setProperty name="currentPage" property="*"/>--%>
<html>
<%
    Page currentPage = (Page) request.getAttribute("requestPage");
%>

<head>
    <link rel="stylesheet" href="catalog.css">
    <title></title>
</head>
<body>

<div class="main" align="center">
    <div class="title">
        <h1>Book Catalog</h1>
    </div>

    <i align="center"><%=currentPage.getPageNumber()%></i>

    <div class="catalog">

        <div class="books">
            <%
                for (Book book : currentPage.getBookList()) { %>

                    <div class="book">

                        <a href="/viewBookController?bookId=<%=book.getId()%>" target="_blank"><%=book.getTitle()%></a><br/>
                        Publishers: <i><%=book.getPublishers()%></i><br/>
                        Release Year: <i><%=book.getYearPublisher()%></i><br/>

                    </div>

                    <%}%>
        </div>
        <div>
            <ul class="navigation">
                <li><a href="/navigationController?requestPage=1">First Page</a></li>
                <li><a href="/navigationController?requestPage=<%=currentPage.getPreviousPage()%>">Previous Page</a></li>
                <li><a href="/navigationController?requestPage=<%=currentPage.getNextPage()%>">Next Page</a></li>
                <li><a href="/navigationController?requestPage=<%=pageContext.getServletContext().getInitParameter("lastPage")%>">Last Page</a></li>
            </ul>
        </div>
    </div>
</div>
</body>
</html>
