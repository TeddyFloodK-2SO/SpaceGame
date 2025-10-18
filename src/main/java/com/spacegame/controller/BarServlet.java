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

@WebServlet("/bar")
public class BarServlet extends HttpServlet {
    private final PlayerService playerService = new PlayerService();
    private final String questText =
            "Ты заходишь внутрь. Грохот музыки, дым, перегар. Толпа людей в кожаных куртках. " +
                    "На тебя смотрят, кто-то смеётся, взгляды явно обращены на тебя из за наготы. " +
                    "Определив нужный размер одежды ты выбираешь цель: блондин с мотоциклетными очками. ";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        HttpSession session = request.getSession();
        PlayerEntity player = (PlayerEntity) session.getAttribute("player");

        if(player == null){
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        player.setCurrentImage("bar.png");
        player.setCurrentLocation("bar");
        playerService.updatePlayer(player);

        session.setAttribute("player", player);
        session.setAttribute("questText", questText);
        request.getRequestDispatcher("/WEB-INF/jsp/bar.jsp").forward(request, response);
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
            case "tell_the_blonde":
                player.setCurrentLocation("fight");
                break;
            case "go_back_to_the_street":
                player.setCurrentLocation("alleyway");
                break;

        }

        playerService.updatePlayer(player);
        session.setAttribute("player", player);
        response.sendRedirect(request.getContextPath() + "/" + player.getCurrentLocation());
    }
}
