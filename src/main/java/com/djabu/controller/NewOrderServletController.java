package com.djabu.controller;

import com.djabu.dao.ProductDAO;
import com.djabu.model.ProductModel;
import com.djabu.model.OrderItemModel;
import com.djabu.service.CartService;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/orders/new-order")
public class NewOrderServletController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        CartService cartService = new CartService();

        Map<String, OrderItemModel> cart = (Map<String, OrderItemModel>) session.getAttribute("cart");
        if (cart == null) {
            cart = new HashMap<>();
        }
        Map<String, Object> cartData = new HashMap<>();
        cartData.put("items", cart.values());
        cartData.put("total", cartService.calcularTotal(cart));
        String jsonResponse = new Gson().toJson(cartData);
        request.setAttribute("cart", jsonResponse);

        List<ProductModel> products = null;
        ProductDAO productDAO = new ProductDAO();
        products = productDAO.getProducts();
        if (products != null){
            request.setAttribute("products", products );
            request.getRequestDispatcher("/WEB-INF/view/newOrder.jsp").forward(request, response);
        }

    }
}
