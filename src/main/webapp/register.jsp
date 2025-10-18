<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ru">

<head>
    <meta charset="UTF-8">
    <title>SpaceGame</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/register.css">
</head>

<body>
<main class="register-page">

    <h1 class="register-title">SpaceGame</h1>

    <h2 class="register-subtitle">Регистрация нового пользователя</h2>

    <c:if test="${not empty errorMessage}">
        <p class="register-error">${errorMessage}</p>
    </c:if>

    <form action="${pageContext.request.contextPath}/register" method="post" class="register-form">

        <div class="form-group">
            <label for="username">Логин:</label>
            <input type="text" name="username" id="username" required/>
        </div>

        <div class="form-group">
            <label for="password">Пароль:</label>
            <input type="password" name="password" id="password" required/>
        </div>

        <div class="form-group">
            <label for="email">Email:</label>
            <input type="email" name="email" id="email" required/>
        </div>

        <button type="submit" class="register-button">Зарегистрироваться</button>
    </form>

    <p class="bottom-link">Есть аккаунт?
        <a href="${pageContext.request.contextPath}/login">Вход</a>
    </p>

</main>
</body>
</html>