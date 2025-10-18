<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>SpaceGame</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/game.css">

<style>
    body, html {
        margin: 0;
        padding: 0;
        height: 100%;
        width: 100%;
    }
    .to_be_continued {
        width: 100%;
        height: 100%;
        object-fit: cover;
    }
</style>

</head>

<body>
    <img src="${pageContext.request.contextPath}/images/${to_be_continued}" class="to_be_continued" alt="Продолжение следует">
</body>

</html>