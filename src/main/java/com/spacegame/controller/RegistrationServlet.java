package com.spacegame.controller;

import com.spacegame.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/register")
public class RegistrationServlet extends HttpServlet{
    private final UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        request.getRequestDispatcher("/register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        try{
            boolean success = userService.registerUser(username, password, email);

            if(success){
                response.sendRedirect(request.getContextPath() + "/register-success");

            }else{
                request.setAttribute("errorMessage", "Пользователь с таким именем уже существует!");
                request.getRequestDispatcher("/register.jsp").forward(request, response);
            }

        }catch(IllegalArgumentException e){
            request.setAttribute("errorMessage", e.getMessage());
            request.getRequestDispatcher("/register.jsp").forward(request, response);
        }
    }
}
