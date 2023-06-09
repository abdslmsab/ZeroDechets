package com.example.zerodechets.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.example.zerodechets.R;
import com.example.zerodechets.api.OpenFoodFactsResponse;
import com.example.zerodechets.api.OpenFoodFactsService;
import com.example.zerodechets.api.Product;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ScanItem extends AppActivity {
    private OpenFoodFactsService openFoodFactsService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_item);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://world.openfoodfacts.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        openFoodFactsService = retrofit.create(OpenFoodFactsService.class);


        ((TextInputLayout) findViewById(R.id.texte_EAN)).setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ScanItem.this, Camera.class);
                cameraLauncher.launch(intent);
            }
        });

        Button validButton = findViewById(R.id.valid_button);
        validButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ean = ((TextInputEditText) findViewById(R.id.ean_text_input)).getText().toString();
                if (ean.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Veuillez saisir un code-barres", Toast.LENGTH_SHORT).show();
                    return;
                }

                openFoodFactsService.getProduct(ean).enqueue(new Callback<OpenFoodFactsResponse>() {
                    @Override
                    public void onResponse(Call<OpenFoodFactsResponse> call, Response<OpenFoodFactsResponse> response) {
                        String errorMessage = getString(R.string.product_not_found);
                        if (response.isSuccessful()) {
                            OpenFoodFactsResponse offResponse = response.body();
                            if (offResponse.getStatus() == 1) {
                                errorMessage = null;
                                Intent intent = new Intent(ScanItem.this, InfosItem.class);
                                intent.putExtra("ean", ean);
                                intent.putExtra("name_product", offResponse.getProduct().getName());
                                intent.putExtra("quantity", offResponse.getProduct().getQuantity());
                                intent.putExtra("brands", offResponse.getProduct().getBrands());
                                startActivity(intent);
                            }
                        }
                        if(errorMessage != null) {
                            Toast.makeText(ScanItem.this, errorMessage, Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<OpenFoodFactsResponse> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), getString(R.string.product_retrieve_error), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }

    private ActivityResultLauncher<Intent> cameraLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK) {
                    Intent data = result.getData();
                    String ean = data.getStringExtra("ean");
                    ((EditText) findViewById(R.id.ean_text_input)).setText(ean);
                }
            });
}
