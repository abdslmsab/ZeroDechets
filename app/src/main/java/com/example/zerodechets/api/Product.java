package com.example.zerodechets.api;

import com.google.gson.annotations.SerializedName;

public class Product {

    @SerializedName("generic_name")
    private String name;

    @SerializedName("quantity")
    private String quantity;

    @SerializedName("brands")
    private String brands;

    public String getBrands() {
        return brands;
    }

    public void setBrands(String brands) {
        this.brands = brands;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}


