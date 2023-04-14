package com.example.zerodechets.api;

import com.google.gson.annotations.SerializedName;

public class Product {

    @SerializedName("generic_name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}


