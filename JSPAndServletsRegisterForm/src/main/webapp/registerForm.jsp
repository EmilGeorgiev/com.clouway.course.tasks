<%@ page import="java.util.Enumeration" %>
<%--
  Created by IntelliJ IDEA.
  User: clouway
  Date: 6/26/14
  Time: 3:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%
        String firstNameMessage = (String) request.getAttribute("first_name");
        String lastNameMessage = (String) request.getAttribute("last_name");
        String EGNMessage = (String) request.getAttribute("user_egn");
        String ageMessage = (String) request.getAttribute("user_age");
        String addressMessage = (String) request.getAttribute("user_address");
        String passwordMessage = (String) request.getAttribute("user_password");

    %>
    <link rel="stylesheet" href="registerForm.css">
    <title></title>
</head>
<body>
<div class="main">
    <div class="title" align="center">
        <h1>REGISTER</h1>
    </div>
    <div class="form">
        <form class="registerForm" method="POST" action="/authenticateServlet">

            <div class="row">
                <label class="userData" for="firstName">First Name:</label>
                <div>
                    <input type="text"
                           class="userData"
                           id="firstName"
                           name="first_name"
                           value="<%=request.getAttribute("first_nameValue")%>">
                </div>
                <div class="message"><i><%=firstNameMessage %></i></div>
            </div>
            <br/>
            <div class="row">
                <label class="userData" for="lastName">Last Name:</label>
                <div>
                    <input
                            type="text"
                            class="userData"
                            id="lastName"
                            name="last_name"
                            value="<%=request.getAttribute("last_nameValue")%>">
                </div>
                <div class="message"><i><%=lastNameMessage %></i></div>
            </div>
            <br/>
            <div class="row">
                <label class="userData" for="EGN">EGN:</label>
                <div>
                    <input
                            type="text"
                            class="userData"
                            id="EGN"
                            name="user_egn"
                            value="<%=request.getAttribute("user_egnValue")%>">
                </div>
                <div class="message"><i><%=EGNMessage %></i></div>
            </div>
            <br/>
            <div class="row">
                <label class="userData" for="age">Age:</label>
                <div>
                    <input
                            type="text"
                            class="userData"
                            id="age"
                            name="user_age"
                            value="<%=request.getAttribute("user_ageValue")%>">
                </div>
                <div class="message"><i><%=ageMessage %></i></div>
            </div>
            <br/>
            <div class="row">
                <label class="userData" for="address">Address:</label>
                <div>
                    <input
                            type="text"
                            class="userData"
                            id="address"
                            name="user_address"
                            value="<%=request.getAttribute("user_addressValue")%>">
                </div>
                <div class="message"><i><%=addressMessage %></i></div>
            </div>
            <br/>
            <div class="row">
                <label class="userData" for="password">Password:</label>
                <div>
                    <input
                            type="password"
                            class="userData"
                            id="password"
                            name="user_password">
                </div>
                <div class="message"><i><%=passwordMessage %></i></div>
            </div>
            <br/>
            <input type="submit" value="Valid Data">

        </form>

    </div>
</div>
</body>
</html>
