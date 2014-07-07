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
<%
    Book book = (Book) request.getAttribute("details");
%>
<head>
    <title></title>
</head>
<body>
<h3><%=book.getTitle()%></h3>
<br/>
<p><%=book.getDescription()%></p>

<%@include file="/viewAllPosts.jsp"%>

<div>
    <form action="postController?bookId=<%=book.getId()%>" method="POST">
        <div>
            <h4>Add new post</h4>
            <input type="text" name="author">
            <input type="text" name="postContent"><br/>
            <input type="submit" value="Add Post">
        </div>
    </form>
</div>
</body>
</html>
