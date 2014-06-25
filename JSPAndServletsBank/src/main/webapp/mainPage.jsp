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
<%
      String contentContent = (String) request.getAttribute("contentPage");
%>
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
                <li><a href="/includePageController?pageName=deposit">Deposit</a></li>
                <li><a href="/includePageController?pageName=withdraw">Withdraw</a></li>
                <li><a href="/includePageController?pageName=viewAmount">Amount</a></li>
            </ul>
        </div>
        <div class="content">
            <jsp:include page="<%=contentContent%>"/>
        </div>
        <div>
            <form method="POST" class="footer" action="/logoutServlet">
                <input type="submit" value="Logout">
            </form>
        </div>
    </div>

</body>
</html>
