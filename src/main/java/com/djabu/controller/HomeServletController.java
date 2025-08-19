package com.djabu.controller;

import com.djabu.dao.ProductDAO;
import com.djabu.model.ProductModel;
import com.djabu.model.VentaItemModel;
import com.djabu.service.CarritoService;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/home")
public class HomeServletController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        CarritoService carritoService = new CarritoService();
        Object confirm = session.getAttribute("confirmacion");
        Map<String, VentaItemModel> carrito = (Map<String, VentaItemModel>) session.getAttribute("carrito");
        if (carrito == null) {
            carrito = new HashMap<>();
        }
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("items", carrito.values());
        respuesta.put("total", carritoService.calcularTotal(carrito));
        String jsonResponse = new Gson().toJson(respuesta);
        request.setAttribute("carritoInicial", jsonResponse);

        if (confirm != null){
            request.setAttribute("confirmacion",confirm);
        }
        List<ProductModel> products = null;
        ProductDAO productDAO = new ProductDAO();
        products = productDAO.getProducts();
        if (products != null){
            request.setAttribute("products", products );
            request.getRequestDispatcher("/WEB-INF/view/home.jsp").forward(request, response);
        }

    }
}
