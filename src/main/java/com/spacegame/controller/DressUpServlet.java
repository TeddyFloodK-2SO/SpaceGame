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

@WebServlet("/dress_up")
public class DressUpServlet extends HttpServlet {
    private final PlayerService playerService = new PlayerService();
    private final String questText =
            "Впрочем зря я в самом деле. Наверняка здесь много разных вещей относящиеся не только к этой эпохе...глаза б мои ее не видели. \n" +
                    "Сканер зафиксировал объект, достойный внимания. Манекен стоял посреди зала, будто ждал именно меня.\n" +
                    "На нём — длинный пыльный плащ в стиле дикого запада, жилет из выцветшей кожи, клетчатая рубаха и широкополая шляпа. \n" +
                    "Всё дополняло старое ружьё на ремне — наверняка нерабочее, но выглядящее внушительно.\n" +
                    "\n" +
                    "“Артефакт эпохи. Вероятность практической пользы — ноль. Вероятность эстетического превосходства — сто.”\n" +
                    "Патронов нет, но меня это не тревожит. С такой экипировкой боеприпасы — просто лишний вес.\n" +
                    "\n" +
                    "Одежда выглядела вызывающе, почти театрально. Но анализируя своё отражение в треснувшем зеркале, я понял — мне плевать. Оно того стоит.\n" +
                    "Я натянул шляпу, закинул ружьё за спину. Материал сел идеально, будто вторая кожа.\n" +
                    "\n" +
                    "Взгляд зацепился за приоткрытую дверь на задний двор.\n" +
                    "Чёрный ход. Узкий переулок вёл к параллельной улице.\n" +
                    "Сканер отметил маршрут. Миссия продолжается.";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        PlayerEntity player = (PlayerEntity) session.getAttribute("player");

        if(player == null){
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        if (player.getInventory().contains("Старое нерабочее ружье")) {
            session.setAttribute("questText",
                    "Ограбленный маникен безучастно пялится на меня. Чувство стыда накрыло меня и уже подумал вернуть Пхахах...да хорош, я угараю");
        }else {
            session.setAttribute("questText", questText);
            player.addItem("Старое нерабочее ружье");
        }

        player.setCurrentImage("dress_up.png");
        player.setCurrentLocation("dress_up");
        playerService.updatePlayer(player);

        session.setAttribute("player", player);
        request.getRequestDispatcher("/WEB-INF/jsp/dress_up.jsp").forward(request, response);
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
            case "back_door":
                player.setCurrentLocation("back_door");
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
