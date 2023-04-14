package com.example.zerodechets.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface OpenFoodFactsService {
    @GET("api/v0/product/{code_barre}.json")
    Call<OpenFoodFactsResponse> getProduct(@Path("code_barre") String code_barre);
}
