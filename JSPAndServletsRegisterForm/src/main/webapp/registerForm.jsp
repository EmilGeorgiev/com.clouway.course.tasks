<%@ page import="com.clouway.core.UserMessage" %>
<%@ page import="java.util.Map" %>
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
        Map<String, UserMessage> messageMap = (Map<String, UserMessage>) request.getAttribute("messages");

        String firstNameMessage = messageMap.get("first_name").getAuthenticateMessage();
        String firstNameValue = messageMap.get("first_name").getFieldValue();

        String lastNameMessage = messageMap.get("last_name").getAuthenticateMessage();
        String lastNameValue = messageMap.get("last_name").getFieldValue();

        String egnMessage = messageMap.get("user_egn").getAuthenticateMessage();
        String egnValue = messageMap.get("user_egn").getFieldValue();

        String ageMessage = messageMap.get("user_age").getAuthenticateMessage();
        String ageValue = messageMap.get("user_age").getFieldValue();

        String addressMessage = messageMap.get("user_address").getAuthenticateMessage();
        String addressValue = messageMap.get("user_address").getFieldValue();

        String passwordMessage = messageMap.get("user_password").getAuthenticateMessage();
        String passwordValue = messageMap.get("user_password").getFieldValue();

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
        <form class="registerForm" method="POST" action="/validatorServlet">

            <div class="row">
                <label class="userData" for="firstName">First Name:</label>
                <div>
                    <input type="text"
                           class="userData"
                           id="firstName"
                           name="first_name"
                           value="<%=firstNameValue%>">
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
                            value="<%=lastNameValue%>">
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
                            value="<%=egnValue%>">
                </div>
                <div class="message"><i><%=egnMessage%></i></div>
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
                            value="<%=ageValue%>">
                </div>
                <div class="message"><i><%=ageMessage%></i></div>
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
                            value="<%=addressValue%>">
                </div>
                <div class="message"><i><%=addressMessage%></i></div>
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
