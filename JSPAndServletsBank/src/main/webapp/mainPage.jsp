<%@ page import="com.clouway.persistents.PersistentUserDAO" %>
<%--
  Created by IntelliJ IDEA.
  User: clouway
  Date: 6/17/14
  Time: 2:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <link rel="stylesheet" href="mainStyle.css">
</head>
<body>

    <div class="main" align="center">
        <div class="title">
            <h1 align="center">INTERNET BANK</h1>
        </div>
        <div class="menu">
            <ul class="navigationBar">
                <li><a href="/depositPage.jsp">Deposit</a></li>
                <li><a href="/withdrawPage.jsp">Withdraw</a></li>
                <li><a href="/viewAmountPage.jsp">Amount</a></li>
            </ul>
        </div>
        <div>
            <form method="POST" class="footer" action="/logoutServlet">
                <input type="submit" value="Logout">
            </form>
        </div>
    </div>


</body>
</html>
