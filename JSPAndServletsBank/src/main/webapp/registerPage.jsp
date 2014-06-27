<%--
  Created by IntelliJ IDEA.
  User: clouway
  Date: 6/25/14
  Time: 10:39 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <link rel="stylesheet" href="registerPage.css">
    <title></title>
</head>
<body>
<div class="wrapper" align="center">
    <div class="title">
        <h1>Internet Bank</h1>
    </div>
    <%
        Object attribute = request.getAttribute("javax.servlet.error.message");

        if (attribute != null) { %>

    <p><% out.println(attribute); %></p>
    <% } %>
    <form method="POST" action="/registerServlet">
        <div class="row">
            <label for="userName" class="dataUser">User Name</label>
            <div class="textField">
                <input type="text" id="userName" name="user_name">
            </div>
        </div>

        <div class="row">
            <label for="password" class="dataUser">Password</label>
            <div class="textField">
                <input type="password" id="password" name="user_password">
            </div>
        </div>
        <div class="register">
            <input type="submit" value="Register">
        </div>
    </form>
</div>


</body>
</html>
