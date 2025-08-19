package com.djabu.model;

import java.security.Timestamp;
import java.util.List;

public class VentaModel {
    private int id;
    private Timestamp fecha;
    private double total;
    private List<VentaItemModel> detalles; // Para guardar los detalles al consultar

    // Constructores, getters y setters

    public VentaModel() {}

    public VentaModel(double total, List<VentaItemModel> detalles) {
        this.total = total;
        this.detalles = detalles;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public Timestamp getFecha() { return fecha; }

    public void setFecha(Timestamp fecha) { this.fecha = fecha; }

    public double getTotal() { return total; }

    public void setTotal(double total) { this.total = total; }

    public List<VentaItemModel> getDetalles() { return detalles; }

    public void setDetalles(List<VentaItemModel> detalles) { this.detalles = detalles; }
}