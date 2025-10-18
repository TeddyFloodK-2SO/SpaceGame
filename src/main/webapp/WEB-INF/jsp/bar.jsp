<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>SpaceGame</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/game.css">
</head>
<body>

<main class="game-page">
    <h1 class="game-title">Бар</h1>
    <div class="game-grid">

        <%-- Левая колонка --%>
        <div class="left-column">

            <!-- Текст сцены -->
            <div class="game-area">
                <p class="game-text">${sessionScope.questText}</p>
            </div>

            <!-- Действия -->
            <div class="actions-window">
                <form action="${pageContext.request.contextPath}/bar" method="post">
                    <button type="submit" name="action" value="tell_the_blonde" class="action-button">
                        🟢 Речь сухая, механическая: — Твоя одежда. Твой мотоцикл. Твои очки.
                    </button>
                    <button type="submit" name="action" value="go_back_to_the_street" class="action-button">
                        🟢 Вернуться на улицу
                    </button>
                </form>
            </div>

        </div>

        <%-- Правая колонка --%>
        <div class="right-column">
            <!-- Инвентарь -->
            <div class="inventory-window">
                <c:forEach var="item" items="${sessionScope.player.inventory}">
                    <p>${item}</p>
                </c:forEach>
            </div>

            <!-- Статы -->
            <div class="player-stats">
                <p>❤️ Здоровье: ${sessionScope.player.health}</p>
                <p>💰 Деньги: ${sessionScope.player.money}</p>
            </div>

            <!-- Картинка -->
            <div class="game-picture">
                <img src="${pageContext.request.contextPath}/images/${sessionScope.player.currentImage}" alt="Бар"
                     class="game-image"/>
            </div>
        </div>

    </div>
</main>
</body>
</html>