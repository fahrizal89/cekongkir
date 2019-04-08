package com.fahrizal.cekongkir.presentation.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;

import com.fahrizal.cekongkir.presentation.model.RowModel;

import java.util.ArrayList;
import java.util.List;

public class SimpleAdapter extends ArrayAdapter {
    public SimpleAdapter(@NonNull Context context, int resource, @NonNull List<RowModel> rows) {
        super(context, resource, rows);
    }

    public RowModel get(int position){
        return (RowModel) getItem(position);
    }
}
