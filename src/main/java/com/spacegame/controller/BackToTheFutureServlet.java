package com.spacegame.controller;

import com.spacegame.entity.PlayerEntity;
import com.spacegame.service.PlayerService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/back_to_the_future")
public class BackToTheFutureServlet extends HttpServlet {
    private final PlayerService playerService = new PlayerService();
    private final String questText =
            "Я сел. Рука легла на рычаг, система ожила. Индикаторы замигали — странно, но она работает.\n" +
                    "\n" +
                    "Опасаясь, что машина всё-таки сломается, я не торопился проверять скорость. Но всё было в порядке: двигатель урчал как кот, и я прибавил. \n" +
                    "На такой прямой невозможно было ехать медленно.\n" +
                    "\n" +
                    "Педаль в пол. Стрелка спидометра ползёт: 60… 70… 80…\n" +
                    "На 88 милях в час панель взорвалась светом. Воздух задрожал, мир обернулся вспышкой. \n" +
                    "Молния прошила ночь, асфальт под колёсами вспух пламенным следом — и всё исчезло.\n" +
                    "\n" +
                    "Мгновение — и вместо улицы города перед глазами раскинулась пустынная степь. Каким-то образом ночь сменилась днём.\n" +
                    "\n" +
                    "А вместо прямой асфальтированной дороги под колесами песок, и прямо на меня несутся индейцы с копьями и луками. \n" +
                    "Интересно бывают ли у роботов запрограммированных на убийство галлюцинации? \n" +
                    "\n" +
                    "Чудом увернувшись от столкновения с наездниками, я заехал в какую-то пещеру. \n" +
                    "При осмотре автомобиля я убедился что долбаные индейцы не галлюцинация, из топливной магистрали торчала стрела, которая помогла топливу покинуть бак. \n" +
                    "\n" +
                    "Оставив автомобиль в более-менее надёжном месте, я решил сходить на разведку. Учитывая мой внешний вид, я явно здесь свой. \n" +
                    "Уже при выходе из пещеры моя нога что то зацепила, оказалась детская игрушка в виде лабиринта. \n" +
                    "\n" +
                    "Оставлю, вдруг смогу поменять на что-нибудь.";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        PlayerEntity player = (PlayerEntity) session.getAttribute("player");

        if (player == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        if (player.getInventory().contains("Детская игрушка")) {
            session.setAttribute("questText",
                    "Это место под ногой выглядит так, будто там лежала Детская игрушка которая сейчас в моем рюкзаке. Та-та-та!!! Крайне загадочно.");
        } else {
            session.setAttribute("questText", questText);
            player.addItem("Детская игрушка");
        }

        player.setCurrentImage("back_to_the_future.png");
        playerService.updatePlayer(player);

        session.setAttribute("player", player);
        session.setAttribute("questText", questText);
        request.getRequestDispatcher("/WEB-INF/jsp/back_to_the_future.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        PlayerEntity player = (PlayerEntity) session.getAttribute("player");

        if (player == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        switch (action) {
            case "leave_the_cave":
                player.setCurrentLocation("to_be_continued");
                break;
        }

        playerService.updatePlayer(player);
        session.setAttribute("player", player);
        response.sendRedirect(request.getContextPath() + "/" + player.getCurrentLocation());
    }
}
