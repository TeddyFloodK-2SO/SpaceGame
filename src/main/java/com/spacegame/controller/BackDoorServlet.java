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

@WebServlet("/back_door")
public class BackDoorServlet extends HttpServlet{
    private final PlayerService playerService = new PlayerService();
    private final String questText =
    "Во дворе царила тишина, нарушаемая лишь моими тяжелыми шагами. Выйдя на улицу я увидел настоящее чудо — DeLorean DMC-12. \n" +
    "Когда-то, возможно, это был автомобиль в классическом его понимании. \n" +
    "Но некий фанат Безумного Макса решил дать этому автомобилю новый брутальный облик. И это явно дало ему характер. \n" +
    "Кузов из нержавеющей стали, характерные двери «крыло чайки». \n" +
    "На капоте множество проводов, катушек, трубок и небольших панелей — выглядит как миниатюрный реактор.\n" +
    "На задней части автомобиля что то похожее на реактор. Трубы и воздухозаборники. Все это напоминало футуристический двигатель.\n" +
    "На приборной панели часы показывающие “02 SEP 1885”. Одобряю, исчерпывающая информация.\n" +
                    "\n" +
    "На бампере номерной знак с надписью «CALIFORNIA», а ниже крупно — «OUTATIME». Вероятно, опечатка. Или диагноз.\n" +
                    "\n" +
    "Дверь приоткрыта, как приглашение.\n" +
    "Машина выглядела как ловушка.\n" +
    "Интересно для кого.";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        HttpSession session = request.getSession();
        PlayerEntity player = (PlayerEntity) session.getAttribute("player");

        if(player == null){
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        player.setCurrentImage("DeLoreanDMS-12.png");
        player.setCurrentLocation("back_door");
        playerService.updatePlayer(player);

        session.setAttribute("player", player);
        session.setAttribute("questText", questText);
        request.getRequestDispatcher("/WEB-INF/jsp/back_door.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        PlayerEntity player = (PlayerEntity) session.getAttribute("player");

        if(player == null){
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        switch (action) {
            case "get_in_the_car":
                player.setCurrentLocation("back_to_the_future");
                break;
            case "walking_down_the_street":
                player.setCurrentLocation("pursuit");
                break;
        }

        playerService.updatePlayer(player);
        session.setAttribute("player", player);
        response.sendRedirect(request.getContextPath() + "/" + player.getCurrentLocation());
    }
}
