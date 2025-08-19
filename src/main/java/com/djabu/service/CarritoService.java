package com.djabu.service;

import com.djabu.model.VentaItemModel;

import java.util.List;
import java.util.Map;

public class CarritoService {

    public double calcularTotal(Map<String, VentaItemModel> carrito) {
        double total = 0;
        for (VentaItemModel item : carrito.values()) {
            total += item.getPrecio() * item.cantidad;
        }
        return total;
    }

    public double calcularTotal(List<VentaItemModel> detalles) {
        double total = 0;
        for (VentaItemModel detalle : detalles) {
            total += detalle.getPrecio() * detalle.cantidad;
        }
        return total;
    }


}
