<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<h2>Hello World!</h2>
</body>
<form action="display.jsp">
    Student name: <input type="text" name="name"/>
    Student number: <input type="text" name="number"/>
    <input type="submit" value="submit">
</form>
<c:redirect url="display.jsp"/>
</html>
