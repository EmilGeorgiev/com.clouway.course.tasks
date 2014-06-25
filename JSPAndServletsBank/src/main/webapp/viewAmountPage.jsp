<%@ page import="com.clouway.core.Transaction" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: clouway
  Date: 6/19/14
  Time: 3:46 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<link rel="stylesheet" href="viewAmountPage.css">
<%

    Float currentAmount = (Float) request.getAttribute("currentAmount");

%>
<head>
    <title></title>
</head>
<body>


<div align="center" class="currentAmount">
    Current amount is: <%= currentAmount%>
</div>
    <br/>

    <%
        List<Transaction> transactions = (List<Transaction>) request.getAttribute("transactionHistory");

       for(Transaction transaction : transactions) {
           String transactionType = transaction.getTransfer();
           float transactionAmount = transaction.getAmountTransaction();
           Date transactionDate = transaction.getDateTransaction(); %>

            <div class="transactionType">
                <i><%=transactionType%></i>
            </div>
            <div class="transactionAmount">
                <i><%=transactionAmount%></i>
            </div>
            <div class="transactionDate">
                <i><%=transactionDate%></i>
            </div>
            <br/>
       <%}%>
</body>
</html>
