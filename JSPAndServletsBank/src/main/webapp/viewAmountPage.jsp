<%@ page import="org.eclipse.jdt.internal.compiler.lookup.SourceTypeBinding" %>
<%--
  Created by IntelliJ IDEA.
  User: clouway
  Date: 6/19/14
  Time: 3:46 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<% String currentAmount = (String) request.getAttribute("currentAmount");
%>
<head>
    <title></title>
</head>
<body>
<form method="POST" action="/viewCurrentAmountServlet">
    <input type="submit" value="Current Amount">
</form>

<% if(currentAmount != null) { %>
    Current amount is: <%= currentAmount%>
<% } %>

<a href="mainPage.jsp">Main Page</a>
</body>
</html>
