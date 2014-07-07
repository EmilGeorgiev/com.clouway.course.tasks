<%@ page import="com.clouway.core.Book" %>
<%--
  Created by IntelliJ IDEA.
  User: clouway
  Date: 7/4/14
  Time: 11:13 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<link rel="stylesheet" href="bookDetails.css">
<%
    Book book = (Book) request.getAttribute("details");
%>
<head>
    <title></title>
</head>
<body>
<div class="main">
    <h1 align="center">Book Info</h1>
    <div>
       <label>Title</label>
        <div class="bookInfo"><%=book.getTitle()%></div>
    </div><br/>
    <div>
        <label>Description</label>
        <div class="bookInfo"><%=book.getDescription()%></div>
    </div><br/>
    <div>
        <%@include file="/viewAllPosts.jsp"%>
    </div><br/>

    <div class="addPosts">
        <h4>Add new post</h4>
        <form action="postController?bookId=<%=book.getId()%>" method="POST">
            <div class="bookInfo">
                <label>
                    Author<input type="text" name="author">
                </label>
                <label>
                    Content<input type="text" name="postContent">
                </label><br/>
                <input type="submit" value="Add Post">
            </div>
        </form>
    </div>
</div>
<br/>
</body>
</html>
