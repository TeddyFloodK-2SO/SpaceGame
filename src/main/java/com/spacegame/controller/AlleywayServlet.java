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

@WebServlet("/alleyway")
public class AlleywayServlet extends HttpServlet {
    private final PlayerService playerService = new PlayerService();
    private final String questText =
    "Темный переулок. Отличное место для неприятностей. \n" +
    "На севере слышен мужской хохот и рокот двигателя мотоцикла, предполагается что там бар.\n" +
    "Правее закуток с мусорными контейнерами, сдается там я мог бы найти мусор если б искал. \n" +
    "Левее уходит улица на которой горит лишь один фонарь, кажется вдали есть магазин.\n" +
    "Восьмидесятые...как же здесь воняет. Без понятия зачем у меня датчик запахов в носу";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        HttpSession session = request.getSession();
        PlayerEntity player = (PlayerEntity) session.getAttribute("player");

        if(player == null){
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        player.setCurrentImage("alleyway.png");
        player.setCurrentLocation("alleyway");
        playerService.updatePlayer(player);

        session.setAttribute("player", player);
        session.setAttribute("questText", questText);
        request.getRequestDispatcher("/WEB-INF/jsp/alleyway.jsp").forward(request, response);
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
            case "enter_the_bar":
                player.setCurrentLocation("bar");
                break;
            case "search_through_the_trash":
                player.setCurrentLocation("trash");
                break;
            case "clothing_store":
                player.setCurrentLocation("clothing_store");
                break;
            case "going_naked":
                player.setCurrentLocation("going_naked");
                break;
        }

        playerService.updatePlayer(player);
        session.setAttribute("player", player);
        response.sendRedirect(request.getContextPath() + "/" + player.getCurrentLocation());
    }
}

