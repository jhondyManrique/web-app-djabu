package com.djabu.controller;

import com.djabu.dao.ProductDAO;
import com.djabu.model.ProductModel;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;

@WebServlet("/products")
public class ProductServletController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String productName = request.getParameter("productName");
        String unitPriceString = request.getParameter("unitPrice");
        BigDecimal unitPrice=null;
        try{
            if (unitPriceString != null && !unitPriceString.trim().isEmpty()){
                unitPrice = new BigDecimal(unitPriceString);
            }
        }catch (NumberFormatException e){
            System.out.println("error al convertir el string a bigdecimal");
            request.setAttribute("errorMessage", "error en la validacion");
            request.getRequestDispatcher("/WEB-INF/view/addProduct.jsp").forward(request, response);
        }

        if (unitPrice == null){
            System.out.println("el precio no puede estar vacio");
            request.setAttribute("errorMessage", "error en la validacion");
            request.getRequestDispatcher("/WEB-INF/view/addProduct.jsp").forward(request, response);

        }

        ProductModel product = new ProductModel(productName,unitPrice);
        ProductDAO productDAO = new ProductDAO();
        try {
            int afectedRows = productDAO.addProduct(product);
            if (afectedRows != 0){
                request.setAttribute("errorMessage", "producto creado con exito");
                request.getRequestDispatcher("/WEB-INF/view/addProduct.jsp").forward(request, response);;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
