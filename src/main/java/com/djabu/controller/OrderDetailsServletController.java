package com.djabu.controller;

import com.djabu.dao.OrderDAO;
import com.djabu.model.OrderItemModel;
import com.djabu.service.CartService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/orders/details")
public class OrderDetailsServletController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        List<OrderItemModel> details = new OrderDAO().getDetailsById(id);
        Double total = new CartService().calcularTotal(details);
        request.setAttribute("total",total);
        request.setAttribute("details", details);
        request.getRequestDispatcher("/WEB-INF/view/details.jsp").forward(request, response);
    }
}
