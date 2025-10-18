<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>

<html lang="ru">

<head>
    <meta charset="UTF-8">
    <title>SpaceGame</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">
</head>

<body>

<main class="login-page">

    <h1 class="login-title">SpaceGame</h1>

    <h2 class="login-subtitle">Вход</h2>

    <c:if test="${not empty error}">
        <p class="login-error" style="color: red;">${error}</p>
    </c:if>

    <form action="login" method="post" class="login-form">
        <div class="form-group">
            <label for="username">Логин:</label>
            <input type="text" id="username" name="username" required/>
        </div>

        <div class="form-group">
            <label for="password">Пароль:</label>
            <input type="password" id="password" name="password" required/>
        </div>

        <button type="submit" class="login-button">Войти</button>
    </form>

    <div class="bottom-link">
        <p>Нет аккаунта?
            <a href="${pageContext.request.contextPath}/register">Зарегистрироваться</a>
        </p>
    </div>

</main>
</body>
</html>

