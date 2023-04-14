package com.example.zerodechets.api;

import com.google.gson.annotations.SerializedName;

public class Product {
    @SerializedName("product_name")
    private String productName;

    @SerializedName("quantity")
    private String quantity;

    @SerializedName("code")
    private String code;

    public String getProductName() {
        return productName;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getCode() {
        return code;
    }
}


