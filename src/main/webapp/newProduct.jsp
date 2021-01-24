<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Добавить пиццу</title>
</head>
<body>
<h1>Добавить пиццу</h1>
<a href="index.jsp">Домой</a>
<hr>
<form action="createProduct" method="POST">
    <label for="title">Название пиццы:</label>
    <input type="text" name="title" id="title" required>

    <br><br>

    <label for="size">Размер пиццы(мм):</label>
    <input type="text" name="size" id="size" required>

    <br><br>

    <label for="price">Цена пиццы:</label>
    <input type="text" name="price" id="price" required>

    <br><br>

    <label for="count">Количество пиццы:</label>
    <input type="text" name="count" id="count" required>

    <br><br>

    <input type="submit" value="Добавить новую пиццу">
</form>
</body>
</html>
