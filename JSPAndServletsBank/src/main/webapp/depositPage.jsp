<%--
  Created by IntelliJ IDEA.
  User: clouway
  Date: 6/19/14
  Time: 2:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>

<form method="POST" action="/transactionServlet">

    Amount: <input type="text" name="transactionAmount">
            <input type="hidden" name="transactionType" value="deposit">
            <input type="submit" value="Deposit">
</form>
</body>
</html>
