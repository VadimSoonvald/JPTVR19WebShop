<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Список имеющихся пицц</title>
</head>

<h1>Список имеющихся пицц</h1>
<a href="index.jsp">Домой</a>
<hr>
<ol>
    <c:forEach var="product" items="${listProducts}">
        <li>
            <p><strong>Название пиццы и размер: </strong>${product.title} ${product.size} мм.</p>
            <p><strong>Цена пиццы: </strong>${product.price} евро</p>
            <p><strong>Осталось пиццы: </strong>${product.count} шт.</p>
            <hr>
        </li>
    </c:forEach>

</ol>

</html>
