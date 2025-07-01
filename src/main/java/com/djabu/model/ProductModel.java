package com.djabu.model;

import java.math.BigDecimal;

public class ProductModel {
    protected String productName;
    protected BigDecimal unitPrice;

    public ProductModel() {
    }

    public ProductModel(String productName, BigDecimal unitPrice) {
        this.productName = productName;
        this.unitPrice = unitPrice;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }
}
