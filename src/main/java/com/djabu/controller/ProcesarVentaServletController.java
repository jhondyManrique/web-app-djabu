package com.djabu.controller;

import com.djabu.dao.ProductDAO;
import com.djabu.dao.VentaDAO;
import com.djabu.model.ProductModel;
import com.djabu.model.VentaItemModel;
import com.djabu.model.VentaModel;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/procesarVenta")
public class ProcesarVentaServletController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String[] productos = request.getParameterValues("producto");
        String[] cantidades = request.getParameterValues("cantidad");
        VentaModel ventaModel = new VentaModel();
        List<VentaItemModel> detalles = new ArrayList<>();
        double totalFinal = 0.0;
        for (int i = 0; i < productos.length; i++){
            String nombre = productos[i];
            int cantidad = Integer.parseInt(cantidades[i]);
            ProductModel product = new ProductDAO().getProductByName(nombre);
            Double precio = product.getUnitPrice();
            VentaItemModel itemModel = new VentaItemModel(nombre, cantidad, precio);
            detalles.add(itemModel);
            System.out.println("detalles :" + detalles);
            totalFinal += precio * cantidad;
        }
        ventaModel.setTotal(totalFinal);
        ventaModel.setDetalles(detalles);
        System.out.println("codigo aqui");

        System.out.println(ventaModel.getDetalles());

        try {
            boolean venta = VentaDAO.guardarVenta(ventaModel);
            response.sendRedirect("/home");
            } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
