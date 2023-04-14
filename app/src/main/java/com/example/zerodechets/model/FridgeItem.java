package com.example.zerodechets.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "fridge_items")
public class FridgeItem {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "item_id")
    private int id;

    @ColumnInfo(name = "item_name")
    private String itemName;

    @ColumnInfo(name = "expiry_date")
    private String expiryDate;

    @ColumnInfo(name = "item_ean")
    private String itemEan;

    private int daysUntilNotif;

    public FridgeItem() {
    }

    @Ignore
    public FridgeItem(String itemName, String expiryDate, String itemEan, int daysUntilNotif) {
        this.itemName = itemName;
        this.expiryDate = expiryDate;
        this.itemEan = itemEan;
        this.daysUntilNotif = daysUntilNotif;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getItemEan() {
        return itemEan;
    }

    public void setItemEan(String itemEan) {
        this.itemEan = itemEan;
    }

    public int getDaysUntilNotif() {
        return daysUntilNotif;
    }

    public void setDaysUntilNotif(int daysUntilNotif) {
        this.daysUntilNotif = daysUntilNotif;
    }
}