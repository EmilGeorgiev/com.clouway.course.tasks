<%@ page import="com.clouway.core.BankAccountMessages" %>
<%--
  Created by IntelliJ IDEA.
  User: clouway
  Date: 6/16/14
  Time: 5:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="loginStyle.css">
    <title></title>
</head>
<body>
    <div class="main" align="center">
        <div align="center">
            <div class="header">
               <h3>Internet Bank</h3>
            </div>
            <form method="POST" action="/loginServlet">
                User Name: <input type="text" name="user_name">
                <br/>
                Password: <input type="password" name="user_password">
                <br/>
                <input type="submit" value="LOGIN">
            </form>
        </div>
        <div align="center">
            <%@ include file="footer.jsp" %>
        </div>
    </div>

</body>
</html>
