package com.djabu.controller;

import com.djabu.model.VentaItemModel;
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
public class CarritoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String action = request.getParameter("action");

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
                carrito.put(nombre, new VentaItemModel(nombre, precio, cantidad));
            }

        } else if ("eliminar".equals(action)) {
            String nombre = request.getParameter("nombre");
            carrito.remove(nombre);
        }

        // Guardamos el carrito actualizado de vuelta en la sesión
        session.setAttribute("carrito", carrito);

        // --- RESPUESTA JSON PARA EL JAVASCRIPT ---

        // Construimos el string JSON manualmente con un StringBuilder
        StringBuilder jsonResponse = new StringBuilder();
        jsonResponse.append("{");
        jsonResponse.append("\"total\": ").append(calcularTotal(carrito)).append(",");
        jsonResponse.append("\"items\": [");

        boolean primero = true;
        for (VentaItemModel item : carrito.values()) {
            if (!primero) {
                jsonResponse.append(",");
            }
            jsonResponse.append("{");
            // Escapamos las comillas dobles en el nombre por si acaso
            jsonResponse.append("\"nombre\": \"").append(item.nombre.replace("\"", "\\\"")).append("\",");
            jsonResponse.append("\"precio\": ").append(item.precio).append(",");
            jsonResponse.append("\"cantidad\": ").append(item.cantidad);
            jsonResponse.append("}");
            primero = false;
        }

        jsonResponse.append("]");
        jsonResponse.append("}");

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonResponse.toString());
    }

    private double calcularTotal(Map<String, VentaItemModel> carrito) {
        double total = 0;
        for (VentaItemModel item : carrito.values()) {
            total += item.precio * item.cantidad;
        }
        return total;
    }
}
