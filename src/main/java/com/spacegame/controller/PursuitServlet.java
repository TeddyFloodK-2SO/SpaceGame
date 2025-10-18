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

@WebServlet("/pursuit")
public class PursuitServlet extends HttpServlet {
    private final PlayerService playerService = new PlayerService();
    private final String questText =
            "Захожу в ломбард и на меня обрушиваются довольно ощутимые удары. \n" +
                    "Удары как наковальней. Святой Скайнет я что наткнулся на профессионального боксера? \n" +
                    "Между ударами он бормочет: «Чувствуешь жжение? Это тебя жжёт гордость. Прорывайся через это дерьмо.»\n" +
                    "\n" +
                    "Я уже почти теряю сознание, как слышу щелчок затвора дробовика. \n" +
                    "Хозяин ломбарда: «Стоять, черт тебя дери!» \n" +
                    "\n" +
                    "Водитель: «Это не твоё дело, мистер.» \n" +
                    "\n" +
                    "Хозяин ломбарда: «Теперь это моё дело.» \n" +
                    "\n" +
                    "— Убери с него ногу, руки за голову, подходи к прилавку, — отдаёт приказ хозяин. \n" +
                    "\n" +
                    "Приклад дробовика с глухим ударом врезается в голову водителя. Тот шатаетcя и падает рядом со мной. \n" +
                    "Хозяин ломбарда берёт телефон, набирает номер, хмуро глядя на меня, и спокойно говорит в трубку: \n" +
                    "«Алло Зед? Это Мейнард. Паук только что поймал парочку мух.»";


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        HttpSession session = request.getSession();
        PlayerEntity player = (PlayerEntity) session.getAttribute("player");

        if(player == null){
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        player.setHealth(player.getHealth()-15);
        if (player.getHealth() <= 0) {
            response.sendRedirect(request.getContextPath() + "/dead");
            return;
        }

        player.setCurrentImage("pursuit.png");
        player.setCurrentLocation("pursuit");
        playerService.updatePlayer(player);

        session.setAttribute("player", player);
        session.setAttribute("questText", questText);
        request.getRequestDispatcher("/WEB-INF/jsp/pursuit.jsp").forward(request, response);
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
            case "pass_out":
                player.setCurrentLocation("to_be_continued");
                break;
        }

        playerService.updatePlayer(player);
        session.setAttribute("player", player);
        response.sendRedirect(request.getContextPath() + "/" + player.getCurrentLocation());
    }
}



