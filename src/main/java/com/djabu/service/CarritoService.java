package com.djabu.service;

import com.djabu.model.VentaItemModel;

import java.util.Map;

public class CarritoService {

    public double calcularTotal(Map<String, VentaItemModel> carrito) {
        double total = 0;
        for (VentaItemModel item : carrito.values()) {
            total += item.getPrecio() * item.cantidad;
        }
        return total;
    }


}
