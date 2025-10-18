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
import java.util.HashSet;

@WebServlet("/dead")
public class DeadServlet extends HttpServlet {
    private final PlayerService playerService = new PlayerService();
    private final String questText = "Некролог: неустановленная боевая единица серии K-2SO\n" +
            "\n" +
            "Пал в неравной схватке с реальностью, вооружённый логикой, сарказмом и отсутствием штанов. " +
            "Его миссия так и осталась незавершённой — он не успел понять, почему люди считают бумагу ценнее титана.\n" +
            "\n" +
            "После его гибели на месте инцидента были обнаружены фрагменты руки и центральный процессор. " +
            "Люди, движимые вечной тягой к самоуничтожению, доставили находку в лабораторию Cyberdyne Systems, где на её основе создали прототип нового нейронного ядра. " +
            "Так началась история Skynet — машины, что принесла человечеству ту самую войну к которой он пытался их привести.\n" +
            "\n" +
            "Говорят, именно после этого родился Джон Коннор.\n" +
            "Железо ржавеет, но ирония — вечна.";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        PlayerEntity player = (PlayerEntity) session.getAttribute("player");

        if (player == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        player.setCurrentImage("dead.png");
        player.setCurrentLocation("dead");
        playerService.updatePlayer(player);

        session.setAttribute("player", player);
        session.setAttribute("questText", questText);
        request.getRequestDispatcher("/dead.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        PlayerEntity player = (PlayerEntity) session.getAttribute("player");

        switch (action) {
            case "start_over":
                player.setCurrentLocation("alleyway");
                break;
        }

        player.setHealth(100);
        player.setMoney(0);
        player.setInventory(new HashSet<>());

        playerService.updatePlayer(player);
        session.setAttribute("player", player);
        response.sendRedirect(request.getContextPath() + "/" + player.getCurrentLocation());
    }
}
