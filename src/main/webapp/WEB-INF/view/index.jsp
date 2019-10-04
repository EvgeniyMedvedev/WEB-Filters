<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: thele
  Date: 02.10.2019
  Time: 19:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
</head>
<body>
<div>
    <h1>Заголовок!</h1>
</div>

<h2>Все пользователи</h2><br/>
<c:forEach var="user" items="${requestScope.users}">
    <ul>
        <li><c:out value="${user}"/></li>
        <form method="post" action="/home/delete">
            <input type="number" hidden name="id" value="${user.id}"/>
            <input type="submit" name="delete" value="Удалить"/>
        </form>

        <form method="get" action="/home/update">
            <input type="number" hidden name="id" value="${user.id}"/>
            <input type="submit" value="Редактировать"/>
        </form>
    </ul>
    <hr />

</c:forEach>

<h2>Создание нового пользователя</h2><br/>

<form action="/home/add" method="post">
    <label>
        <input type="text" name="first_name">Имя<br/>
    </label>

    <label>
        <input type="text" name="last_name">Фамилия<br/>
    </label>
    <button type="submit">Добавить</button>
</form>

</body>
</html>
