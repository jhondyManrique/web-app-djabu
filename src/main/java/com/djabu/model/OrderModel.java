package com.djabu.model;

import java.sql.Timestamp;
import java.util.List;

public class OrderModel {
    private int id;
    private Timestamp date;
    private double total;
    private List<OrderItemModel> details; // Para guardar los detalles al consultar

    // Constructores, getters y setters

    public OrderModel() {}

    public OrderModel(double total, List<OrderItemModel> details) {
        this.total = total;
        this.details = details;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public Timestamp getDate() { return date; }

    public void setDate(Timestamp date) { this.date = date; }

    public double getTotal() { return total; }

    public void setTotal(double total) { this.total = total; }

    public List<OrderItemModel> getDetails() { return details; }

    public void setDetails(List<OrderItemModel> details) { this.details = details; }
}