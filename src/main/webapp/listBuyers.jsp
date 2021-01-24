<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Список покупателей</title>
</head>
<body>
<h1>Список покупателей</h1>
<a href="index.jsp">Домой</a>
<hr>
<ol>
    <c:forEach var="buyer" items="${listBuyers}">
        <li>
            <p><strong>Имя: </strong>${buyer.name}</p>
            <p><strong>Фамилия: </strong>${buyer.lastname}</p>
            <p><strong>Емаил: </strong>${buyer.email}</p>
            <p><strong>Баланс: </strong>${buyer.money} евро</p>
            <hr>
        </li>
    </c:forEach>

</ol>

</body>
</html>
