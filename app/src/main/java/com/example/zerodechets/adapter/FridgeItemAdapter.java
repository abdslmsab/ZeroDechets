package com.example.zerodechets.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.zerodechets.R;
import com.example.zerodechets.model.FridgeItem;

import java.util.List;

public class FridgeItemAdapter extends ArrayAdapter<FridgeItem> {

    private List<FridgeItem> itemsList;

    public FridgeItemAdapter(Context context, List<FridgeItem> items) {
        super(context, 0, items);
        this.itemsList = items;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_items, parent, false);
        }

        TextView itemName = convertView.findViewById(R.id.itemNameTextView);
        TextView itemDate = convertView.findViewById(R.id.itemExpirationDateTextView);
        TextView itemBarcode = convertView.findViewById(R.id.itemEanTextView);

        FridgeItem currentItem = itemsList.get(position);
        itemName.setText(currentItem.getItemName());
        itemDate.setText(currentItem.getExpiryDate());
        itemBarcode.setText(currentItem.getItemEan());

        return convertView;
    }
}
