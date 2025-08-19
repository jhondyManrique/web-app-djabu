package com.djabu.controller;

import com.djabu.dao.VentaDAO;
import com.djabu.model.VentaItemModel;
import com.djabu.service.CarritoService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/ventas/detalles")
public class DetalleVentaServletController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        List<VentaItemModel> detalles = new VentaDAO().obtenerDetallesPorId(id);
        Double total = new CarritoService().calcularTotal(detalles);
        request.setAttribute("total",total);
        request.setAttribute("detalles", detalles);
        request.getRequestDispatcher("/WEB-INF/view/detalles.jsp").forward(request, response);
    }
}
