package com.example.zerodechets.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.zerodechets.R;

import java.util.Objects;

public class InfosItem extends AppCompatActivity {
    private TextView valeur_ean_article;
    private TextView valeur_nom_article;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infos_item);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);

        valeur_ean_article = findViewById(R.id.valeur_ean_article);
        valeur_nom_article = findViewById(R.id.valeur_nom_article);

        String ean = getIntent().getStringExtra("ean");
        String nameProduct = getIntent().getStringExtra("name_product");

        valeur_ean_article.setText(ean);
        valeur_nom_article.setText(nameProduct);
    }
}
