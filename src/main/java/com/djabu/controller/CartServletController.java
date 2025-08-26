package com.djabu.controller;

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
import java.util.Map;



@WebServlet("/cart")
public class CartServletController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        CartService cartService = new CartService();


        // Obtenemos el cart de la sesión. Usamos el nombre del producto como clave (String).
        Map<String, OrderItemModel> cart = (Map<String, OrderItemModel>) session.getAttribute("cart");
        if (cart == null) {
            cart = new HashMap<>();
        }

        if ("addProduct".equals(action)) {
            // Recibimos los datos que nos envía el fetch
            String name = request.getParameter("name");
            double price = Double.parseDouble(request.getParameter("price"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));

            if (cart.containsKey(name)) {
                // Si el producto ya existe, sumamos la nueva cantidad a la existente
                OrderItemModel itemExist = cart.get(name);
                itemExist.quantity += quantity;
            } else {
                // Si es un producto nuevo, lo agregamos al cart
                cart.put(name, new OrderItemModel(name, quantity, price));
            }
        } else if ("deleteProduct".equals(action)) {
            String name = request.getParameter("name");
            cart.remove(name);
        }
        // Guardamos el cart actualizado de vuelta en la sesión
        session.setAttribute("cart", cart);
        Map<String,Object> respuesta = new HashMap<>();
        respuesta.put("items", cart.values());
        respuesta.put("total", cartService.calcularTotal(cart));
        String jsonResponse = new Gson().toJson(respuesta);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        //Escribimos el string JSON en la respuesta.
        response.getWriter().write(jsonResponse);
    }
}
