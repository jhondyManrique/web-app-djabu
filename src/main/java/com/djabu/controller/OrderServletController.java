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
import java.util.ArrayList;
import java.util.List;

@WebServlet("/orders")
public class OrderServletController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String startDateString = request.getParameter("startDate");
        String endDateString = request.getParameter("endDate");
        OrderDAO orderDAO = new OrderDAO();
        List<OrderModel> orders;

        if (startDateString != null && !startDateString.isEmpty() && endDateString != null && !endDateString.isEmpty()) {
            // Si hay fechas, las convertimos y llamamos al metodo de filtrado
            LocalDate startDate = LocalDate.parse(startDateString);
            LocalDate endDate = LocalDate.parse(endDateString);
            orders = orderDAO.getOrdersByDate(startDate,endDate);
        } else {
            // Si no hay fechas, obtenemos todas las ventas
            orders = orderDAO.getAllOrders();
        }

        request.setAttribute("orders", orders);
        request.getRequestDispatcher("/WEB-INF/view/orders.jsp").forward(request, response);
    }
}
