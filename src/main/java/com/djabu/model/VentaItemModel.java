package com.djabu.model;

public class VentaItemModel {
    private int id;
    private int idVenta;
    private String nombre;
    public int cantidad;
    private double precio;

    // Constructores, getters y setters

    public VentaItemModel() {}

    public VentaItemModel(String nombreProducto, int cantidad, double precioUnitario) {
        this.nombre = nombreProducto;
        this.cantidad = cantidad;
        this.precio = precioUnitario;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getIdVenta() { return idVenta; }
    public void setIdVenta(int idVenta) { this.idVenta = idVenta; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }
}
