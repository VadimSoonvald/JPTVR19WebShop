<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Список проданных пицц</title>
</head>
<body>
<h1>Список проданных пицц</h1>
<a href="index.jsp">Домой</a>
<hr>
<ol>
    <c:forEach var="history" items="${listHistories}">
        <li>
            <p><strong>Название пиццы и размер: </strong>${history.product.title} ${history.product.size} мм.</p>
            <p><strong>Цена пиццы: </strong>${history.product.price} евро</p>
            <p><strong>Осталось пиццы: </strong>${history.product.count} шт.</p>
            <hr>
        </li>
    </c:forEach>
</ol>
</body>
</html>
