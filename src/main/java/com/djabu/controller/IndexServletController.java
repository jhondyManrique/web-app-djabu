package com.djabu.controller;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/")
public class IndexServletController extends HttpServlet {

    @Override
    //this method return
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Object user = request.getSession().getAttribute("user");
        if(user != null){
            response.sendRedirect("dashboard");
            return;
        }
        request.getRequestDispatcher("/WEB-INF/view/userLogin.jsp").forward(request, response);
    }

}
