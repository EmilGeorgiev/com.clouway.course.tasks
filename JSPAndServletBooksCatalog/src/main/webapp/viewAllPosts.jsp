<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.clouway.core.Post" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: clouway
  Date: 7/4/14
  Time: 11:42 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<link rel="stylesheet" href="viewAllPosts.css">
 <%
     List<Post> postList = book.getPostList();
 %>
<head>
    <title></title>
</head>
<body>

<%--<%if (postList.size() != 0) {%>--%>
<label>Posts</label>
<div class="allPosts">
    <% for(Post post : postList) {%>
    <div class="post">
        <i><%=post.getAuthor()%></i> &nbsp;||
        <i><%=post.getPostContent()%></i>
    </div>
    <br/>
    <% } %>
<%--<%}%>--%>

</div>
</body>
</html>
