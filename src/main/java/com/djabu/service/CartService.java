package com.djabu.service;

import com.djabu.model.OrderItemModel;

import java.util.List;
import java.util.Map;

public class CartService {

    public double calcularTotal(Map<String, OrderItemModel> carrito) {
        double total = 0;
        for (OrderItemModel item : carrito.values()) {
            total += item.getPrice() * item.quantity;
        }
        return total;
    }

    public double calcularTotal(List<OrderItemModel> detalles) {
        double total = 0;
        for (OrderItemModel detalle : detalles) {
            total += detalle.getPrice() * detalle.quantity;
        }
        return total;
    }


}
