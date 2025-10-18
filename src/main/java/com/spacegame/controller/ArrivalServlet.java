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

@WebServlet("/arrival")
public class ArrivalServlet extends HttpServlet {
    private final PlayerService playerService = new PlayerService();
    private final String questText =
    "Разряд света вспарывает темноту, и я появляюсь — на одном колене, в клубах электричества. Воздух гудит и пахнет озоном.\n" +
    "Кожа парит от перепада температур, но система сообщает: «Температура в норме». Под коленом что-то шуршит — старая газета. \n" +
    "На видимой части заголовка читается: «…как же я горяч…». Логика фиксирует иронию момента. \n" +
    "Вокруг пусто, только тусклый переулок впереди. Похоже, туда и придётся идти.";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        HttpSession session = request.getSession();
        PlayerEntity player = (PlayerEntity) session.getAttribute("player");

        if(player == null){
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        player.getInventory().size();
        player.setCurrentImage("arrival.png");
        player.setCurrentLocation("arrival");
        playerService.updatePlayer(player);

        session.setAttribute("player", player);
        session.setAttribute("questText", questText);
        request.getRequestDispatcher("/WEB-INF/jsp/arrival.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        PlayerEntity player = (PlayerEntity) session.getAttribute("player");

        if(player == null){
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        switch (action) {
            case "go_alleyway":
                player.setCurrentLocation("alleyway");
                playerService.updatePlayer(player);
                response.sendRedirect(request.getContextPath() + "/alleyway");
                return;
        }
    }
}
