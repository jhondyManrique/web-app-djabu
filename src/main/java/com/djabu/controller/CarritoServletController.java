package com.djabu.controller;

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
import java.util.HashMap;
import java.util.Map;



@WebServlet("/carrito")
public class CarritoServletController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        CarritoService carritoService = new CarritoService();


        // Obtenemos el carrito de la sesión. Usamos el nombre del producto como clave (String).
        Map<String, VentaItemModel> carrito = (Map<String, VentaItemModel>) session.getAttribute("carrito");
        if (carrito == null) {
            carrito = new HashMap<>();
        }

        if ("agregar".equals(action)) {
            // Recibimos los datos que nos envía el fetch
            String nombre = request.getParameter("nombre");
            double precio = Double.parseDouble(request.getParameter("precio"));
            int cantidad = Integer.parseInt(request.getParameter("cantidad"));

            if (carrito.containsKey(nombre)) {
                // Si el producto ya existe, sumamos la nueva cantidad a la existente
                VentaItemModel itemExistente = carrito.get(nombre);
                itemExistente.cantidad += cantidad;
            } else {
                // Si es un producto nuevo, lo agregamos al carrito
                carrito.put(nombre, new VentaItemModel(nombre, cantidad, precio));
            }
        } else if ("eliminar".equals(action)) {
            String nombre = request.getParameter("nombre");
            carrito.remove(nombre);
        }
        // Guardamos el carrito actualizado de vuelta en la sesión
        session.setAttribute("carrito", carrito);
        Map<String,Object> respuesta = new HashMap<>();
        respuesta.put("items", carrito.values());
        respuesta.put("total", carritoService.calcularTotal(carrito));
        String jsonResponse = new Gson().toJson(respuesta);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        //Escribimos el string JSON en la respuesta.
        response.getWriter().write(jsonResponse);
    }
}
