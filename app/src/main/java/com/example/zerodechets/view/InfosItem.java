package com.example.zerodechets.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.zerodechets.R;

import java.util.Objects;

public class InfosItem extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infos_item);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
    }
}
