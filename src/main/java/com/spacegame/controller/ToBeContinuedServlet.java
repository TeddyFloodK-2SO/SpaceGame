package com.spacegame.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/to_be_continued")
public class ToBeContinuedServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        request.setAttribute("to_be_continued", "to_be_continued.png");
        request.getRequestDispatcher("/WEB-INF/jsp/to_be_continued.jsp").forward(request, response);
    }
}
