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

@WebServlet("/trash")
public class TrashServlet extends HttpServlet{
    private final PlayerService playerService = new PlayerService();
    private final String questText =
    "Мусорные баки скрипят и воняют, запахи жареного и гниющего смешались в хаотичный коктейль. \n" +
    "Ты сканируешь каждый контейнер: куртки, старые футболки, грязные носки… и вдруг — доллар. \n" +
    "Блестит словно награда за труды. Логический модуль активирует чувство удовлетворения. \n" +
    "Но тут проверка идентификатора валюты: «Канадский доллар». \n" +
    "Конвертация мгновенная, курс учтён… и настроение падает. Денег на одежду не хватит. \n" +
    "Кажется он называется луни. Какое смешное название....засмеялся бы если б мог. \n" +
    "Твои процессоры фиксируют иронию: почти всё правильно, но бессмысленно. \n" +
            "Пока я анализировал ситуацию, кто то выбросил мусор из окна надо мной. \n" +
            "За вычетом кирпича грохнувшего по башке остальное не нанесло мне повреждений. \n" +
            "Кто выбрасывает кирпичи из окон? \n" +
            "Загадка дыры. \n" +
            "Пересилив с трудом алгоритм блокирующий меня от бесполезностей, я в итоге подобрал металл используемый для такой глупости, как денежный эквивалент… тем более канадский. \n" +
            "Чуть не выплюнув при этом гайки, сделанные конечно же в США. \n" +
            "Осталось решить куда его положить? \n" +
            "Карманы я еще не раздобыл";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        PlayerEntity player = (PlayerEntity) session.getAttribute("player");

        if(player == null){
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        if(player.getInventory().contains("Канадский доллар")){
            session.setAttribute("questText", "Ты уже проверял мусорные баки. Больше ничего нет");
        }else {
            session.setAttribute("questText", questText);
            player.addItem("Канадский доллар");

            player.setHealth(player.getHealth() - 10);
            if (player.getHealth() <= 0) {
                response.sendRedirect(request.getContextPath() + "/dead");
                return;
            }
        }

        player.setCurrentImage("trash.png");
        player.setCurrentLocation("trash");
        playerService.updatePlayer(player);

        session.setAttribute("player", player);
        request.getRequestDispatcher("/WEB-INF/jsp/trash.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        PlayerEntity player = (PlayerEntity) session.getAttribute("player");

        if(player == null){
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        switch (action) {
            case "return_alleyway":
                player.setCurrentLocation("alleyway");
                break;
        }

        playerService.updatePlayer(player);
        session.setAttribute("player", player);
        response.sendRedirect(request.getContextPath() + "/" + player.getCurrentLocation());
    }
}
