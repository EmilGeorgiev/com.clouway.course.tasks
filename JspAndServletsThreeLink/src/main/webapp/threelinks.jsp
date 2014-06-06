<%--
  Created by IntelliJ IDEA.
  User: clouway
  Date: 5/19/14
  Time: 11:45 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>

</head>
<body>
    <a href="/counterServlet?param=first_link">first link </a> <% if (!(session.getAttribute("first_link") == null)) { %>
                                                                       <%=session.getAttribute("first_link") %>
                                                                <% } %>



    <p/>

    <a href="/counterServlet?param=second_link">second link</a> <% if (!(session.getAttribute("second_link") == null)) { %>
                                                                    <%=session.getAttribute("second_link") %>
                                                                <% } %>

    <p/>

    <a href="/counterServlet?param=third_link">third link </a> <% if (!(session.getAttribute("third_link") == null)) { %>
                                                                    <%=session.getAttribute("third_link") %>
                                                            <% } %>

</body>
</html>
