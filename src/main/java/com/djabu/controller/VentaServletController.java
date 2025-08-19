package com.djabu.controller;

import com.djabu.dao.VentaDAO;
import com.djabu.model.VentaModel;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/ventas/nueva")
public class VentaServletController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<VentaModel> ventas = new VentaDAO().obtenerVentas();
        request.setAttribute("ventas", ventas);
        request.getRequestDispatcher("/WEB-INF/view/ventas.jsp").forward(request, response);
    }
}
