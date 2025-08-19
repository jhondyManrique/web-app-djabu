package com.djabu.model;

import java.math.BigDecimal;

public class ProductModel {
    protected String productName;
    protected double unitPrice;

    public ProductModel() {
    }

    public ProductModel(String productName, Double unitPrice) {
        this.productName = productName;
        this.unitPrice = unitPrice;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }
}
