package com.djabu.controller;

import com.djabu.dao.ProductDAO;
import com.djabu.dao.OrderDAO;
import com.djabu.model.ProductModel;
import com.djabu.model.OrderItemModel;
import com.djabu.model.OrderModel;
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

@WebServlet("/orders/save")
public class SaveOrderServletController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String[] productNameList = request.getParameterValues("name");
        String[] productQuantityList = request.getParameterValues("quantity");
        OrderModel orderModel = new OrderModel();
        List<OrderItemModel> orderDetails = new ArrayList<>();
        double totalFinal = 0.0;
        for (int i = 0; i < productNameList.length; i++){
            String productName = productNameList[i];
            int productQuantity = Integer.parseInt(productQuantityList[i]);
            ProductModel product = new ProductDAO().getProductByName(productName);
            Double productPrice = product.getPrice();
            OrderItemModel itemModel = new OrderItemModel(productName, productQuantity, productPrice);
            orderDetails.add(itemModel);
            System.out.println("orderDetails :" + orderDetails);
            totalFinal += productPrice * productQuantity;
        }
        orderModel.setTotal(totalFinal);
        orderModel.setDetails(orderDetails);
        System.out.println("code here");

        System.out.println(orderModel.getDetails());

        try {
            int venta = OrderDAO.saveNewOrder(orderModel);
            session.setAttribute("confirmation","the order was successfully saved!!");
            session.removeAttribute("carrito");
            response.sendRedirect("/home");
            } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
