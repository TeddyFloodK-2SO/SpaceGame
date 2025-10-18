<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>

<html lang="ru">

<head>
    <meta charset="UTF-8">
    <title>SpaceGame</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/register-success.css">
</head>
<body>
<main class="register-success-page">

    <h1 class="success-title">Поздравляем!</h1>

    <%--Сообщение об ошибке, если она есть--%>
    <c:if test="${not empty errorMessage}">
        <p class="success-error">${errorMessage}</p>
    </c:if>

    <p class="success-message">Вы успешно зарегистрировались в SpaceGame</p>

    <div class="success-action">
        <form action="${pageContext.request.contextPath}/login.jsp" method="get">
            <button type="submit" class="success-button">Войти</button>
        </form>
    </div>

</main>
</body>
</html>