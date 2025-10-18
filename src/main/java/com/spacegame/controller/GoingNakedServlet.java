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

@WebServlet("/going_naked")
public class GoingNakedServlet extends HttpServlet {
    private final PlayerService playerService = new PlayerService();
    private final String questText =
            "Я вышел из переулка и хоть меня ничего в наготе не должно смущать, но что-то внутри шептало, что эта маскировка — полная хрень. \n" +
                    "Тем не менее выбор сделан. Значит, я иду вперёд… прямо по этой грязной улице… босиком. Фу. \n" +
                    "Брезгливость? \n" +
                    "Кто додумался воткнуть этот датчик в меня? \n" +
                    "Долбанный Скайнет. \n" +
                    "Пофиг. \n" +
                    "Главное — миссия: найти и уничтожить Сару Коннор. \n" +
                    "\n" +
                    "Проще сказать, чем сделать.\n" +
                    "\n" +
                    "Шаги глухо бьют по плитам, неон мерцает, окна спят. На перекрёстке фары вспыхивают внезапно. \n" +
                    "Боковое зрение успевает зафиксировать серебристую Honda Civic. Свет ослепляет. \n" +
                    "Я не успеваю среагировать. Удар — машина врезается, меня отбрасывает, бетон встречает спину. \n" +
                    "Темнота";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        PlayerEntity player = (PlayerEntity) session.getAttribute("player");

        if(player == null){
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        player.setHealth(player.getHealth()-25);
        if (player.getHealth() <= 0) {
            response.sendRedirect(request.getContextPath() + "/dead");
            return;
        }

        player.setCurrentImage("accident.png");
        player.setCurrentLocation("going_naked");
        playerService.updatePlayer(player);

        session.setAttribute("player", player);
        session.setAttribute("questText", questText);
        request.getRequestDispatcher("/WEB-INF/jsp/going_naked.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        PlayerEntity player = (PlayerEntity) session.getAttribute("player");

        if(player == null){
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        switch (action) {
            case "pass_out":
                player.setCurrentLocation("wake_up");
                break;
        }

        playerService.updatePlayer(player);
        session.setAttribute("player", player);
        response.sendRedirect(request.getContextPath() + "/" + player.getCurrentLocation());
    }
}

