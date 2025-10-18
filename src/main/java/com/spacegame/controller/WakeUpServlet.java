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

@WebServlet("/wake_up")
public class WakeUpServlet extends HttpServlet{
    private final PlayerService playerService = new PlayerService();
    private final String questText =
    "Мир возвращается рывками — шум, свет, запах гари. Я лежу на холодном асфальте, не сразу понимаю, что случилось. \n" +
    "Голова звенит, будто по ней проехался поезд. Постепенно выстраивается картина: улица, ночь, фонари мигают, где-то недалеко сигналит машина.\n" +
                    "\n" +
    "Ищу глазами надоедающий звук и нахожу. Серебристая Honda Civic, врезавшаяся в столб. Капот всмятку, пар валит из-под него. \n" +
    "Дверь распахивается, и наружу вываливается мужчина. \n" +
    "Короткие светлые волосы, крепкое телосложение, лицо в крови, глаза — растерянные, будто он не понимает, где находится.\n" +
                    "\n" +
    "Он поднимается, морщится от боли — правая нога явно повреждена. На секунду встречается со мной взглядом. \n" +
    "Что-то мелькает в этом взгляде — узнавание? страх? Невозможно понять. Но у него сейчас явно будут непрятности. \n" +
    "Видимо он тоже так подумал, потому что поспешил уковылять в обратную от меня сторону. \n" +
            "Возможно подумал что я не вижу и спрятался в каком то местном ломбарде \"Maynard's Pawn Shop\".";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        PlayerEntity player = (PlayerEntity) session.getAttribute("player");

        if(player == null){
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        player.setCurrentImage("wake_ap.png");
        player.setCurrentLocation("wake_ap");
        playerService.updatePlayer(player);

        session.setAttribute("player", player);
        session.setAttribute("questText", questText);
        request.getRequestDispatcher("/WEB-INF/jsp/wake_up.jsp").forward(request, response);
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
            case "pursuit":
                player.setCurrentLocation("pursuit");
                break;
            case "go_to_the_store":
                player.setCurrentLocation("clothing_store");
                break;
        }

        playerService.updatePlayer(player);
        session.setAttribute("player", player);
        response.sendRedirect(request.getContextPath() + "/" + player.getCurrentLocation());
    }
}
