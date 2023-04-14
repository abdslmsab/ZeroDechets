package com.example.zerodechets.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.zerodechets.model.FridgeItem;

import java.util.List;


@Dao
public interface FridgeItemDao {
    //Permet d'insérer un article
    @Insert
    void insert(FridgeItem item);

    //Permet de mettre à jour un article
    @Update
    void update(FridgeItem item);

    //Permet de supprimer un article
    @Delete
    void delete(FridgeItem item);

    //Retourne tous les articles
    @Query("SELECT * FROM fridge_items")
    List<FridgeItem> getAllItems();

    //Requête SELECT avec une clause WHERE pour filtrer les enregistrements en fonction de l'ean entré/scanné
    @Query("SELECT * FROM fridge_items WHERE item_ean = :ean")
    FridgeItem getItemByEAN(String ean);

    //Requête qui supprime toutes les lignes de la table fridge_items
    @Query("DELETE FROM fridge_items")
    void nukeTable();
}
