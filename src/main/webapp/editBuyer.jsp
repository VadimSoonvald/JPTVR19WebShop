<%@page contentType="text/html" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Редактирование пользователя</title>
</head>
<body>
<h1>Редактирование пользователя</h1>
<p>${info}</p>
<a href="index.jsp">Домой</a>
<hr>
<form action="editBuyer" method="POST">

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

    <input type="hidden" name="buyerId" value="${buyer.id}">
    <label for="name">Имя:</label>
    <input type="text" name="name" id="name" value="${buyer.name}" required>

    <br><br>

    <label for="lastname">Фамилия:</label>
    <input type="text" name="lastname" id="lastname" value="${buyer.lastname}" required>

    <br><br>

    <label for="email">Эмаил:</label>
    <input type="text" name="email" id="email" value="${buyer.email}" required>

    <br><br>

    <label for="money">Деньги:</label>
    <input type="text" name="money" id="money" value="${buyer.money}" required>

    <br><br>

    <input type="submit" name="submit" value="Изменить пользователя">
</form>
</body>
</html>
