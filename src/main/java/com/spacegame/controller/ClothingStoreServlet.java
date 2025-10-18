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

@WebServlet("/clothing_store")
public class ClothingStoreServlet extends HttpServlet{
    private final PlayerService playerService = new PlayerService();
    private final String questText =
    "Сканер фиксирует витрину с надписью: «Секонд хэнд. Люди не выдержали — вещи остались.»\n" +
    "Странно, что магазин закрыт на ночь, но мне это только на руку. Пытаясь выбить ногой дверь, я просто проломил её — и ушиб мизинчик.\n" +
    "\n" +
    "Мелькнула мысль: «Я чертовски силён. Надо быть аккуратнее. С большой силой приходит большая ответственность...и ушибленный мизинчик.»\n" +
    "\n" +
    "Посмотрим одежду.\n" +
    "Great Scott!!!\n" +
    "Я попал в ад, где черти одеваются исключительно в оверсайз и кричаще разноцветные тряпки. Какая безвкусица.\n" +
    "Неудивительно, что война началась с машинами. Скайнет вынес модный приговор. Есть и плюсы. \n" +
    "В таких шмотках моя миссия обречена на успех. Я смогу прикончить Сару Коннор своим кринжовым вкусом...если раньше не умру от стыда. \n" +
    "Может вернуться в бар? Байкеры выглядели постильней.";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        HttpSession session = request.getSession();
        PlayerEntity player = (PlayerEntity) session.getAttribute("player");

        if(player == null){
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        player.setCurrentImage("clothing_store.png");
        player.setCurrentLocation("clothing_store");
        playerService.updatePlayer(player);

        session.setAttribute("player", player);
        session.setAttribute("questText", questText);
        request.getRequestDispatcher("/WEB-INF/jsp/clothing_store.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        HttpSession session = request.getSession();
        String action =  request.getParameter("action");
        PlayerEntity player = (PlayerEntity) session.getAttribute("player");

        if(player == null){
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        switch(action){
            case "dress_up":
                player.setCurrentLocation("dress_up");
                break;
            case "return_alleyway":
                player.setCurrentLocation("alleyway");
                break;
        }

        playerService.updatePlayer(player);
        session.setAttribute("player", player);
        response.sendRedirect(request.getContextPath() + "/" + player.getCurrentLocation());
    }
}

