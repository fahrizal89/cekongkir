package com.fahrizal.cekongkir.presentation.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;

import java.util.List;

public class SimpleProvincesAdapter extends ArrayAdapter {
    List list;
    public SimpleProvincesAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
        list=objects;
    }

    public void clear(){
        list.clear();
    }
}
