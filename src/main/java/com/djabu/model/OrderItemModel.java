package com.djabu.model;

public class OrderItemModel {
    private int id;
    private int idVenta;
    private String name;
    public int quantity;
    private double price;


    // Constructores, getters y setters

    public OrderItemModel() {}

    public OrderItemModel(String name, int cantidad, Double precio) {
        this.name = name;
        this.quantity = cantidad;
        this.price = precio;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getIdVenta() { return idVenta; }
    public void setIdVenta(int idVenta) { this.idVenta = idVenta; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

}
