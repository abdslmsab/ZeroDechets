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

public class MainActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);

        AppDatabase.initialiser(this);

        //Item servant de test
        new Thread() {
            @Override
            public void run() {
                AppDatabase.getInstance().fridgeItemDao().nukeTable();

                FridgeItem itemTestOne = new FridgeItem("Carottes râpées", "11/05/2023", "0000000000000");
                AppDatabase.getInstance().fridgeItemDao().insert(itemTestOne);
                FridgeItem itemTestTwo = new FridgeItem("Compotes", "19/05/2023", "1111111111111");
                AppDatabase.getInstance().fridgeItemDao().insert(itemTestTwo);
                FridgeItem itemTestThree = new FridgeItem("Mâche", "15/04/2023", "2222222222222");
                AppDatabase.getInstance().fridgeItemDao().insert(itemTestThree);
                FridgeItem itemTestFour = new FridgeItem("Riz au lait", "20/04/2023", "3333333333333");
                AppDatabase.getInstance().fridgeItemDao().insert(itemTestFour);
                FridgeItem itemTestFive = new FridgeItem("Riz au lait", "20/04/2023", "3333333333333");
                AppDatabase.getInstance().fridgeItemDao().insert(itemTestFive);
                FridgeItem itemTestSix = new FridgeItem("Riz au lait", "20/04/2023", "3333333333333");
                AppDatabase.getInstance().fridgeItemDao().insert(itemTestSix);
            }
        }.start();

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home_menu, menu);
        return true;
    }
}