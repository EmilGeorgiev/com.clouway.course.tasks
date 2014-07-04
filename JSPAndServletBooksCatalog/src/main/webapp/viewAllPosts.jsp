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
 <%
     BookDetails details = (BookDetails) request.getAttribute("details");
     List<Post> postList = details.getPosts();
 %>
<head>
    <title></title>
</head>
<body>
<div>
    <% for(Post post : postList) { %>
        <div>
            <i><%=post.getAuthor()%></i> &nbsp;||
            <i><%=post.getPostContent()%></i>
        </div>
        <br/>
    <%}%>
</div>
</body>
</html>
