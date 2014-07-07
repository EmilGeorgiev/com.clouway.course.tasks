<%@ page import="com.clouway.core.UserMessage" %>
<jsp:useBean id="userinfo" class="com.clouway.core.UserData"/>
<jsp:setProperty name="userinfo" property="*"/>
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
                           value="<jsp:getProperty property="first_name" name="userinfo"/>">
                </div>
                <%--<div class="message"><i><%=firstNameMessage %></i></div>--%>
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
                            value="<jsp:getProperty property="last_name" name="userinfo"/>">
                </div>
                <%--<div class="message"><i><%=lastNameMessage %></i></div>--%>
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
                            value="<jsp:getProperty property="user_egn" name="userinfo"/>">
                </div>
                <%--<div class="message"><i><%=egnMessage%></i></div>--%>
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
                            value="<jsp:getProperty property="user_age" name="userinfo"/>">
                </div>
                <%--<div class="message"><i><%=ageMessage%></i></div>--%>
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
                            value="<jsp:getProperty property="user_address" name="userinfo"/>">
                </div>
                <%--<div class="message"><i><%=addressMessage%></i></div>--%>
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
                <%--<div class="message"><i><%=passwordMessage %></i></div>--%>
            </div>
            <br/>
            <input type="submit" value="Valid Data">

        </form>

    </div>
</div>
</body>
</html>
