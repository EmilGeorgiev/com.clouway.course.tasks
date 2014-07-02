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
<%
    Integer lastPage = (Integer) request.getAttribute("lastPage");
    Integer currentPage = (Integer) request.getAttribute("currentPage");
    Integer nextPage = currentPage + 1;
    Integer previousPage = currentPage - 1;
    Integer booksNumber = (Integer) request.getAttribute("booksNumber");

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
    <div class="catalog">
        <div>
            <ul class="navigation">
                <li><a href="/navigationPageController?page=1">First Page</a></li>
                <li><a href="/navigationPageController?page=<%=previousPage%>">Previous Page</a></li>
                <li><a href="/navigationPageController?page=<%=nextPage%>">Next Page</a></li>
                <li><a href="/navigationPageController?page=<%=lastPage%>">Last Page</a></li>
            </ul>
        </div>
        <p align="center"><%=currentPage%></p>
        <div class="books">
            <%
                String book = "book";
                for (int i = 1; i <= booksNumber; i++) {
                    book = book + i; %>

                    <div class="book">
                        <a href="/bookInfoController?title=<%=book%>" target="_blank"><%=book%>></a><br/>
                        Publishers: <book:info input="Hermes"/><br/>
                        Release Year: <book:info input="2013"/><br/>
                    </div>

                    <%
                    book = "book";
                }%>
        </div>
    </div>
</div>
</body>
</html>
