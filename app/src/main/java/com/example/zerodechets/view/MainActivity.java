package com.example.zerodechets.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.zerodechets.R;
import com.example.zerodechets.adapter.FridgeItemAdapter;
import com.example.zerodechets.database.AppDatabase;
import com.example.zerodechets.model.FridgeItem;

import java.util.List;
import java.util.Objects;

public class MainActivity extends AppActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);

        AppDatabase.initialiser(this);

        listView = findViewById(R.id.listView);

        new Thread(new Runnable() {
            @Override
            public void run() {
                List<FridgeItem> fridgeItems = AppDatabase.getInstance().fridgeItemDao().getAllItems();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        FridgeItemAdapter adapter = new FridgeItemAdapter(MainActivity.this, fridgeItems);
                        listView.setAdapter(adapter);
                    }
                });
            }
        }).start();

        Button addButton = findViewById(R.id.add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ScanItem.class);
                startActivity(intent);
            }
        });

    }
}