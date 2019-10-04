<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: thele
  Date: 04.10.2019
  Time: 17:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Редактировать</title>
</head>
<body>
    <h2>Редактировать</h2>

    <div>Имя:     <c:out value="${requestScope.user.firstName}"/> </div>
    <div>Фамилия: <c:out value="${requestScope.user.lastName}"/> </div><br/>

    <form action = "/home/update" method="post">
        <label>
            <input type="text" name="first_name">Новое Имя<br />
        </label>

        <label>
            <input type="text" name="last_name">Новая Фамилия<br />
        </label>

        <input type="number" hidden name="id" value="${requestScope.user.id}"/>

        <button type="submit">Применить</button>
    </form>
</body>
</html>
