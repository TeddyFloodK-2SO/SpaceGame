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

@WebServlet("/fight")
public class FightServlet extends HttpServlet {
    private final PlayerService playerService = new PlayerService();
    private final String questText = "Хохот байкеров сотряс стены бара. Что интересно так их позабавило? " +
            "Впрочем значения не имеет, я добуду одежду даже если положу здесь всех. " +
            "Блондин картинно начал поворачиваться. Сейчас я ему сломаю руку и все перестанутся смеятся. " +
            "Драка короткая. Ты наносишь несколько ударов, но блондин уклоняется и отвечает с такой скоростью, что за движениями не уследить. " +
            "Подсечка, удар стулом по голове, кто-то снимает всё на камеру...притащил же эту бандуру в бар. " +
            "Силы покидают тебя. Сознание гаснет. \n" +
            "Последнее, что ты слышишь сквозь звон в ушах:\n" +
            "— «Латте для Чака Норриса!»\n" +
            "И блондин спокойно отвечает:\n" +
            "— «Я здесь.»\n" +
            "\n" +
            "Тьма.";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        PlayerEntity player = (PlayerEntity) session.getAttribute("player");

        if(player == null){
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        player.setCurrentImage("fight.png");
        player.setCurrentLocation("fight");
        playerService.updatePlayer(player);

        session.setAttribute("player", player);
        session.setAttribute("questText", questText);
        request.getRequestDispatcher("/WEB-INF/jsp/fight.jsp").forward(request, response);
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
            case "black_out":
                player.setCurrentLocation("to_be_continued");
                break;
        }

        playerService.updatePlayer(player);
        session.setAttribute("player", player);
        response.sendRedirect(request.getContextPath() + "/" + player.getCurrentLocation());
    }
}

