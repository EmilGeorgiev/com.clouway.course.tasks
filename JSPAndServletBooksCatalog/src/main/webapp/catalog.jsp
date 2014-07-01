<%--
  Created by IntelliJ IDEA.
  User: clouway
  Date: 6/30/14
  Time: 9:32 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="book" uri="/WEB-INF/SubstringDescriptor.tld"%>
<html>
<head>
    <link rel="stylesheet" href="catalog.css">
    <title></title>
</head>
<body>
<div class="main" align="center">
    <div class="title">
        <h1>Book Catalog</h1>
    </div>
    <div class="catalog">
        <div>
            <ul class="navigation">
                <li><a href="/pageController?page=first_page">First Page</a></li>
                <li><a href="/pageController?page=previous_page">Previous Page</a></li>
                <li><a href="/pageController?page=next_page">Next Page</a></li>
                <li><a href="/pageController?page=last_page">Last Page</a></li>
            </ul>
        </div>
        <div class="books">
            <%
                String book = "book";
                for (int i = 0; i < 20; i++) {
                    book = book + i; %>
                    <a href="/bookInfoController?title=<%=book%>" target="_blank"><%=book%>></a>
                    <book:info input=""></book:info>
            <%}%>
        </div>
    </div>
</div>
</body>
</html>
