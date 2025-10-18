package com.spacegame.controller;

import com.spacegame.entity.PlayerEntity;
import com.spacegame.entity.User;
import com.spacegame.service.PlayerService;
import com.spacegame.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet{
    private final UserService userService = new UserService();
    private final PlayerService playerService = new PlayerService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
            request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = userService.findByUsername(username);

        if(user != null && user.getPassword().equals(password)){
            HttpSession session = request.getSession();
            session.setAttribute("currentUser", user);

//            Загружаем или создаем персонажа через PlayerService
            PlayerEntity playerEntity = playerService.getOrCreatePlayer(user);
            session.setAttribute("player", playerEntity);

//            Перенаправляем на последнюю локацию
            response.sendRedirect(request.getContextPath() + "/" + playerEntity.getCurrentLocation());

        } else {
            request.setAttribute("error", "Неверный логин или пароль");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }
}
