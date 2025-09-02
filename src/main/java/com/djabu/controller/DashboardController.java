package com.djabu.controller;

import com.djabu.dao.OrderDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/dashboard")
public class DashboardController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        OrderDAO orderDAO = new OrderDAO();
        double todaysRevenue = orderDAO.getTodaysRevenue();
        request.setAttribute("todaysRevenue", todaysRevenue);
        request.getRequestDispatcher("/WEB-INF/view/dashboard.jsp").forward(request,response);
    }

}
