<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: thele
  Date: 10.10.2019
  Time: 20:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User</title>
</head>
<body>
<h1>Hello <c:out value="${requestScope.user.firstName}"/></h1>

<form action="/home/">
    <input type="submit" value="Выйти"/>
</form>

</body>
</html>
