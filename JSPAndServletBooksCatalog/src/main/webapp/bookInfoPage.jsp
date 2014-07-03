<%--
  Created by IntelliJ IDEA.
  User: clouway
  Date: 7/1/14
  Time: 3:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <% String bookTitle = (String) request.getAttribute("bookTitle");%>
    <title></title>
</head>
<body>
<div class="bookInfo">
    <div class="header">
        <h1>Book Store</h1>
    </div>
    <div class="bookTitle">
        <h3><%=bookTitle%></h3>
    </div>
    <div class="description">
        <i>This is best reference text book for Java subject
            In this text book Contents are as follows:

            Part I The Java Language
            1.The Genesis of Java
            2.An Overview of Java
            3.Data Types, Variables, and Arrays
            4.Operators
            5.Control Statements
            6.Introducing Classes</i>
    </div>
    <div class="posts">

    </div>
</div>
</body>
</html>
