<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Покупка пиццы</title>
</head>
<body>
<p>${info}</p>
<a href="index.jsp">Домой</a>
<form action="buyProduct" method="POST">
    <hr>
    <h2>Список пицц</h2>
    <label>
        <select name="productId">
            <c:forEach var="product" items="${listProducts}" varStatus="status">
                <option value="${product.id}">${status.index+1}. <strong>Название: </strong>${product.title} ${product.size},
                        <strong>Цена: </strong>${product.price}, <strong>Осталось: </strong>${product.count}шт.
                </option>
            </c:forEach>
        </select>
    </label>

    <hr>
    <h2>Список покупателей</h2>
    <label>
        <select name="buyerId">
            <c:forEach var="buyer" items="${listBuyers}" varStatus="status">
                <option value="${buyer.id}">
                    ${status.index+1}. <strong>Имя: </strong>${buyer.name} ${buyer.lastname},
                        <strong>Баланс: </strong>${buyer.money} евро, <strong>Е-маил: </strong>${buyer.email}
                </option>
            </c:forEach>
        </select>
    </label>

    <br><br>

    <input type="submit" value="Купить пиццу">
</form>
</body>
</html>
