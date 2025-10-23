package com.djabu.controller;

import com.djabu.dao.OrderDAO;
import com.djabu.model.OrderModel;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/dashboard")
public class DashboardController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        LocalDate todaysDate = LocalDate.now();
        List<OrderModel> orders;
        OrderDAO orderDAO = new OrderDAO();
        orders = orderDAO.getOrdersByDate(todaysDate,todaysDate);
        double todaysRevenue = orderDAO.getTodaysRevenue();
        request.setAttribute("todaysRevenue", todaysRevenue);
        request.setAttribute("todaysOrders", orders);
        request.getRequestDispatcher("/WEB-INF/view/dashboard.jsp").forward(request,response);
    }

}
