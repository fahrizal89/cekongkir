package com.fahrizal.cekongkir.presentation.model;

import lombok.Data;

@Data
public class RowModel {
    private int id;
    private String name;

    public RowModel(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
