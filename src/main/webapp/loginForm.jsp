<%@page contentType="text/html" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Вход в систему</title>
</head>
<body>
<h1>Вход в систему</h1>
<p>${info}</p>
<a href="index.jsp">Домой</a>
<hr>
<form action="login" method="POST">
    <label for="login">Логин</label>
    <input type="text" name="login" id="login" value="" required>

    <br><br>

    <label for="password">Пароль</label>
    <input type="password" name="password" id="password" value="" required>

    <br><br>

    <input type="submit" value="Войти"><br>
</form>
<p>Нет аккаунта? <a href="registrationForm">Зарегистрироваться</a></p>

</body>
</html>
