package com.example.zerodechets.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.zerodechets.R;
import com.example.zerodechets.database.AppDatabase;
import com.example.zerodechets.model.FridgeItem;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Objects;

public class InfosItem extends AppActivity {
    private TextView valeur_ean_article;
    private TextView valeur_nom_article;
    private TextView valeur_quantite;
    private TextView valeur_marque;
    private Button bouton_valider;

    private DatePicker date_picker;
    private RadioGroup radioGroup;

    private Date selectedExpiryDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infos_item);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);

        date_picker = findViewById(R.id.datePicker);
        radioGroup = findViewById(R.id.radioGroup);

        valeur_ean_article = findViewById(R.id.valeur_ean_article);
        valeur_nom_article = findViewById(R.id.valeur_nom_article);
        valeur_quantite = findViewById(R.id.valeur_quantite);
        valeur_marque = findViewById(R.id.valeur_marque);

        String ean = getIntent().getStringExtra("ean");
        String nameProduct = getIntent().getStringExtra("name_product");
        String quantity = getIntent().getStringExtra("quantity");
        String brands = getIntent().getStringExtra("brands");

        valeur_ean_article.setText(ean);
        valeur_nom_article.setText(nameProduct);
        valeur_quantite.setText(quantity);
        valeur_marque.setText(brands);

        bouton_valider = findViewById(R.id.bouton_valider);
        bouton_valider.setOnClickListener(view -> {
            new Thread() {
                @Override
                public void run() {
                    int daysUntilNotif = 0;
                    switch (radioGroup.getCheckedRadioButtonId()) {
                        case R.id.unJour:
                            daysUntilNotif = 1;
                            break;
                        case R.id.troisJour:
                            daysUntilNotif = 3;
                            break;
                        case R.id.septJours:
                            daysUntilNotif = 7;
                            break;
                    }

                    int month = date_picker.getMonth() + 1;
                    FridgeItem item = new FridgeItem(nameProduct, date_picker.getDayOfMonth() + "/" + month + "/" + date_picker.getYear(), ean, daysUntilNotif);
                    AppDatabase.getInstance().fridgeItemDao().insert(item);

                    Intent intent = new Intent(InfosItem.this, MainActivity.class);
                    startActivity(intent);
                }
            }.start();
        });


    }
}
