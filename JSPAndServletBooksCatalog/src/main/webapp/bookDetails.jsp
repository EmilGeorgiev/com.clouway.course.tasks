<%@ page import="com.clouway.core.Book" %>
<%@ page import="com.clouway.core.Post" %>
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
<% for (Post post : book.getPostList()) {%>
    <div>
        <i><%=post.getAuthor()%></i>&nbsp;&nbsp;||
        <i><%=post.getPostContent()%></i>
    </div>
    <br/>
<%}%>

<%@include file="/viewAllPosts.jsp"%>

<div>
    <form action="addPostController" method="POST">
        <div>
            <h4>Add new post</h4>
            <input type="text" name="content">
            <input type="text" name="author"><br/>
            <input type="submit" value="Add Post">
        </div>
    </form>
</div>
</body>
</html>
