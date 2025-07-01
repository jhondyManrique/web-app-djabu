package com.djabu.controller;

import com.djabu.dao.ProductDAO;
import com.djabu.model.ProductModel;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/home")
public class HomeServletController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<ProductModel> products = null;
        ProductDAO productDAO = new ProductDAO();
        products = productDAO.getProducts();
        if (products != null){
            request.setAttribute("products", products );
            request.getRequestDispatcher("/WEB-INF/view/home.jsp").forward(request, response);
        }

    }
}
